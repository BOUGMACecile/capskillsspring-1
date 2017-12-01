package com.capgemini.capskills.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.capskills.controllers.SkillTypeController;
import com.capgemini.capskills.controllers.base.BaseController;
import com.capgemini.capskills.models.SkillType;

@Controller
@RequestMapping(SkillTypeController.BASE_URL)
public class SkillTypeController extends BaseController<SkillType> {
	public static final String BASE_URL = "/skilltype";

	protected SkillTypeController() {
		super(SkillType.class);
	}

	@GetMapping("/send")
	public String sendSkillType(){
		return "coucou";
	}
}
