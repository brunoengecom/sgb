package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
}
