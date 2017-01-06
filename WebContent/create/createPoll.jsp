<%@page import="LocationSubsystem.Location"%>
<%@page import="java.util.ArrayList"%>
<%@page import="RouteSubsystem.Route"%>
<%@page import="TravelSubsystem.Travel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Travel travel = (Travel) session.getAttribute("travel");%>
<% Route route = travel.getRoute(); %>
<% ArrayList<Location> locations = route.getLocations();%>
<% session.setAttribute("currentList", locations); %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Richiesta di inserimento</title>
</head>
<body>
	<form action="../CreatePoll" method="post">
		<input type="hidden" value="<%= travel.getId() %>" name="travelID">
		<span>Data inizio visita </span><input type="text" name="startDate"><br>
		<span>Data fine visita </span><input type="text" name="endDate"><br>
		<b>Descrizione</b><br><textarea row="10" col="20" name="descrizione"></textarea><br>
		<input type="submit" value="Crea richiesta">
	</form> 
</body>
</html>