package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.PrazoEmprestimo;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.repository.PrazoEmprestimoRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

//faz o registro de um componete do spring
@Service
public class PrazoEmprestimoService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private PrazoEmprestimoRepository repository;

	public List<PrazoEmprestimo> findAll() {
		return repository.findAll();
	}

	public PrazoEmprestimo findById(Integer idPrazoEmprestimo) {
		// optional ter ou nao um "PrazoEmprestimo"
		Optional<PrazoEmprestimo> oPrazoEmprestimo = repository.findById(idPrazoEmprestimo);
		return oPrazoEmprestimo.orElseThrow(() -> new ObjectNotFoundException("Prazo Emprestimo não encontrado! id: " + idPrazoEmprestimo)); 
	}

	public void save(PrazoEmprestimo prazoEmprestimo) {
		prazoEmprestimo.setId(null);
		repository.save(prazoEmprestimo);

	}

	public void update(PrazoEmprestimo prazoEmprestimo) {
		PrazoEmprestimo ant = this.findById(prazoEmprestimo.getId());
		updateData(ant, prazoEmprestimo);
		repository.save(ant);

	}
	
	
	private void updateData(PrazoEmprestimo ant, PrazoEmprestimo prazoEmprestimo) {
		ant.setPrazo(prazoEmprestimo.getPrazo() == null ? ant.getPrazo() : prazoEmprestimo.getPrazo());
	}

	public void delete(Integer id) {
		this.findById(id);
		PrazoEmprestimo prazoEmprestimo = new PrazoEmprestimo();
		prazoEmprestimo.setId(id);
		repository.delete(prazoEmprestimo);
	}
	
	public Page<PrazoEmprestimo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}

	public PrazoEmprestimo findFirstByEnumRoleOrderByIdDesc(EnumRoles role) {
		return repository.findFirstByEnumRoleOrderByIdDesc(role);
	}

}
