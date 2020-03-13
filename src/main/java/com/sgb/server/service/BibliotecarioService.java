package com.sgb.server.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Bibliotecario;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.domain.enums.EnumStatus;
import com.sgb.server.repository.BibliotecarioRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;
import com.sgb.server.sevice.exception.ViolationException;

@Service
public class BibliotecarioService {
	@Autowired
	private BibliotecarioRepository repository;
	@Autowired
	private UsuarioService usuarioService;

	public void save(Usuario usuario) {
		Random r = new Random();
		usuario = usuarioService.findById(usuario.getId());
		if(!usuario.getRoles().contains(EnumRoles.FUNCIONARIO)) {
			throw new ViolationException("Somente Funcionarios podem ter previlegios Bibliotecarios");
		}
		if(!usuario.getRoles().contains(EnumRoles.BIBLIOTECARIO)) {
			Double s = r.nextDouble() * 1000000;
			Integer senha = (int) Math.round(s);
			usuario.addRoles(EnumRoles.BIBLIOTECARIO);
			Bibliotecario bibliotecario = new Bibliotecario();
			bibliotecario.setStatus(EnumStatus.ATIVO);
			bibliotecario.setUsuario(usuario);
			bibliotecario.setSenha(senha.toString());
			// Enviar email informando a senha para o bibliotecário
			repository.save(bibliotecario);
		}else {
			Bibliotecario bibliotecario = this.findByUsuario(usuario);
			if(bibliotecario != null) {
				bibliotecario.setStatus(EnumStatus.ATIVO);
				this.update(bibliotecario);
			}else {
				throw new ViolationException("Erro de Solicitação");
			}
		}
		
	}
	

	private void update(Bibliotecario bibliotecario) {
		Bibliotecario ant = this.findById(bibliotecario.getId());
		updateData(ant, bibliotecario);
		repository.save(ant);
	}

	private void updateData(Bibliotecario ant, Bibliotecario bibliotecario) {
		ant.setStatus(bibliotecario.getStatus() == null? ant.getStatus() : bibliotecario.getStatus());		
	}


	public Bibliotecario findById(Integer id) {
		//optional ter ou nao uma "Bibliotecario"
			Optional<Bibliotecario> oBibliotecario = repository.findById(id);
			return oBibliotecario.orElseThrow(()->new ObjectNotFoundException("Bibliotecario não encontrado! id: "+id)); // orELSEThrow retorna uma exceção caso o objeto nao exista		
		}
	private Bibliotecario findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}
}
