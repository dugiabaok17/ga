package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Department;
import com.example.demo.response.DepartmentResponse;

public interface IDepartmentService {
	public Page<DepartmentResponse> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy);

	public Department getDepartmentByID(Short id);

	public Department getDepartmentByName(String name);

	public void createDepartment(Department department);

	public void updateDepartment(Department department);

	public void deleteDepartment(short id);

	List<Department> searchWithDepartmentName(String name);

	List<Department> minMaxWithDepartmentId(String min, String max);

	List<Department> numberOfEmployeesBetween(Long minAccount, Long maxAccount);
}
