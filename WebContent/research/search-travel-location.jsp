<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<%=request.getContextPath().toString()%>/CSS/ricerca.css rel="stylesheet" type="text/css">
<title>Cerca viaggi dal luogo</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>
	<form action="../SearcTravelFromLocation" method="post">
		<input type="text" id="campo" name="search">
		<input type="submit" value="Cerca">
	</form>
</body>
</html>