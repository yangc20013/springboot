package com.springboot.repository;

import org.springframework.stereotype.Repository;

import com.springboot.entity.User;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface  UserRepository extends CrudRepository<User, Long> {

}
