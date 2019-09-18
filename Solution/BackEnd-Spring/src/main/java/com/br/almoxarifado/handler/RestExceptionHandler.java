package com.br.almoxarifado.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.almoxarifado.error.ErrorDetails;
import com.br.almoxarifado.error.ResourceNotFoundException;

	@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException){
		ErrorDetails rfnDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.NOT_FOUND.value())
		.title("Resource Not Found")
		.detail(rfnException.getMessage())
		.developerMessage(rfnException.getClass().getSimpleName())
		.build();
		return new ResponseEntity<>(rfnDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException divException){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title(HttpStatus.BAD_REQUEST.name())
		.detail("Ja existe um Fornecedor cadastrado com esse nome")
		.developerMessage(divException.getRootCause().getMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleRollbackException(MethodArgumentNotValidException rException){
        BindingResult result = rException.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title(HttpStatus.BAD_REQUEST.name())
		.fields(this.getFildDefaultMessage(fieldErrors))
		.developerMessage(rException.getMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<?> handleRollbackException(RollbackException bException){
		Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)bException.getCause()).getConstraintViolations();
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title(HttpStatus.BAD_REQUEST.name())
		.detail(this.getFildRollBack(violations))
		.developerMessage(bException.getCause().initCause(bException).getLocalizedMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.BAD_REQUEST);
	}
	private List<String> getFildDefaultMessage(List<FieldError> fieldErrors) {
		List<String> fieldErrorsList = new ArrayList<>();
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
        	fieldErrorsList.add(fieldError.getDefaultMessage());
            
        }
        return fieldErrorsList;
	}
	private String getFildRollBack(Set<ConstraintViolation<?>> constraint){
		String fild = null;
		for (ConstraintViolation v : constraint) {	
			fild = v.getMessageTemplate();
		}
		return fild;
	}
}
