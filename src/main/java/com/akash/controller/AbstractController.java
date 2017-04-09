package com.akash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.akash.service.PersonService;

public class AbstractController {

	@Autowired
	@Qualifier(value = "personService")
	private PersonService personService;
	
	void test() {
		System.out.println("hello");
	}
}