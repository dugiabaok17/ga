package com.example.demo.repository;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Filter {
	private String field;
	private QueryOperator operator;
	private String value;
	private List<String> values;
}
