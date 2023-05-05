package com.example.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Department;
import com.example.demo.service.IDepartmentService;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExists,String>{
	
	@Autowired
	private IDepartmentService departmentService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Department department = departmentService.getDepartmentByName(value);
		if (department != null) {
			return false;
		}
		return true;
	}

}
