package com.akash.model.domain.dao;

import java.util.List;

import com.akash.model.domain.Person;

public interface PersonDao {
	public void updateName(String id, String name);
	public List<Person> getAllPerson();
	public Person getPersonById(String id);
}