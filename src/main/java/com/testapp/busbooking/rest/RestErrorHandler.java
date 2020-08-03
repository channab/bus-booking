package com.testapp.busbooking.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.testapp.busbooking.exceptions.BadRequestExpection;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value 
		      = { EntityNotFoundException.class})
	protected ResponseEntity<Object> handleNotFound(
		      RuntimeException ex, WebRequest request) {
		        
		        return handleExceptionInternal(ex, ex.getMessage(), 
		          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		    }
	
	@ExceptionHandler(value 
		      = { BadRequestExpection.class})
	protected ResponseEntity<Object> handleBadRequest(
		      RuntimeException ex, WebRequest request) {
		        
		        return handleExceptionInternal(ex, ex.getMessage(), 
		          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		    }
	
	@ExceptionHandler(value 
		      = { Exception.class})
	protected ResponseEntity<Object> handleGeneralException(
		      RuntimeException ex, WebRequest request) {
		        
		        return handleExceptionInternal(ex, ex.getMessage(), 
		          new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
		    }
}
