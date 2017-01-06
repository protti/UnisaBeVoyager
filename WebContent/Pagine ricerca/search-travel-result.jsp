<%@page import="TravelSubsystem.Travel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%List<Travel> travels = (List<Travel>) request.getAttribute("travels"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati</title>
</head>
<body>
	
	<%if(travels.size() > 0){ %>
		<%for(Travel travel: travels){ %>
			<a href="ShowTravel?id=<%= travel.getId()%>"><%= travel.getNome() %></a>
		<%} %>
	<%} else{%>
		<b>Non ci sono viaggi con quel luogo</b>
	<%} %>
</body>
</html>