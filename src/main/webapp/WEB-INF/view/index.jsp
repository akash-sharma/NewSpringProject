<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
		<div id="testTemplate"></div><br>
		
		<%@ include file="footer.html" %>
	
<script>
	$(document).ready(function(){
		updateData();
	});
	
	function updateData()
	{
		$.ajax({
			  url: "${pageContext.request.contextPath}/readPerson/1",
			})
			.done(function(person) {
			  	if(person=="") //when person object is null 
			  		alert("data is null");
			  	else
			  		$("#testTemplate").html(JSON.stringify(person));
			});
	}
</script>
		
	</body>
</html>