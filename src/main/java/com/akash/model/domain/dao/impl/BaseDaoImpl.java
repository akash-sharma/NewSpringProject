package com.akash.model.domain.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.akash.model.domain.BaseDomain;
import com.akash.model.domain.dao.BaseDao;

@Named("baseDao")
public class BaseDaoImpl implements BaseDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		System.out.println("entityManager : "+entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void persist(BaseDomain ob) {
		entityManager.persist(ob);
	}

	@Override
	public BaseDomain merge(BaseDomain ob) {
		return entityManager.merge(ob);
	}

	@Override
	public BaseDomain get(Class<? extends BaseDomain> clazz, Long id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public void persistAll(List<BaseDomain> objectList) {
		Iterator iterator = objectList.iterator();
		while (iterator.hasNext()) {
			BaseDomain object = (BaseDomain) iterator.next();
			persist(object);
		}
	}
}