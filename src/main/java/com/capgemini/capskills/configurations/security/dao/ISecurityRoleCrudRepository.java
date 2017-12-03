package com.capgemini.capskills.configurations.security.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.capskills.configurations.security.models.SecurityRole;

public interface ISecurityRoleCrudRepository extends CrudRepository<SecurityRole, Integer> {

}
