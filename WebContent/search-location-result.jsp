<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="LocationSubsystem.Location,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	Risultati della ricerca:
	
	<%List<Location> locations = (List<Location>) request.getAttribute("locations"); %>
	
	<% if (locations != null) { 
		for(int i = 0; i < locations.size(); i++) { %>
		<p>Nome: <%=locations.get(i).getName() %></p>
		<a href=ShowLocation?id=<%=locations.get(i).getId() %>><%=locations.get(i).getName() %></a><br>
		
		<p>Descrizione: <%=locations.get(i).getDescrizione() %></p>
		<br><br>
	<%	} %>
	<%} else {%>
		<p>Nessuno risultato trovato!</p>
	<%}%>
	
</body>
</html>