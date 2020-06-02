package com.sgb.server.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import com.sgb.server.sevice.validation.EditoraInsert;
@EditoraInsert
public class EditoraNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@NotNull
	@Length(min = 3 ,max = 40)
	private String nome;
	
	@NotEmpty
	@NotNull
	@CNPJ
	private String cnpj;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	
}