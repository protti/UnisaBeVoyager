<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="RouteSubsystem.Route,java.util.List" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati ricerca</title>
</head>
<body>
	<jsp:include page="search-route-location.jsp" /> <br></br>

	Risultati della ricerca:
	
	<%List<Route> routes = (List<Route>) request.getAttribute("routes"); %>
	
	<% if (routes != null && !routes.isEmpty()) { 
		for(int i = 0; i < routes.size(); i++) { %>
		<p>Nome: <%=routes.get(i).getName() %></p>
		<a href=ShowRoute?id=<%=routes.get(i).getId() %>><%=routes.get(i).getName() %></a><br>
		
		<p>Descrizione: <%=routes.get(i).getDescription() %></p>
		<br><br>
	<%	} %>
	<%} else {%>
		<p>Nessuno risultato trovato!</p>
	<%}%>
</body>
</html>