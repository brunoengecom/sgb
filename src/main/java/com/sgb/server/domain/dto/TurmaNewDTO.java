package com.sgb.server.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.sgb.server.domain.enums.EnumPeriodo;
import com.sgb.server.sevice.validation.TurmaInsert;
@TurmaInsert
public class TurmaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private Date dtInicio;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private Date dtFim;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private EnumPeriodo periodo;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private Integer curso;

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public EnumPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(EnumPeriodo periodo) {
		this.periodo = periodo;
	}


}
