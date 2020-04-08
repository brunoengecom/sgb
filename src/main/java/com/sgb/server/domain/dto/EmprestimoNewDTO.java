package com.sgb.server.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sgb.server.sevice.validation.EmprestimoInsert;

@EmprestimoInsert
public class EmprestimoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Preencha o campo Patrimonio")
	@NotEmpty(message = "Preencha o campo Patrimonio")
	private String patrimonio;
	
	@NotNull
	@NotNull(message = "Preencha o campo Usuario")
	private Integer usuario;
	
	
	
	public String getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
		
}
