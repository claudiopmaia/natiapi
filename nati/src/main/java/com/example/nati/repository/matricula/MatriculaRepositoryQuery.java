package com.example.nati.repository.matricula;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.nati.model.Matricula;
import com.example.nati.repository.filter.MatriculaFilter;

public interface MatriculaRepositoryQuery {

	public Page<Matricula> filtrar(MatriculaFilter matriculaFilter, Pageable pageable);
}
