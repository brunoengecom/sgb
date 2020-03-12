package com.sgb.server.domain.enums;

public enum EnumStatus {

	ATIVO(1, "Ativo"), INATIVO(2, "Inativo");

	private Integer id;
	private String descricao;

	private EnumStatus(Integer id, String descricao) {
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

	public static EnumStatus toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EnumStatus roles : EnumStatus.values()) {
			if (id.equals(roles.getId())) {
				return roles;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
