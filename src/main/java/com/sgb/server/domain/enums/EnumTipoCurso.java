package com.sgb.server.domain.enums;

public enum EnumTipoCurso {

	TECNICO(1, "Cursos Tecnicos"), QUALIFICACAO(2, "Cursos de Qualificação");

	private Integer id;
	private String descricao;

	private EnumTipoCurso(Integer id, String descricao) {
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

	public static EnumTipoCurso toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EnumTipoCurso enumTipoCurso : EnumTipoCurso.values()) {
			if (id.equals(enumTipoCurso.getId())) {
				return enumTipoCurso;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
