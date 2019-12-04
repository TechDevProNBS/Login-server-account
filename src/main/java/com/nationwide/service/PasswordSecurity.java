package com.nationwide.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nationwide.util.Hashing;

public class PasswordSecurity {
	
	@Autowired
	private Hashing hashing;

	public String securePassword(String password) {
		return applySalt(10, password);
	}
	
	public boolean checkPassword(String password, Long hashPassword) {
		return securePassword(password).equals(hashPassword);
	}
	
	private String addSalt(String text) {
		String saltText = "$~#}/%*!£&^+-=(>@)<?";
		StringBuilder saltedText = new StringBuilder(text.length()*2);
		for(int i = 0; i < text.length(); i++ ) {
			int saltPosition = i%20;
			saltedText.append(text.substring(i, i+1));
			saltedText.append(saltText.substring(saltPosition, saltPosition+1));
		}
		return saltedText.toString();
	}
	
	private String applySalt(int number, String text) {
		for(int i = 0; i < number; i++) {
			text = hashing.hash(addSalt(text));
		}
		return text;
	}
}
