package com.capgemini.capskills.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.capskills.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(
		name = "skill",
		uniqueConstraints=@UniqueConstraint(columnNames="id")
	)
public class Skill extends BaseEntity {

	/**
	 * Class Attributes
	 */

	@Column(name = "skill_name")
	private String name;


	@ManyToOne
	@JoinColumn(name = "skill_type_id")
	private SkillType skillType;
	

	/**
	 * Getters & Setters
	 * @param skillType
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonManagedReference
	public SkillType getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	public Skill(String name) {
	super();
	this.name = name;
}

	

	/**
	 * Constructors
	 * @param name
	 */

	public Skill() {
		super();
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", skillType=" + skillType + ", projects=" /*+ projects */+ "]";
	}

}

