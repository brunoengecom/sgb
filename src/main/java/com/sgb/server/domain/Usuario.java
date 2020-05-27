package com.sgb.server.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//Serializable transforma um objeto java em texto
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.domain.enums.EnumStatus;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String nome;
	private String email;
	private Date dataNasc;
	private String cpf;
	private Integer status;
	private String cargo;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private Set<Emprestimo> emprestimos = new HashSet<>();

	@OneToMany(mappedBy = "id.usuario")
	private Set<Matricula> matriculas = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles")
	private Set<Integer> roles = new HashSet<>();

	@OneToOne(mappedBy = "usuario")
	@JsonIgnore
	private Bibliotecario bibliotecario;
	
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

	public Set<EnumRoles> getRoles() {
		return roles.stream().map(x -> EnumRoles.toEnum(x)).collect(Collectors.toSet());
	}

	public void addRoles(EnumRoles role) {
		roles.add(role.getId());
	}

	public void setRoles(Set<Integer> roles) {
		this.roles = roles;
	}

	public Set<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(Set<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
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

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	public void addMatricula(Matricula matricula) {
		this.matriculas.add(matricula);
	}

}
