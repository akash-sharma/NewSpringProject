package com.akash.model.domain.dao.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.akash.model.domain.dao.DaoUtils;

@Named("daoUtils")
public class DaoUtilsImpl implements DaoUtils {

	public List<Object> executeQuery(Query q, EntityManager em) {
		return null;
	}

	public Object executeQueryForSingleResult(Query q, EntityManager em) {
		return null;
	}
}