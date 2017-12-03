package com.capgemini.capskills.configurations.security.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.capgemini.capskills.configurations.security.models.SecurityRole;

/**
 * To realy enable security use thoses command line under mysql
 * insert into security_user VALUES(1,1,"admin","admin");
 * insert into security_role VALUES(1,"admin");
 * insert into users_securityroles VALUES (1,1);
 * Now connect with admin admin
 * @author tactfactory
 *
 */
@Entity
@Table(name="security_user")
public class SecurityUser {

	@Id
	private Integer id;
	private String login;
	private String password;
	private Boolean enable;

	@ManyToMany
	@JoinTable(name = "users_securityroles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<SecurityRole> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the roles
	 */
	public Set<SecurityRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<SecurityRole> roles) {
		this.roles = roles;
	}

	public SecurityUser(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public SecurityUser() {
	}
}

