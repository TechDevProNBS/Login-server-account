package com.nationwide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nationwide.persistence.domain.Account;
import com.nationwide.persistence.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordSecurity passwordSecurity;
	
	public Long getIdFromUsername(String username) {
		return getAccountFromUsername(username).getId();
	}
	
	public String getUsernameFromId(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found")).getUsername();
	}
	
	public Account addUser(Account user) {
		user.setPassword(passwordSecurity.securePassword(user.getPassword()));
		if(accountRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exists");
		} else {
			return accountRepository.save(user);			
		}
	}
	
	private Account getAccountFromUsername(String username) {
		return accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Account username not found"));
	}
	
	private boolean checkAvailable(String username) {
		return !(accountRepository.findByUsername(username).isPresent());
	}
	
	public Account updateUser(String currentUsername, Account newInfo) {
		Account foundUser = getAccountFromUsername(currentUsername);
		if(!(currentUsername.equals(newInfo.getUsername())) && checkAvailable(newInfo.getUsername())) {
			foundUser.setUsername(newInfo.getUsername());
		} else {	
			throw new RuntimeException("Username in use");
		}
		foundUser.setPassword(passwordSecurity.securePassword(newInfo.getPassword()));
		accountRepository.flush();
		return foundUser;
	}
	
	public Account deleteUser(String username) {
		Account foundUser = getAccountFromUsername(username);
		accountRepository.delete(foundUser);
		return foundUser;
	}

	public Account authenticate(Account user) {
		Account foundUser = getAccountFromUsername(user.getUsername());
		System.out.println(foundUser);
		if (passwordSecurity.securePassword(user.getPassword()).equals(foundUser.getPassword())) {
			return user;
		} else {
			throw new RuntimeException("Unauthorised");
		}
	}

}
