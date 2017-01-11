<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href=<%=request.getContextPath().toString()%>/CSS/ricerca.css rel="stylesheet" type="text/css">
<title>Cerca Utente</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<link href=<%=request.getContextPath().toString()%>/CSS/ricerca.css rel="stylesheet" type="text/css">


</head>
<body>
<%@ include file="../navbar.jsp" %>
<div id="form">
	<form>
		<input id="campo" placeholder="Inserisci il soprannome dell'utente ricercato" type="text">
		<input id="btn" type="button"  onclick="searchUser()" value="Cerca">
		
					
	</form>
	<div id="response"></div>
	</div>
	<script type="text/javascript">
	function searchUser() {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		    }
		  };
		  var username = $('#campo').val();
		  xhttp.open("POST", "../searchUser?search="+username, true);
		  xhttp.send();
		}</script>
</body>
</html>