package com.example.nati.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nati.model.Disciplina;
import com.example.nati.repository.disciplina.DisciplinaRepositoryQuery;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>, DisciplinaRepositoryQuery{

}
