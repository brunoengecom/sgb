package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Curso;
import com.sgb.server.repository.CursoRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

//faz o registro de um componete do spring
@Service
public class CursoService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private CursoRepository repository;

	public List<Curso> findAll() {
		return repository.findAll();
	}

	public Curso findById(Integer idCurso) {
		// optional ter ou nao um "Curso"
		Optional<Curso> oCurso = repository.findById(idCurso);
		return oCurso.orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado! id: " + idCurso)); // orELSEThrow
																												// retorna
	}

	public void save(Curso curso) {
		curso.setId(null);
		repository.save(curso);

	}

	public void update(Curso curso) {
		Curso ant = this.findById(curso.getId());
		updateData(ant, curso);
		repository.save(ant);

	}
	
	
	private void updateData(Curso ant, Curso curso) {
		ant.setNome(curso.getNome() == null ? ant.getNome() : curso.getNome());
		ant.setTipo(curso.getTipo() == null ? ant.getTipo() : curso.getTipo());
	}

	public void delete(Integer id) {
		this.findById(id);
		Curso curso = new Curso();
		curso.setId(id);
		repository.delete(curso);
	}

	public Page<Curso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}
}
