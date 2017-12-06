package com.capgemini.capskills.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.capskills.models.base.BaseEntity;

@Entity
@Table(name = "grading", uniqueConstraints = { @UniqueConstraint(columnNames = "user_id"),
		@UniqueConstraint(columnNames = "skill_id"), @UniqueConstraint(columnNames = "project_id") })
public class Grading extends BaseEntity {

	// @Id
	@ManyToMany
	private User user;
	// @Id
	@ManyToMany
	private Skill skill;
	// @Id
	@ManyToMany
	private Project project;

	private Integer collaboratorgrade;
	private Integer targetgrade;
	private Integer actualgrade;

	// constructeur referent
	public Grading(Integer collaboratorgrade, Integer targetgrade) {
		super();
		this.collaboratorgrade = collaboratorgrade;
		this.targetgrade = targetgrade;
	}

	// constructeur collaborateur
	public Grading(Integer actualgrade) {
		super();
		this.actualgrade = actualgrade;
	}

	public Grading(Integer collaboratorgrade, Integer targetgrade, Integer actualgrade) {
		super();
		this.collaboratorgrade = collaboratorgrade;
		this.targetgrade = targetgrade;
		this.actualgrade = actualgrade;
	}

	// getter and setter

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

	// Default Constructor
	public Grading() {
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
