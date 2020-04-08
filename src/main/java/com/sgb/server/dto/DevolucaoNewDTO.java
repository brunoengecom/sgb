package com.sgb.server.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sgb.server.domain.Multa;
import com.sgb.server.sevice.validation.EmprestimoDevolucao;

@EmprestimoDevolucao
public class DevolucaoNewDTO {
	@NotNull(message = "Preencha o campo Patrimonio")
	@NotEmpty(message = "Preencha o campo Patrimonio")
	private String patrimonio;
	private Set<Multa> multas;

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Set<Multa> getMultas() {
		return multas;
	}

	public void setMultas(Set<Multa> multas) {
		this.multas = multas;
	}
}
