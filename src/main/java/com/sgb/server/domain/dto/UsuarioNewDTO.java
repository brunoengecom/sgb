package com.sgb.server.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sgb.server.domain.enums.EnumStatus;
import com.sgb.server.sevice.validation.LivroInsert;
@LivroInsert
public class UsuarioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String nome;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String email;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private Date dataNasc;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private String cpf;
	
	@NotNull(message = "Preenchimento Obrigatório")
	private Integer status;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String cargo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public EnumStatus getStatus() {
		return EnumStatus.toEnum(status);
	}

	public void setStatus(EnumStatus status) {
		this.status = status.getId();
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}