package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{
	
}
