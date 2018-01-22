package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.UserLogout;

public interface UserLogoutRepo extends CrudRepository<UserLogout,Integer>{

}
