<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.practicas.model.Identification"%>
<%@page import="com.practicas.model.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./css/bootstrap.min.css" >
		<link rel="stylesheet" href="./css/bootstrap-select.min.css">
		<meta charset="UTF-8">
		<title>Exercise 2</title>
	</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<jsp:include page="head.jsp" /> 
			</div>
			<div class="col-sm-2"></div>
		</div>
		<%
		
		Car car = (Car)request.getAttribute("car");
		%>
		
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<form action="./update" method="post">
					<input type="hidden" name="action" value="updateCar"/>
					<input type="hidden" name="pk" value="<%=car.getPk()%>"/>
					<input type="hidden" name="redirect" value="<%=encodeValue(request.getAttribute("redirect").toString()) %>"/>
					<fieldset>
						<legend>Engine Information</legend>
						<div class="form-group">
						    <label for="enginetype">Engine Type</label>
						    <input type="text" name="enginetype" class="form-control" id="enginetype" aria-describedby="enginetypeHelp" value="<%=car.getEngineinformation().getEnginetype() %>" placeholder="Introduce un Engine Type">
						    <small id="enginetypeHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
						</div>
						<div class="form-group">
						    <label for="enginetype">DriveLine</label>
						    <input type="text" name="driveline" class="form-control" id="enginetype" aria-describedby="enginetypeHelp" value="<%=car.getEngineinformation().getDriveline() %>" placeholder="Introduce un Engine Type">
						    <small id="enginetypeHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
						</div>
					</fieldset>
				
					<button class="btn btn-primary btn-lg" type="submit">Actualizar</button>
					<a href="./?<%=encodeValue(request.getAttribute("redirect").toString()) %>" class="btn btn-secondary btn-lg " role="button" aria-disabled="true">Volver</a>
				</form>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<%!  public static String encodeValue(String value) {
		String result = "";
		try{
	    	result = java.net.URLDecoder.decode(value, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
		    // not going to happen - value came from JDK's own StandardCharsets
		}
		return result;
	}
	
	%>
	
	<script src="./js/jquery-3.4.1.slim.min.js" ></script>
    <script src="./js/popper.min.js" ></script>
    <script src="./js/bootstrap-select.min.js" ></script>
    <script src="./js/bootstrap.min.js"></script>
    
    
</body>
</html>