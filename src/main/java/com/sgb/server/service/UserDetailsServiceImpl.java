package com.sgb.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Bibliotecario;
import com.sgb.server.repository.BibliotecarioRepository;
import com.sgb.server.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private BibliotecarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Bibliotecario bibliotecario = repo.findByEmail(email);
		if(bibliotecario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(bibliotecario.getId(), bibliotecario.getUsuario().getEmail(), bibliotecario.getSenha(), bibliotecario.getUsuario().getRoles());
	}

}
