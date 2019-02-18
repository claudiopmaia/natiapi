package com.example.nati.repository.semestre;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.nati.model.Semestre;
import com.example.nati.repository.filter.SemestreFilter;

public interface SemestreRepositoryQuery {

	public Page<Semestre> filtrar(SemestreFilter semestreFilter, Pageable pageable);
}
