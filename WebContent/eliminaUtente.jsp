<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%if(session.getAttribute("user") != null){ %>
<%RegisteredUser user =(RegisteredUser) session.getAttribute("user");%>
<title>Eliminazione </title>
</head>
<body>
<%@ include file="navbar.jsp" %>

Sei sicuro di voler eliminare il tuo account?

<form action="deleteUser" method="post">
		<div>
			<input type="hidden" value="<%=user.getId()%>" name="id"/><br />
			<input type="submit" value="Eliminami"/>
		</div>
	</form>


<%} else {%>
<%} %>
</body>
</html>