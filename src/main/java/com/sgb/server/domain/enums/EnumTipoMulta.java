package com.sgb.server.domain.enums;

public enum EnumTipoMulta {

	ATRASO(1, "Atraso de Devolução"), DANOS(2, "Danos"),PERCA(3,"Perca");

	private Integer id;
	private String descricao;

	private EnumTipoMulta(Integer id, String descricao) {
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

	public static EnumTipoMulta toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EnumTipoMulta roles : EnumTipoMulta.values()) {
			if (id.equals(roles.getId())) {
				return roles;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
