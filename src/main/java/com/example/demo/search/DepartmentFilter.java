package com.example.demo.search;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentFilter {
	private String departmentName;
	private Short minId;
	private Short maxId;
	private Long minTotalMember;
	private Long maxTotalMember;
	private LocalDate minCreatedDate;
	private LocalDate maxCreatedDate;
	private Integer minYear;
	private Integer maxYear;
}
