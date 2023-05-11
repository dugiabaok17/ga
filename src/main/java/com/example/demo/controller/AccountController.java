package com.example.demo.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.search.AccountFilter;
import com.example.demo.search.AccountSearch;
import com.example.demo.service.impl.AccountServiceImpl;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AccountServiceImpl iAccountService;

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping
	public List<Account> getAllAccounts(@RequestParam(name = "department-name", required = false) String departmentName,
			@RequestParam(name = "user-name", required = false) String userName,
			@RequestParam(name = "department-type", required = false) String departmentType) {

		return iAccountService.getAllAccounts(
				AccountSearch.builder().departmentName(departmentName).username(userName).build(),
				AccountFilter.builder().departmentType(departmentType).build());

	}

	@GetMapping("/find")
	public List<Account> getAllAccount() {
		return accountRepository.findAll();

	}

}
