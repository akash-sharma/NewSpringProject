package com.akash.model.domain.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.akash.model.domain.Person;
import com.akash.model.domain.dao.BaseDao;
import com.akash.model.domain.dao.PersonDao;

@Repository(value = "personDao")
public class PersonDaoImpl implements PersonDao {

	@Inject
	@Named("baseDao")
	private BaseDao baseDao;

	private final String GET_ALL_PERSON = "SELECT p FROM Person p";

	@Override
	public void updateName(String id, String name) {

		Person person = (Person) baseDao.get(Person.class, id);
		if (person != null) {
			person.setName(name);
			baseDao.merge(person);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPerson() {
		//SQLQueryImpl -> abstractSessionImpl
		Query query = baseDao.getEntityManager().createQuery(GET_ALL_PERSON, Person.class);
		return query.getResultList();
	}
}