<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LocationSubsystem.Location"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><%=(String) request.getAttribute("nome")%></title>
</head>
<body>
	<p>Nome: <%=(String) request.getAttribute("nome")%></p>
	<br>
	<h4>Descrizione</h4>
	<p><%=(String) request.getAttribute("descrizione")%></p>
	<br>
	<h4>Luoghi</h4>
	<%List<Location> locations = (List<Location>) request.getAttribute("locationList");%>
	<%for(Location location:locations){ %>
	
		<a href=ShowLocation?id=<%=location.getId()%>><%=location.getName()%></a>
	<%} %>
</body>
</html>