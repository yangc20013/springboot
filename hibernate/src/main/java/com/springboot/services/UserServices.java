package com.springboot.services;

import java.util.List;

import com.springboot.entity.User;

public interface UserServices {
	List<User> findAll();
	
	User save(User user);
	
	List<User> save(List<User> users);
}
