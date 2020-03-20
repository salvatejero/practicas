<%@page import="com.practicas.model.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Exercise 2</title>
	</head>
	<body>

		<h1>Coches</h1>
		<%
		
		List<Car> cars = (List<Car>)request.getAttribute("cars");
		
		if(cars != null){
			
			for(Car c: cars){ %>
		
			<%=c.getIdentification().getId() %> | <%=c.getIdentification().getMake() %> <br />	
			 
			<%}
		
		}
		%>

	</body>
</html>