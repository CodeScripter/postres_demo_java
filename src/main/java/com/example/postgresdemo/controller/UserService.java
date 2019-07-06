package com.example.postgresdemo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	public String hashPassword(String password) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
		return bcrypt.encode(password);
	}
	
	public Boolean comparePassword(String hashPassword, String password) {
		return new BCryptPasswordEncoder(10).matches(password, hashPassword);
	}
}
