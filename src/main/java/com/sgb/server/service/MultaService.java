package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Multa;
import com.sgb.server.repository.MultaRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

//faz o registro de um componete do spring
@Service
public class MultaService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private MultaRepository repository;

	public List<Multa> findAll() {
		return repository.findAll();
	}

	public Multa findById(Integer idMulta) {
		// optional ter ou nao um "Livro"
		Optional<Multa> oMulta = repository.findById(idMulta);
		return oMulta.orElseThrow(() -> new ObjectNotFoundException("Multa não encontrado! id: " + idMulta)); // orELSEThrow
																												// retorna
	}

	public void save(List<Multa> set) {
		repository.saveAll(set);

	}

	public void update(Multa multa) {
		Multa ant = this.findById(multa.getId());
		updateData(ant, multa);
		repository.save(ant);

	}

	private void updateData(Multa ant, Multa multa) {
//		ant.setNome(multa.getNome() == null ? ant.getNome() : autor.getNome());
	}

	public void delete(Integer id) {
		this.findById(id);
		Multa multa = new Multa();
		multa.setId(id);
		repository.delete(multa);
	}

	public Page<Multa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}
}
