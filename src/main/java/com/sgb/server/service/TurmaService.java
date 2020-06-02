package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Curso;
import com.sgb.server.domain.Turma;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.dto.TurmaEditDTO;
import com.sgb.server.domain.dto.TurmaNewDTO;
import com.sgb.server.domain.dto.UsuarioNewDTO;
import com.sgb.server.repository.TurmaRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

//faz o registro de um componete do spring
@Service
public class TurmaService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private TurmaRepository repository;

	public List<Turma> findAll() {
		return repository.findAll();
	}

	public Turma findById(Integer idTurma) {
		// optional ter ou nao um "Turma"
		Optional<Turma> oTurma = repository.findById(idTurma);
		return oTurma.orElseThrow(() -> new ObjectNotFoundException("Turma não encontrado! id: " + idTurma)); // orELSEThrow
																												// retorna
	}
	

	public void save(Turma turma) {
		turma.setId(null);
		repository.save(turma);

	}

	public void update(Turma turma) {
		Turma ant = this.findById(turma.getId());
		updateData(ant, turma);
		repository.save(ant);

	}
	
	
	private void updateData(Turma ant, Turma turma) {
		ant.setDtInicio(turma.getDtInicio() == null ? ant.getDtInicio() : turma.getDtInicio());
		ant.setDtFim(turma.getDtFim() == null ? ant.getDtFim() : turma.getDtFim());
		ant.setCurso(turma.getCurso() == null ? ant.getCurso() : turma.getCurso());
		ant.setPeriodo(turma.getCurso() == null ? ant.getPeriodo() : turma.getPeriodo());
		
	}

	public void delete(Integer id) {
		this.findById(id);
		Turma turma = new Turma();
		turma.setId(id);
		repository.delete(turma);
	}

	public Page<Turma> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}
	
	public Turma dtoFromTurma(TurmaNewDTO turmaNewDTO) {
		Turma turma = new Turma();
		Curso curso = new Curso();
		curso.setId(turmaNewDTO.getCurso());
		turma.setDtInicio(turmaNewDTO.getDtInicio());
		turma.setDtFim(turmaNewDTO.getDtFim());
		turma.setPeriodo(turmaNewDTO.getPeriodo());
		turma.setCurso(curso);
		
		return turma;
	}

	public Turma dtoFromTurma( TurmaEditDTO dto) {
		Turma turma = new Turma();
		Curso curso = new Curso();
		curso.setId(dto.getCurso());
		curso.setId(dto.getCurso());
		turma.setDtInicio(dto.getDtInicio());
		turma.setDtFim(dto.getDtFim());
		turma.setPeriodo(dto.getPeriodo());
		turma.setCurso(curso);
		
		
		return turma;
	}

	public Usuario dtoFromUsuario(@Valid UsuarioNewDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
