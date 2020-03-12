package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.ValorPorDia;
import com.sgb.server.repository.ValorPorDiaRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

@Service
public class ValorPorDiaService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private ValorPorDiaRepository repository;

	public List<ValorPorDia> findAll() {
		return repository.findAll();
	}

	public ValorPorDia findById(Integer id) {
		// optional ter ou nao um "ValorPorDia"
		Optional<ValorPorDia> oValorPorDia = repository.findById(id);
		return oValorPorDia.orElseThrow(() -> new ObjectNotFoundException("ValorPorDia não encontrado! id: " + id)); // orELSEThrow
	}

	public void save(ValorPorDia valorPorDia) {
		repository.save(valorPorDia);

	}

	public void update(ValorPorDia valorPorDia) {
		this.findById(valorPorDia.getId());
		repository.save(valorPorDia);
	}

	public void delete(Integer id) {
		this.findById(id);
		ValorPorDia valorPorDia = new ValorPorDia();
		valorPorDia.setId(id);
		repository.delete(valorPorDia);
	}

	public Page<ValorPorDia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}
}
