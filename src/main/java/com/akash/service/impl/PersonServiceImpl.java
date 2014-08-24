package com.akash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akash.model.domain.Person;
import com.akash.model.domain.dao.PersonDao;
import com.akash.service.PersonService;

@Transactional
@Service(value="personService")
public class PersonServiceImpl implements PersonService
{
	@Autowired
	@Qualifier(value="personDao")
	private PersonDao personDao;
	
	@Override
	public Person get(long id) {
		return personDao.get(id);
	}

	@Override
	public void save(Person person) {
		personDao.save(person);
	}

	@Override
	public void updateName(long id, String name) {
		personDao.updateName(id, name);
	}
}