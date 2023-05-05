package com.example.demo.request;

import javax.validation.constraints.NotBlank;

import com.example.demo.annotation.DepartmentNameNotExists;

public class DepartmentRequest {
	@NotBlank(message = "Không đưuọc để trống tên phòng ban")
	@DepartmentNameNotExists
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
