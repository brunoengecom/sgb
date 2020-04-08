package com.sgb.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Serializable transforma um objeto java em texto
@Entity
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	GenerateValue diz que ele e auto incrementavel
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer edicao;
	private Integer ano;
	@Column(unique = true)
	private String isbn;
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "editora_id")
	private Editora editora;

	@JsonIgnore
	@OneToMany(mappedBy = "livro")
	private Set<Patrimonio> patrimonios = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "areaDeConhecimento_id")
	private AreaDeConhecimento areaDeConhecimento;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "autor")
	private List<String> autores = new ArrayList<>();

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}

	public void setAreaDeConhecimento(AreaDeConhecimento areaDeConhecimento) {
		this.areaDeConhecimento = areaDeConhecimento;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Set<Patrimonio> getPatrimonios() {
		return patrimonios;
	}

	public void setPatrimonios(Set<Patrimonio> patrimonios) {
		this.patrimonios = patrimonios;
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

	public AreaDeConhecimento getAreaDeConhecimento() {
		return areaDeConhecimento;
	}

	public void setAreaConhecimento(AreaDeConhecimento areaDeConhecimento) {
		this.areaDeConhecimento = areaDeConhecimento;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}