package com.nationwide.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nationwide.persistence.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	public Optional<Account> findByUsername(String username);

}
