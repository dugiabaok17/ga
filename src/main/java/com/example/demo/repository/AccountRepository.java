package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Short>, JpaSpecificationExecutor<Account> {
	  Optional<Account> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
}
