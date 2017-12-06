package com.capgemini.capskills.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capskills.models.base.BaseEntity;

@Service
@Transactional
public abstract class BaseDAO<T extends BaseEntity> {

	@PersistenceContext
	protected EntityManager entityManager;

	
}
