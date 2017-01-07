<%@page import="java.util.List"%>
<%@page import="FeedbackSubsystem.FeedbackUser"%>
<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% RegisteredUser own = (RegisteredUser) session.getAttribute("user"); %>
<% RegisteredUser user = (RegisteredUser) request.getAttribute("user"); %>
<% List<FeedbackUser> feedbackUser = (List<FeedbackUser>) request.getAttribute("feedbacks"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback utente</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>

	<h4>Feedback</h4>
	
	<textarea rows="10" cols="20" id="feedback"></textarea><br>
	<button onclick="putFeedback(<%= own.getId()%>,<%= user.getId()%>)">Commenta</button>
	
	<%if(feedbackUser != null){ %>
		<%if(feedbackUser.size() > 0){ %>
			<%for(FeedbackUser fu: feedbackUser){ %>
				<a href="showProfile?id="<%= fu.getSender().getId() %>>
				<%= fu.getSender().getUsername() %></a>
				<p> <%= fu.getMessage() %></p>
			<%} %>
		<%} else{ %>
			<b>Non ci sono feedback relativi a questo utente</b>
		<%} %>
	<%} else{ %>
		<b>Non ci sono feedback relativi a questo utente</b>
	<%} %>
	
	<script type="text/javascript">
	function putFeedback(idSender,idReceiver) {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		    }
		  };
		  var feedback = $('#feedback').val();
		  xhttp.open("POST", "#", true);
		  xhttp.send();
		}</script>
</body>
</html>