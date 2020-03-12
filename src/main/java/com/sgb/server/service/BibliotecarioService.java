package com.sgb.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Bibliotecario;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.enums.EnumStatus;
import com.sgb.server.repository.BibliotecarioRepository;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

@Service
public class BibliotecarioService {
	@Autowired
	private BibliotecarioRepository repository;
	private 

	public void save(Bibliotecario bibliotecario) {
		bibliotecario.setStatus(EnumStatus.ATIVO);
		Usuario usuario = 
		repository.save(bibliotecario);
		
	}

	public Bibliotecario findById(Integer id) {
		//optional ter ou nao uma "Bibliotecario"
			Optional<Bibliotecario> oBibliotecario = repository.findById(id);
			return oBibliotecario.orElseThrow(()->new ObjectNotFoundException("Bibliotecario não encontrado! id: "+id)); // orELSEThrow retorna uma exceção caso o objeto nao exista		
		}
}
