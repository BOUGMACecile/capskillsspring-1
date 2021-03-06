package com.capgemini.capskills.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.capskills.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
	@JsonIgnore()
	private String password;
	
	@NotNull
	private Boolean referent;
	
	@JsonIgnore()
	private String token;
	
	@OneToMany
	private List<User> collaborators;
	
	@ManyToOne
	private User careerManager;
	
	/**
	 * Getters and Setters
	 * @return
	 */
	
	public List<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;

		this.collaborators.forEach(c -> c.setCareerManager(this));
	}
	
	public User getCareerManager() {
		return careerManager;
	}
	
	public void setCareerManager(User careerManager) {
		if (this.careerManager != careerManager) {
			this.careerManager = careerManager;
			
			if (!this.careerManager.getCollaborators().contains(this)) {
				this.careerManager.getCollaborators().add(this);
			}
		}
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
	
	public Boolean getReferent() {
		return referent;
	}

	public void setReferent(Boolean referent) {
		this.referent = referent;
	}

	/**
     * Constructors
     */
    
    public User() {
    	
    }
    
    public User(String firstname, String lastname, String email, String password, Boolean referent) {
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.email = email;
    	this.password = password;
    	this.referent = referent;
    }
	

//	public String getLogin() {
//		return this.firstname + "." + this.lastname;
//	}

	
	@Override
	public String toString() {
		return "User [lastname=" + lastname + ", firstname=" + firstname
				+ ", email=" + email + ", password=" + password + ", referent=" + referent + ", getId()="
				+ getId() + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
