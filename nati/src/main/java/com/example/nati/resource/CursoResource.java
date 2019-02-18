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
import com.example.nati.model.Curso;
import com.example.nati.repository.CursoRepository;
import com.example.nati.repository.filter.CursoFilter;
import com.example.nati.service.CursoService;
import com.example.nati.service.exception.ProfessorInexistenteException;

@RestController
@RequestMapping("/cursos")
public class CursoResource {
	
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	public Page<Curso> pesquisar(CursoFilter cursoFilter, Pageable pageable){
		return cursoRepository.filtrar(cursoFilter, pageable);
		
	}
	
	@GetMapping("/{codigo}")
	public Optional<Curso> buscarCursoPorCodigo(@PathVariable Long codigo){
		return cursoRepository.findById(codigo);
		
	}
	
	@PostMapping
	public ResponseEntity<Curso> criar(@Valid @RequestBody Curso curso, HttpServletResponse response){
		Curso cursoSalva = cursoService.salvar(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, curso.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalva);
		
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
		cursoRepository.deleteById(codigo);
	}
	
	
	
	
	
	
	
}
