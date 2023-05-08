package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.CustomDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;


	@Override
	public Page<Department> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

		Page<Department> pagedResult = departmentRepository.findAll(paging);

		return pagedResult;
	}

	@Override
	public Department getDepartmentByID(Short id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			return null;
		}
		return department.get();
	}

	@Override
	public Department getDepartmentByName(String name) {
		Department department = departmentRepository.findByDepartmentName(name);
		if (department ==  null) {
			return null;
		}
		return department;
	}

	@Override
	public void createDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public void updateDepartment(short id, String newName) {
		

	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepository.save(department);

	}

	@Override
	public void deleteDepartment(short id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public boolean isDepartmentExistsByID(short id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDepartmentExistsByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Department> searchWithDepartmentName(String name) {
		return departmentRepository.findAll(CustomDepartmentRepository.nameLike(name));
	}

	@Override
	public List<Department> minMaxWithDepartmentId(String min, String max) {
		return departmentRepository.findAll(CustomDepartmentRepository.minMaxWithDepartmentId(Short.valueOf(min),Short.valueOf(max)));
	}

	@Override
	public List<Department> numberOfEmployeesBetween(Long minAccount, Long maxAccount) {
		return departmentRepository.findAll(CustomDepartmentRepository.hasNumberOfDepartmentBetween(minAccount,maxAccount));
	}

}
