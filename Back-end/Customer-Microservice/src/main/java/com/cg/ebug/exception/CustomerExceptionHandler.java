package com.cg.ebug.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EbugException.class)
	public ResponseEntity<EntityResponse> handleEbugException(EbugException exception, WebRequest request){
		EntityResponse errorDetails = new EntityResponse(new Date(), exception.getMessage(), request.getDescription(false));
	  return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<EntityResponse> handleGlobalException(Exception exception, WebRequest request){
		EntityResponse errorDetails = new EntityResponse(new Date(), exception.getMessage(), request.getDescription(false));
	  return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
}
