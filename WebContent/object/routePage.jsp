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
<title><%=route.getName()%></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	<b>Nome: </b><span id="n"><%=route.getName()%></span>
	
	<br>
	<h4>Descrizione</h4>
	<p id="d"><%=route.getDescription()%></p>
	
	
	<%if(travel != null){ %>
		<%if(travel.getCreatoreViaggio().getId() == u.getId()){ %>
			<div id="update"><button onClick="modify(<%= route.getId()%>,<%= travel.getId()%>,'<%= route.getName()%>','<%= route.getDescription()%>')">Modifica</button></div>
			
		<%} %>
	<%} %>
	
	<br>
	<h4>Luoghi</h4>
	<%List<Location> locations = route.getLocations();%>
	<%for(Location location:locations){ %>
	
		<a href="ShowLocation?id=<%=location.getId()%>"><%=location.getName()%></a>
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
	
	<br>
	
	<%if(u.getAuthorization() > 0){ %>
		<a href="<%=request.getContextPath().toString()%>/deleteRoute.jsp?idr=<%=route.getId()%>">Elimina itinerario</a>
	<%} %>
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
	
	<script type="text/javascript">
		function modify(idRoute,idTravel,name,desc){
			var form = "<form action=\"UpdateRoute\" method=\"post\">";
			form = form + "<input type=\"hidden\" value=\"" + idRoute + "\" name=\"idr\">";
			form = form + "<input type=\"hidden\" value=\"" + idTravel + "\" name=\"idt\">";
			form = form + "<span>Nome: </span>";
			form = form + "<input type=\"text\" name=\"nameRoute\" value=\"" + name + "\"><br>";
			form = form + "<b>Descrizione: </b>";
			form = form + "<textarea row=\"10\" col=\"20\" name=\"descRoute\">" + desc + "</textarea>";
			form = form + "<input type=\"submit\" value=\"Aggiorna\">";
			form = form + "</form>"
			document.getElementById("update").innerHTML = form;
		}
	</script>
</body>
</html>