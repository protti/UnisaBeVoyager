<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="UserSubsystem.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%//if(session.getAttribute("user") != null){ 

RegisteredUser userOne = (RegisteredUser) session.getAttribute("user");
RegisteredUser user = (RegisteredUser) request.getAttribute("user");
%>
<title><%= user.getNome() %> <%= user.getCognome() %></title>
<link href=<%=request.getContextPath().toString()%>/CSS/page.css rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div id="container">

<img src=<%=request.getContextPath().toString()%>/CSS/image/user.jpg alt="Mia Immagine"> 
		
<span class="intern">Username: <%= user.getUsername() %></span><br><br>
<span class="intern">Nome: <%= user.getNome()%></span><br><br>
<span class="intern">Cognome: <%= user.getCognome() %></span><br><br>
<span class="intern">Età: <%= user.getAge() %></span><br><br>
</div>

<%@ include file="../feedback/feedbackUser.jsp" %>



</body>
</html>