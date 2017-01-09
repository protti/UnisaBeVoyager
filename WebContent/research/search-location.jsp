<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<%=request.getContextPath().toString()%>/CSS/ricerca.css rel="stylesheet" type="text/css">
<title>Cerca luoghi da visitare</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<form method="get" action="<%=request.getContextPath().toString()%>/SearchLocation">
				<p>Cerca un luogo da visitare:</p> <input type="text" class="focus" id="campo" name="search"><br>
				<input type="submit" class="submit" value="Cerca">	
</form>
	<div id="response"></div>
</body>
</html>