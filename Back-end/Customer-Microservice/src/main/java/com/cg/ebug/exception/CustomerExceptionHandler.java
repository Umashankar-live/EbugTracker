package com.cg.ebug.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EntityResponse> customValidationErrorHandling(MethodArgumentNotValidException exception){
		EntityResponse errorDetails = new EntityResponse(new Date(), "Validation error", 
				exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<EntityResponse>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EbugException.class)
	public ResponseEntity<EntityResponse> handleEbugException(EbugException exception, WebRequest request){
		EntityResponse errorDetails = new EntityResponse(new Date(), exception.getMessage(), request.getDescription(false));
	  return new ResponseEntity<EntityResponse>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<EntityResponse> handleGlobalException(Exception exception, WebRequest request){
		EntityResponse errorDetails = new EntityResponse(new Date(), exception.getMessage(), request.getDescription(false));
	  return new ResponseEntity<EntityResponse>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
}
