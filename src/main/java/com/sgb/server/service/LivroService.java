package com.sgb.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.AreaDeConhecimento;
import com.sgb.server.domain.Editora;
import com.sgb.server.domain.Livro;
import com.sgb.server.domain.dto.LivroNewDTO;
import com.sgb.server.repository.LivroRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

//faz o registro de um componete do spring
@Service
public class LivroService {

// autowired cria um objeto quando a classe e chamada
	@Autowired
	private LivroRepository repository;
	@Autowired
	private AreaDeConhecimentoService areaDeConhecimentoService;

	public List<Livro> findAll() {
		return repository.findAll();
	}

	public Livro findById(Integer idLivro) {
		// optional ter ou nao um "Livro"
		Optional<Livro> oLivro = repository.findById(idLivro);
		return oLivro.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! id: " + idLivro)); // orELSEThrow
																												// retorna
	}

	public void save(Livro livro) {
		livro.setId(null);
		repository.save(livro);

	}

	public void update(Livro livro) {
		Livro ant = this.findById(livro.getId());
		updateData(ant, livro);
		repository.save(ant);

	}

	private void updateData(Livro ant, Livro livro) {
		ant.setAno(livro.getAno() == null ? ant.getAno() : livro.getAno());
		ant.setAreaConhecimento(livro.getAreaDeConhecimento() == null || livro.getAreaDeConhecimento().getId() == null ? ant.getAreaDeConhecimento() : livro.getAreaDeConhecimento());
		ant.setEdicao(livro.getEdicao() == null ? ant.getEdicao() : livro.getEdicao());
		ant.setNome(livro.getNome() == null ? ant.getNome() : livro.getNome());
		ant.setEditora(livro.getEditora() == null || livro.getEditora().getId() == null ? ant.getEditora() : livro.getEditora());
	}

	public void delete(Integer id) {
		this.findById(id);
		Livro livro = new Livro();
		livro.setId(id);
		repository.delete(livro);
	}
	
	public Livro dtoFromLivro(LivroNewDTO livroNewDTO) {
		AreaDeConhecimento areaDeConhecimento = new AreaDeConhecimento();
		Editora editora = new Editora();
		Livro livro = new Livro();
		areaDeConhecimento.setId(livroNewDTO.getAreaDeConhecimento());
		editora.setId(livroNewDTO.getEditora());
		livro.setAno(livroNewDTO.getAno());
		livro.setAutores(livroNewDTO.getAutores());
		livro.setEdicao(livroNewDTO.getEdicao());
		livro.setNome(livroNewDTO.getNome());
		livro.setIsbn(livroNewDTO.getIsbn());
		livro.setEditora(editora);
		livro.setAreaConhecimento(areaDeConhecimento);
		livro.setValor(livroNewDTO.getValor());
		return livro;
	}
	public Page<Livro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);		
	}
	public Livro findByIsbn(String isbn) {
		return repository.findByIsbn(isbn);
	}

	public Page<Livro> findByAreaDeConhecimento(Integer id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		AreaDeConhecimento areaDeConhecimento = areaDeConhecimentoService.findById(id);
		return repository.findByAreaDeConhecimento(areaDeConhecimento,pageRequest);
		
	}

}
