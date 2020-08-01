package com.rafaellagisck.udemyspring.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Categoria;
import com.rafaellagisck.udemyspring.domain.Cidade;
import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.domain.Endereco;
import com.rafaellagisck.udemyspring.domain.enums.TipoCliente;
import com.rafaellagisck.udemyspring.dto.ClienteDTO;
import com.rafaellagisck.udemyspring.dto.ClienteNovoDTO;
import com.rafaellagisck.udemyspring.services.exceptions.DataIntegrityException;
import com.rafaellagisck.udemyspring.services.exceptions.ObjectNotFoundException;
import com.rafaellagisck.udemyspring.repositories.CidadeRepository;
import com.rafaellagisck.udemyspring.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente inserir(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente); 
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

	public Cliente fromDTO(ClienteNovoDTO clienteNovoDTO) {
		Cliente cliente = new Cliente(null, clienteNovoDTO.getNome(), clienteNovoDTO.getEmail(), 
				clienteNovoDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNovoDTO.getTipoCliente()));
		
		Optional<Cidade> cidade = cidadeRepository.findById(clienteNovoDTO.getIdCidade());
		Endereco endereco = new Endereco(null, clienteNovoDTO.getLogradouro(), clienteNovoDTO.getNumero(), clienteNovoDTO.getComplemento(), clienteNovoDTO.getBairro(), clienteNovoDTO.getCep(),(Cidade)cidade, cliente);
		return null;
	}

}
