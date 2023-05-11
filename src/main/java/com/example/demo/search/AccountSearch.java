package com.example.demo.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class AccountSearch {
	private String departmentName;
	private String username;
	
}
