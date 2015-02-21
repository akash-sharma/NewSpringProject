package com.akash.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myRest")
public class MyRestController {
	
//	Rest ws security => http://docs.oracle.com/cd/E24329_01/web.1211/e24983/toc.htm
//	jax ws http://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html
//	http://stackoverflow.com/questions/4817643/how-to-secure-restful-web-services

	@RequestMapping(value = "/myAction1/{param1}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Abc> myAction1(
			@PathVariable("param1") String param1) {

		Abc ob = new Abc();
		ob.setAge(20);
		ob.setName("obama-" + param1);
		List<Abc> listOfAbc = new ArrayList<Abc>();
		listOfAbc.add(ob);
		return listOfAbc;
	}

	/*
	 * curl -i -X GET -H "Accept:application/json"
	 * http://localhost:8080/myRest/myAction1/123
	 */

	@RequestMapping(value = "/myAction2", method = RequestMethod.POST, headers = {
			"Accept=application/json", "Content-type=application/json" })
	public @ResponseBody List<Abc> myAction2(@RequestBody AbcWrapper wrapper) {

		Abc ob = new Abc();
		ob.setAge(wrapper.getListOfAbc().size());
		ob.setName("obama");
		List<Abc> listOfAbc = new ArrayList<Abc>();
		listOfAbc.add(ob);
		return listOfAbc;
	}

	/*
	 * curl -i -X POST -H "Accept: application/json" -H
	 * "Content-Type: application/json" http://localhost:8080/myRest/myAction2
	 * -d "{\"listOfAbc\":[{\"age\":20,\"name\":\"akash\"}]}"
	 */

	@RequestMapping(value = "/myAction3", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Abc> myAction3(@RequestBody AbcWrapper wrapper) {

		Abc ob = new Abc();
		ob.setAge(wrapper.getListOfAbc().size());
		ob.setName("obama");
		List<Abc> listOfAbc = new ArrayList<Abc>();
		listOfAbc.add(ob);
		return listOfAbc;
	}
	/*
	 * curl -i -X POST -H "Accept: application/json" -H
	 * "Content-Type: application/json" http://localhost:8080/myRest/myAction3
	 * -d "{\"listOfAbc\":[{\"age\":20,\"name\":\"akash\"}]}"
	 */
}

class Abc {
	private int age;
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class AbcWrapper {
	List<Abc> listOfAbc;

	public List<Abc> getListOfAbc() {
		return listOfAbc;
	}

	public void setListOfAbc(List<Abc> listOfAbc) {
		this.listOfAbc = listOfAbc;
	}
}