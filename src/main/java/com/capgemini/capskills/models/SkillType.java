package com.capgemini.capskills.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.capskills.models.base.BaseEntity;


@Entity
@Table(name = "skilltype")
public class SkillType extends BaseEntity {

	@Column(length=150, unique=true)
	@NotNull
	@Length(min=3, max=150)
	private String name;
	
	public SkillType() {
	}
	
	public SkillType(String name) {
		this.setName(name);
	}

	public SkillType(Integer id, String name) {
		super(id);
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}