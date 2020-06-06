package com.sgb.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

	List<Matricula> findByNumero(String numero);

}
