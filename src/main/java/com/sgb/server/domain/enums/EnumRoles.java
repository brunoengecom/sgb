package com.sgb.server.domain.enums;

public enum EnumRoles {

	FUNCIONARIO(1, "Funcionário"), ALUNO(2, "Aluno"),SUPERADMIN(3,"Administrador do Sistema"),BIBLIOTECARIO(4,"BIBLIOTECARIO");

	private Integer id;
	private String descricao;

	private EnumRoles(Integer id, String descricao) {
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

	public static EnumRoles toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EnumRoles roles : EnumRoles.values()) {
			if (id.equals(roles.getId())) {
				return roles;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
