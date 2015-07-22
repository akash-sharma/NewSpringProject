package com.akash.service;

import java.util.List;

import com.akash.model.domain.BaseDomain;
import com.akash.model.domain.Person;

public interface PersonService
{
	public Person get(String id);
	public Person load(String id);
	public void save(Person person);
	public void updateName(String id, String name);
	public List<Person> getAllPerson();
	public BaseDomain merge(BaseDomain ob);
	public void update(BaseDomain ob);
}