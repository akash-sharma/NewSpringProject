<h1>Facebook Login using Java</h1>
<h2>Application Main Menu</h2>
<div>Welcome ${fbProfileData.first_name}</div>
<div>email : ${fbProfileData.email}</div>
<div>gender : ${fbProfileData.gender}</div>


<button onclick="getUserProfilePic()">get profile pic</button>


<div id="profilePic"></div>

<script>
function getUserProfilePic() {
	var userId = "${fbProfileData.id}";
	$.ajax({
		  	url: "${pageContext.request.contextPath}/facebook/oauth2/profilePicUrl?userId="+userId,
	})
	.done(function(data) {
		console.log(data);
		var html="<img src='"+data+"' height='50' width='50'>";
		$("#profilePic").html(html);
	});
}

</script>