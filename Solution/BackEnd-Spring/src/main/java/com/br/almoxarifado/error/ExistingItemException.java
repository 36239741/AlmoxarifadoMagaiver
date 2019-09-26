package com.br.almoxarifado.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExistingItemException extends RuntimeException {
	public ExistingItemException(String message) {
		super(message);
	}
	
}
