package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserLogout;
import com.example.demo.repository.UserLogoutRepo;
import com.example.demo.services.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private UserLogoutRepo logRepo;

	@Override
	public void write() {
		UserLogout log = new UserLogout();
		log.setUsername("test");
		logRepo.save(log);		
	}

}
