<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="UserSubsystem.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%if(session.getAttribute("user") != null){ 

RegisteredUser user = (RegisteredUser) session.getAttribute("user");

%>
<title><%= user.getNome() %> <%= user.getCognome() %></title>
</head>
<body>
Username: <%= user.getUsername() %>
Nome: <%= user.getNome()%>
Cognome: <%= user.getCognome() %>
Età: <%= user.getAge() %>
<a href="eliminaUtente.jsp">Elimina account</a>
<%} %>
</body>
</html>