package com.example.nati.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nati.model.Matricula;
import com.example.nati.model.Semestre;
import com.example.nati.repository.MatriculaRepository;
import com.example.nati.repository.SemestreRepository;
import com.example.nati.service.exception.MatriculaInexistenteException;

@Service
public class SemestreService {
	
	@Autowired
	private SemestreRepository semestreRepository;
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	
	
	public Semestre salvar(@Valid Semestre  semestre){
		Optional<Matricula> matriculaCash = matriculaRepository.findById(semestre.getMatricula().getCodigo());
		if(matriculaCash.isPresent()) {
			throw new MatriculaInexistenteException();			
			
			
		}
		
		return semestreRepository.save(semestre);
	}

}
