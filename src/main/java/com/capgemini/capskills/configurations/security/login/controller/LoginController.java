package com.capgemini.capskills.configurations.security.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private static final String LOGIN = "login/login";

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {
		return LOGIN;
	}
}
