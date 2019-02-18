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
import com.example.nati.model.Semestre;
import com.example.nati.repository.SemestreRepository;
import com.example.nati.repository.filter.SemestreFilter;
import com.example.nati.service.SemestreService;
import com.example.nati.service.exception.MatriculaInexistenteException;

@RestController
@RequestMapping("/semestres")
public class SemestreResource {
	
	
	@Autowired
	private SemestreRepository semestreRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Autowired
	private SemestreService semestreService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	public Page<Semestre> pesquisar(SemestreFilter semestreFilter, Pageable pageable){
		return semestreRepository.filtrar(semestreFilter, pageable);
		
	}
	
	@GetMapping("/{codigo}")
	public Optional<Semestre> buscarSemestrePorCodigo(@PathVariable Long codigo){
		return semestreRepository.findById(codigo);
		
	}
	
	@PostMapping
	public ResponseEntity<Semestre> criar(@Valid @RequestBody Semestre semestre, HttpServletResponse response){
		Semestre semestreSalva = semestreService.salvar(semestre);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, semestre.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(semestreSalva);
		
	}
	
	@ExceptionHandler({MatriculaInexistenteException.class})
	public ResponseEntity<Object> handleProfessorInexistenteException(MatriculaInexistenteException ex){
		String messagemUsuario = messageSource.getMessage("matricula.inexistente", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
		
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		semestreRepository.deleteById(codigo);
	}
	
	
	
	
	
	
	
}
