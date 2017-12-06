package com.capgemini.capskills.configurations.security.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "security_role")
public class SecurityRole {

	@Id
	private Integer id;
	private String role;

	@ManyToMany
	@JoinTable(name = "users_securityroles",
    	joinColumns = @JoinColumn(name = "role_id"),
    	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<SecurityUser> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the users
	 */
	public Set<SecurityUser> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<SecurityUser> users) {
		this.users = users;
	}

	public SecurityRole(String role) {
		this.role = role;
	}

	public SecurityRole() {
	}
}
