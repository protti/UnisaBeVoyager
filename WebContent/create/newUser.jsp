<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrati</title>
<link href=<%=request.getContextPath().toString()%>/CSS/newuser.css rel="stylesheet" type="text/css">

</head>
<body>

<%@ include file="../navbar.jsp" %>

<% boolean dati_mancanti = false; %>
<% if(request.getAttribute("dati_mancanti") != null){
	dati_mancanti = true;
}%>




<div id="newUser" class="nUser">
<form method="post" action="<%= request.getContextPath() %>/newuser">
				
				<div id="form">
				<p>Registrazione</p><br>
				<span>Email </span><input id="campo" type="email" class="focus" name="email"><br>
				<span>Nome </span><input id="campo" type="text" class="focus" name="name"><br>
				<span>Cognome </span><input id="campo" type="text" class="focus" name="surname"><br>
				<span>Password </span><input id="campo" type="password" class="focus" name="secret"><br>
				<span>Data di nascita </span><input id="campo" type="text" class="focus" name="birth" placeholder="YYYY-MM-DD"><br>
				<span>Username </span><input id="campo" type="text" class="focus" name="username"><br>
				<input value="Registrati!" id="btn" type="submit">				
				</div>
</form>
</div>

<%if(dati_mancanti){ %>
	<b>Inserisci tutti i dati</b>
<%} %>
</body>
</html>