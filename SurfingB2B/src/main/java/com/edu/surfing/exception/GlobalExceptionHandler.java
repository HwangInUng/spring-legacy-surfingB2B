package com.edu.surfing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	/* business Exception handler */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex){
		log.error("CuntomException:: ", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
		return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
	}
	
	/* field Exception handler */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleFieldException(Exception ex){
		log.error(ex.getClass().getName());
		log.error(ex.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server 내부 오류 발생");
	}
	
}
