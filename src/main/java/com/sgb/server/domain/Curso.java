package com.sgb.server.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgb.server.domain.enums.EnumTipoCurso;



@Entity
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curso")
	private Set<Turma> turmas =  new HashSet<>();
	
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
	
	public EnumTipoCurso getTipo() {
		return EnumTipoCurso.toEnum(tipo);
	}
	public void setTipo(EnumTipoCurso tipo) {
		this.tipo = tipo.getId();
	}
	public Set<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}
}
	
