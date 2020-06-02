package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer>{

	Editora findByNome(String nome);

	Editora findByCnpj(String cnpj);
	
}
