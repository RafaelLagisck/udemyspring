package com.rafaellagisck.udemyspring.services;

import org.springframework.mail.SimpleMailMessage;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage message);
	
	void sendNewPasswordEmail(Cliente cliente, String newPassword);

}
