package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.exception.DepartmentIdNotExists;
import com.example.demo.request.DepartmentRequest;
import com.example.demo.response.DepartmentResponse;
import com.example.demo.response.ResponseObject;
import com.example.demo.search.DepartmentFilter;
import com.example.demo.service.IDepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<Page<DepartmentResponse>> getAllDepartmentResponse(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		Page<DepartmentResponse> list = departmentService.getAllDepartments(pageNo, pageSize, sortBy);
		return new ResponseEntity<Page<DepartmentResponse>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Department findByIdDepartment(@PathVariable String id,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		Department department = departmentService.getDepartmentByID(Short.valueOf(id));
		if (department == null) {
			throw new DepartmentIdNotExists(400, messageSource.getMessage("found.message", null, locale));
		}
		return department;
	}

	@GetMapping("/name")
	private Department findByNameDepartment(@RequestParam(name = "department-name") String name,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		Department department = departmentService.getDepartmentByName(name);
		if (department == null) {
			throw new DepartmentIdNotExists(400, messageSource.getMessage("found.message", null, locale));
		}
		return department;
	}

	@PostMapping
	private ResponseEntity<Object> createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

		Department department = modelMapper.map(departmentRequest, Department.class);
		department.setCreatedDate(LocalDateTime.now());
		departmentService.createDepartment(department);

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseObject("ok", messageSource.getMessage("success.message", null, locale), 0, department));
	}

	@PutMapping("/{id}")
	private ResponseEntity<Object> updateDepartment(@PathVariable String id,
			@RequestBody @Valid DepartmentRequest departmentRequest,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		Department department = departmentService.getDepartmentByID(Short.valueOf(id));
		if (department == null) {
			throw new DepartmentIdNotExists(400, messageSource.getMessage("found.message", null, locale));
		}
		department.setDepartmentName(departmentRequest.getDepartmentName());
		departmentService.updateDepartment(department);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", messageSource.getMessage("update.message", null, locale), 0, department));
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Object> deleteDepartment(@PathVariable String id,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		Department department = departmentService.getDepartmentByID(Short.valueOf(id));
		if (department == null) {
			throw new DepartmentIdNotExists(400, messageSource.getMessage("found.message", null, locale));
		}
		departmentService.deleteDepartment(Short.valueOf(id));
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", messageSource.getMessage("delete.message", null, locale), 0, department));
	}

	@GetMapping("/search-filter")
	private ResponseEntity<List<Department>> searchWithDepartmentName(
			@RequestParam(name = "department-name", required = false) String name,
			@RequestParam(name = "minId", required = false) Short minId,
			@RequestParam(name = "maxId", required = false) Short maxId,
			@RequestParam(name = "min-total-member", required = false) Long minTotalMember,
			@RequestParam(name = "max-total-member", required = false) Long maxTotalMember,
			@RequestParam(name = "min-created-date", required = false) LocalDate minCreatedDate,
			@RequestParam(name = "max-created-date", required = false) LocalDate maxCreatedDate,
			@RequestParam(name = "min-year", required = false) Integer minYear,
			@RequestParam(name = "max-year", required = false) Integer maxYear) {
		List<Department> listDepartment = departmentService
				.searchFilterWithDepartment(DepartmentFilter.builder().departmentName(name).minId(minId).maxId(maxId)
						.minTotalMember(minTotalMember).maxTotalMember(maxTotalMember).minCreatedDate(minCreatedDate)
						.maxCreatedDate(maxCreatedDate).minYear(minYear).maxYear(maxYear).build());
		if (listDepartment != null) {

			return ResponseEntity.ok(listDepartment);
		}

		return ResponseEntity.badRequest().body(null);

	}
}
