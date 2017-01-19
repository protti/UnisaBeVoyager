<%@page import="RouteSubsystem.Route"%>
<%@page import="java.util.List"%>
<%@page import="FeedbackSubsystem.FeedbackUser"%>
<%@page import="UserSubsystem.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% RegisteredUser own = (RegisteredUser) session.getAttribute("user"); %>
<% RegisteredUser u = (RegisteredUser) request.getAttribute("user");%>
<% List<FeedbackUser> feedbackUser = (List<FeedbackUser>) request.getAttribute("feedbacks"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Feedback utente</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<link href=<%=request.getContextPath().toString()%>/CSS/feedback.css rel="stylesheet" type="text/css">
</head>
<body>
	<div id="feed">
	<h4>Feedback</h4>
	<%if(own.getId() != u.getId()){ %>
	<form>
		<textarea id="feedback"></textarea><br>
		<input type="button" id="btn" onclick="putFeedback(<%= u.getId()%>)" value="Commenta">
	</form>
	<%} %>
	<div id="response"></div>
	<%if(feedbackUser != null){ %>
		<%if(feedbackUser.size() > 0){ %>
			<%for(FeedbackUser fu: feedbackUser){ %>
				<div id="realFeed">
				<a href="showProfile?id=<%= fu.getSender().getId() %>">
				<div id="nome">
				<%= fu.getSender().getUsername() %>:	
				</div>
				</a>
				<div id="descr">
				 <%= fu.getMessage() %>
				 </div><br>
				</div>
			<%} %>
		<%} else{ %>
			<b>Non sono presenti feedback relativi a questo utente</b>
		<%} %>
	<%} else{ %>
		<b>Non ci sono feedback relativi a questo utente</b>
	<%} %>
	</div>
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
		  xhttp.open("POST", "GiveFeedbackToUser?id="+idRecevier+"&message="+feedback, true);
		  xhttp.send();
		}</script>
</body>
</html>