package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;

public interface IAccountService {
    List<Department> searchWithDepartmentName(String name);
}
