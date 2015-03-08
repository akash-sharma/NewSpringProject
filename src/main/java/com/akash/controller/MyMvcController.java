package com.akash.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myMvc")
public class MyMvcController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model map, HttpServletRequest req) {
		return "home";
	}
	
	@RequestMapping(value = "/action1", method = RequestMethod.POST)
	public @ResponseBody String action1(Model map, HttpServletRequest req) {

		return "Sending response body as POST";
	}

	@RequestMapping(value = "/action2", method = RequestMethod.GET)
	public @ResponseBody String action2(Model map, HttpServletRequest req) {

		return "sending response body as GET";
	}
	
	/*****action3 to action6 Redirect******/
	
	//POST to GET
	@RequestMapping(value = "/action3", method = RequestMethod.POST)
	public String action3(Model map, HttpServletRequest req) {

		return "redirect:/myMvc/action2";
	}

	//POST to POST
	@RequestMapping(value = "/action4", method = RequestMethod.POST)
	public String action4(Model map, HttpServletRequest req) {

		//will not execute 
		//Request method 'GET' not supported
		return "redirect:/myMvc/action1";
	}
	
	//GET to POST
	@RequestMapping(value = "/action5", method = RequestMethod.GET)
	public String action5(Model map, HttpServletRequest req) {

		//will not execute 
		//Request method 'GET' not supported
		return "redirect:/myMvc/action1";
	}

	//GET to GET
	@RequestMapping(value = "/action6", method = RequestMethod.GET)
	public String action6(Model map, HttpServletRequest req) {

		return "redirect:/myMvc/action2";
	}
	
	/*****action7 to action10 Forward******/
	
	//POST to GET
	@RequestMapping(value = "/action7", method = RequestMethod.POST)
	public String action7(Model map, HttpServletRequest req) {

		//will not execute 
		//Request method 'POST' not supported
		return "forward:/myMvc/action2";
	}

	//POST to POST
	@RequestMapping(value = "/action8", method = RequestMethod.POST)
	public String action8(Model map, HttpServletRequest req) {

		return "forward:/myMvc/action1";
	}
	
	//GET to POST
	@RequestMapping(value = "/action9", method = RequestMethod.GET)
	public String action9(Model map, HttpServletRequest req) {

		//will not execute 
		//Request method 'GET' not supported
		return "forward:/myMvc/action1";
	}

	//GET to GET
	@RequestMapping(value = "/action10", method = RequestMethod.GET)
	public String action10(Model map, HttpServletRequest req) {

		return "forward:/myMvc/action2";
	}
}
