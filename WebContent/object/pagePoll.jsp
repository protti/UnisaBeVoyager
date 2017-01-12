<%@page import="PollSubsystem.Poll,UserSubsystem.*,TravelSubsystem.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Poll poll = (Poll) request.getAttribute("poll"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Richiesta</title>
<link href=<%=request.getContextPath().toString()%>/CSS/page.css rel="stylesheet" type="text/css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	
	
	<div id="containerPoll">
	<img src=<%=request.getContextPath().toString()%>/CSS/image/sondaggio.jpg alt="Mia Immagine"> 
	
	<h4>Descrizione</h4>
	<span><%= poll.getDescription()%></span>
	<br>
	<br>
	<b>Voti positivi: </b><span class="intern"><%= poll.getVpositive() %></span><br><br>
	<b>Voti negativo: </b><span class="intern"><%= poll.getVnegative() %></span><br><br>
	<a href=ShowTravel?id=<%= poll.getIdTravel()%>>Torna al Viaggio!</a><br><br>
	
	
	<%RegisteredUser user = (RegisteredUser)session.getAttribute("user"); %>
	<%
	if(TravelManager.checkUserPartecipant(user.getId(), poll.getIdTravel())){ %>
	<h4>Vota</h4>
	<form>
		<input id="poll" type="hidden" name="pollID" value="<%= poll.getId()%>">
		<span><b>SI </b></span><input name="vote" id="si" type="radio" value="1"><br><br>
		<span><b>NO </b></span><input name="vote" id="no" type="radio" value="-1"><br><br>
		<input type="button" id="btn" onclick="vota()" value="Vota">
	</form>
	<%} %>
	<div id="risposta"></div> 
	
	
	
	
	</div>	
	
	<script type="text/javascript">
	function vota() {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("risposta").innerHTML = xhttp.responseText;
		      setTimeout(function() {
		    	  window.location.reload()
		    	}, 2000)
		    }
		  };
		  var id = $('#poll').val();
		  if (document.getElementById('si').checked) {
			  vote = document.getElementById('si').value;
		  }
		  if (document.getElementById('no').checked) {
			  vote = document.getElementById('no').value;
		  }
		  xhttp.open("POST", "Vote?pollID="+id+"&vote="+vote, true);
		  xhttp.send();
		}</script>
</body>
</html>