<!DOCTYPE html>

<%@ page import="com.akash.constant.Gender"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="mycustom"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Person Details</title>
</head>
<body>
	<form:form action="savePerson" commandName="person" method="post">
		name :
		<%-- <form:input path="name"/> --%>
		<mycustom:input path="name" />
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

	<style>
.errorColor {
	color: red;
}
</style>

	<script type="text/javascript">
$(document).ready(function(){
	clearForm();
	getAllPerson();
});

function clearForm() {
	$("#person").find("input[type=text], input[type=password]").val("");
	$("#gender").val("");
}

function getAllPerson() {
	var editPersonUrl = "${pageContext.request.contextPath}/editPerson";
	$.ajax({
			method:'GET',
		  	url: "${pageContext.request.contextPath}/getAllPerson",
	}).done(function(data) {
		var html="";
		for (var counter = 0; counter < data.length; counter++) {
			html=html+data[counter].name+"     ---     "+data[counter].age+"     ---     "+data[counter].gender+"  --- ";
			var editUrl = editPersonUrl+"/"+data[counter].id;
			html=html+"<a href='"+editUrl+"'>EDIT PERSON</a><br>" ;
		}
		$("#persons").html(html);
	});
}
</script>

	<br><br><br>

	<div id="personlist">
		<div id="heading">
			name &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; age
			&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; gender <br>
			<div id="persons" />
		</div>
	</div>

</body>
</html>