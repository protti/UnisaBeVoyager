<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cerca itierario per luogo</title>
<link href=<%=request.getContextPath().toString()%>/CSS/ricerca.css rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="../navbar.jsp" %>

<form method="get" action="<%=request.getContextPath().toString()%>/SearchRouteFromLocation">
				<p>Cerca un'itinerario:</p> <input type="text" id="campo" class="focus" name="search"><br>
				<input type="submit" class="submit" value="Cerca">	
</form>

</body>
</html>