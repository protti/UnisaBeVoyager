<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="UserSubsystem.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% if(session.getAttribute("UserR") != null) { %>
<%RegisteredUser ru =(RegisteredUser)session.getAttribute("UserR"); %>

<title><%=ru.getNome()%> <%=ru.getCognome()%></title>
</head>
<body>
<%} %>
</body>
</html>