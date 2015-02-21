package com.akash.model.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.akash.model.domain.BaseDomain;

public interface BaseDao {

	public EntityManager getEntityManager();

	public void persist(BaseDomain ob);

	public BaseDomain get(Class<? extends BaseDomain> clazz, Long id);

	public BaseDomain merge(BaseDomain ob);
	
	public void persistAll(List<BaseDomain> objectList);
}