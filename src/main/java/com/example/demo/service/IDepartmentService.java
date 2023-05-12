package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Department;
import com.example.demo.response.DepartmentResponse;
import com.example.demo.search.DepartmentFilter;

public interface IDepartmentService {
	public Page<DepartmentResponse> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy);

	public Department getDepartmentByID(Short id);

	public Department getDepartmentByName(String name);

	public void createDepartment(Department department);

	public void updateDepartment(Department department);

	public void deleteDepartment(short id);

	List<Department> searchFilterWithDepartment(DepartmentFilter department);
}
