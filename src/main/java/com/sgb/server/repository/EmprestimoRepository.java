package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Emprestimo;
import com.sgb.server.domain.Patrimonio;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>{
	
	@Query("SELECT DISTINCT obj FROM Emprestimo obj WHERE devolucao IS NOT NULL AND obj.patrimonio.id = :patrimonio")
	Patrimonio isEmprestimoAtivo(Integer patrimonio);
	
}
