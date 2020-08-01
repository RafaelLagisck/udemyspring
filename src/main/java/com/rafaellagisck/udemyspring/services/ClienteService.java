package com.rafaellagisck.udemyspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.dto.ClienteDTO;
import com.rafaellagisck.udemyspring.services.exceptions.DataIntegrityException;
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
	
	public Cliente atualizar(Cliente cliente) {
		Cliente clienteAtualizado = buscarPorId(cliente.getId());
		atualizarCliente(clienteAtualizado, cliente);
		return clienteRepository.save(clienteAtualizado);
	}

	private void atualizarCliente(Cliente clienteAtualizado, Cliente cliente) {
		clienteAtualizado.setNome(cliente.getNome());
		clienteAtualizado.setEmail(cliente.getEmail());
		
	}

	public void excluir(Integer id) {
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir cliente que possuem produtos");
		}
		
	}

	public List<Cliente> buscarTodas() {
		return clienteRepository.findAll();
	}
	
	//Metodo para Paginação
	public Page<Cliente> buscarPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	//Metodo Auxiliar transformar objeto DTO em objeto comum
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
}
