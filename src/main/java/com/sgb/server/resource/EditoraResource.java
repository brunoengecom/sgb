package com.sgb.server.resource;

import java.net.URI;
import java.util.List;

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

import com.sgb.server.domain.Editora;
import com.sgb.server.domain.dto.EditoraNewDTO;
import com.sgb.server.service.EditoraService;

@RestController
@RequestMapping(value = "/editora")

public class EditoraResource {
	@Autowired
	private EditoraService service;
//							RequestMethod informa o metodo que sera acessado	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public ResponseEntity<Editora> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/all")
	public ResponseEntity<List<Editora>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Editora>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<Editora> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@Valid @RequestBody EditoraNewDTO dto) {
		Editora editora = service.dtoFromObject(dto);
		service.save(editora);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/editora/{id}").buildAndExpand(editora.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{id}")
	public void update(@RequestBody Editora editora,@PathVariable Integer id) {
		editora.setId(id);
		service.update(editora);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	

}
