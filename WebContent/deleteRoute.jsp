<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% RegisteredUser user = (RegisteredUser) session.getAttribute("user");%>
<% int idRoute = Integer.parseInt(request.getParameter("idr"));%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elimina itinerario</title>
<link href=<%=request.getContextPath().toString()%>/CSS/page.css rel="stylesheet" type="text/css">

</head>
<body>
	<%@ include file="navbar.jsp" %>
	<div id="container">
	<h4>Vuoi cancellare questo itinerario?</h4>
	<%if(user.getAuthorization() > 0){ %>
	<div id="container">
	<form action="DeleteRoute" method="post">
		<input type="hidden" value="<%= idRoute %>" name="idRoute">
		<input type="submit" id="btn" value="Cancella">
	</form>
	</div>
	</div>
	<%} else{ %>
		<b>Accesso negato</b>
	<%} %>
	
</body>
</html>