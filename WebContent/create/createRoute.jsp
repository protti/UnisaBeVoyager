<%@page import="java.util.ArrayList"%>
<%@page import="LocationSubsystem.Location"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%List<Location> currentList= new ArrayList<Location>();%>
<%session.setAttribute("currentList", currentList); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<%=request.getContextPath().toString()%>/CSS/create.css rel="stylesheet" type="text/css">


<title>Crea Itinerario</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="../navbar.jsp" %>
	<div id="form">
	<form action="../CreateRoute" method="post">
		<span>Nome </span><input type="text" id="campo" class="focus" name="name"><br>
		<span>Descrizione </span><textarea id="descrizione" row="10" col="10" type="text" name="descrizione"></textarea><br>
		<input type="submit" id="btn" value="Crea">
	</form>
	
	<form>
		<input type="text" id="nameLoc" name="name">
		<input type="button" id="btn1" onclick="searchLoc()" value="Cerca">
	</form>
	
	<div id="list"></div>
	<div id="response"></div>
	</div>
	<script type="text/javascript">
	function searchLoc() {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		    }
		  };
		  var name = $('#nameLoc').val();
		  xhttp.open("POST", "../SearchLocationForRoute?nameLocation="+name, true);
		  xhttp.send();
		}
	</script>
	
	<script type="text/javascript">
	function addToList(id) {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("list").innerHTML = xhttp.responseText;
		    }
		  };
		  xhttp.open("POST", "../AddToLocationList?id="+id, true);
		  xhttp.send();
		}
	</script>
	
</body>

</html>