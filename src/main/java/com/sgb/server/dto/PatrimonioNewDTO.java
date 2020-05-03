package com.sgb.server.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PatrimonioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@NotNull
	private String numero;
	@NotNull
	private Integer livro;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getLivro() {
		return livro;
	}
	public void setLivro(Integer livro) {
		this.livro = livro;
	}
	
	public PatrimonioNewDTO() {
		
	}
	
}
