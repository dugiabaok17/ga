package com.example.demo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.demo.response.ResponseObject;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ DepartmentIdNotExists.class, NumberFormatException.class })
	public ResponseEntity<String> handleExceptionA(Exception e) {
		return ResponseEntity.status(400).body(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public HashMap<String, String> handleException(HttpServletRequest request, Exception e) {
		HashMap<String, String> response = new HashMap<>();
		response.put("message", e.getMessage());
		return response;
	}

	@ExceptionHandler(MissingPathVariableException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseObject handleMissingPathVariableException(HttpServletRequest request,
			MissingPathVariableException e) {

		return ResponseObject.builder().status("fail").message(messageSource.getMessage("missingVariable.message", null,request.getLocale())).errCode(2).error(e.getCause())
				.detailMessage(e.getMessage()).build();
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseObject handleNotFoundResourceException(HttpServletRequest request,
			NoHandlerFoundException e) {
		return ResponseObject.builder().status("fail").message(messageSource.getMessage("notFound.message", null,request.getLocale())).errCode(1).detailMessage(e.getMessage()).build();
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseObject handleHttpRequestMethodNotSupportedException(HttpServletRequest request,
			HttpRequestMethodNotSupportedException e) {

		return ResponseObject.builder().status("fail").message("Not Support HTTP Method").errCode(3)
				.detailMessage(e.getMessage()).build();
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ResponseObject handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
			HttpMediaTypeNotSupportedException e) {
		return ResponseObject.builder().status("fail").message("Not Support MediaType").errCode(4)
				.detailMessage(e.getMessage()).build();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseObject handleMissingServletRequestParameterException(HttpServletRequest request,
			MissingServletRequestParameterException e) {
		HashMap<String, String> response = new HashMap<>();
		response.put("message", "Missing request parameter");
		return ResponseObject.builder().status("fail").message(messageSource.getMessage("missingVariable.message", null,request.getLocale())).errCode(5)
				.detailMessage(e.getMessage()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public HashMap<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		HashMap<String, Object> response = new HashMap<>();
		HashMap<String, Object> responses = new HashMap<>();
		List<Object> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> responses.put(x.getField(), x.getDefaultMessage())).collect(Collectors.toList());
		response.put("detail message", ex.getMessage());
		response.put("error", responses);
		response.put("errorCode", 6);
		response.put("status", "fail");
		return response;
	}
}
