<%@page import="UserSubsystem.RegisteredUser"%>
<%@page import="TravelSubsystem.Travel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<% RegisteredUser user = (RegisteredUser) session.getAttribute("user"); %>
<head>
<link href=<%=request.getContextPath().toString()%>/CSS/page.css rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elimina Viaggio</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div id="container">
	<h4>Vuoi cancellare questo viaggio?</h4>
	<%if(user.getAuthorization() > 0){ %>
	<div id="container">
	<form action="DeleteTravel" method="post">
		<input type="submit" id="btn" value="Cancella">
	</form>
	</div>
	</div>
	<%} else{ %>
		<b>Accesso negato</b>
	<%} %>
</body>
</html>