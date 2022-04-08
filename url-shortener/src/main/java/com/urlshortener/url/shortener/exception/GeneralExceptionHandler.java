package com.urlshortener.url.shortener.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			                                                      HttpHeaders headers,
			                                                      HttpStatus status,
			                                                      WebRequest request) {

		Map<String, String> errors =new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName = ((FieldError)error).getField();
		String message = error.getDefaultMessage();
		errors.put(fieldName, message);
		});
		
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	@ExceptionHandler(ShortUrlNotFoundException.class)
	public ResponseEntity<?> shortUrlNotFoundException(ShortUrlNotFoundException e){
		Map<String, String> errors = new HashMap<>();
		errors.put("error", e.getMessage());
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CodeAlreadyExists.class)
	public ResponseEntity<?> codeAlreadyExists(ShortUrlNotFoundException e){
		Map<String, String> errors = new HashMap<>();
		errors.put("error", e.getMessage());
		return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
	}
}
