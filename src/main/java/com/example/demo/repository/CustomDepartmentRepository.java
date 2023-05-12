package com.example.demo.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Account;
import com.example.demo.entity.Department;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class CustomDepartmentRepository {
	public static Specification<Department> nameLike(String name) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("departmentName"), "%" + name + "%");
	}

	public static Specification<Department> minMaxWithDepartmentId(Short min, Short max) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("id"), min, max);
	}

	public static Specification<Department> hasNumberOfDepartmentBetween(Long min, Long max) {
		return (root, query, criteriaBuilder) -> {
			Join<Department, Account> join = root.join("accounts", JoinType.LEFT);
			query.groupBy(root.get("id"));
			Predicate having = criteriaBuilder.between(criteriaBuilder.count(join), min, max);
			query.having(having);
			return query.distinct(true).getRestriction();
		};
	}

	public static Specification<Department> minMaxCreatedDateDepartment(LocalDate min, LocalDate max) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdDate"), min, max);
	}

	public static Specification<Department> minMaxYearDepartment(Integer minYear, Integer maxYear) {
		return (root, query, criteriaBuilder) -> {
			Expression<Integer> createdYear = criteriaBuilder.function("year", Integer.class, root.get("createdDate"));
			return criteriaBuilder.between(createdYear, minYear, maxYear);
		};
	}

}
