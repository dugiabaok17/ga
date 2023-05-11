package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentIdNotExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
}
