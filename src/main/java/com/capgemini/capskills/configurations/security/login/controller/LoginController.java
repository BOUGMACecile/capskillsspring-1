package com.capgemini.capskills.configurations.security.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	private static final String LOGIN = "login/login";
	
	public String loginGet(HttpSession session) {
		return LOGIN;
	}	
}
