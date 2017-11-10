package com.springboot.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.entity.User;
import com.springboot.services.UserServices;


@Controller
public class PageContorller {
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/save", method={RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public User save(User user) {
        return this.userServices.save(user);
    }
    @RequestMapping("/get")
    @ResponseBody
    public List<User> get() {
    	return this.userServices.findAll();
    }
    
}
