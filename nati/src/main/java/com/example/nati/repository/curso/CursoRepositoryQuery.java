package com.example.nati.repository.curso;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.nati.model.Curso;
import com.example.nati.repository.filter.CursoFilter;

public interface CursoRepositoryQuery {

	public Page<Curso> filtrar(CursoFilter cursoFilter, Pageable pageable);
}
