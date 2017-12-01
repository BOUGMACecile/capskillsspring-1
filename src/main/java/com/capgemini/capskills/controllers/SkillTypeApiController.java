package com.capgemini.capskills.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.SkillType;

@RestController
@RequestMapping("skill_types")
public class SkillTypeApiController {
	
	@Autowired
	private IBaseManager<SkillType> manager;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<SkillType> getAll() {
		return this.manager.getAll();
	}
	
	@RequestMapping("/(id)")
	public SkillType get(@PathVariable Integer id) {
		return this.manager.getById(id);
	}
	
	@RequestMapping("/fill")
	public List<SkillType> fill() {
		for (String name : Arrays.asList("Soft", "Technical", "Super", "Mega", "Ultra")) {
			SkillType type = new SkillType();
			this.manager.create(type);
		}
		
		return this.getAll();
	}

}