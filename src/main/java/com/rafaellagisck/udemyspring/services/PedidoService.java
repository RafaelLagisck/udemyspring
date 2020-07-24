package com.rafaellagisck.udemyspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Pedido;
import com.rafaellagisck.udemyspring.services.exceptions.ObjectNotFoundException;
import com.rafaellagisck.udemyspring.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Pedido.class.getName()));
	}
}
