package com.capgemini.capskills.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.models.base.BaseEntity;

@Service
@Transactional
public abstract class BaseDAO<T extends BaseEntity> {

	@PersistenceContext
	protected EntityManager entityManager;

	/** Creates a custom query. */
	public Query createQuery(String qlString) {
		 return this.entityManager.createQuery(qlString);
	}
}
