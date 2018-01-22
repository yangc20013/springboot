package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.services.LogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {
	@Autowired
	private LogService logService;

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

	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, SecurityContextLogoutHandler handler) {
		logService.write();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         handler.setInvalidateHttpSession(true);
		 handler.setClearAuthentication(true);
		 handler.logout(request, response, auth);
		 
		return "login";
	}
}
