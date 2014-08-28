<!DOCTYPE html>

<%@ page import="com.akash.constant.Gender" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Person Details</title>
</head>
<body>
	<form:form action="savePerson" commandName="person" method="post">
		name : 	<input type="text" id="name" name="name"
					<c:choose>
					<c:when test="${not empty person}">value="${person.name}"</c:when>
					<c:otherwise>value=""</c:otherwise>
					</c:choose>
				/>
		<font color="red"><form:errors path="name" /></font>
		<br/><br/>
		
		age : 	<input  type="text" id="age" name="age"
					<c:choose> 
					<c:when test="${not empty person}">value="${person.age}"</c:when>
					<c:otherwise>value=""</c:otherwise>
					</c:choose>
				/>
		<font color="red"><form:errors path="age" /></font>
		<br/><br/>
		
		gender : <select name="gender">
                       <option value="" label="Select Gender" />
                       <option value="MALE" label="Male" />
                       <option value="${Gender.FEMALE}" label="Female" />
                   </select>
        <font color="red"><form:errors path="gender" /></font>   
        <br/><br/>
           
		<input type="submit" name="submit">
	</form:form>
</body>
</html>