package com.sgb.server.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Matricula;
import com.sgb.server.repository.MatriculaRepository;

@Service
public class MatriculaService {
	@Autowired
	private MatriculaRepository repository;
	
//	public void save(Set<Matricula> matriculas) {
//		for (Matricula matricula : matriculas) {
//			System.out.println(matricula);
//			this.save(matricula);
//		}
//	}

	public void save(Set<Matricula> matriculas) {
		System.out.println(matriculas);
		repository.saveAll(matriculas);
	}
	
	public void save(Matricula matricula) {
		repository.save(matricula);
	}
	public Matricula findByMatricula(Integer matricula) {
		
		return repository.findByMatricula(matricula);
	}
}
