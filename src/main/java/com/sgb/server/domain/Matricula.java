package com.sgb.server.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private MatriculaPK id = new MatriculaPK();
	private Integer numero;

	public Usuario getUsuario() {
		return id.getUsuario();
	}
	 public void setUsuario(Usuario usuario) {
		 this.id.setUsuario(usuario);
	 }
	
	public Turma getTurma() {
		return id.getTurma();
	}
	public void setTurma(Turma turma) {
		this.id.setTurma(turma);
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
