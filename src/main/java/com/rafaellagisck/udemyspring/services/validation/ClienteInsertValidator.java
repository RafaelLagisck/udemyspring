package com.rafaellagisck.udemyspring.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rafaellagisck.udemyspring.domain.enums.TipoCliente;
import com.rafaellagisck.udemyspring.dto.ClienteNovoDTO;
import com.rafaellagisck.udemyspring.resources.exceptions.FieldMessage;
import com.rafaellagisck.udemyspring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNovoDTO> {
	
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
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}