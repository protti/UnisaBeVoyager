<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%RegisteredUser user = (RegisteredUser) session.getAttribute("user"); %>
<%if(user != null){
	response.sendRedirect("showProfile?id="+user.getId());
}%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/login.css" rel="stylesheet" type="text/css">
<title>Accedi</title>
</head>
<body>
<div class="welcome">
<div id="log">
<form method="post" action="Login">
				<p>Accedi</p><br>
				<span class="email">Username </span><input class="email" type="text" class="focus" name="username"><br>
				<span id="p">Password </span><input class="pass" type="password" class="focus" name="password"><br>
				<input id="submit" type="submit" class="submit" class="focus" value="Accedi!">	
</form>
</div>
<div id="reg">
Oppure <a href="create/newUser.jsp">Registrati!</a>.
</div>
</div>

<%if(request.getAttribute("dati_mancanti") != null){ %>
	<b>Inserisci username e password</b>
<%} %>

<%if(request.getAttribute("wrong") != null){ %>
	<b>Username o password errata</b>
<%} %>
</body>
</html>