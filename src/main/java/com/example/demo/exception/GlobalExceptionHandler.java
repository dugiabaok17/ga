package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ DepartmentIdNotExists.class, NumberFormatException.class })
	public ResponseEntity<String> handleExceptionA(Exception e) {
		return ResponseEntity.status(400).body(e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NoHandlerFoundException ex) {
		// Xử lý ngoại lệ ở đây
		return new ResponseEntity<>("Không tìm thấy trang yêu cầu", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		// Xử lý ngoại lệ ở đây
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Phương thức không được hỗ trợ");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	}

}
