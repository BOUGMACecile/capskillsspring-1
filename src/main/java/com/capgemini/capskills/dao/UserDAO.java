package com.capgemini.capskills.dao;

import java.util.List;


import javax.persistence.Query;


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
		entityManager.remove(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return entityManager.createQuery("SELECT u FROM User u").getResultList();
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
