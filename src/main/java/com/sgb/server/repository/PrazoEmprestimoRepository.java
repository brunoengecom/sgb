package com.sgb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.PrazoEmprestimo;
import com.sgb.server.domain.enums.EnumRoles;

@Repository
public interface PrazoEmprestimoRepository extends JpaRepository<PrazoEmprestimo, Integer>{

	PrazoEmprestimo findFirstByEnumRoleOrderByIdDesc(EnumRoles role);
	
}
