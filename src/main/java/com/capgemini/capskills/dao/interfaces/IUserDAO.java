package com.capgemini.capskills.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.capgemini.capskills.dao.interfaces.base.IBaseDAO;
import com.capgemini.capskills.models.User;

@Repository
public interface IUserDAO extends IBaseDAO<User> {
	public User getByEmail(String email);
}
