package com.example.demo.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.example.demo.entity.Department;
import com.example.demo.service.IDepartmentService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExists, String> {

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Department department = departmentService.getDepartmentByName(value);
		if (department != null) {
			String[] args = new String[] { value };
			String message = messageSource.getMessage("exist.message", args, LocaleContextHolder.getLocale());
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
