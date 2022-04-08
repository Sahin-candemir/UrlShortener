package com.urlshortener.url.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShortUrlNotFoundException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShortUrlNotFoundException(String message) {
		super(message);
	}

	
}
