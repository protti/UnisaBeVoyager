<%@page import="RouteSubsystem.Route"%>
<%@page import="java.util.List"%>
<%@page import="FeedbackSubsystem.FeedbackRoute"%>
<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% RegisteredUser user = (RegisteredUser) session.getAttribute("user"); %>
<% Route r = (Route) request.getAttribute("route");%>
<% List<FeedbackRoute> feedbackRoute = (List<FeedbackRoute>) request.getAttribute("feedback"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback itinerario</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>

	<h4>Feedback</h4>
	<form>
		<textarea id="feedback"></textarea><br>
		<input type="button" onclick="putFeedback(<%= r.getId()%>)" value="Commenta">
	</form>
	
	
	
	<%if(feedbackRoute != null){ %>
		<%if(feedbackRoute.size() > 0){ %>
			<div id="response"></div>
			<%for(FeedbackRoute fu: feedbackRoute){ %>
				<a href="showProfile?id=<%= fu.getSender().getId() %>">
				<%= fu.getSender().getUsername() %></a>
				<p> <%= fu.getMessage() %></p>
			<%} %>
		<%} else{ %>
			<div id="response"><b>Non ci sono feedback relativi a questo itinerario</b></div>
		<%} %>
	<%} else{ %>
		<div id="response"><b>Non ci sono feedback relativi a questo itinerario</b></div>
	<%} %>
	
	<script type="text/javascript">
	function putFeedback(idRecevier) {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		    }
		  };
		  var feedback = $('#feedback').val();
		  xhttp.open("POST", "GiveFeedbackToRoute?message="+feedback+"&id="+idRecevier, true);
		  xhttp.send();
		}</script>
		
</body>
</html>