package com.akash.model.domain.dao.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Repository;

import com.akash.model.domain.Person;
import com.akash.model.domain.dao.BaseDao;
import com.akash.model.domain.dao.PersonDao;

@Repository(value = "personDao")
public class PersonDaoImpl implements PersonDao {
	
	@Inject
	@Named("baseDao")
	private BaseDao baseDao;

	@Override
	public void updateName(long id, String name) {

		Person person = (Person) baseDao.get(Person.class, id);
		if (person != null) {
			person.setName(name);
			baseDao.merge(person);
		}
	}
}