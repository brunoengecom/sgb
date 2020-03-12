package com.sgb.server.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.sgb.server.domain.enums.EnumPeriodo;
import com.sgb.server.sevice.validation.TurmaEdit;

@TurmaEdit
public class TurmaEditDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date dtInicio;
	private Date dtFim;
	private EnumPeriodo periodo;
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
