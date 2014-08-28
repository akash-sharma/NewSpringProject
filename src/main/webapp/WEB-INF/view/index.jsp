<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<jsp:include page="header.jsp" />
	
		hello this is index page for new spring project
		<br/><br/>
		<a href="${pageContext.request.contextPath}/addPerson">add new person</a>
		<br/><br/>
		
		Below is just a loop iteration<br/>
		<c:forEach step="2" var="user" items="${listOfUsers}">
			name : ${user.name}  <br>
			age : ${user.age}  <br>
			salary : ${user.salary}  <br>
		</c:forEach>
		<br/>
		<%@ include file="footer.html" %>
		
	</body>
</html>