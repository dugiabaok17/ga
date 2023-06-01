package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.repository.CustomAccountRepository;
import com.example.demo.repository.CustomDepartmentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.response.DepartmentResponse;
import com.example.demo.search.DepartmentFilter;
import com.example.demo.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<DepartmentResponse> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

		Page<Department> pagedResult = departmentRepository.findAll(paging);
		Page<DepartmentResponse> departmentResponses = pagedResult
				.map(department -> modelMapper.map(department, DepartmentResponse.class));
		return departmentResponses;
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
		if (department == null) {
			return null;
		}
		return department;
	}

	@Override
	public void createDepartment(Department department) {
		departmentRepository.save(department);
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
	public List<Department> searchFilterWithDepartment(DepartmentFilter department) {
		Specification<Department> specification = Specification.where(null);
		if (department.getDepartmentName() != null && !department.getDepartmentName().isEmpty()) {
			specification = specification.and(CustomDepartmentRepository.nameLike(department.getDepartmentName()));
		}

		if (department.getMinId() != null && department.getMaxId() != null) {

			specification = specification.and(
					CustomDepartmentRepository.minMaxWithDepartmentId(department.getMinId(), department.getMaxId()));
		}

		if (department.getMinTotalMember() != null && department.getMaxTotalMember() != null) {
			specification = specification.and(CustomDepartmentRepository
					.hasNumberOfDepartmentBetween(department.getMinTotalMember(), department.getMaxTotalMember()));
		}

		if (department.getMinCreatedDate() != null && department.getMaxCreatedDate() != null) {

			specification = specification.and(CustomDepartmentRepository
					.minMaxCreatedDateDepartment(department.getMinCreatedDate(), department.getMaxCreatedDate()));
		}

		if (department.getMinYear() != null && department.getMaxYear() != null) {

			specification = specification.and(
					CustomDepartmentRepository.minMaxYearDepartment(department.getMinYear(), department.getMaxYear()));
		}

		List<Department> departments = departmentRepository.findAll(specification);
		return departments;
	}

}
