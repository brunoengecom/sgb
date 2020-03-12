package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.AreaDeConhecimento;
import com.sgb.server.repository.AreaDeConhecimentoRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

@Service
public class AreaDeConhecimentoService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private AreaDeConhecimentoRepository repository;

	public List<AreaDeConhecimento> findAll() {
		return repository.findAll();
	}

	public AreaDeConhecimento findById(Integer id) {
	//optional ter ou nao uma "Administrador"
		Optional<AreaDeConhecimento> oAreaDeConhecimento = repository.findById(id);
		return oAreaDeConhecimento.orElseThrow(()->new ObjectNotFoundException("Area de conhecimento não encontrada! id: "+id)); // orELSEThrow retorna uma exceção caso o objeto nao exista
		
			
	}

	public void save(AreaDeConhecimento areaDeConhecimento) {
		repository.save(areaDeConhecimento);

	}

	public void update(AreaDeConhecimento areaDeConhecimento) {
		AreaDeConhecimento ant = this.findById(areaDeConhecimento.getId());
		updateData(ant, areaDeConhecimento);
		repository.save(ant);
	}

	

	private void updateData(AreaDeConhecimento ant, AreaDeConhecimento areaDeConhecimento) {
	ant.setNome(areaDeConhecimento.getNome() == null ? ant.getNome() : areaDeConhecimento.getNome());	
	}
	public void delete(Integer id) {
		this.findById(id);
		AreaDeConhecimento areaDeConhecimento = new AreaDeConhecimento();
		areaDeConhecimento.setId(id);
		repository.delete(areaDeConhecimento);
	}

	public Page<AreaDeConhecimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}

}
