package com.sgb.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgb.server.domain.enums.EnumStatusPagamento;
import com.sgb.server.domain.enums.EnumTipoMulta;

@Entity
public class Multa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer pagamento;

	@ManyToOne
	@JoinColumn(name = "emprestimo_id")
	@JsonIgnore
	private Emprestimo emprestimo;
	private Date dataPagamento;
	private Integer tipo;
	@ManyToOne
	@JoinColumn(name = "valorPorDia_id")
	private ValorPorDia valorPorDia;
	private Double valor;

	public Multa() {
		// TODO Auto-generated constructor stub
	}

	public Multa(Integer id, EnumStatusPagamento pagamento, Emprestimo emprestimo, Date dataPagamento, EnumTipoMulta tipo,
			ValorPorDia valorPorDia) {
		this.id = id;
		this.pagamento = pagamento.getId();
		this.emprestimo = emprestimo;
		this.dataPagamento = dataPagamento;
		this.tipo = tipo.getId();
		this.valorPorDia = valorPorDia;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public EnumTipoMulta getTipo() {
		return EnumTipoMulta.toEnum(tipo);
	}

	public void setTipo(EnumTipoMulta tipo) {
		this.tipo = tipo.getId();
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public ValorPorDia getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(ValorPorDia valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public EnumStatusPagamento getPagamento() {
		return EnumStatusPagamento.toEnum(pagamento);
	}

	public void setPagamento(EnumStatusPagamento pagamento) {
		this.pagamento = pagamento.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
