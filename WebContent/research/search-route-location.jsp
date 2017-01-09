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
<div id="form">

<%@ include file="../navbar.jsp" %>

<form method="get" action="<%=request.getContextPath().toString()%>/SearchRouteFromLocation">
				<input type="text" id="campo" placeholder="Inserisci la meta del tuo itinerario..." class="focus" name="search">
				<input type="submit" id="btn" class="submit" value="Cerca">	
</form>
</div>
</body>
</html>