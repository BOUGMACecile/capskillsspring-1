package com.capgemini.capskills.managers.base;

import com.capgemini.capskills.managers.interfaces.IUserManager;
import com.capgemini.capskills.models.User;

public class UserManager extends BaseManager<User> implements IUserManager  {

	public User getByEmail(String email) {
		return (User) this.getDao().createQuery("SELECT u FROM User u WHERE u.email=:email").setParameter("email", email).setMaxResults(1).getSingleResult();
	}

}
