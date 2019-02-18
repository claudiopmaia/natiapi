package com.example.nati.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nati.model.Disciplina;
import com.example.nati.model.Professor;
import com.example.nati.repository.DisciplinaRepository;
import com.example.nati.repository.ProfessorRepository;
import com.example.nati.service.exception.ProfessorInexistenteException;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public Disciplina salvar(@Valid Disciplina disciplina){
		Optional<Professor> professorCash = professorRepository.findById(disciplina.getProfessor().getCodigo());
		if (!professorCash.isPresent() ) {
				throw new ProfessorInexistenteException();			
		}		
		return disciplinaRepository.save(disciplina);
	}

}
