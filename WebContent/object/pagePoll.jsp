<%@page import="PollSubsystem.Poll"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Poll poll = (Poll) request.getAttribute("poll"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Richiesta</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<h4>Descrizione</h4>
	<p><%= poll.getDescription()%></p>
	
	<b>Voti positivi: </b><span><%= poll.getVpositive() %></span><br>
	<b>Voti negativo: </b><span><%= poll.getVnegative() %></span><br><br>
	
	
	
	<h4>Vota</h4>
	<form action="Vote" method="post">
		<input id="poll" type="hidden" name="pollID" value="<%= poll.getId()%>">
		<span><b>SI </b></span><input name="vote" type="radio" value="1"><br>
		<span><b>NO </b></span><input name="vote" type="radio" value="-1"><br>
		<input type="submit" value="Vota">
	</form>
	
	<div id="response"></div>
	
	<script type="text/javascript">
	function vote() {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		    }
		  };
		  var id = $('#poll').val();
		  var vote = document.getElementsByName("vote").valueOf();
		  xhttp.open("POST", "/Vote?pollID="+id+"&vote="+vote, true);
		  xhttp.send();
		}</script>
</body>
</html>