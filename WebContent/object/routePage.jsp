<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LocationSubsystem.Location"%>
<%@page import="RouteSubsystem.Route"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Route route = (Route) request.getAttribute("route");%>

<title><%=route.getName()%></title>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	<p>Nome: <%=route.getName()%></p>
	<br>
	<h4>Descrizione</h4>
	<p><%=route.getDescription()%></p>
	<br>
	<h4>Luoghi</h4>
	<%List<Location> locations = route.getLocations();%>
	<%for(Location location:locations){ %>
	
		<a href=ShowLocation?id=<%=location.getId()%>><%=location.getName()%></a>
	<%} %>
	
	<%@include file="../feedback/feedbackRoute.jsp"%>
</body>
</html>