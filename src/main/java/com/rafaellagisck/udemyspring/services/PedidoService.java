package com.rafaellagisck.udemyspring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.ItemPedido;
import com.rafaellagisck.udemyspring.domain.PagamentoComBoleto;
import com.rafaellagisck.udemyspring.domain.Pedido;
import com.rafaellagisck.udemyspring.domain.enums.EstadoPagamento;
import com.rafaellagisck.udemyspring.repositories.ItemPedidoRepository;
import com.rafaellagisck.udemyspring.repositories.PagamentoRepository;
import com.rafaellagisck.udemyspring.repositories.PedidoRepository;
import com.rafaellagisck.udemyspring.repositories.ProdutoRepository;
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

	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido inserir(Pedido pedido) {
		pedido.setId(null);
		pedido.setDataHoraPedido(new Date());
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
			item.setPreco(produtoService.buscarPorId(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}
}
