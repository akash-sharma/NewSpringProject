package com.akash.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * NOTE : new transaction is not created when method is called from same
	 * class.
	 * 
	 * hence, test1 = test3 = test5
	 * 
	 * test2 = test4 = test6
	 */

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private MyTransactionService myTransactionService;

	/**
	 * 1,2,4,5 will be saved in DB
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

	/**
	 * no entry will be saved in DB
	 */
	@RequestMapping("/test2")
	@Transactional
	@ResponseBody
	public String test2() {

		for (int count = 1; count <= 5; count++) {
			requiresNew(count, "test2");
		}
		return "test2";
	}

	/**
	 * 1,2,4,5 will be saved in DB
	 */
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

	/**
	 * no entry will be saved in DB
	 */
	@RequestMapping("/test4")
	@Transactional
	@ResponseBody
	public String test4() {

		for (int count = 1; count <= 5; count++) {
			required(count, "test4");
		}
		return "test4";
	}

	/**
	 * 1,2,4,5 will be saved in DB
	 */
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

	/**
	 * no entry will be saved in DB
	 */
	@RequestMapping("/test6")
	@Transactional
	@ResponseBody
	public String test6() {

		for (int count = 1; count <= 5; count++) {
			noTransaction(count, "test6");
		}
		return "test6";
	}

	/**
	 * 1,2,4,5 will be saved in DB
	 */
	@RequestMapping("/test7")
	@Transactional
	@ResponseBody
	public String test7() {

		for (int count = 1; count <= 5; count++) {
			try {
				myTransactionService.requiresNew(count, "test7");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "test7";
	}

	/**
	 * 1,2 will be saved in DB
	 */
	@RequestMapping("/test8")
	@Transactional
	@ResponseBody
	public String test8() {

		for (int count = 1; count <= 5; count++) {
			myTransactionService.requiresNew(count, "test8");
		}
		return "test8";
	}

	/**
	 * no entry will be saved in DB
	 */
	@RequestMapping("/test9")
	@Transactional
	@ResponseBody
	public String test9() {

		for (int count = 1; count <= 5; count++) {
			try {
				myTransactionService.required(count, "test9");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "test9";
	}

	/**
	 * no entry will be saved in DB
	 */
	@RequestMapping("/test10")
	@Transactional
	@ResponseBody
	public String test10() {

		for (int count = 1; count <= 5; count++) {
			myTransactionService.required(count, "test10");
		}
		return "test10";
	}

	/**
	 * 1,2,4,5 will be saved in DB
	 */
	@RequestMapping("/test11")
	@Transactional
	@ResponseBody
	public String test11() {

		for (int count = 1; count <= 5; count++) {
			try {
				myTransactionService.noTransaction(count, "test11");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "test11";
	}

	/**
	 * no entry will be saved in DB
	 */
	@RequestMapping("/test12")
	@Transactional
	@ResponseBody
	public String test12() {

		for (int count = 1; count <= 5; count++) {
			myTransactionService.noTransaction(count, "test12");
		}
		return "test12";
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

	@RequestMapping("/errorInSecondTransaction")
	@ResponseBody
	public String errorInSecondTransaction() {

		myTransactionService.errorInSecondTransaction(1);
		myTransactionService.errorInSecondTransaction(2);
		return "errorInSecondTransaction";
	}
}