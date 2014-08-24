package com.akash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akash.constant.Gender;
import com.akash.model.domain.Person;
import com.akash.service.PersonService;

@Controller
public class DashboardController
{
	@Autowired
	@Qualifier(value="personService")
	private PersonService personService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String homePage(Model model)
	{
		System.out.println("index page");
		return "index";
	}
	
	@RequestMapping(value="/savePerson", method=RequestMethod.GET)
	@ResponseBody
	public String savePerson(Model model)
	{
		Person person=new Person();
		person.setAge(20);
		person.setNabalik(false);
		person.setName("rahul dravid");
		person.setGender(Gender.Male);
		personService.save(person);
		return "new person is saved";
	}
	
	@RequestMapping(value="/readPerson", method=RequestMethod.GET)
	@ResponseBody
	public String readPerson(Model model)
	{
		Person person=personService.get(1);
		String response="";
		if(person!=null)
			response="name="+person.getName()+" , version="+person.getVersion()+" , age="+person.getAge();
		else
			response="no person is present with that id";
		return response;
	}
}