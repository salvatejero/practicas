<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.net.URLEncoder"%>
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
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
	</head>
<body>
	<%
	
	System.out.println(request.getQueryString());
	
	
	
	String pageActual = (String)request.getAttribute("page");
	Long total = (Long)request.getAttribute("total");
	String make = (String)request.getAttribute("make");
	Integer yearP = (Integer)request.getAttribute("year");
	if(yearP == null){
		yearP = 0;
	}
	
	List<String> identifications = (List<String>)request.getAttribute("makes");
	List<Integer> years = (List<Integer>)request.getAttribute("years");
	%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<jsp:include page="head.jsp" /> 
			</div>
			<div class="col-sm-2"></div>
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<input type="hidden" name="page" id="page" value="<%=pageActual%>"/>
				<input type="hidden" name="makeFilterValue" id="makeFilterValue" value="<%=make%>"/>
				<input type="hidden" name="yearFilterValue" id="yearFilterValue" value="<%=yearP%>"/>
				<h1>Listado de coches</h1>
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<div class="container-fluid">
	  					<span class="navbar-brand mb-0 h1">Acciones</span>
	  					<div class=" navbar-collapse" id="navbarNav">
						    <ul class="navbar-nav">
						      <li class="nav-item active">
						        <select class="selectpicker filterMake" data-live-search="true" title="Filter by makes">
								  <%for(String iden: identifications){ %>
								  	<option <%if (iden.equals(make)){ %> selected <%} %> value="<%=iden %>" ><%=iden %></option>
								  <% }%>
								</select>
						      </li>
						      <li class="nav-item">
						        <select class="selectpicker filterYear" data-live-search="true" title="Filter by year">
								  <%for(Integer year: years){ %>
								  	<option <%if (year.equals(yearP)){ %> selected <%} %> value="<%=year %>"><%=year %></option>
								  <% }%>
								</select>
						      </li>
						    </ul>
					  	</div>
					  	<span style="padding-right: 10px;">Se han encontrado <strong> <%=total %></strong> coches</span>
	  					<form class="form-inline my-2 my-lg-0">
	      					<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
	      					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    				</form>
    				</div>
				</nav>	
				<table id="carsTable" class="table table-bordered">
					<thead class="thead-light">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Model</th>
							<th scope="col">Year</th>
							<th scope="col">Combustible</th>
							<th scope="col">Acciones</th>
						</tr>
					</thead>
					<tbody>
					
						
					</tbody>
				</table>
				<%
				
				
				%>
				
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<%!  private static String encodeValue(String value) {
        String url = "";
		try {
			url = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception ex) {
            
        }
		return url;
    } %>
	
	<script  src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
    <script src="./js/popper.min.js" ></script>
    <script src="./js/bootstrap-select.min.js" ></script>
    <script src="./js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script type="text/javascript">
    
    	$(document).ready(function(){
    		
    		$('#carsTable').DataTable( {
    		    ajax: './carsJSON'
    		} );
    		
    		$('.filterYear').change(function (){
    	        var valor = $(this). children("option:selected"). val();
    	        location.href = './?action=pagination&filterYear='+valor+'&filterMake='+$('#makeFilterValue').val()+'&page='+$('#page').val();
    	    });
    	   	$('.filterMake').change(function (){
    	        var valor = $(this). children("option:selected"). val();
    	        location.href = './?action=pagination&filterMake='+valor+'&filterYear='+$('#yearFilterValue').val()+'&page='+$('#page').val();
    	   	});
    	});
	    
    
    	function modoOscuro(){
    		
    		if($(table).hasClass('table-dark')){
    			$(table).addClass('.table-light');
    			$(table).removeClass('.table-dark');
    		}else{
    			$(table).removeClass('.table-light');
    			$(table).addClass('.table-dark');
    		}
    		
    	}
    	
    </script>
</body>
</html>