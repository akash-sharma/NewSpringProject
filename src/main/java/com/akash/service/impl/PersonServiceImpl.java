package com.akash.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akash.hibernate.utils.HibernateUtils;
import com.akash.model.domain.BaseDomain;
import com.akash.model.domain.Person;
import com.akash.model.domain.dao.BaseDao;
import com.akash.model.domain.dao.PersonDao;
import com.akash.service.PersonService;

@Transactional
@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
	@Autowired
	@Qualifier(value = "personDao")
	private PersonDao personDao;

	@Inject
	@Named("baseDao")
	private BaseDao baseDao;

	@Override
	public Person get(String id) {
		return (Person) baseDao.get(Person.class, id);
	}
	
	@Override
	public Person load(String id) {
		
		Person p = (Person) baseDao.load(Person.class, id);
		HibernateUtils.initializeAndUnproxy(p);
		return p;
	}

	@Override
	public void save(Person person) {
		baseDao.persist(person);
	}

	@Override
	public void updateName(String id, String name) {
		personDao.updateName(id, name);
	}

	@Override
	public List<Person> getAllPerson() {
		return personDao.getAllPerson();
	}
	
	@Override
	public BaseDomain merge(BaseDomain ob) {
		return baseDao.merge(ob);
	}
	
	@Override
	public void update(BaseDomain ob) {
		baseDao.update(ob);
	}
	
	@Override
	public Person getPersonById(String id) {
		return personDao.getPersonById(id);
	}
}