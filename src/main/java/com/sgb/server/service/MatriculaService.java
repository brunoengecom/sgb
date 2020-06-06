package com.sgb.server.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Matricula;
import com.sgb.server.repository.MatriculaRepository;

@Service
public class MatriculaService {
	@Autowired
	private MatriculaRepository repository;

	public void save(Set<Matricula> matriculas) {
		repository.saveAll(matriculas);
	}
	
	public void save(Matricula matricula) {
		repository.save(matricula);
	}

	public Matricula findByNumero(String numero) {
		List<Matricula> matricula = repository.findByNumero(numero);
		
	return matricula == null || matricula.isEmpty()?  null :  matricula.get(0);
	}
	
	
	
}
