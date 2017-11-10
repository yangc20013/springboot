package com.springboot.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.config.AppConfig;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.services.UserServices;
import com.springboot.utils.CollectionUtils;

@Service
@Profile("dev")
public class UserServicesDev implements UserServices {
	@Autowired
	private AppConfig config;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		System.out.println("=======================config==="+config.getName());
		Iterable<User> users = userRepository.findAll();
		return CollectionUtils.getCollection(users);
	}
	
	public User save(User user) {
		System.out.println("=======================config==="+config.getName());
		return this.userRepository.save(user);
	}
	
	public List<User> save(List<User> users){
		System.out.println("=======================config==="+config.getName());
		Iterable<User> us = userRepository.save(users);
		
		List<User> result = new ArrayList<User>();
		for(Iterator<User> itr = us.iterator(); itr.hasNext();) {
			result.add(itr.next());
		}
		return result;
	}

}
