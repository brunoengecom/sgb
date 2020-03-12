package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.ValorPorDia;

@Repository
public interface ValorPorDiaRepository extends JpaRepository<ValorPorDia, Integer>{
	
}
