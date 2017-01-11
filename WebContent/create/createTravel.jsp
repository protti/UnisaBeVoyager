<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%if(session.getAttribute("user") != null){ %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea un viaggio</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<link href=<%=request.getContextPath().toString()%>/CSS/create.css rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../navbar.jsp" %>

<%if(request.getAttribute("dati_mancanti") != null){ %>
		<b>Inserisci tutti i dati</b>
	<%} %>

<div id="form">
	<form action="<%= request.getContextPath() %>/createTravel" method="post">
		<span>Nome </span><input id="campo" type="text" class="focus" name="nome"><br>
		<span>Descrizione</span><textarea id="descrizione"  name="descrizione"></textarea><br>
		<span>Start Date </span><input id="campo" type="text" class="focus" name="startDate"><br>
		<span>End Date </span><input id="campo" type="text" class="focus" name="endDate"><br><br>
		<span>Modificabile </span><input type="radio" class="focus" name="type"><br>
		
		<input id="btn" type="submit" value="Crea">
	</form>
	
	<form>
		<input id="nameLoc" type="text">
		<input  id="btn1" type="button" onclick="searchRoute('<%= request.getContextPath()%>')" value="Cerca">
	</form>
	
	<div id="result"></div>
	</div>
	
	
	
	<script type="text/javascript">
	function searchRoute(path) {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("result").innerHTML = xhttp.responseText;
		    }
		  };
		  var name = $('#nameLoc').val();
		  xhttp.open("POST", ""+path+"/SearchRouteForTravel?location="+name, true);
		  xhttp.send();
		}
	</script>
	
	<script type="text/javascript">
	function addToList(path,id) {
		  var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("result").innerHTML = xhttp.responseText;
		    }
		  };
		  xhttp.open("POST", "" + path + "/addRouteForNewTravel?id="+id, true);
		  xhttp.send();
		}
	</script>
	
<%} else{%>
Non sei loggato
<%}%>

	
</body>
</html>
