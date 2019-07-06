package com.example.postgresdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		String hashPassword = userService.hashPassword(user.getPassword());
		user.setPassword(hashPassword);
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new ResourceNotFoundException("Email already exist");
		}
		return userRepository.save(user);
	}
	
	@PostMapping("/users/login")
	public User login(@RequestBody User user) {
		try {
			User result = userRepository.findByEmail(user.getEmail());
			if (!userService.comparePassword(result.getPassword(), user.getPassword())) {
				throw new ResourceNotFoundException("Invalid credentials");
			}
			return result;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Invalid credentials");
		}
	}

}
