<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="LocationSubsystem.Location,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati ricerca</title>
</head>
<body>
	<jsp:include page="search-location.jsp" /> <br><br>
	<div>
	
	<%List<Location> locations = (List<Location>) request.getAttribute("locations"); %>
	
	<% if (locations != null && !locations.isEmpty()) { 
		for(int i = 0; i < locations.size(); i++) { %>
		<p>Nome: <%=locations.get(i).getName() %></p>
		<a href=ShowLocation?id=<%=locations.get(i).getId()%>>
		<div class="cont">
		<img src=<%=request.getContextPath().toString()%>/CSS/image/luogo.jpg alt="Mia Immagine"> 
		<span class="intern"><%=locations.get(i).getName() %></span><br>
		<span class="intern"><%=locations.get(i).getDescrizione() %></span>
		</div>
		</a>
		</div>
		<br><br>
	<%	} %>
	<%} else {%>
		<p>Nessuno risultato trovato!</p>
	<%}%>
	
</body>
</html>