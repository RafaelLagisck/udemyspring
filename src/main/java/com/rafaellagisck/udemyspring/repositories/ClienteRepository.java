package com.rafaellagisck.udemyspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaellagisck.udemyspring.domain.Endereco;

@Repository
public interface ClienteRepository extends JpaRepository<Endereco, Integer> {
	
	

}
