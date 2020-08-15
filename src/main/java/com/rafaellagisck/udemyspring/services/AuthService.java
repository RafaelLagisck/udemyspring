package com.rafaellagisck.udemyspring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.repositories.ClienteRepository;
import com.rafaellagisck.udemyspring.services.exceptions.ObjectNotFoundException;


@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		
		String newPassword = newPassword();
		cliente.setSenha(passwordEncoder.encode(newPassword));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPassword);
	}

	private String newPassword() {
		char vet [] = new char [10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) { //gera um digito
			return (char)(rand.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char)(rand.nextInt(26) + 65);
		}
		else {
			return (char)(rand.nextInt(26) + 97);
		}
	}
}
