<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="UserSubsystem.RegisteredUser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<%=request.getContextPath().toString()%>/CSS/correct.css rel="stylesheet" type="text/css">

<title>Operazione riuscita</title>
</head>
<body>
	
	<div id="container">
	<%RegisteredUser user = (RegisteredUser) session.getAttribute("user"); %>
	<h1>Operazione riuscita con successo</h1>
	<a href="showProfile?id=<%= user.getId()%>">Ritorna alla tua pagina</a>
	</dir>
</body>
</html>