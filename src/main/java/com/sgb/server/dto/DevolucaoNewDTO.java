package com.sgb.server.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sgb.server.sevice.validation.EmprestimoDevolucao;
@EmprestimoDevolucao
public class DevolucaoNewDTO {
	@NotNull(message = "Preencha o campo Patrimonio")
	@NotEmpty(message = "Preencha o campo Patrimonio")
	private String patrimonio;

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
}
