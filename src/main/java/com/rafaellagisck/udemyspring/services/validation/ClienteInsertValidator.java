package com.rafaellagisck.udemyspring.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rafaellagisck.udemyspring.domain.Cliente;
import com.rafaellagisck.udemyspring.domain.enums.TipoCliente;
import com.rafaellagisck.udemyspring.dto.ClienteNovoDTO;
import com.rafaellagisck.udemyspring.repositories.ClienteRepository;
import com.rafaellagisck.udemyspring.resources.exceptions.FieldMessage;
import com.rafaellagisck.udemyspring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNovoDTO> {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNovoDTO clienteNovoDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (clienteNovoDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getId()) && !BR.isValidCPF(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));

		} 
		if (clienteNovoDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getId()) && !BR.isValidCNPJ(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(clienteNovoDTO.getEmail());
		if(cliente != null) {
			list.add(new FieldMessage("email", "Email já existente na base de dados"));
		}
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}