package com.example.nati.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nati.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
