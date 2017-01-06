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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= travel.getNome() %></title>
</head>
<body>
	<h2><%= travel.getNome() %></h2>


	<%RegisteredUser admin = (RegisteredUser) session.getAttribute("user"); 
	int flag = 0;%>
	<%if(admin.getAuthorization() > 0){ %>
	<a href="deleteTravel.jsp">Cancella questo viaggio</a>
	<br>
	<%} %>
	<%if(admin.getId() != travel.getCreatoreViaggio().getId()){ %>
		<a href="insertUserInTravel.jsp">Partecipa a questo viaggio</a><br>
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

	<h4>Caratteristiche</h4>
	<br>
	<span>Data di partenza: </span>
	<p><%= travel.getStartDate()%></p>
	<br>
	<span>Data di fine: </span>
	<p><%= travel.getEndDate() %></p>
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
	<a href="ShowRoute?id=<%= travel.getRoute().getId()%>"><%= travel.getRoute().getName()%></a>
	<%} else{ %>
	<b>Non ci sono itinerari</b>
	<%} %>
<<<<<<< HEAD
	
	
	
=======


>>>>>>> branch 'master' of https://github.com/protti/UnisaBeVoyager.git
	<h4>Richieste inserimento luogo</h4>
	<%List<Poll> polls = travel.getPollList(); %>
	<%if(polls != null){ %>
<<<<<<< HEAD
		<%if(polls.size() > 0){ %>
			<%for(Poll poll:polls){ %>
				<a href="ShowPoll?id=<%= poll.getId()%>"><%=poll.getDescription() %></a>
			<%} %>
		<%} else{ %>
			<b>Non ci sono richieste</b>
		<%} %>
=======
	<%if(polls.size() > 0){ %>
	<%for(Poll poll:polls){ %>
	<a href="#"><%=poll.getDescription() %></a>
	<%} %>
>>>>>>> branch 'master' of https://github.com/protti/UnisaBeVoyager.git
	<%} else{ %>
	<b>Non ci sono richieste</b>
	<%} %>
	<%} else{ %>
	<b>Non ci sono richieste</b>
	<%}%>
	
</body>
</html>