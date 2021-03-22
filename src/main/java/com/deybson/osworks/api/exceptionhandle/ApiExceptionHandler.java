package com.deybson.osworks.api.exceptionhandle;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deybson.osworks.api.exceptionhandle.Problema.Campo;
import com.deybson.osworks.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleException(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDatahora(LocalDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleExceptionConstraintViolation(ConstraintViolationException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		
		Problema problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos são invalidos. Faça o preenchimento correto e tente novamente");
		problema.setDatahora(LocalDateTime.now());
		
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		
		Problema problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo("Utilize um dos parametros: ABERTA, CANCELADA, ou FINALIZADA, para esta URI");
		problema.setDatahora(LocalDateTime.now());
		
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ArrayList<Campo> campos = new ArrayList<Campo>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			campos.add(new Campo(nome, mensagem));
		}
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos são invalidos. Faça o preenchimento correto e tente novamente");
		problema.setDatahora(LocalDateTime.now());
		problema.setCampos(campos);
		

		
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}
