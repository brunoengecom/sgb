package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.AreaDeConhecimento;

@Repository
public interface AreaDeConhecimentoRepository extends JpaRepository<AreaDeConhecimento, Integer>{
	
}
