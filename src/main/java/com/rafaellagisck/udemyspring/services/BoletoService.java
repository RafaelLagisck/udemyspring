package com.rafaellagisck.udemyspring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.rafaellagisck.udemyspring.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoBoleto, Date dataHoraPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataHoraPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoBoleto.setDataVencimento(cal.getTime());
	}

}
