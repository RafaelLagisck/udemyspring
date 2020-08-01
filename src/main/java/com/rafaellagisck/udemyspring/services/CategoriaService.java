package com.rafaellagisck.udemyspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Categoria;
import com.rafaellagisck.udemyspring.domain.Categoria;
import com.rafaellagisck.udemyspring.dto.CategoriaDTO;
import com.rafaellagisck.udemyspring.repositories.CategoriaRepository;
import com.rafaellagisck.udemyspring.services.exceptions.DataIntegrityException;
import com.rafaellagisck.udemyspring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria inserir(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria); 
	}

	public Categoria atualizar(Categoria categoria) {
		Categoria categoriaAtualizado = buscarPorId(categoria.getId());
		atualizarCategoria(categoriaAtualizado, categoria);
		return categoriaRepository.save(categoriaAtualizado);
	}

	private void atualizarCategoria(Categoria categoriaAtualizado, Categoria categoria) {
		categoriaAtualizado.setNome(categoria.getNome());
		
	}

	public void excluir(Integer id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categoria que possuem produtos");
		}
		
	}

	public List<Categoria> buscarTodas() {
		return categoriaRepository.findAll();
	}
	
	//Metodo para Paginação
	public Page<Categoria> buscarPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	//Metodo Auxiliar transformar objeto DTO em objeto comum
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
