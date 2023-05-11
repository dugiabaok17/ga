package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DepartmentId")
	private Short id;
	
	@Column(name = "DepartmentName")
	private String departmentName;
	
	@Column
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "department",fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Account> accounts;

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", departmentName='" + departmentName + '\'' +
				'}';
	}
}
