package com.example.nati.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nati.model.Semestre;
import com.example.nati.repository.semestre.SemestreRepositoryQuery;

public interface SemestreRepository extends JpaRepository<Semestre, Long>, SemestreRepositoryQuery{

}
