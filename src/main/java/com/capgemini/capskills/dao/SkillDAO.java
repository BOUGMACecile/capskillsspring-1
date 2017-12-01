package com.capgemini.capskills.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.dao.base.BaseDAO;
import com.capgemini.capskills.dao.interfaces.ISkillDAO;
import com.capgemini.capskills.models.Skill;

@Transactional
public class SkillDAO extends BaseDAO<Skill> implements ISkillDAO {

	@Override
	public void create(Skill item) {
		entityManager.persist(item);		
	}

	@Override
	public void delete(Skill item) {
		entityManager.detach(item);
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill getById(Integer id) {
		return entityManager.find(Skill.class, id);
	}

	@Override
	public void update(Skill item) {
		entityManager.merge(item);
	}

}
