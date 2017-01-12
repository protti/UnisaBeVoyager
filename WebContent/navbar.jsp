<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="UserSubsystem.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href=<%=request.getContextPath().toString()%>/CSS/style.css rel="stylesheet" type="text/css">
<link href=<%=request.getContextPath().toString()%>/CSS/responsive.css rel="stylesheet" type="text/css">
</head>
<body>
<div id="page">
	<%RegisteredUser user1; %>
	<%if(session.getAttribute("user") != null){ 
	user1 =(RegisteredUser)session.getAttribute("user");
	request.setAttribute("id", user1.getId());
	%>
	<div id="navigation">
	<ul>
	<li><form method="get" action=<%=request.getContextPath().toString()%>/showProfile style="text-decoration: none;">
		<input id="submit" type="submit" value="Profilo">
	</form></li>
	<li><a href=<%=request.getContextPath().toString()%>/create/createRoute.jsp>Crea Itinerari</a></li>
	<li><a href=<%=request.getContextPath().toString()%>/create/createTravel.jsp>Crea Viaggi</a></li>
	<% if(user1.getAuthorization() == 1){%>
	<li><a href=<%=request.getContextPath().toString()%>/create/creaLuogo.jsp>Crea Luoghi</a></li>
	<%} %>
	<li><a href=<%=request.getContextPath().toString()%>/research/search-route-location.jsp>Cerca Itinerari</a></li>
	<li><a href=<%=request.getContextPath().toString()%>/research/search-travel-location.jsp>Cerca Viaggi</a></li>
	<li><a href=<%=request.getContextPath().toString()%>/research/searchUser.jsp>Cerca Utente</a></li>
	<li><form method="get" action=<%=request.getContextPath().toString()%>/Logout style="text-decoration: none;">
		<input id="submit" type="submit" value="Logout">
	</form></li>
	</ul>
	</div>
	</div>
	<%} %>
</body>
</html>