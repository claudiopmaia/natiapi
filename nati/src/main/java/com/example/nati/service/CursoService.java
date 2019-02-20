package com.example.nati.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nati.model.Curso;
import com.example.nati.model.Disciplina;
import com.example.nati.repository.CursoRepository;
import com.example.nati.repository.DisciplinaRepository;
import com.example.nati.service.exception.DisciplinaInexistenteException;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	public Curso salvar(@Valid Curso curso){
		Optional<Disciplina> disciplinaCash = disciplinaRepository.findById(curso.getDisciplina().getCodigo());
		
		if (!disciplinaCash.isPresent() ) {
				throw new DisciplinaInexistenteException();			
		}
		
		return cursoRepository.save(curso);
	}

}
