package com.akash.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akash.hibernate.utils.HibernateUtils;
import com.akash.model.domain.Person;
import com.akash.model.domain.validator.PersonValidator;
import com.akash.service.PersonService;

//	http://www.journaldev.com/2668/spring-mvc-form-validation-example-using-annotation-and-custom-validator-implementation

// http://docs.spring.io/spring-framework/docs/4.0.5.RELEASE/spring-framework-reference/htmlsingle/

//	http://spring.io/blog/2012/10/30/spring-mvc-from-jsp-and-tiles-to-thymeleaf
//	http://viralpatel.net/blogs/tutorial-create-custom-tag-library-taglib-in-jsp/

//	http://www.eclipsezone.com/eclipse/forums/t53459.html
//	http://xstream.codehaus.org/tutorial.html

// important =>   intercept the call of session in db in hibernate

@Controller
public class DashboardController {
	Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	@Qualifier(value = "personService")
	private PersonService personService;

	@Value("${IsValidExample}")
	private boolean isValidExample;

	@Value("${IsValidFile}")
	private boolean isValidFile;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(HttpServletRequest req, HttpServletResponse res,
			Model model) {
		logger.debug("index page:" + isValidExample);
		List listOfUsers = new ArrayList();
		for (int i = 0; i < 5; i++) {
			Map mapData = new HashMap();
			mapData.put("name", "rahul dravid_" + (i + 1));
			mapData.put("age", i + 20);
			mapData.put("salary", i * 10 + 200);
			listOfUsers.add(mapData);
		}
		model.addAttribute("listOfUsers", listOfUsers);
		return "index";
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header(Model model) {
		logger.debug("header part executed...");
		model.addAttribute("name", "akash");
		return "header";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new PersonValidator());
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.GET)
	public String addPerson(Model model) {
		model.addAttribute("person", new Person());
		return "addPerson";
	}

	@RequestMapping(value = "/savePerson", method = RequestMethod.POST)
	public String savePerson(@Validated Person person, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			if (person.getVersion() == null) {
				personService.save(person);
			} else {
				logger.error("XXXXXXXXXXX before merge XXXXXXXXXXXXXX");
//				personService.merge(person);
				personService.update(person);
				logger.error("XXXXXXXXXXX after merge XXXXXXXXXXXXXX");
			}
		}
		return "addPerson";
	}

	@RequestMapping(value = "/getAllPerson", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getAllPerson(Model model) {

		return personService.getAllPerson();
	}

	//NOTE : both load() and get() fires 2 select queries
	//i.e. get with get , load with load, get with load
	@RequestMapping(value = "/editPerson/{personId}", method = RequestMethod.GET)
	public String editPerson(@PathVariable String personId, Model model) {

		logger.error("<<<<<<<<<<<<<<<<<<queries starting here>>>>>>>>>>>>>>>>>>>>>>>>");
		Person person1 = personService.getPersonById(personId);
//		Person person2 = personService.get(personId);
		Person person3 = personService.get(personId);
//		Person person4 = personService.load(personId);
		Person person5 = personService.load(personId);
		logger.error("<<<<<<<<<<<<<<<<<<queries ending here>>>>>>>>>>>>>>>>>>>>>>>>");
		model.addAttribute("person", person5);
		return "editPerson";
	}
}