package com.example.nati.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nati.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
