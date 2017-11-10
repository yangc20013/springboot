package com.springboot.mybatis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.mybatis.mapper.UserMapper;
import com.springboot.mybatis.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserMapper mapper;
	
	public JSONArray getAll() {
		
		return mapper.getAll();
	}

	public JSONObject getUserById(int id) {
		return mapper.getUserById(id);
	}

}
