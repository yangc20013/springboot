package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Account;
import com.example.demo.repository.AccountRepo;
import com.example.demo.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public void save(Account user) {
		accountRepo.save(user);
		
	}

	@Override
	public Account findById(int id) {
		return accountRepo.findOne(id);
	}


	@Override
	public Account findByUsername(String username) {
		return accountRepo.findByUsername(username);
	}

}
