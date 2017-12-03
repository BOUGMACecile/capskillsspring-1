package com.capgemini.capskills.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.capskills.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(
		name = "skill",
		uniqueConstraints=@UniqueConstraint(columnNames="id")
		)

public class Skill extends BaseEntity {

	private String name;
	//private SkillType type;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "skill_type_id")
	private SkillType skillType;


	@JsonManagedReference
	public SkillType getSkillType() {
		return skillType;
	}
	
	@ManyToMany(mappedBy = "skills")
	private List<Project> projects;
	
	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Skill(String name) {
		super();
		this.name = name;
	}

	public Skill( ) {
		super();

	}
	
	
	@Override
	public String toString() {
		return "Skill [name=" + name + ", skillType=" + skillType + ", projects=" + projects + "]";
	}
	@JsonIgnore
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	

	
}




