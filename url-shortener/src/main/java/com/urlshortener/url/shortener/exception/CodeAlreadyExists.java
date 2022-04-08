package com.urlshortener.url.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CodeAlreadyExists extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodeAlreadyExists(String message) {
		super(message);
	}

	
}
