package com.capgemini.capskills.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.dao.base.BaseDAO;
import com.capgemini.capskills.dao.interfaces.IUserDAO;
import com.capgemini.capskills.models.User;

@Transactional
public class UserDAO extends BaseDAO<User> implements IUserDAO {

	@Override
	public void create(User item) {
		entityManager.persist(item);		
	}

	@Override
	public void delete(User item) {
		entityManager.detach(item);
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void update(User item) {
		entityManager.merge(item);
	}

}
