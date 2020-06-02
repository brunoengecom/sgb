package com.sgb.server.domain.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sgb.server.sevice.validation.LivroInsert;

@LivroInsert
public class LivroNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String nome;
	@NotNull(message = "Preenchimento Obrigatório")
	private Integer edicao;
	@NotNull(message = "Preenchimento Obrigatório")
	private Integer ano;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	private String isbn;
	@NotNull(message = "Preenchimento Obrigatório")
	private Double valor;

	@NotNull(message = "Preenchimento Obrigatório")
	private Integer editora;

	@NotNull(message = "Preenchimento Obrigatório")
	private Integer areaDeConhecimento;

	@NotEmpty(message = "Preenchimento Obrigatório")
	private Set<String> autores = new HashSet<>();

	public Set<String> getAutores() {
		return autores;
	}

	public void setAutores(Set<String> autores) {
		this.autores = autores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getEditora() {
		return editora;
	}

	public void setEditora(Integer editora) {
		this.editora = editora;
	}

	public Integer getAreaDeConhecimento() {
		return areaDeConhecimento;
	}

	public void setAreaDeConhecimento(Integer areaDeConhecimento) {
		this.areaDeConhecimento = areaDeConhecimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
