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

import com.sgb.server.domain.ValorPorDia;
import com.sgb.server.service.ValorPorDiaService;

@RestController
@RequestMapping(value = "/valorPorDia")
public class ValorPorDiaResource {

	@Autowired
	private ValorPorDiaService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idValorPorDia}")
	public ResponseEntity<ValorPorDia> findById(@PathVariable Integer idValorPorDia) {
		return ResponseEntity.ok().body(service.findById(idValorPorDia));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ValorPorDia>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<ValorPorDia> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@RequestBody ValorPorDia valorPorDia) {
		service.save(valorPorDia);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/valorPorDia/{id}").buildAndExpand(valorPorDia.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{idValorPorDia}")
	public void update(@RequestBody ValorPorDia valorPorDia,@PathVariable Integer idValorPorDia) {
		valorPorDia.setId(idValorPorDia);
		service.update(valorPorDia);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idValorPorDia}")
	public void delete(@PathVariable Integer idValorPorDia) {
		service.delete(idValorPorDia);
	}
	
	
	
}
