package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.response.AccountResponse;
import com.example.demo.search.AccountFilter;
import com.example.demo.search.AccountSearch;

@Service
public class AccountServiceImpl {
	private final AccountRepository accountRepository;
	private final ModelMapper modelMapper;

	public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	public List<Account> getAllAccounts(AccountSearch search) {
		Specification<Account> specification = Specification.where(null);

		if (search.getDepartmentName() != null && !search.getDepartmentName().isEmpty()) {
			specification = specification.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
					criteriaBuilder.lower(root.get("department").get("departmentName")),
					"%" + search.getDepartmentName().toLowerCase() + "%"));
		}

		if (search.getUsername() != null && !search.getUsername().isEmpty()) {
			specification = specification.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
					.like(criteriaBuilder.lower(root.get("username")), "%" + search.getUsername().toLowerCase() + "%"));
		}

		

		List<Account> accounts = accountRepository.findAll(specification);
		return accounts;
//		return accounts.stream().map(account -> modelMapper.map(account, AccountResponse.class))
//				.collect(Collectors.toList());
	}

}
