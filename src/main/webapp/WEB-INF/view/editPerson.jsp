<!DOCTYPE html>

<%@ page import="com.akash.constant.Gender"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Person Details</title>
</head>
<body>
	<form:form action="../savePerson" commandName="person" method="post">
		<form:input type="hidden" path="version" />
		<form:input type="hidden" path="id" />
		name :
		 <form:input path="name"/>
		
		<form:errors class="errorColor" path="name" />
		<br />
		<br />
		
		age :
		<form:input path="age" />
		<form:errors path="age" class="errorColor" />
		<br />
		<br />
		
		gender :  
		<form:select path="gender">
			<form:option value="" label="--Please Select Gender--" />
			<form:options items="${Gender.values()}" />
		</form:select>
		<form:errors path="gender" class="errorColor" />
		<br />
		<br />

		<input type="submit" name="submit">
	</form:form>

</body>
</html>