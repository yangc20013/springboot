package com.demo.redis.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
	@RequestMapping(value="/main", method=RequestMethod.GET)  
    public String main(HttpServletRequest request) {  
        HttpSession session = request.getSession();  
        String sessionId = (String) session.getAttribute("sessionId");  
        if (null != sessionId) { // sessionId不为空  
            System.out.println("main sessionId:" + sessionId);  
            return "main";  
        } else { // sessionId为空  
            return "redirect:/login";  
        }  
    }  
      
      
    @RequestMapping(value="/login", method=RequestMethod.GET)  
    public String login() {  
        return "login";  
    }  
      
    @RequestMapping(value="/doLogin", method=RequestMethod.POST)  
    public String doLogin(HttpServletRequest request) {  
        System.out.println("I do real login here");  
        HttpSession session = request.getSession();  
        String sessionId = UUID.randomUUID().toString();  
        session.setAttribute("sessionId", sessionId);  
        System.out.println("login sessionId:" + sessionId);  
        return "redirect:/main";  
    } 

}
