package com.capgemini.capskills.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.dao.base.BaseDAO;
import com.capgemini.capskills.dao.interfaces.IProjectDAO;
import com.capgemini.capskills.models.Project;

@Transactional
public class ProjectDAO extends BaseDAO<Project> implements IProjectDAO {
	@Override
	public void create(Project item) {
		entityManager.persist(item);
	}

	@Override
	public void delete(Project item) {
		entityManager.remove(item);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<Project> getAll() {
		return entityManager.createQuery("SELECT proj FROM Project proj").getResultList();
	}

	@Override
	public Project getById(Integer id) {
		return entityManager.find(Project.class, id);
	}

	@Override
	public void update(Project item) {
		entityManager.merge(item);
	}

}
