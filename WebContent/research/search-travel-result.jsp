<%@page import="TravelSubsystem.Travel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%List<Travel> travels = (List<Travel>) request.getAttribute("travels"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<%=request.getContextPath().toString()%>/CSS/ricerca.css rel="stylesheet" type="text/css">

<title>Risultati</title>
</head>
<body>
	<jsp:include page="search-travel-location.jsp" />
	
	<div>
	<%if(travels != null){ %>
	<%if(travels.size() > 0){ %>
		<%for(Travel travel: travels){ %>
			<a class="clickDiv " href="ShowTravel?id=<%= travel.getId()%>"><div class="cont"><img src=<%=request.getContextPath().toString()%>/CSS/image/iconatravel.jpg alt="Mia Immagine">
			<span class="intern"><%= travel.getNome() %></span><br>
			<span class="intern">Data Inizio:<%= travel.getStartDate()%></span><br>
			<span class="intern">Data Fine:<%= travel.getEndDate()%></span><br>
			<span class="intern">Creatore: <%= travel.getCreatoreViaggio().getUsername()%></span>
			</div>
			</a>
		<%} %>
	<%} else{%>
		<b>Non ci sono viaggi con quel luogo</b>
	<%} %>
	<%} %>
	</div>
	
	<%if(request.getAttribute("dati_mancanti") != null){ %>
		<b>Inserisci prima l'itinerario da ricercare</b>
	<%} %>
	
</body>
</html>