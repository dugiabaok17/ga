package com.example.demo.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import com.example.demo.search.AccountFilter;
import com.example.demo.search.AccountSearch;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class CustomAccountRepository {
	public static Specification<Account> searchDepartmentName(AccountSearch search) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.lower(root.get("department").get("departmentName")),
				"%" + search.getDepartmentName().toLowerCase() + "%");
	}

	public static Specification<Account> searchAccountUserName(AccountSearch search) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.like(criteriaBuilder.lower(root.get("username")), "%" + search.getUsername().toLowerCase() + "%");
	}

	public static Specification<Account> filterDepartmentName(AccountFilter search) {
		return (root, query, cb) -> {
			Join<Account, Department> departmentJoin = root.join("department", JoinType.RIGHT);
			return cb.equal(departmentJoin.get("departmentName"), search.getDepartmentType());
		};
	}

}
