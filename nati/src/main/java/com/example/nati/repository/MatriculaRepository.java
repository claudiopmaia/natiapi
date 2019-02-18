package com.example.nati.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nati.model.Matricula;
import com.example.nati.repository.matricula.MatriculaRepositoryQuery;

public interface MatriculaRepository extends JpaRepository<Matricula, Long>, MatriculaRepositoryQuery{

}
