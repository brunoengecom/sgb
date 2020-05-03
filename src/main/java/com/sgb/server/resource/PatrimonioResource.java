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
import com.sgb.server.domain.Patrimonio;
import com.sgb.server.dto.PatrimonioNewDTO;
import com.sgb.server.service.PatrimonioService;

@RestController
@RequestMapping(value = "/patrimonio")
public class PatrimonioResource {

	@Autowired
	private PatrimonioService service;
//							RequestMethod informa o metodo que sera acessado
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public ResponseEntity<Patrimonio> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Patrimonio>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "numero")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<Patrimonio> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@RequestBody @Valid PatrimonioNewDTO dto) {
		Patrimonio patrimonio = new Patrimonio();
		Livro livro = new Livro();
		livro.setId(dto.getLivro());
		
		patrimonio.setNumero(dto.getNumero());
		patrimonio.setLivro(livro);
		service.save(patrimonio);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/patrimonio/{id}").buildAndExpand(patrimonio.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{id}")
	public void update(@RequestBody Patrimonio patrimonio,@PathVariable Integer id) {
		patrimonio.setId(id);
		service.update(patrimonio);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	
	
}

