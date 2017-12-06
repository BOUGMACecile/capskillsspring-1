package com.capgemini.capskills.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

import com.capgemini.capskills.models.base.BaseEntity;


@Entity
@Table(name = "user")
public class User extends BaseEntity {

	/**
	 * Class Attributes
	 */
	
	@Column(length=255, unique=false)
//	@NotNull
	@Length(min=3, max=255)
	private String firstname;
	
	@Column(length=255, unique=false)
	@Length(min=3, max=255)
	private String lastname;

	@Column(length=255, unique=true)
	@Length(min=10, max=255)
	private String email;
	
	@Column(length=150, unique=false)
	@Length(min=8, max=150)	
	private String password;
	
	@OneToMany
	private List<Skill> skills = new ArrayList<Skill>();

	
	/**
	 * Getters and Setters
	 * @return
	 */
	
	
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public String getLogin() {
//		return this.firstname + "." + this.lastname;
//	}
//
	
	/**
	 * toString method
	 */
	
	@Override
	public String toString() {
		return "User [lastname=" + lastname + ", firstname=" + firstname
				+ ", email=" + email + ", password=" + password + ", getId()="
				+ getId() + "]";
	}

	
}
