package com.capgemini.capskills.configurations.security.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.capskills.configurations.security.models.SecurityUser;

public interface ISecurityUserCrudRepository extends CrudRepository<SecurityUser, Integer>{
	
	SecurityUser findByLogin(String login);

}
