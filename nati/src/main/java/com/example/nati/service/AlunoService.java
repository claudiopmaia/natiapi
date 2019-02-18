package com.example.nati.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.nati.model.Aluno;
import com.example.nati.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Aluno atualizar(Long codigo, Aluno aluno) {
		Aluno alunoCash = buscarAlunoPeloCodigo(codigo);	
		BeanUtils.copyProperties(aluno,alunoCash, "codigo");		
		return alunoRepository.save(alunoCash);
	}
	
	
	public void atualizarPropriedadeAtiva(Long codigo, Boolean ativo) {
		Aluno alunoCash = buscarAlunoPeloCodigo(codigo);
		alunoCash.setAtivo(ativo);
		alunoRepository.save(alunoCash);
	}

	public Aluno buscarAlunoPeloCodigo(Long codigo) {
		Optional<Aluno> alunoSalva = alunoRepository.findById(codigo);
		Aluno alunoCash = null;
		if (alunoSalva.isPresent()) {
			alunoCash  = alunoSalva.get();			
			
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		return alunoCash;
	}

}
