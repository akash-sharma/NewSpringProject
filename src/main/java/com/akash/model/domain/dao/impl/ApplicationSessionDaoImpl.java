package com.akash.model.domain.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akash.model.domain.dao.ApplicationSessionDao;

@Repository(value="applicationSessionDao")
public class ApplicationSessionDaoImpl implements ApplicationSessionDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.openSession();
	}
	
	public void persist(Object object) throws HibernateException
	{
		Session session=getSession();
		session.beginTransaction();
		session.persist(object);
		session.getTransaction().commit();
		session.close();
	}
	
	public void persistAll(List objectList) throws HibernateException
	{
		Session session=getSession();
		session.beginTransaction();
		Iterator iterator=objectList.iterator();
		while(iterator.hasNext())
		{
			Object object=iterator.next();
			session.persist(object);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public Object get(Class clazz, Serializable id) throws HibernateException
	{
		return null;
	}
}