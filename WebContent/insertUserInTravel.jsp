<%@page import="java.util.List"%>
<%@page import="TravelSubsystem.Travel"%>
<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%RegisteredUser user = (RegisteredUser) session.getAttribute("user"); %>
<%Travel travel = (Travel) session.getAttribute("travel"); %>
<%List<RegisteredUser> users = travel.getPartecipantiViaggio(); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aggiungi</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>
	<%boolean isInTravel = false; %>
	<%if(users != null){ %>
		<%for(RegisteredUser u: users){ %>
			<%if(user.getId() == u.getId()) isInTravel = true; %>
		<%} %>
	<%} %>

	<%if(!isInTravel && user.getId() != travel.getCreatoreViaggio().getId()){ %>
		<h4>Partecipa a questo viaggio</h4>
		<form action="JoinTravel" method="post">
			<input type="submit" value="Partecipa">
		</form>
	<%} else{%>
		<b>Già partecipi a questo viaggio</b>
	<%} %>
</body>
</html>