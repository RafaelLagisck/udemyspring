package com.rafaellagisck.udemyspring.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int id;
	private String descricao;
	
	private EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if(id != null) {
			for(EstadoPagamento tipoCliente : EstadoPagamento.values()) {
				if(id.equals(tipoCliente.getId())) {
					return tipoCliente;
				}
			}
		}
		throw new IllegalArgumentException("Id Invalido: "+ id);
	}

}
