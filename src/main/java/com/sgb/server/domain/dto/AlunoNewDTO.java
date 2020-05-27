package com.sgb.server.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgb.server.sevice.validation.AlunoInsert;
@AlunoInsert
public class AlunoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@NotNull
	@Length(min = 10 ,max = 40)
	private String nome;
	@NotEmpty
	@NotNull
	@Email
	private String email;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNasc;
	@NotEmpty
	@NotNull
	@CPF
	private String cpf;
	@NotNull
	private Integer turma;
	@NotNull
	private Integer matricula;
	
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	

	
}
