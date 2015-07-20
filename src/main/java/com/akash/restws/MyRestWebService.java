package com.akash.restws;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping(value = "/rest")
public class MyRestWebService {

	@RequestMapping(value = "/getMessage", method = RequestMethod.POST, headers = {
			"Accept=application/json", "Content-type=application/json" })
	public @ResponseBody Demo1Response getMessage(
			@RequestBody Demo1Request req, HttpServletRequest httpReq) {

		Enumeration headerNames = httpReq.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = httpReq.getHeader(key);
		}
		
		Demo1Response res = new Demo1Response();
		res.setId(req.getId());
		res.setName(req.getName());
		res.setPhone("1111111111");
		res.setSalary(1234);
		return res;
	}
}