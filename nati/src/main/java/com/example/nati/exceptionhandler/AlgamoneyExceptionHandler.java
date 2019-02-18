package com.example.nati.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	
	//Esse metodo controla a resposta para o cliente quando acontece algum error
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			   
		// pega a mensagem do messages.propeties equivalente a acao desejada
		String messagemUsuario = messageSource.getMessage("menssagem.invalida",null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.getCause() != null? ex.getCause().toString(): ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		
			   //handleExceptionInternal metodo interno que pode alterar a menssagem de retorno ao cliente
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	
}
	
	// tratando a exception para ao tentar acessar um registro inexistente devolver ao cliente com as messagens
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException (EmptyResultDataAccessException ex, WebRequest request) {	
		
		String messagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ex.getCause() != null? ex.getCause().toString(): ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		
		String messagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String messagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, messagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			
		String mensagemUsuaio= messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = fieldError.toString();
		
		erros.add(new Erro(mensagemUsuaio, mensagemDesenvolvedor));
		}
		
		return erros;
		
	}
	
	
	public static class Erro{
		
		private String messagemUsuario;
		private String messagemDesenvolvedor;
		
		
		
		public Erro(String messagemUsuario, String messagemDesenvolvedor) {
			this.messagemUsuario = messagemUsuario;
			this.messagemDesenvolvedor = messagemDesenvolvedor;
		}
		public String getMessagemUsuario() {
			return messagemUsuario;
		}
		public String getMessagemDesenvolvedor() {
			return messagemDesenvolvedor;
		}
		public void setMessagemUsuario(String messagemUsuario) {
			this.messagemUsuario = messagemUsuario;
		}
		public void setMessagemDesenvolvedor(String messagemDesenvolvedor) {
			this.messagemDesenvolvedor = messagemDesenvolvedor;
		}
		
		
	}
		
	
}