package com.akash.spring.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import com.akash.service.PersonService;

@Resource
public class MySpringFilter extends GenericFilterBean {

	@Autowired
	private PersonService personService;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
			ServletException {
		
		personService.get("1");
		arg2.doFilter(arg0, arg1);
	}
	

}