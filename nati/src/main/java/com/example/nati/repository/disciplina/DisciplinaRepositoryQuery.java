package com.example.nati.repository.disciplina;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.nati.model.Disciplina;
import com.example.nati.repository.filter.DisciplinaFilter;

public interface DisciplinaRepositoryQuery {

	public Page<Disciplina> filtrar(DisciplinaFilter disciplinaFilter, Pageable pageable);
}
