package com.capgemini.capskills.dao;

import java.util.List;

import javax.persistence.Query;


import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.dao.base.BaseDAO;
import com.capgemini.capskills.dao.interfaces.IGradingDAO;
import com.capgemini.capskills.models.Grading;

@Transactional
public class GradingDAO extends BaseDAO<Grading> implements IGradingDAO{

	@Override
	public void create(Grading item) {
		entityManager.persist(item);
	}

	@Override
	public void delete(Grading item) {
		entityManager.detach(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grading> getAll() {
		return entityManager.createQuery("SELECT g FROM Grading g").getResultList();
	}

	@Override
	public Grading getById(Integer id) {
		return entityManager.find(Grading.class, id);
	}

	@Override
	public void update(Grading item) {
		entityManager.merge(item);
	}


	@Override
	public Query createQuery(String qlString) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Grading> select(String query) {
		return  this.entityManager.createQuery(query).getResultList();
	}
}