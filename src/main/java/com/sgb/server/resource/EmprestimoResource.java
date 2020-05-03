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

import com.sgb.server.domain.Emprestimo;
import com.sgb.server.domain.Multa;
import com.sgb.server.domain.Patrimonio;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.dto.EmprestimoNewDTO;
import com.sgb.server.dto.DevolucaoNewDTO;
import com.sgb.server.service.EmprestimoService;
import com.sgb.server.service.UsuarioService;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoResource {

	@Autowired
	private EmprestimoService service;
	
	@Autowired
	private UsuarioService usuario;
	
	@RequestMapping(method = RequestMethod.GET,value = "/{idEmprestimo}")
	public ResponseEntity<Emprestimo> findById(@PathVariable Integer idEmprestimo) {
		return ResponseEntity.ok().body(service.findById(idEmprestimo));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Emprestimo>>findPage(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "15")Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "aquisicao")String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction){
		
		Page<Emprestimo> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
	}
//	@requestBody exige que no corpo da requisição tenha um objeto do tipo do parametro
	@RequestMapping(method = RequestMethod.POST)
	
	public ResponseEntity<Void> save(@Valid @RequestBody EmprestimoNewDTO dto) {
		Emprestimo emprestimo = new Emprestimo();
		Usuario usuario = new Usuario();
		Patrimonio patrimonio = new Patrimonio();
		usuario.setId(dto.getUsuario());
		patrimonio.setNumero(dto.getPatrimonio());
		emprestimo.setUsuario(usuario);
		emprestimo.setPatrimonio(patrimonio);
		
		service.save(emprestimo);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/emprestimo/{idEmprestimo}").buildAndExpand(emprestimo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(method = RequestMethod.POST,value = "/devolucao")
	public ResponseEntity<List<Multa>> devolucao(@Valid @RequestBody DevolucaoNewDTO dto) {
		Emprestimo emprestimo = new Emprestimo();
		Patrimonio patrimonio = new Patrimonio();
		patrimonio.setNumero(dto.getPatrimonio());
		emprestimo.setPatrimonio(patrimonio);
		emprestimo.setMultas(dto.getMultas());
		
		emprestimo = service.devolucao(emprestimo);
		return ResponseEntity.ok().body(emprestimo.getMultas());
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{idEmprestimo}")
	public void delete(@PathVariable Integer idEmprestimo) {
		service.delete(idEmprestimo);
	}
	
	
	
}
