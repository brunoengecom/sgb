package com.sgb.server.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Matricula;
import com.sgb.server.domain.Turma;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.dto.AlunoNewDTO;
import com.sgb.server.domain.dto.FuncionarioNewDTO;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.domain.enums.EnumStatus;
import com.sgb.server.repository.UsuarioRepository;
import com.sgb.server.sevice.exception.DataIntegrityViolationException;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private MatriculaService matriculaService;
	
	public Usuario findById(Integer id) {
		Optional<Usuario> oUsuario = repository.findById(id);
		return oUsuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado! id: " + id)); // orELSEThrow
		
	}
	
	
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}

	public Usuario alunoDtoFromUsuario(AlunoNewDTO dto) {
		Usuario usuario = new Usuario();
		Turma turma = new Turma();
		Matricula matricula = new Matricula();
		matricula.setNumero(dto.getMatricula());
		turma.setId(dto.getTurma());
		matricula.setTurma(turma);
		
		turma.setId(dto.getTurma());
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setDataNasc(dto.getDataNasc());
		usuario.setCpf(dto.getCpf());
		usuario.addMatricula(matricula);
		return usuario;
	}
	
	public Usuario funcionarioDtoFromUsuario(FuncionarioNewDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setDataNasc(dto.getDataNasc());
		usuario.setCpf(dto.getCpf());
		usuario.setCargo(dto.getCargo());
		usuario.setStatus(EnumStatus.ATIVO);
		return usuario;
	}

	public void save(Usuario usuario) {
		usuario.setId(null);
		repository.save(usuario);
	}
	
	public void saveAluno(Usuario usuario) {
		Usuario u = this.findByCpf(usuario.getCpf());
		Usuario us = this.findByEmail(usuario.getEmail());
		
		if(u != null || us != null) {
			if((u != null && u.getRoles().contains(EnumRoles.ALUNO) )|| (us != null && us.getRoles().contains(EnumRoles.ALUNO))) {
				throw new DataIntegrityViolationException("Este Aluno já está cadastrado no Sistema");
			}
			
			usuario.setId(u != null ? u.getId() : us.getId());
			this.update(usuario);
		}else {
			this.save(usuario);
			
		}
		Set<Matricula> matriculas = new HashSet<>();
		
		for (Matricula matricula : usuario.getMatriculas()) {
			matricula.setUsuario(usuario);
			
			matriculas.add(matricula);
		}
		
		this.matriculaService.save(matriculas);
	}
	
	public void saveFuncionario(Usuario usuario) {
		Usuario u = this.findByCpf(usuario.getCpf());
		Usuario us = this.findByEmail(usuario.getEmail());
		
		if(u != null || us != null) {
			if((u != null && u.getRoles().contains(EnumRoles.FUNCIONARIO) )|| (us != null && us.getRoles().contains(EnumRoles.FUNCIONARIO))) {
				throw new DataIntegrityViolationException("Este Funcionario já está cadastrado no Sistema");
			}
			usuario.setId(u != null ? u.getId() : us.getId());
			this.update(usuario);
		}else {
			this.save(usuario);
		}
	}
	
	public Usuario findByCpf(String cpf) {
		
		return repository.findByCpf(cpf);
	}

	public void update(Usuario usuario) {
		Usuario ant = this.findById(usuario.getId());
		updateData(ant, usuario);
		repository.save(ant);
	}

	private void updateData(Usuario ant, Usuario usuario) {
		ant.setNome(usuario.getNome() == null ? ant.getNome() : usuario.getNome());
		ant.setEmail(usuario.getEmail() == null ? ant.getEmail() : usuario.getEmail());
		ant.setDataNasc(usuario.getDataNasc() == null ? ant.getDataNasc() : usuario.getDataNasc());
		ant.setCpf(usuario.getCpf() == null ? ant.getCpf() : usuario.getCpf());
		ant.setStatus(usuario.getStatus() == null ? ant.getStatus() : usuario.getStatus());
		ant.setCargo(usuario.getCargo() == null ? ant.getCargo() : usuario.getCargo());
		
		if(usuario.getRoles() != null) {
			for (EnumRoles role : usuario.getRoles()) {
				ant.addRoles(role);
			}
		}
	}


}
