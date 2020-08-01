package com.rafaellagisck.udemyspring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafaellagisck.udemyspring.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo nome deve ser preenchido")
	@Length(min = 5, max = 80, message = "O campo nome de ter entre {min} e {max} caracteres")
	private String nome;
	
	@NotEmpty(message = "O campo nome deve ser preenchido")
	@Email(message = "Email inválido")
	private String email;

	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
