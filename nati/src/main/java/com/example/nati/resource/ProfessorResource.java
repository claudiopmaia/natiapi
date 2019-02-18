package com.example.nati.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.nati.event.RecursoCriadoEvent;
import com.example.nati.model.Professor;
import com.example.nati.repository.ProfessorRepository;
import com.example.nati.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Professor> listarProfessor(){
		return professorRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public Optional<Professor> buscarProfessorPorCodigo(@PathVariable Long codigo){
		return professorRepository.findById(codigo);
		
	}
	
	@PostMapping
	public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor, HttpServletResponse response){
		Professor professorSalvo = professorRepository.save(professor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, professorSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		professorRepository.deleteById(codigo);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Professor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Professor professor){
		Professor professorAtualizado = professorService.atualizar(codigo, professor);
		return ResponseEntity.ok(professorAtualizado);
	}
	
	

}
