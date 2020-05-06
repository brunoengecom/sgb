package com.sgb.server.dto;

import java.io.Serializable;
import java.util.List;

import com.sgb.server.domain.AreaDeConhecimento;
import com.sgb.server.domain.Editora;
import com.sgb.server.domain.Livro;

public class LivroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer edicao;
	private Integer ano;
	private String isbn;
	private Editora editora;
	private AreaDeConhecimento AreaDeConhecimento;
	private List<String> autores;
	
	public LivroDTO(Livro obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.edicao = obj.getEdicao();
		this.ano = obj.getAno();
		this.isbn = obj.getIsbn();
		this.editora = obj.getEditora();
		this.AreaDeConhecimento = obj.getAreaDeConhecimento();
		this.autores = obj.getAutores();
	}
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
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public AreaDeConhecimento getAreaDeConhecimento() {
		return AreaDeConhecimento;
	}
	public void setAreaDeConhecimento(AreaDeConhecimento areaDeConhecimento) {
		AreaDeConhecimento = areaDeConhecimento;
	}
	public List<String> getAutores() {
		return autores;
	}
	public void setAutores(List<String> autores) {
		this.autores = autores;
	}
	
	
}
