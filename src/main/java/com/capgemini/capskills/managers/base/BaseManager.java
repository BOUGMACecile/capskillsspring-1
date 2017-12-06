package com.capgemini.capskills.managers.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capskills.dao.interfaces.base.IBaseDAO;
import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.base.BaseEntity;

@Service
public abstract class BaseManager<T extends BaseEntity> implements IBaseManager<T> {

	@Autowired
	private IBaseDAO<T> dao;// déclaration d'1 attribut de type base dao

	@Override
	public void create(T item) {
		dao.create(item);
	}

	@Override
	public void delete(T item) {
		dao.delete(item);
	}

	@Override
	public List<T> getAll() {
		return dao.getAll();
	}

	@Override
	public T getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void update(T item) {
		dao.update(item);
	}
}
