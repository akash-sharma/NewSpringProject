package com.akash.service;

import com.akash.model.domain.Person;

public interface PersonService
{
	public Person get(long id);
	public void save(Person person);
	public void updateName(long id, String name);
}