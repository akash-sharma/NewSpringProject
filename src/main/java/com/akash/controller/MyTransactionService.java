package com.akash.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.akash.constant.Gender;
import com.akash.model.domain.Person;

@Service
public class MyTransactionService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void requiresNew(int count, String name) {

		if (count == 3) {
			throw new NullPointerException();
		}
		Person person = new Person();
		person.setName(name);
		person.setAge(20 + count);
		person.setNabalik(false);
		person.setGender(Gender.Male);
		entityManager.persist(person);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void required(int count, String name) {

		if (count == 3) {
			throw new NullPointerException();
		}
		Person person = new Person();
		person.setName(name);
		person.setAge(20 + count);
		person.setNabalik(false);
		person.setGender(Gender.Male);
		entityManager.persist(person);
	}

	public void noTransaction(int count, String name) {

		if (count == 3) {
			throw new NullPointerException();
		}
		Person person = new Person();
		person.setName(name);
		person.setAge(20 + count);
		person.setNabalik(false);
		person.setGender(Gender.Male);
		entityManager.persist(person);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void errorInSecondTransaction(int count) {
		
		if(count==2) {
			throw new RuntimeException("");
		}
		Person person = new Person();
		person.setName("errorInSecondTransaction");
		person.setAge(20 + count);
		person.setNabalik(false);
		person.setGender(Gender.Male);
		entityManager.persist(person);
	}
}