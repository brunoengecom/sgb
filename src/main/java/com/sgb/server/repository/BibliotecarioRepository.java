package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Bibliotecario;
import com.sgb.server.domain.Usuario;

@Repository
public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Integer>{

	Bibliotecario findByUsuario(Usuario usuario);
	
	@Query("SELECT DISTINCT obj FROM Bibliotecario obj INNER JOIN obj.usuario usu WHERE usu.email = :email")
	Bibliotecario findByEmail(@Param("email") String email);
	
}
