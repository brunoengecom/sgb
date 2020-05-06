package com.sgb.server.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sgb.server.domain.Livro;
import com.sgb.server.domain.dto.LivroNewDTO;
import com.sgb.server.dto.LivroDTO;
import com.sgb.server.service.LivroService;

@RestController
@RequestMapping(value = "/livro")
public class LivroResource {

	@Autowired
	private LivroService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idLivro}")
	public ResponseEntity<LivroDTO> findById(@PathVariable Integer idLivro) {
		return ResponseEntity.ok().body(new LivroDTO(service.findById(idLivro)));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}/areaDeConhecimento")
	public ResponseEntity<Page<LivroDTO>> findByAreaDeConhecimento(@PathVariable Integer id, @RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction) {
		Page<Livro> list = service.findByAreaDeConhecimento(id,page,linesPerPage,orderBy,direction);
		Page<LivroDTO> dto = list.map(obj->new LivroDTO(obj));
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<LivroDTO>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
	
		Page<Livro> list = service.findPage(page,linesPerPage,orderBy,direction);
		Page<LivroDTO> dto = list.map(obj -> new LivroDTO(obj));
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@Valid @RequestBody LivroNewDTO dto) {
		Livro livro = service.dtoFromLivro(dto);
		service.save(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livro/{idLivro}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{idLivro}")
	public void update(@RequestBody Livro livro,@PathVariable Integer idLivro) {
		livro.setId(idLivro);
		service.update(livro);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idLivro}")
	public void delete(@PathVariable Integer idLivro) {
		service.delete(idLivro);
	}
	
	
	
}
