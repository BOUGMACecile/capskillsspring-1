package com.capgemini.capskills.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.dao.base.BaseDAO;
import com.capgemini.capskills.dao.interfaces.ISkillTypeDAO;
import com.capgemini.capskills.models.SkillType;

@Transactional
public class SkillTypeDAO extends BaseDAO<SkillType> implements ISkillTypeDAO {

	@Override
	public void create(SkillType item) {
		entityManager.persist(item);
	}

	@Override
	public void delete(SkillType item) {
		entityManager.remove(item);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<SkillType> getAll() {
		return entityManager.createQuery("SELECT st FROM SkillType st").getResultList();
	}

	@Override
	public SkillType getById(Integer id) {
		return entityManager.find(SkillType.class, id);
	}

	@Override
	public void update(SkillType item) {
		entityManager.merge(item);
	}

}
