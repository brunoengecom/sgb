package com.sgb.server.resource;

import java.net.URI;

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

import com.sgb.server.domain.Curso;
import com.sgb.server.service.CursoService;

@RestController
@RequestMapping(value = "/curso")
public class CursoResource {

	@Autowired
	private CursoService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idCurso}")
	public ResponseEntity<Curso> findById(@PathVariable Integer idCurso) {
		return ResponseEntity.ok().body(service.findById(idCurso));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Curso>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<Curso> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@RequestBody Curso curso) {
		service.save(curso);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/curso/{idCurso}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{idCurso}")
	public void update(@RequestBody Curso curso,@PathVariable Integer idCurso) {
		curso.setId(idCurso);
		service.update(curso);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idCurso}")
	public void delete(@PathVariable Integer idCurso) {
		service.delete(idCurso);
	}
	
	
	
}
