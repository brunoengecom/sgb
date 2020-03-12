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

import com.sgb.server.domain.AreaDeConhecimento;
import com.sgb.server.service.AreaDeConhecimentoService;

@RestController
@RequestMapping(value = "/areaDeConhecimento")
public class AreaDeConhecimentoResource {

	@Autowired
	private AreaDeConhecimentoService service;
//							RequestMethod informa o metodo que sera acessado
	
//	Role BIBLIOTECARIA
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public ResponseEntity<AreaDeConhecimento> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<AreaDeConhecimento>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<AreaDeConhecimento> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
//	Role BIBLIOTECARIA
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@RequestBody AreaDeConhecimento areaDeConhecimento) {
		service.save(areaDeConhecimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/areaDeConhecimento/{id}").buildAndExpand(areaDeConhecimento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
//	ROLE BIBLIOTECARIA
	@RequestMapping(method = RequestMethod.PUT,value = "/{id}")
	public void update(@RequestBody AreaDeConhecimento areaDeConhecimento,@PathVariable Integer id) {
		areaDeConhecimento.setId(id);
		service.update(areaDeConhecimento);
	}
//	ROLE BIBLIOTECARIA
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	
	
}

