<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cerca</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="../navbar.jsp" %>

	<form>
		<input id="user" type="text">
		<input type="button" onclick="searchUser()" value="Cerca">
	</form>
	<div id="response"></div>
	<script type="text/javascript">
	function searchUser() {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		    }
		  };
		  var username = $('#user').val();
		  xhttp.open("POST", "../searchUser?search="+username, true);
		  xhttp.send();
		}</script>
</body>
</html>