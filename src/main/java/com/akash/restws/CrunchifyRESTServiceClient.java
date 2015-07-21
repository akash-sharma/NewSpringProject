package com.akash.restws;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


//http://stackoverflow.com/questions/14423638/which-jar-file-contains-the-jsonprocessingexception-class
//http://howtodoinjava.com/2015/02/20/spring-restful-client-resttemplate-example/
//http://stackoverflow.com/questions/15739838/post-request-with-spring-resttemplate-badrequest-400

//https://malalanayake.wordpress.com/2014/06/27/spring-security-on-rest-api/
//http://stackoverflow.com/questions/10826293/restful-authentication-via-spring
public class CrunchifyRESTServiceClient {

	public static void main(String args[]) {
		final String uri = "http://localhost:8080/rest/getMessage";

		Demo1Request req = new Demo1Request();
		req.setId("12");
		req.setName("aaaa");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Collections.singletonList(new MediaType(
				"application", "json")));

		// Note the body object as first parameter!
		HttpEntity<Demo1Request> requestEntity = new HttpEntity<Demo1Request>(
				req, requestHeaders);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		ResponseEntity<Demo1Response> model = restTemplate.exchange(uri,
				HttpMethod.POST, requestEntity, Demo1Response.class);
		Demo1Response res = model.getBody();
		System.out.println(res.getPhone());
	}
}