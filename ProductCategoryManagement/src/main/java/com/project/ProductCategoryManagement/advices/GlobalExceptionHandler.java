package com.project.ProductCategoryManagement.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.ProductCategoryManagement.exception.EmptyListException;
import com.project.ProductCategoryManagement.exception.ResourceNotFoundException;

@RestControllerAdvice // it handles all the exception
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String>handleResourceNotFound
	(ResourceNotFoundException exception){
		return new ResponseEntity<String>
		(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String>handleInternalServerError
	(Exception exception){
		return new ResponseEntity<String>
		(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<String>handleListNotFound
	(EmptyListException exception){
		return new ResponseEntity<String>
		(exception.getMessage(),HttpStatus.NOT_FOUND);
	}	
}
