package com.capgemini.capskills.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.capgemini.capskills.models.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "grading", uniqueConstraints = { @UniqueConstraint(columnNames = "id"),
/*@UniqueConstraint(columnNames = "skill_id")*//*, @UniqueConstraint(columnNames = "project_id") */})

public class Grading extends BaseEntity {

	/**
	 * Class Attributes
	 */
	
	// @Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;
	// @Id
	@ManyToOne
	private Skill skill;
//	// @Id
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Project project;

	@Min(0)
	@Max(5)
	private Integer collaboratorgrade;
	
	@Min(0)
	@Max(5)
	private Integer targetgrade;
	
	@Min(0)
	@Max(5)
	private Integer actualgrade;

	/**
	 * Getters and Setters
	 */

	public Integer getCollaboratorgrade() {
		return collaboratorgrade;
	}

	public void setCollaboratorgrade(Integer collaboratorgrade) {
		this.collaboratorgrade = collaboratorgrade;
	}

	public Integer getTargetgrade() {
		return targetgrade;
	}

	public void setTargetgrade(Integer targetgrade) {
		this.targetgrade = targetgrade;
	}

	public Integer getActualgrade() {
		return actualgrade;
	}

	public void setActualgrade(Integer actualgrade) {
		this.actualgrade = actualgrade;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
	
	/**
	 * Constructors
	 */
	
	// Default Constructor
	public Grading() {
	}
	
	// constructeur collaborateur
	public Grading(Integer actualgrade) {
		super();
		this.actualgrade = actualgrade;
	}
	
	// constructeur referent
	public Grading(Integer collaboratorgrade, Integer targetgrade) {
		super();
		this.collaboratorgrade = collaboratorgrade;
		this.targetgrade = targetgrade;
	}

	public Grading(Integer collaboratorgrade, Integer targetgrade, Integer actualgrade) {
		super();
		this.collaboratorgrade = collaboratorgrade;
		this.targetgrade = targetgrade;
		this.actualgrade = actualgrade;
	}

}
