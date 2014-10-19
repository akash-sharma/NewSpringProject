package com.akash.model.domain.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akash.model.domain.User;
import com.akash.model.domain.dao.UserDao;

@Repository(value="userDao")
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.openSession();
	}
	
	public User findByUsername(String username)
	{
		User user=null;
		Session session=getSession();
		session.beginTransaction();
		List list=session.createQuery("from Users where username=:username").setParameter("username", username).list();
		if(list.size()>0)
			user=(User)list.get(0);
		session.getTransaction().commit();
		session.close();
		return user;
	}
}