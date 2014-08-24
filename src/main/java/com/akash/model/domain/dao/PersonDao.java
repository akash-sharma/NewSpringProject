package com.akash.model.domain.dao;

import com.akash.model.domain.Person;

public interface PersonDao
{
	public Person get(long id);
	public void save(Person person);
	public void updateName(long id, String name);
}