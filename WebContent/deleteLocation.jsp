<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="LocationSubsystem.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%if(session.getAttribute("user") != null){ %>

<title>Delete Location</title>
</head>

<%Location location = (Location)session.getAttribute("location"); %>
<body>
<%@ include file="../navbar.jsp" %>
Vuoi davvero eliminare questo luogo?
<form action="DeleteLocation" method="post">
		<div>
			<input type="hidden" value="<%=location.getId()%>" name="id"/><br />
			<input type="submit" value="Eliminami"/>
		</div>
	</form>





<% } else {%>
Non sei loggato
<%} %>
</body>

</html>