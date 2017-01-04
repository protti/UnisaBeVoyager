<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="UserSubsystem.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= (String) request.getAttribute("nome") %> <%= (String) request.getAttribute("cognome") %></title>
</head>
<body>
</body>

Username: <%= (String) request.getAttribute("username") %>
Nome: <%= (String) request.getAttribute("nome") %>
Cognome: <%= (String) request.getAttribute("cognome") %>
Età: <%= request.getAttribute("eta") %>

</html>