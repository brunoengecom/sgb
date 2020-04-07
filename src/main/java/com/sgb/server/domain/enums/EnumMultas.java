package com.sgb.server.domain.enums;

public enum EnumMultas {

	MATUTINO(1, "Matutino"), VESPERTINO(2, "Vespertino"),NOTURNO(3,"Noturno");

	private Integer id;
	private String descricao;

	private EnumMultas(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static EnumMultas toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EnumMultas roles : EnumMultas.values()) {
			if (id.equals(roles.getId())) {
				return roles;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
