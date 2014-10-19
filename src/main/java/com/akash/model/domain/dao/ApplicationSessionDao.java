package com.akash.model.domain.dao;

import java.io.Serializable;
import java.util.List;

public interface ApplicationSessionDao
{
	void persist(Object object);
	Object get(Class clazz, Serializable id);
	void persistAll(List objectList);
}