package com.sgb.server.resource;

import java.net.URI;
import java.util.Set;

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

import com.sgb.server.domain.Turma;
import com.sgb.server.domain.dto.TurmaEditDTO;
import com.sgb.server.domain.dto.TurmaNewDTO;
import com.sgb.server.domain.enums.EnumPeriodo;
import com.sgb.server.service.TurmaService;

@RestController
@RequestMapping(value = "/turma")
public class TurmaResource {

	@Autowired
	private TurmaService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idTurma}")
	public ResponseEntity<Turma> findById(@PathVariable Integer idTurma) {
		return ResponseEntity.ok().body(service.findById(idTurma));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/periodo")
	public ResponseEntity<EnumPeriodo[]> findPeriodos(){
		return ResponseEntity.ok().body(EnumPeriodo.values());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Turma>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "dtInicio")String orderBy,
			@RequestParam(value = "direction",defaultValue = "DESC")String direction){
		
		Page<Turma> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@Valid @RequestBody TurmaNewDTO dto) {
		Turma turma = service.dtoFromTurma(dto);
		service.save(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/turma/{idTurma}").buildAndExpand(turma.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{idTurma}")
	public void update(@Valid @RequestBody TurmaEditDTO dto,@PathVariable Integer idTurma) {
		Turma turma = service.dtoFromTurma(dto);
		turma.setId(idTurma);
		service.update(turma);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idTurma}")
	public void delete(@PathVariable Integer idTurma) {
		service.delete(idTurma);
	}
	
	
	
}
