<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="LocationSubsystem.Location"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Location location = (Location) request.getAttribute("location");%>
<title><%= location.getName() %></title>
<link href=<%=request.getContextPath().toString()%>/CSS/page.css rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div id="containerViaggio">
<img id="mappa" src=<%=request.getContextPath().toString()%>/CSS/image/luogo.jpg alt="Mia Immagine"> 	<br><br>
<span class="intern"><%= location.getName() %></span><br><br>
<span class="intern"><%= location.getDescrizione() %></span><br><br>

</div>
<%@ include file="../feedback/feedbackLocation.jsp" %>
<a id="eliminaRoute" href="deleteLocation.jsp?idl=<%= location.getId()%>">Elimina Luogo</a>
</body>
</html>