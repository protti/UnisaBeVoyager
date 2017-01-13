<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%if(session.getAttribute("user") != null){ %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<%=request.getContextPath().toString()%>/CSS/create.css rel="stylesheet" type="text/css">
<title>Inserisci Luoghi</title>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	<div id="form">
	<form action="../CreateLocation" method="post">
		<span>Nome </span><input type="text" id="campo" class="focus" name="nome"><br><br>
		<span>Descrizione </span><textarea id="descrizione" class="focus" name="descrizione"></textarea><br>
		<input type="submit" id="btn" value="Crea">
	</form>
	</div>
<%} else{%>
Non sei loggato
<%}%>
</body>
</html>
