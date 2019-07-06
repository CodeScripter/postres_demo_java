package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgresdemo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Boolean existsByEmail(String email);
	
	public User findByEmail(String email);
}
