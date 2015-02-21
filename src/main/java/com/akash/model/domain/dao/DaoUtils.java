package com.akash.model.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface DaoUtils {

	public List<Object> executeQuery(Query q, EntityManager em);

	public Object executeQueryForSingleResult(Query q, EntityManager em);
}