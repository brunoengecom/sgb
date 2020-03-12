package com.sgb.server.domain.enums;

public enum EnumStatusPagamento {

	ATIVO(1, "Ativo"), INATIVO(2, "Inativo");

	private Integer id;
	private String status;

	private EnumStatusPagamento(Integer id, String status) {
		this.id = id;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static EnumStatusPagamento toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EnumStatusPagamento status : EnumStatusPagamento.values()) {
			if (id.equals(status.getId())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}
	

}
