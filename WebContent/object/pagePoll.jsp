<%@page import="PollSubsystem.Poll"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Poll poll = (Poll) request.getAttribute("poll"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Richiesta</title>
</head>
<body>
	<h4>Descrizione</h4>
	<p><%= poll.getDescription()%></p>
	
	<b>Voti positivi: </b><span><%= poll.getVpositive() %></span><br>
	<b>Voti negativo: </b><span><%= poll.getVnegative() %></span><br><br>
	
	
	<h4>Vota</h4>
	<form action="Vote" method="post">
		<inpu type="hidden" name="pollID" value="<%= poll.getId()%>">
		<span><b>SI </b></span><input name="vote" type="radio" value="1"><br>
		<span><b>NO </b></span><input name="vote" type="radio" value="-1"><br>
		<input type="submit" value="Vota">
	</form>
</body>
</html>