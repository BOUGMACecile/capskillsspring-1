package com.capgemini.capskills.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.capskills.controllers.base.BaseController;
import com.capgemini.capskills.models.Skill;

@Controller
@RequestMapping(SkillController.BASE_URL)
public class SkillController extends BaseController<Skill> {
	public static final String BASE_URL= "/skill";
	
	
	//Constructor
	protected SkillController() {
		super(Skill.class);
	}

	@GetMapping("/send")
	public String sendSkill(){
		return "coucou";
	}

}
