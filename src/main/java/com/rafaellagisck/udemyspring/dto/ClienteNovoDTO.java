package com.rafaellagisck.udemyspring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafaellagisck.udemyspring.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNovoDTO  implements Serializable {

	private static final long serialVersionUID = 1L; 

	@NotEmpty(message = "O campo nome deve ser preenchido")
	@Length(min = 5, max = 80, message = "O campo nome de ter entre {min} e {max} caracteres")
	private String nome;

	@NotEmpty(message = "O campo email deve ser preenchido")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "O campo cpf ou cnpj deve ser preenchido")
	private String cpfOuCnpj;

	private Integer tipoCliente;
	
	@NotEmpty(message = "O campo senha deve ser preenchido")
	private String senha;

	@NotEmpty(message = "O campo logradouro deve ser preenchido")
	private String logradouro;

	@NotEmpty(message = "O campo numero deve ser preenchido")
	private String numero;

	private String complemento;

	private String bairro;

	@NotEmpty(message = "O campo nome deve ser preenchido")
	private String cep;
	
	@NotEmpty(message = "O campo nome deve ser preenchido")
	private String tel1;

	private String tel2;
	
	private String tel3;
	
	private Integer idCidade;

	public ClienteNovoDTO() {
		super();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
		
}
