package com.rafaellagisck.udemyspring.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage message = prepareSimpleMailFromPedido(pedido);
		
		sendEmail(message);
		
	}

	protected SimpleMailMessage prepareSimpleMailFromPedido(Pedido pedido) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(pedido.getCliente().getEmail());
		message.setFrom(sender);
		message.setSubject("Pedido nº " + pedido.getId() + " confirmado");
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(pedido.toString());
		return message;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
		SimpleMailMessage message = prepareNewPasswordEmail(cliente, newPassword);
		sendEmail(message);
		
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPassword ) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(cliente.getEmail());
		message.setFrom(sender);
		message.setSubject("Solicitação de nova senha");
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText("Nova senha: " + newPassword);
		return message;
	}
}
