<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="UserSubsystem.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%//if(session.getAttribute("user") != null){ 

RegisteredUser userOne = (RegisteredUser) session.getAttribute("user");
RegisteredUser user = (RegisteredUser) request.getAttribute("user");
%>
<title><%= user.getNome() %> <%= user.getCognome() %></title>
</head>
<body>
Username: <%= user.getUsername() %>
Nome: <%= user.getNome()%>
Cognome: <%= user.getCognome() %>
Età: <%= user.getAge() %>

<% if(user.getId() == userOne.getId()) {%>
<a href="eliminaUtente.jsp">Elimina account</a>
<%} %>
<a href="create/createRoute.jsp">Crea un tuo itinerario</a>
<a href="create/createTravel.jsp">Crea un tuo viaggio</a>

<% if(user.getAuthorization() == 1){%>
<a href="create/creaLuogo.jsp">Aggiungi Luogo</a>
<%} %>
<a href="research/search-route-location.jsp">Cerca itinerari per luoghi</a>
<a href="research/searchUser.html">Cerca Utente</a>
<a href="research/search-travel-location.jsp">Cerca Viaggi per luoghi</a>
<form method="post" action="Logout">
<input type="submit" class="submit" value="Logout!">
</form>





</body>
</html>