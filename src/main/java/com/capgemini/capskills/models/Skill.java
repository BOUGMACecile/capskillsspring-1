package com.capgemini.capskills.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
=======
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
>>>>>>> 4e3ee855c3df1f468dea524c11b8be432829cdf5

import com.capgemini.capskills.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


//	@ManyToMany
//	@JoinColumn(name = "user_id")
//	private User user;
	
//	@ManyToMany(mappedBy = "skills")
//	private List<Project> projects;
//	
//	@ManyToMany
//	private List<User> users;
	

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

