package com.capgemini.capskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.capskills.dao.SkillTypeDAO;
import com.capgemini.capskills.dao.UserDAO;
import com.capgemini.capskills.dao.interfaces.base.IBaseDAO;
import com.capgemini.capskills.managers.SkillTypeManager;
import com.capgemini.capskills.managers.UserManager;
import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.SkillType;
import com.capgemini.capskills.models.User;

@SpringBootApplication
public class CapskillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapskillsApplication.class, args);
	}
	
	@Bean
	public IBaseManager<SkillType> getSkillTypeManager(){
		return new SkillTypeManager();
	}

	@Bean
	public IBaseDAO<SkillType> getSkillTypeDAO(){
		return new SkillTypeDAO();
	}
	
	@Bean
	public IBaseManager<User> getUserManager(){
		return new UserManager();
	}
	
	@Bean
	public IBaseDAO<User> getUserDAO() {
		return new UserDAO();
	}
	
}
