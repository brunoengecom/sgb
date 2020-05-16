package com.sgb.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.Multa;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer>{
	
	//notação
		@Query("SELECT obj FROM Multa obj INNER JOIN obj.emprestimo emp WHERE emp.usuario.id = :usuario AND obj.pagamento = :enumStatus ")
		//metodo
		List<Multa> findByMultasUsuario(@Param("usuario")Integer usuario,@Param("enumStatus") Integer enumStatus);
		//List<Emprestimo = retorno do methodo
		//Nome do methodo = findByMultasUsuario
		//Parametro = (Integer usuario)
	
}
