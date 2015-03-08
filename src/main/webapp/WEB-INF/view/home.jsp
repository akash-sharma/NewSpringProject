<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home for MyMvc</title>
</head>
<body>

	<h3>MyMvc home page</h3>
	<br>
	<button onclick="hitAction3()">hit action3</button><br><br>
	<button onclick="hitAction4()">hit action4</button><br><br>
	<button onclick="hitAction5()">hit action5</button><br><br>
	<button onclick="hitAction6()">hit action6</button><br><br>
	<button onclick="hitAction7()">hit action7</button><br><br>
	<button onclick="hitAction8()">hit action8</button><br><br>
	<button onclick="hitAction9()">hit action9</button><br><br>
	<button onclick="hitAction10()">hit action10</button><br><br>

<script>
	function hitAction3() {
		$.ajax({
				method:'POST',
			  	url: "${pageContext.request.contextPath}/myMvc/action3",
		}).done(function(data) {alert(data);});
	}
	
	function hitAction4() {
		$.ajax({
				method:'POST',
			  	url: "${pageContext.request.contextPath}/myMvc/action4",
		}).done(function(data) {alert(data);});
	}
	
	function hitAction5() {
		$.ajax({
				method:'GET',
			  	url: "${pageContext.request.contextPath}/myMvc/action5",
			}).done(function(data) {alert(data);});
	}
	
	function hitAction6() {
		$.ajax({
				method:'GET',
			  	url: "${pageContext.request.contextPath}/myMvc/action6",
			}).done(function(data) {alert(data);});
	}
	
	function hitAction7() {
		$.ajax({
				method:'POST',
			  	url: "${pageContext.request.contextPath}/myMvc/action7",
		}).done(function(data) {alert(data);});
	}
	
	function hitAction8() {
		$.ajax({
				method:'POST',
			  	url: "${pageContext.request.contextPath}/myMvc/action8",
		}).done(function(data) {alert(data);});
	}
	
	function hitAction9() {
		$.ajax({
				method:'GET',
			  	url: "${pageContext.request.contextPath}/myMvc/action9",
			}).done(function(data) {alert(data);});
	}
	
	function hitAction10() {
		$.ajax({
				method:'GET',
			  	url: "${pageContext.request.contextPath}/myMvc/action10",
			}).done(function(data) {alert(data);});
	}
</script>

</body>
</html>