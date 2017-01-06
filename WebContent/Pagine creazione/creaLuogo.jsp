<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%if(session.getAttribute("user") != null){ %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="CreateLocation" method="post">
		<span>Nome </span><input type="text" class="focus" name="nome"><br>
		<span>Descrizione </span><br><textarea cols="10" rows="5" class="focus" name="descrizione"></textarea><br>
		<input type="submit" value="Crea">
	</form>
<%} else{%>
Non sei loggato
<%}%>
</body>
</html>
