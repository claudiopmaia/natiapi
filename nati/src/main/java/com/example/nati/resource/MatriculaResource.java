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
import com.example.nati.model.Matricula;
import com.example.nati.repository.MatriculaRepository;
import com.example.nati.repository.filter.MatriculaFilter;
import com.example.nati.service.MatriculaService;
import com.example.nati.service.exception.CursoInexistenteException;

@RestController
@RequestMapping("/matriculas")
public class MatriculaResource {
	
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	public Page<Matricula> pesquisar(MatriculaFilter matriculaFilter, Pageable pageable){
		return matriculaRepository.filtrar(matriculaFilter, pageable);
		
	}
	
	@GetMapping("/{codigo}")
	public Optional<Matricula> buscarMatriculaPorCodigo(@PathVariable Long codigo){
		return matriculaRepository.findById(codigo);
		
	}
	
	@PostMapping
	public ResponseEntity<Matricula> criar(@Valid @RequestBody Matricula matricula, HttpServletResponse response){
		Matricula matriculaSalva = matriculaService.salvar(matricula);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, matricula.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(matriculaSalva);
		
	}
	
	@ExceptionHandler({CursoInexistenteException.class})
	public ResponseEntity<Object> handleCursoInexistenteException(CursoInexistenteException ex){
		String messagemUsuario = messageSource.getMessage("curso.inexistente", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
		
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		matriculaRepository.deleteById(codigo);
	}
	
	
	
	
	
	
	
}
