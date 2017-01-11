<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrati</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>

<% boolean dati_mancanti = false; %>
<% if(request.getAttribute("dati_mancanti") != null){
	dati_mancanti = true;
}%>




<div id="newUser" class="nUser">
<form method="post" action="<%= request.getContextPath() %>/newuser">
				<p>Registrazione</p><br>
				<span>Email </span><input type="email" class="focus" name="email"><br>
				<span>Nome </span><input type="text" class="focus" name="name"><br>
				<span>Cognome </span><input type="text" class="focus" name="surname"><br>
				<span>Password </span><input type="password" class="focus" name="secret"><br>
				<span>Data di nascita </span><input type="text" class="focus" name="birth" placeholder="YYYY-MM-DD"><br>
				<span>Username </span><input type="text" class="focus" name="username"><br>
				<input type="submit" class="submit" class="focus" value="Registrati!">	
</form>
</div>

<%if(dati_mancanti){ %>
	<b>Inserisci tutti i dati</b>
<%} %>
</body>
</html>