<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%if(session.getAttribute("user") != null){ %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea un viaggio</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<form action="../createTravel" method="post">
		<span>Nome </span><input type="text" class="focus" name="nome"><br>
		<p>Descrizione</p><br><textarea rows="10" cols="20" name="descrizione"></textarea>
		<span>Start Date </span><input type="text" class="focus" name="startDate"><br>
		<span>End Date </span><input type="text" class="focus" name="endDate"><br>
		<span>Modificabile </span><input type="radio" class="focus" name="type"><br>
		
		<input type="submit" value="Crea">
	</form>
	
	<form>
		<input id="nameLoc" type="text">
		<input type="button" onclick="searchRoute()" value="Cerca">
	</form>
	
	<div id="result"></div>
	
	<script type="text/javascript">
	function searchRoute() {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("result").innerHTML = xhttp.responseText;
		    }
		  };
		  var name = $('#nameLoc').val();
		  xhttp.open("POST", "../SearchRouteForTravel?location="+name, true);
		  xhttp.send();
		}
	</script>
	
	<script type="text/javascript">
	function addToList(id) {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("result").innerHTML = xhttp.responseText;
		    }
		  };
		  xhttp.open("POST", "../addRouteForNewTravel?id="+id, true);
		  xhttp.send();
		}
	</script>
	
<%} else{%>
Non sei loggato
<%}%>

	
</body>
</html>
