package com.springboot.mybatis.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface UserService {
	JSONArray getAll();
	JSONObject getUserById(int id);
}
