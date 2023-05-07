package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CustomDepartmentRepository {
    public static Specification<Department> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("departmentName"), "%" + name + "%");
    }

    public static Specification<Department> minMaxWithDepartmentId(Short min, Short max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("id"), min, max);
    }

    public static Specification<Department> hasNumberOfEmployeesBetween(Long min, Long max) {
        return (root, query, criteriaBuilder) -> {
            Join<Department, Account> join = root.join("accounts", JoinType.LEFT);
            query.groupBy(root.get("id"));
            Predicate having = criteriaBuilder.between(
                    criteriaBuilder.count(join),
                    min,
                    max
            );
            query.having(having);
            return query.distinct(true).getRestriction();
        };
    }
}
