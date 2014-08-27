<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<jsp:include page="header.jsp" />
	
		hello this is index page for new spring project
		
		<c:forEach step="2" var="user" items="${listOfUsers}">
			name : ${user.name}  <br>
			age : ${user.age}  <br>
			salary : ${user.salary}  <br>
		</c:forEach>
		
		<form:form action="addPerson1" commandName="person" method="post">
			name : <input type="text" id="name" name="name"/>
			<form:errors path="name" />
			
			
			age : <input  type="text" id="age" name="id" />
			<form:errors path="age" />
			
			gender : <select name="gender">
                        <option value="" label="Select Gender" />
                        <option value="${com.akash.constant.Gender.MALE}" label="Male" />
                        <option value="${com.akash.constant.Gender.FEMALE}" label="Female" />
                    </select>
			<input type="submit" name="submit">
		</form:form>
		
		<%@ include file="footer.html" %>
		
	</body>
</html>

