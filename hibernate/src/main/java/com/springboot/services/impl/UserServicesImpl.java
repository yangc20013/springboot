package com.springboot.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.services.UserServices;
import com.springboot.utils.CollectionUtils;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		Iterable<User> users = userRepository.findAll();
		return CollectionUtils.getCollection(users);
	}
	
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	public List<User> save(List<User> users){
		Iterable<User> us = userRepository.save(users);
		
		List<User> result = new ArrayList<User>();
		for(Iterator<User> itr = us.iterator(); itr.hasNext();) {
			result.add(itr.next());
		}
		return result;
	}

}
