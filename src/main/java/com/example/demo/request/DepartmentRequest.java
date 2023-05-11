package com.example.demo.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.example.demo.annotation.DepartmentNameNotExists;

import jakarta.validation.constraints.NotBlank;

public class DepartmentRequest {

	
	
	@NotBlank(message = "{notBlank.message}")
	@DepartmentNameNotExists
	@Length(max = 30)
	private String departmentName;
	
	private LocalDateTime createdDate;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
