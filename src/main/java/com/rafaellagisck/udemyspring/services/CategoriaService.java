package com.rafaellagisck.udemyspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Categoria;
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
		return categoriaRepository.save(categoria);
	}

	public void excluir(Integer id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categoria que possuem produtos");
		}
		
	}
}
