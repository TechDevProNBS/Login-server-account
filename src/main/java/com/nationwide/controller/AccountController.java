package com.nationwide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nationwide.dto.RequestAccountDto;
import com.nationwide.dto.ResponseAccountDto;
import com.nationwide.mapping.Mapping;
import com.nationwide.persistence.domain.Account;
import com.nationwide.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private Mapping mapping;
	
	@GetMapping("/{username}")
	public Long getIdByUsername(@PathVariable String username) {
		return service.getIdFromUsername(username);
	}
	
	@GetMapping("/id/{id}")
	public String getUsernameById(@PathVariable Long id) {
		return service.getUsernameFromId(id);
	}
	
	@PostMapping("/new")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseAccountDto addUser(@RequestBody RequestAccountDto accountDto) {
		Account account = mapping.map(accountDto, Account.class);
		return mapping.map(service.addUser(account), ResponseAccountDto.class);
	}
	
	@PostMapping
	public ResponseAccountDto authenticateUser(@RequestBody RequestAccountDto accountDto) {
		Account account = mapping.map(accountDto, Account.class);
		return mapping.map(service.authenticate(account), ResponseAccountDto.class);
	}
	
	@PutMapping("/{username}")
	public ResponseAccountDto updateUser(@PathVariable String username, @RequestBody RequestAccountDto accountDto) {
		Account account = mapping.map(accountDto, Account.class);
		return mapping.map(service.updateUser(username, account), ResponseAccountDto.class);
	}
	
	@DeleteMapping("/{username}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseAccountDto deleteUser(@PathVariable String username) {
		return mapping.map(service.deleteUser(username), ResponseAccountDto.class);
	}
	
}
