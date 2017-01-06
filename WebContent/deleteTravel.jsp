<%@page import="UserSubsystem.RegisteredUser"%>
<%@page import="TravelSubsystem.Travel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% RegisteredUser user = (RegisteredUser) session.getAttribute("user"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elimina Viaggio</title>
</head>
<body>

	<h4>Vuoi cancellare questo viaggio?</h4>
	<%if(user.getAuthorization() > 0){ %>
	<form action="DeleteTravel" method="post">
		<input type="submit" value="Cancella">
	</form>
	<%} else{ %>
		<b>Accesso negato</b>
	<%} %>
</body>
</html>