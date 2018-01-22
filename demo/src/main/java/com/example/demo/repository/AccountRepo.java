package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Account;

public interface AccountRepo extends CrudRepository<Account,Integer>{
	Account findByUsername(String username);
}
