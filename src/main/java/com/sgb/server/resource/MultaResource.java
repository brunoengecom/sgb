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

import com.sgb.server.domain.Multa;
import com.sgb.server.service.MultaService;

@RestController
@RequestMapping(value = "/multa")
public class MultaResource {

	@Autowired
	private MultaService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idMulta}")
	public ResponseEntity<Multa> findById(@PathVariable Integer idMulta) {
		return ResponseEntity.ok().body(service.findById(idMulta));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Multa>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "dataPagamento")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<Multa> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@RequestBody Multa multa) {
		service.save(multa);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/multa/{idMulta}").buildAndExpand(multa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{idMulta}")
	public void update(@RequestBody Multa multa,@PathVariable Integer idMulta) {
		multa.setId(idMulta);
		service.update(multa);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idMulta}")
	public void delete(@PathVariable Integer idMulta) {
		service.delete(idMulta);
	}
	
	
	
}
