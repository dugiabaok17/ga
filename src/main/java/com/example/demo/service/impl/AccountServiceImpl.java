package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomAccountRepository;
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

	public List<Account> getAllAccounts(AccountSearch search, AccountFilter accountFilter) {
		Specification<Account> specification = Specification.where(null);

		if (search.getDepartmentName() != null && !search.getDepartmentName().isEmpty()) {
			specification = CustomAccountRepository.searchDepartmentName(search);
		}

		if (search.getUsername() != null && !search.getUsername().isEmpty()) {
			specification = CustomAccountRepository.searchAccountUserName(search);
		}

		if (accountFilter.getDepartmentType() != null && !accountFilter.getDepartmentType().isEmpty()) {
			specification = CustomAccountRepository.filterDepartmentName(accountFilter);
		}

		List<Account> accounts = accountRepository.findAll(specification);
		return accounts;
//		return accounts.stream().map(account -> modelMapper.map(account, AccountResponse.class))
//				.collect(Collectors.toList());
	}

}
