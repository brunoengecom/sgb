package com.sgb.server.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgb.server.domain.enums.EnumStatus;

@Entity
public class Bibliotecario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	private String senha;
	private Integer status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bibliotecarioEmprestimo")	
	private List<Emprestimo> emprestimosRealizados;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bibliotecarioDevolucao")
	private List<Emprestimo> emprestimosDevolvidos;
	
	public List<Emprestimo> getEmprestimosDevolvidos() {
		return emprestimosDevolvidos;
	}
	public void setEmprestimosDevolvidos(List<Emprestimo> emprestimosDevolvidos) {
		this.emprestimosDevolvidos = emprestimosDevolvidos;
	}
	public List<Emprestimo> getEmprestimosRealizados() {
		return emprestimosRealizados;
	}
	public void setEmprestimosRealizados(List<Emprestimo> emprestimos) {
		this.emprestimosRealizados = emprestimos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public EnumStatus getStatus() {
		return EnumStatus.toEnum(status);
	}
	public void setStatus(EnumStatus status) {
		this.status = status.getId();
	}
	
}
	
