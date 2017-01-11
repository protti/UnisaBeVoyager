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
	
	<%List<Route> routes = (List<Route>) request.getAttribute("routes"); %>
	
	<div>
	<% if (routes != null && !routes.isEmpty()) { 
		for(int i = 0; i < routes.size(); i++) { %>
		
		<a class="clickDiv" href=ShowRoute?id=<%=routes.get(i).getId() %>>
		<div class="cont">
		<img src=<%=request.getContextPath().toString()%>/CSS/image/itinerario.jpg alt="Mia Immagine"> 
		<span class="intern"><%=routes.get(i).getName() %></span><br><br>
		<span class="intern"><%=routes.get(i).getDescription() %></span><br>
		</div>
		</a>
		<br><br>
		
	</div>
	<%	} %>
	<%} else {%>
		<p>Nessuno risultato trovato!</p>
	<%}%>
</body>
</html>