package com.capgemini.capskills.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.capskills.controllers.base.BaseController;
import com.capgemini.capskills.models.User;

@Controller
@RequestMapping(UserController.BASE_URL)
public class UserController extends BaseController<User>{

	public static final String BASE_URL = "/user";
	
	protected UserController() {
		super(User.class);
	}

}
