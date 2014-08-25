package com.akash.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List listOfUsers=new ArrayList();
		for(int i=0; i<5; i++)
		{
			Map mapData=new HashMap();
			mapData.put("name", "rahul dravid_"+(i+1));
			mapData.put("age", i+20);
			mapData.put("salary", i*10+200);
			listOfUsers.add(mapData);
		}
		model.addAttribute("listOfUsers", listOfUsers);
		return "index";
	}
	
	@RequestMapping(value="/header" , method=RequestMethod.GET)
	public String header(Model model)
	{
		System.out.println("header part executed...");
		model.addAttribute("name", "akash");
		return "header";
	}
	
	@RequestMapping(value="/savePerson", method=RequestMethod.GET)
	@ResponseBody
	public String savePerson(Model model)
	{
		System.out.println("savePerson action");
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
		System.out.println("readPerson action");
		Person person=personService.get(1);
		String response="";
		if(person!=null)
			response="name="+person.getName()+" , version="+person.getVersion()+" , age="+person.getAge();
		else
			response="no person is present with that id";
		return response;
	}
}