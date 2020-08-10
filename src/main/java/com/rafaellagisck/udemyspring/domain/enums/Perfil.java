package com.rafaellagisck.udemyspring.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int id;
	private String descricao;
	
	private Perfil(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer id) {
		if(id != null) {
			for(Perfil tipoCliente : Perfil.values()) {
				if(id.equals(tipoCliente.getId())) {
					return tipoCliente;
				}
			}
		}
		throw new IllegalArgumentException("Id Invalido: "+ id);
	}

}
