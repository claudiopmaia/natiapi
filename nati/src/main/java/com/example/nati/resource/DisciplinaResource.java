package com.example.nati.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.nati.event.RecursoCriadoEvent;
import com.example.nati.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.nati.model.Disciplina;
import com.example.nati.repository.DisciplinaRepository;
import com.example.nati.repository.filter.DisciplinaFilter;
import com.example.nati.service.DisciplinaService;
import com.example.nati.service.exception.ProfessorInexistenteException;

@RestController
@RequestMapping("/diciplinas")
public class DisciplinaResource {
	
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	public Page<Disciplina> pesquisar(DisciplinaFilter disciplinaFilter, Pageable pageable){
		return disciplinaRepository.filtrar(disciplinaFilter, pageable);
		
	}
	
	@GetMapping("/{codigo}")
	public Optional<Disciplina> buscarDisciplinaPorCodigo(@PathVariable Long codigo){
		return disciplinaRepository.findById(codigo);
		
	}
	
	@PostMapping
	public ResponseEntity<Disciplina> criar(@Valid @RequestBody Disciplina disciplina, HttpServletResponse response){
		Disciplina disciplinaSalva = disciplinaService.salvar(disciplina);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, disciplina.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaSalva);
		
	}
	
	@ExceptionHandler({ProfessorInexistenteException.class})
	public ResponseEntity<Object> handleProfessorInexistenteException(ProfessorInexistenteException ex){
		String messagemUsuario = messageSource.getMessage("professor.inexistente", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
		
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		disciplinaRepository.deleteById(codigo);
	}
	
	
	
	
	
	
	
}
