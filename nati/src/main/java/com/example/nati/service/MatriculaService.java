package com.example.nati.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nati.model.Aluno;
import com.example.nati.model.Curso;
import com.example.nati.model.Matricula;
import com.example.nati.repository.AlunoRepository;
import com.example.nati.repository.CursoRepository;
import com.example.nati.repository.MatriculaRepository;
import com.example.nati.service.exception.AlunoInexistenteException;
import com.example.nati.service.exception.CursoInexistenteException;

@Service
public class MatriculaService {
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Matricula salvar(@Valid Matricula matricula){
		Optional<Aluno> alunoCash = alunoRepository.findById(matricula.getAluno().getCodigo());
		Optional<Curso> cursoCash = cursoRepository.findById(matricula.getCurso().getCodigo());
		if (!alunoCash .isPresent() || alunoCash.get().isInativo()) {
				throw new AlunoInexistenteException();			
		}
		if(cursoCash.isPresent()) {
			throw new CursoInexistenteException();			
			
			
		}
		
		return matriculaRepository.save(matricula);
	}

}
