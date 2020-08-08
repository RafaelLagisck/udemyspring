package com.rafaellagisck.udemyspring.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rafaellagisck.udemyspring.services.DBService;
import com.rafaellagisck.udemyspring.services.EmailService;
import com.rafaellagisck.udemyspring.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		dbService.instantiateTestDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
