package com.br.almoxarifado.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.almoxarifado.error.ErrorDetails;
import com.br.almoxarifado.error.ExistingItemException;

	@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ExistingItemException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ExistingItemException rfnException){
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
	public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException divException){
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
	public ResponseEntity<ErrorDetails> handleRollbackException(MethodArgumentNotValidException rException){
        BindingResult result = rException.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
		.title(HttpStatus.UNPROCESSABLE_ENTITY.name())
		.fields(this.getFildDefaultMessage(fieldErrors))
		.developerMessage(rException.getMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(RollbackException bException){
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
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(HttpRequestMethodNotSupportedException httpMNSException){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.METHOD_NOT_ALLOWED.value())
		.title(HttpStatus.METHOD_NOT_ALLOWED.name())
		.detail(httpMNSException.getMethod())
		.developerMessage(httpMNSException.getMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.METHOD_NOT_ALLOWED);
	}
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(MissingServletRequestParameterException msrPexception){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title(HttpStatus.BAD_REQUEST.name())
		.detail(msrPexception.getParameterName())
		.developerMessage(msrPexception.getMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(IllegalArgumentException iaExcption){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
		.title(HttpStatus.INTERNAL_SERVER_ERROR.name())
		.detail(iaExcption.getMessage())
		.developerMessage(iaExcption.getClass().getSimpleName())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(HttpMediaTypeNotSupportedException hmtnsExcption){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
		.title(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name())
		.detail(hmtnsExcption.getMessage())
		.developerMessage(hmtnsExcption.getClass().getSimpleName())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(InvalidDataAccessApiUsageException hmtnExcption){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
		.title(HttpStatus.INTERNAL_SERVER_ERROR.name())
		.detail(hmtnExcption.getCause().getMessage())
		.developerMessage(hmtnExcption.getMessage())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(NullPointerException npExcption){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
		.title(HttpStatus.INTERNAL_SERVER_ERROR.name())
		.detail(npExcption.getMessage())
		.developerMessage(npExcption.getClass().getSimpleName())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(HttpMessageNotReadableException hmnrException){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title(HttpStatus.BAD_REQUEST.name())
		.detail(hmnrException.getMessage())
		.developerMessage(hmnrException.getClass().getSimpleName())
		.build();
		return new ResponseEntity<>(divDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<ErrorDetails> handleRollbackException(HttpMessageConversionException hmcException){
		ErrorDetails divDetails = ErrorDetails.builder()
		.timeStemp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title(HttpStatus.BAD_REQUEST.name())
		.detail(hmcException.getMessage())
		.developerMessage(hmcException.getClass().getSimpleName())
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
