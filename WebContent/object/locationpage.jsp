<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="LocationSubsystem.Location"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Location location = (Location) request.getAttribute("location");%>
<title><%= location.getName() %></title>
</head>
<body>
Nome: <%= location.getName() %>
Descrizione: <%= location.getDescrizione() %>

<a href="deleteLocation.jsp">Elimina Luogo</a>

<%@ include file="../feedback/feedbackLocation.jsp" %>

</body>
</html>