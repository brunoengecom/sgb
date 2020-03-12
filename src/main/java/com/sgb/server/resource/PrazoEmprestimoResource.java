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

import com.sgb.server.domain.PrazoEmprestimo;
import com.sgb.server.service.PrazoEmprestimoService;

@RestController
@RequestMapping(value = "/prazoEmprestimo")
public class PrazoEmprestimoResource {

	@Autowired
	private PrazoEmprestimoService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idPrazoEmprestimo}")
	public ResponseEntity<PrazoEmprestimo> findById(@PathVariable Integer idPrazoEmprestimo) {
		return ResponseEntity.ok().body(service.findById(idPrazoEmprestimo));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<PrazoEmprestimo>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<PrazoEmprestimo> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@RequestBody PrazoEmprestimo prazoEmprestimo) {
		service.save(prazoEmprestimo);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/prazoEmprestimo/{idPrazoEmprestimo}").buildAndExpand(prazoEmprestimo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{idPrazoEmprestimo}")
	public void update(@RequestBody PrazoEmprestimo prazoEmprestimo,@PathVariable Integer idPrazoEmprestimo) {
		prazoEmprestimo.setId(idPrazoEmprestimo);
		service.update(prazoEmprestimo);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idPrazoEmprestimo}")
	public void delete(@PathVariable Integer idPrazoEmprestimo) {
		service.delete(idPrazoEmprestimo);
	}
	
	
	
}
