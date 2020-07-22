package com.rafaellagisck.udemyspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.services.exceptions.ObjectNotFoundException;
import com.rafaellagisck.udemyspring.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Cliente.class.getName()));
	}
}
