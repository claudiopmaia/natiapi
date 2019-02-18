package com.example.nati.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.nati.model.Professor;
import com.example.nati.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public Professor atualizar(Long codigo, Professor professor) {
		Professor professorCash = buscarProfessorPeloCodigo(codigo);	
		BeanUtils.copyProperties(professor,professorCash, "codigo");		
		return professorRepository.save(professorCash);
	}
	

	public Professor buscarProfessorPeloCodigo(Long codigo) {
		Optional<Professor> professorSalva = professorRepository.findById(codigo);
		Professor professorCash = null;
		if (professorSalva.isPresent()) {
			professorCash  = professorSalva.get();			
			
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		return professorCash;
	}

}
