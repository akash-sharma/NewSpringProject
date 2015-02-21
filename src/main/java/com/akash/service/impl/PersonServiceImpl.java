package com.akash.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akash.model.domain.Person;
import com.akash.model.domain.dao.BaseDao;
import com.akash.model.domain.dao.PersonDao;
import com.akash.service.PersonService;

@Transactional
@Service(value="personService")
public class PersonServiceImpl implements PersonService
{
	@Autowired
	@Qualifier(value="personDao")
	private PersonDao personDao;
	
	@Inject
	@Named("baseDao")
	private BaseDao baseDao;
	
	@Override
	public Person get(long id) {
		return (Person) baseDao.get(Person.class, id);
	}

	@Override
	public void save(Person person) {
		baseDao.persist(person);
	}

	@Override
	public void updateName(long id, String name) {
		personDao.updateName(id, name);
	}
}