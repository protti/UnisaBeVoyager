<%@page import="TravelSubsystem.TravelController"%>
<%@page import="PollSubsystem.Poll"%>
<%@page import="UserSubsystem.RegisteredUser"%>
<%@page import="java.util.List"%>
<%@page import="TravelSubsystem.Travel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%Travel travel = (Travel) request.getAttribute("travel"); %>
<%session.setAttribute("travel", travel); %>
<head>
<link href=<%=request.getContextPath().toString()%>/CSS/page.css rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= travel.getNome() %></title>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	<img src=<%=request.getContextPath().toString()%>/CSS/image/iconatravel.jpg alt="Mia Immagine"> 
	<div id="containerViaggio">	
	<h2><%= travel.getNome() %></h2>
	<%RegisteredUser admin = (RegisteredUser) session.getAttribute("user"); 
	int flag = 0;%>
	<%if(admin.getAuthorization() > 0){ %>
	<a href="deleteTravel.jsp">Cancella questo viaggio</a>
	<br>
	<br>
	<%} %>
	
	<%if(admin.getId() == travel.getCreatoreViaggio().getId() ||
			TravelController.isUserInTravel(admin.getId(), travel.getPartecipantiViaggio())){ %>
		<a href="create/createPoll.jsp">Proponi un luogo</a><br>	
	<%} %>

	<%if(admin.getId() == travel.getCreatoreViaggio().getId() && travel.getType()){ %>
	<form action="CloseTravel" method="post">
		<input type="submit" value="Conferma Viaggio">
	</form>
	<%} %>
	<br><br>
	<span>Data di partenza: </span><%= travel.getStartDate()%>
	<br>
	<br>
	<span>Data di fine: </span><%= travel.getEndDate() %>
	<br>	
	<%if(travel.getType()){ %>
	<b>E' possibile modificare l'itinerario</b>
	<%} %>

	<h4>Creatore Viaggio</h4>
	<a href="showProfile?id=<%=travel.getCreatoreViaggio().getId()%>">
		<%=travel.getCreatoreViaggio().getUsername() %></a>
	<h4>Lista partecipanti viaggio</h4>
	<%if(travel.getPartecipantiViaggio() != null){ %>
	<%if(travel.getPartecipantiViaggio().size() > 0){ %>
	<%List<RegisteredUser> travelers = travel.getPartecipantiViaggio(); %>
	<%for(RegisteredUser traveler: travelers){ %>
	<a href="showProfile?id=<%= traveler.getId()%>"><%=traveler.getNome() %></a>
	<br>
	<br>
	<%
				if(traveler.getId() == admin.getId())
					flag = 1;			
			} %>
	<%} else{ %>
	<b>Non ci sono partecipanti</b>
	<%} %>
	<%} else{ %>
	<b>Non ci sono partecipanti</b>
	<%} %>
	<%
	if(flag != 1 && travel.getCreatoreViaggio().getId() != admin.getId()){	
		session.setAttribute("travel", travel);
		
	%>
	
	<form method="post" action="JoinTravel">
				<input type="submit" class="submit" value="Partecipa viaggio!">	
	</form>	

	<%} %>

	<h4>Itinerario</h4>

	<%if(travel.getRoute() != null){ %>
	<a href="ShowTravelRoute?id=<%= travel.getRoute().getId()%>&idt=<%= travel.getId()%>"><%= travel.getRoute().getName()%></a>
	<%} else{ %>
	<b>Non ci sono itinerari</b>
	<%} %>

	<h4>Richieste inserimento luogo</h4>
	<%List<Poll> polls = travel.getPollList(); %>
	<%if(polls != null){ %>
		<%if(polls.size() > 0){ %>
			<%for(Poll poll:polls){ %>
				<a href="ShowPoll?id=<%= poll.getId()%>"><%=poll.getDescription() %></a>
			<%} %>
		<%} else{ %>
			<b>Non ci sono richieste</b>
		<%} %>
	<%} %>
	</div>
</body>
</html>