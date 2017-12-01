package com.capgemini.capskills.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.capskills.models.base.BaseEntity;


@Entity
@Table(name="skill")
public class Skill extends BaseEntity {

	@Column(length=150, unique=true)
	@NotNull
	@Length(min=3, max=150)
	
	//Attributes
	private String name;

	//Specific Constructor
	public Skill(String name) {
		super();
		this.name = name;
	}

	//Default Constructor
	public Skill(){}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Skill [name=" + this.name + ", getId()="+ getId() +"]";
	}

}
