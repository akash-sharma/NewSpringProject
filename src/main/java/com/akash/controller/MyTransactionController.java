package com.akash.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akash.constant.Gender;
import com.akash.model.domain.Person;


@Controller
@RequestMapping("/MyTransaction")
public class MyTransactionController {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 */
	@RequestMapping("/test1")
	@Transactional
	@ResponseBody
	public String test1() {

		for (int count = 1; count <= 5; count++) {
			try {
				requiresNew(count, "test1");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "test1";
	}

	@RequestMapping("/test2")
	@Transactional
	@ResponseBody
	public String test2() {

		for (int count = 1; count <= 5; count++) {
			requiresNew(count, "test2");
		}
		return "test2";
	}

	@RequestMapping("/test3")
	@Transactional
	@ResponseBody
	public String test3() {

		for (int count = 1; count <= 5; count++) {
			try {
				required(count, "test3");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "test3";
	}

	@RequestMapping("/test4")
	@Transactional
	@ResponseBody
	public String test4() {

		for (int count = 1; count <= 5; count++) {
			required(count, "test4");
		}
		return "test4";
	}

	@RequestMapping("/test5")
	@Transactional
	@ResponseBody
	public String test5() {

		for (int count = 1; count <= 5; count++) {
			try {
				noTransaction(count, "test5");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "test5";
	}

	@RequestMapping("/test6")
	@Transactional
	@ResponseBody
	public String test6() {

		for (int count = 1; count <= 5; count++) {
			noTransaction(count, "test6");
		}
		return "test6";
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void requiresNew(int count, String name) {

		if (count == 3) {
			throw new NullPointerException();
		}
		Person person = new Person();
		person.setName(name);
		person.setAge(20 + count);
		person.setIsNabalik(false);
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
		person.setIsNabalik(false);
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
		person.setIsNabalik(false);
		person.setGender(Gender.Male);
		entityManager.persist(person);
	}
}