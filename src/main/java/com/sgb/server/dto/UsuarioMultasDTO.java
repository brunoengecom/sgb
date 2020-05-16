package com.sgb.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sgb.server.domain.Multa;
import com.sgb.server.domain.Usuario;

public class UsuarioMultasDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Multa> multas = new ArrayList<>();
	private Integer emprestimosAtivos;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Multa> getMultas() {
		return multas;
	}
	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}
	public Integer getEmprestimosAtivos() {
		if(emprestimosAtivos == null) {
			return 0;
		}
		return emprestimosAtivos;
	}
	public void setEmprestimosAtivos(Integer emprestimosAtivos) {
		this.emprestimosAtivos = emprestimosAtivos;
	}
	
	

}
