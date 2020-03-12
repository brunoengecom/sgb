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

import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.dto.AlunoNewDTO;
import com.sgb.server.domain.dto.FuncionarioNewDTO;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idUsuario}")
	public ResponseEntity<Usuario> findById(@PathVariable Integer idUsuario) {
		return ResponseEntity.ok().body(service.findById(idUsuario));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction",defaultValue = "DESC")String direction){
		
		Page<Usuario> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/aluno")
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@Valid @RequestBody AlunoNewDTO dto) {
		Usuario usuario = service.alunoDtoFromUsuario(dto);
		usuario.addRoles(EnumRoles.ALUNO);
		service.saveAluno(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario/{idUsuario}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/funcionario")
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	public ResponseEntity<Void> save(@Valid @RequestBody FuncionarioNewDTO dto) {
		Usuario usuario = service.funcionarioDtoFromUsuario(dto);
		usuario.addRoles(EnumRoles.FUNCIONARIO);
		service.saveFuncionario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario/{idUsuario}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
//	
//	@RequestMapping(method = RequestMethod.PUT,value = "/{idUsuario}")
//	public void update(@Valid @RequestBody UsuarioEditDTO dto,@PathVariable Integer idUsuario) {
//		Usuario usuario = service.dtoFromUsuario(dto);
//		service.update(usuario);
//	}
//	
//	@RequestMapping(method = RequestMethod.DELETE,value = "/{idUsuario}")
//	public void delete(@PathVariable Integer idUsuario) {
//		service.delete(idUsuario);
//	}
//	
	
	
}
