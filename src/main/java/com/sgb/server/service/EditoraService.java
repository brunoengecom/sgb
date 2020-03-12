package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Editora;
import com.sgb.server.repository.EditoraRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

@Service
public class EditoraService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private EditoraRepository repository;

	public List<Editora> findAll() {
		return repository.findAll();
	}

	public Editora findById(Integer id) {
	//optional ter ou nao uma "Editora"
		Optional<Editora> oEditora = repository.findById(id);
		return oEditora.orElseThrow(()->new ObjectNotFoundException("Editora não encontrada! id: "+id)); // orELSEThrow retorna uma exceção caso o objeto nao exista		
	}

	public void save(Editora editora) {
		repository.save(editora);

	}

	public void update(Editora editora) {
		Editora ant = this.findById(editora.getId());
		updateData(ant,editora);
		repository.save(ant);
	}

	private void updateData(Editora ant, Editora editora) {
		ant.setNome(editora.getNome() == null ? ant.getNome() : editora.getNome());
		ant.setCnpj(editora.getCnpj() == null ? ant.getCnpj() : editora.getCnpj());
	}

	public void delete(Integer id) {
		this.findById(id);
		Editora editora = new Editora();
		editora.setId(id);
		repository.delete(editora);
	}

	public Page<Editora> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}

}
