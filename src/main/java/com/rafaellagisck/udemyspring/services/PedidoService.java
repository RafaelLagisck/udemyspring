package com.rafaellagisck.udemyspring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.domain.ItemPedido;
import com.rafaellagisck.udemyspring.domain.PagamentoComBoleto;
import com.rafaellagisck.udemyspring.domain.Pedido;
import com.rafaellagisck.udemyspring.domain.enums.EstadoPagamento;
import com.rafaellagisck.udemyspring.repositories.ItemPedidoRepository;
import com.rafaellagisck.udemyspring.repositories.PagamentoRepository;
import com.rafaellagisck.udemyspring.repositories.PedidoRepository;
import com.rafaellagisck.udemyspring.security.UserSS;
import com.rafaellagisck.udemyspring.services.exceptions.AuthorizationException;
import com.rafaellagisck.udemyspring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private EmailService emailService;

	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido inserir(Pedido pedido) {
		pedido.setId(null);
		pedido.setDataHoraPedido(new Date());
		pedido.setCliente(clienteService.buscarPorId(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoBoleto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamentoBoleto, pedido.getDataHoraPedido());
		}

		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.buscarPorId(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		return pedido;
	}
	
	//Metodo para Paginação
		public Page<Pedido> buscarPage(Integer page, Integer linesPage, String orderBy, String direction){
			UserSS user = UserService.authenticated();
			
			if(user == null) {
				throw new AuthorizationException("Acesso Negado");
			}
			
			PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
			Cliente cliente = clienteService.buscarPorId(user.getId());
			return pedidoRepository.findByCliente(cliente, pageRequest);
			
		}
}
