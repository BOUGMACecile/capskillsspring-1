package com.capgemini.capskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.capgemini.capskills.dao.GradingDAO;
//Import dao
import com.capgemini.capskills.dao.ProjectDAO;
import com.capgemini.capskills.dao.SkillDAO;
import com.capgemini.capskills.dao.SkillTypeDAO;
import com.capgemini.capskills.dao.UserDAO;
import com.capgemini.capskills.dao.interfaces.base.IBaseDAO;
import com.capgemini.capskills.managers.GradingManager;
//Import managers
import com.capgemini.capskills.managers.ProjectManager;
import com.capgemini.capskills.managers.SkillManager;
import com.capgemini.capskills.managers.SkillTypeManager;
import com.capgemini.capskills.managers.UserManager;
import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Grading;
//Import entities
import com.capgemini.capskills.models.Project;
import com.capgemini.capskills.models.Skill;
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
	public IBaseManager<Grading> getGradingManager(){
		return new GradingManager();
	}

	@Bean
	public IBaseDAO<Grading> getGradingDAO(){
		return new GradingDAO();
	}


	@Bean
	public UserManager getUserManager() {
		return new UserManager();
	}

	@Bean
	public IBaseDAO<User> getUserDAO() {
		return new UserDAO();
	}
	
	@Bean
	public IBaseManager<Project> getProjectManager(){
		return new ProjectManager();
	}

	@Bean
	public IBaseDAO<Project> getProjectDAO(){
		return new ProjectDAO();
	}
	
	@Bean
	public IBaseManager<Skill> getSkillManager(){
		return new SkillManager();
	}

	@Bean
	public IBaseDAO<Skill> getSkillDAO(){
		return new SkillDAO();
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**/*").allowedOrigins("http://localhost:4200");
            }
        };
    }

}
