package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Bibliotecario;
import com.sgb.server.domain.Usuario;

@Repository
public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Integer>{

	Bibliotecario findByUsuario(Usuario usuario);
	
}
