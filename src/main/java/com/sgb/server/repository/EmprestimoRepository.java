package com.sgb.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Emprestimo;
import com.sgb.server.domain.Usuario;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>{
	
	@Query("SELECT DISTINCT obj FROM Emprestimo obj WHERE obj.patrimonio.id = :patrimonio AND obj.devolucao IS NULL")
	List<Emprestimo> isEmprestimoAtivo(Integer patrimonio);

	Integer countByUsuarioAndDevolucaoIsNull(Usuario u);
	
	
}
