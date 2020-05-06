package com.sgb.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgb.server.domain.AreaDeConhecimento;
import com.sgb.server.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	Livro findByIsbn(String isbn);

	Page<Livro> findByAreaDeConhecimento(AreaDeConhecimento areaDeConhecimento, Pageable pageRequest);
}
