package com.sgb.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date aquisicao;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date devolucao;

	@ManyToOne
	@JoinColumn(name = "prazoEmprestimo_id")
	private PrazoEmprestimo prazoEmprestimo;

	@ManyToOne
	@JoinColumn(name = "patrimonio_id")
	private Patrimonio patrimonio;

	@ManyToOne
	@JoinColumn(name = "bibliotecario_emprestimo")
	private Bibliotecario bibliotecarioEmprestimo;

	@ManyToOne
	@JoinColumn(name = "bibliotecario_devolucao")
	private Bibliotecario bibliotecarioDevolucao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToMany(mappedBy = "emprestimo",cascade = CascadeType.ALL)
	private List<Multa> multas = new ArrayList<Multa>();

	public Bibliotecario getBibliotecarioDevolucao() {
		return bibliotecarioDevolucao;
	}

	public void setBibliotecarioDevolucao(Bibliotecario bibliotecarioDevolucao) {
		this.bibliotecarioDevolucao = bibliotecarioDevolucao;
	}

	public Bibliotecario getBibliotecarioEmprestimo() {
		return bibliotecarioEmprestimo;
	}

	public void setBibliotecarioEmprestimo(Bibliotecario bibliotecario) {
		this.bibliotecarioEmprestimo = bibliotecario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAquisicao() {
		return aquisicao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Date devolucao) {
		this.devolucao = devolucao;
	}

	public void setAquisicao(Date aquisicao) {
		this.aquisicao = aquisicao;
	}

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

	public PrazoEmprestimo getPrazoEmprestimo() {
		return prazoEmprestimo;
	}

	public void setPrazoEmprestimo(PrazoEmprestimo prazoEmprestimo) {
		this.prazoEmprestimo = prazoEmprestimo;
	}

	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}
	public void addMulta(Multa multa) {
		this.multas.add(multa);
	}

}
