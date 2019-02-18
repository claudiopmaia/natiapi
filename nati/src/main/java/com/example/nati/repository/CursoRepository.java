package com.example.nati.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nati.model.Curso;
import com.example.nati.repository.curso.CursoRepositoryQuery;

public interface CursoRepository extends JpaRepository<Curso, Long>, CursoRepositoryQuery{

}
