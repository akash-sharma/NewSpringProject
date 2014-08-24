package com.akash.model.domain.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akash.model.domain.Person;
import com.akash.model.domain.dao.PersonDao;

@Repository(value="personDao")
public class PersonDaoImpl implements PersonDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.openSession();
	}
	
	@Override
	public Person get(long id) {
		Session session=getSession();
		session.beginTransaction();
		Person person=(Person)session.get(Person.class, id);
		session.getTransaction().commit();
		session.close();
		return person;
	}

	@Override
	public void save(Person person) {
		Session session=getSession();
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateName(long id, String name) {
		Session session=getSession();
		session.beginTransaction();
		Person person=(Person)session.get(Person.class, id);
		if(person!=null)
		{
			person.setName(name);
			session.saveOrUpdate(person);
		}
		session.getTransaction().commit();
		session.close();
	}
}