package com.example.demo.services;

import com.example.demo.models.Account;

public interface AccountService {
	void save(Account user);
	Account findById(int id);
	Account findByUsername(String username);
}
