package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Patrimonio;
import com.sgb.server.repository.PatrimonioRepository;
import com.sgb.server.sevice.exception.DataIntegrityViolationException;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

@Service
public class PatrimonioService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private PatrimonioRepository repository;
	@Autowired
	private LivroService livroService;

	public List<Patrimonio> findAll() {
		return repository.findAll();
	}

	public Patrimonio findById(Integer id) {
		// optional ter ou nao uma "PATRIMONIO"
		Optional<Patrimonio> oPatrimonio = repository.findById(id);
		return oPatrimonio.orElseThrow(() -> new ObjectNotFoundException("Patrimonio Não Encontrada! id: " + id));
	}

	public void save(Patrimonio patrimonio) {
		validNumero(patrimonio);
		this.livroService.findById(patrimonio.getLivro().getId());
		repository.save(patrimonio);

	}

	private void validNumero(Patrimonio patrimonio) {
		List<Patrimonio> patrimonios = repository.findByNumero(patrimonio.getNumero());
		if (!patrimonios.isEmpty()) {
			throw new DataIntegrityViolationException("Este Patrimonio Já Existe!");
		}
	}

	public void update(Patrimonio patrimonio) {
		validNumero(patrimonio);
		Patrimonio ant = this.findById(patrimonio.getId());
		updateData(ant, patrimonio);
		repository.save(patrimonio);
	}

	private void updateData(Patrimonio ant, Patrimonio patrimonio) {
		ant.setNumero(patrimonio.getNumero() == null ? ant.getNumero() : patrimonio.getNumero());
	}

	public void delete(Integer id) {
		this.findById(id);
		Patrimonio patrimonio = new Patrimonio();
		patrimonio.setId(id);
		repository.delete(patrimonio);
	}
	public Page<Patrimonio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}

	public Patrimonio findByNumero(String numero) {
		List<Patrimonio> patrimonios = repository.findByNumero(numero);
		if(patrimonios.isEmpty()) {
			throw new ObjectNotFoundException("Patrimonio não Encontrado");
		}
		return patrimonios.get(0);
	}

}
