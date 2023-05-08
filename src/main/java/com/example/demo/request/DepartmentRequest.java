package com.example.demo.request;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.example.demo.annotation.DepartmentNameNotExists;

public class DepartmentRequest {
	
	@Autowired
	private MessageSource messageSource;
	
	
	@NotBlank(message = "{notBlank.message}")
	@DepartmentNameNotExists
	private String name;
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
