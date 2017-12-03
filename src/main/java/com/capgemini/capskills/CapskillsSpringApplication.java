package com.capgemini.capskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.capskills.dao.ProjectDAO;
import com.capgemini.capskills.dao.SkillDAO;
import com.capgemini.capskills.dao.SkillTypeDAO;
import com.capgemini.capskills.dao.interfaces.base.IBaseDAO;
import com.capgemini.capskills.managers.ProjectManager;
import com.capgemini.capskills.managers.SkillManager;
import com.capgemini.capskills.managers.SkillTypeManager;
import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Project;
import com.capgemini.capskills.models.Skill;
import com.capgemini.capskills.models.SkillType;

@SpringBootApplication
public class CapskillsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapskillsSpringApplication.class, args);
	}
// pour st
	@Bean
	public IBaseManager<SkillType> getSkillTypeManager(){
		return new SkillTypeManager();
	}

	@Bean
	public IBaseDAO<SkillType> getSkillTypeDAO(){
		return new SkillTypeDAO();
	}
	// pour project
	@Bean
	public IBaseManager<Project> getProjectManager(){
		return new ProjectManager();
	}

	@Bean
	public IBaseDAO<Project> getProjectDAO(){
		return new ProjectDAO();
	}
	
	// pour skill
		@Bean
		public IBaseManager<Skill> getSkillManager(){
			return new SkillManager();
		}

		@Bean
		public IBaseDAO<Skill> getSkillDAO(){
			return new SkillDAO();
		}
		
		
	/*	// pour skillProject
				@Bean
				public IBaseManager<SkillProject> getSkillProjectManager(){
					return new SkillProjectManager();
				}

				@Bean
				public IBaseDAO<SkillProject> getSkillProjectDAO(){
					return new SkillProjectDAO();
				}
			*/
		
	/*
	// pour skillproject annuler car on a déclarer une list dans la classe Project
	
	
		@Bean
		public IBaseManager<SkillProject> getSkillProjectManager(){
			return new SkillProjectManager();
		}

		@Bean
		public ISkillProjectDAO getSkillProjectDAO(){
			return new SkillProjectDAO();
		}*/
}
