package com.springboot.mybatis.mapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface UserMapper {
	JSONArray getAll();

	JSONObject getUserById(Integer id);
}
