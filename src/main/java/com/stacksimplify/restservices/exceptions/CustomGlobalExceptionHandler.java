package com.stacksimplify.restservices.exceptions;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
 @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"from MethosArgumentNotValid exception", ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"from HttpRequestMethodNotSupportedException exception", ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
		
	}

	// handling custom exception in custom global handler class
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUsernameNotFoundException(UserNameNotFoundException ex, WebRequest request)
	{
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),  ex.getLocalizedMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND );
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request)
	{
CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),  ex.getLocalizedMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	
}
