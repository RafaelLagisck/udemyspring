package com.rafaellagisck.udemyspring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafaellagisck.udemyspring.domain.Pedido;
import com.rafaellagisck.udemyspring.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService pedidoService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPorId(id);
		return ResponseEntity.ok().body(pedido);
	}
}
