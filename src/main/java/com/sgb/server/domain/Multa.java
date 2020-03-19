package com.sgb.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sgb.server.domain.enums.EnumStatusPagamento;

@Entity
public class Multa implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private EnumStatusPagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "emprestimo_id")
	private Emprestimo emprestimo;
	private Date dataPagamento;
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	public Date getDataPagamento() {
		return dataPagamento;
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
		return pagamento;
	}
	public void setPagamento(EnumStatusPagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	@ManyToOne
	@JoinColumn(name = "valorPorDia_id")
	private ValorPorDia valorPorDia;
	
	private static final long serialVersionUID = 1L;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
