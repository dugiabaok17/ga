package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ DepartmentIdNotExists.class, NumberFormatException.class })
	public ResponseEntity<String> handleExceptionA(Exception e) {
		return ResponseEntity.status(400).body(e.getMessage());
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleException(Exception e) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
//	}

}
