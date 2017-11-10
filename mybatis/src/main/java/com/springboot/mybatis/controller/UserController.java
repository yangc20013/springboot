package com.springboot.mybatis.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.mybatis.services.UserService;

@RestController
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public JSONArray getAll() {
    	JSONArray users = userService.getAll();
        if(users!=null){
            logger.info(users.toJSONString());
        }
        return users;
    }
    @RequestMapping("/getUserById/{id}")
    @ResponseBody
    public JSONObject getUserById(@PathVariable("id") int id) {
    	JSONObject user = userService.getUserById(id);
    	if(user!=null){
    		logger.info(user.toJSONString());
    	}
    	return user;
    }
}
