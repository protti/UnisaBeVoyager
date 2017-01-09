<%@page import="TravelSubsystem.Travel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LocationSubsystem.Location"%>
<%@page import="RouteSubsystem.Route"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% RegisteredUser u = (RegisteredUser) session.getAttribute("user");%>
<% Route route = (Route) request.getAttribute("route");%>
<% Travel travel = (Travel) request.getAttribute("travel"); %>
<% session.setAttribute("currentList", route.getLocations()); %>
<title><%=route.getName()%></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	<b>Nome: </b><span id="name"><%=route.getName()%></span>
	<%if(travel != null){ %>
		<%if(travel.getCreatoreViaggio().getId() == u.getId()){ %>
			<button onClick="">Modifica nome</button>
		<%} %>
	<%} %>
	<br>
	<h4>Descrizione</h4>
	<span id="desc"><%=route.getDescription()%></span>
	<%if(travel != null){ %>
		<%if(travel.getCreatoreViaggio().getId() == u.getId()){ %>
			<form action="" method="post">
				<input type="submit" value="Modifica itinerario"> 
			</form>
		<%} %>
	<%} %>
	<br>
	<h4>Luoghi</h4>
	<%List<Location> locations = route.getLocations();%>
	<%for(Location location:locations){ %>
	
		<a href=ShowLocation?id=<%=location.getId()%>><%=location.getName()%></a>
	<%} %>
	
	<%if(travel != null){ %>
		<%if(travel.getCreatoreViaggio().getId() == u.getId()){ %>
			<form>
				<input id="idr" type="hidden" value="<%= route.getId()%>">
				<input id="search" type="text">
				<input type="button" onClick="searchLocation()" value="Cerca">
			</form>
			<div id="response"></div>
		<%} %>
	<%} %>
	
	
	<%@include file="../feedback/feedbackRoute.jsp"%>
	
	<script type="text/javascript">
		function searchLocation(){
			var xhttp;
			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
			  if (xhttp.readyState == 4 && xhttp.status == 200) {
			    document.getElementById("response").innerHTML = xhttp.responseText;
			  }
			};
			var name = $('#search').val();
			xhttp.open("POST", "SearchLocationForRoute?nameLocation="+name, true);
			xhttp.send();	
		}
	</script>
	
	<script type="text/javascript">
	function addToList(idl){
		var xhttp;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
		  if (xhttp.readyState == 4 && xhttp.status == 200) {
		    document.getElementById("response").innerHTML = xhttp.responseText;
		  }
		};
		var idr = $('#idr').val();
		xhttp.open("POST", "AddLocationRouteTravel?idRoute="+idr+"&idLocation="+idl, true);
		xhttp.send();	
	}
	</script>
</body>
</html>