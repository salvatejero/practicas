<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.net.URLEncoder"%>
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
	<%
	
	System.out.println(request.getQueryString());
	
	
	
	String pageActual = (String)request.getAttribute("page");
	Long total = (Long)request.getAttribute("total");
	if(pageActual == null){
		pageActual = "0";
	}
	String make = (String)request.getAttribute("make");
	Integer yearP = (Integer)request.getAttribute("year");
	if(yearP == null){
		yearP = 0;
	}
	int pagei = Integer.valueOf(pageActual);
	int prev = -1;
	int next = -1;
	if(pagei > 1){
		prev = pagei -1;
	}
	next = pagei+1;
	
	List<String> identifications = (List<String>)request.getAttribute("makes");
	List<Integer> years = (List<Integer>)request.getAttribute("years");
	List<Car> cars = (List<Car>) request.getAttribute("cars");
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
				<table class="table table-bordered">
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
					
						<%

						if (cars != null) {

							for (Car c : cars) { %>
							
							<tr>
								<th scope="row"><%=c.getId() %></th>
								<td><%=c.getMake().getMake()%></td>
								<td><%=c.getYear() %></td>
								<td><%=c.getFueltype().getFuelType() %></td>
								<td>
									<div class="dropdown">
									  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Acciones
									  <span class="caret"></span></button>
									  <ul class="dropdown-menu">
									    <li><a href="./?action=detail&pk=<%=c.getId() %>&redirect=<%=encodeValue(request.getQueryString())%>">Detalle</a></li>
									    <li><a href="#">Eliminar</a></li>
									  </ul>
									</div>
								</td>
							</tr>
							<%}

						}else{%>	
						
					  <%} %>
					</tbody>
				</table>
				<%
				
				
				%>
				<nav aria-label="...">
				  <ul class="pagination">
				    <li class="page-item" <%if(prev<0){ %> disabled="disabled" <%} %> >
				      <a class="page-link"  <%if(prev>0){ %> href="./?action=paginacion&page=<%=prev %>" <%} %>  <%if(prev<0){ %> tabindex="-1"aria-disabled="true"<%} %> >Previous</a>
				    </li>
				    <%if(prev>0){ %>
				    	<li class="page-item"><a class="page-link" href="./?action=paginacion&page=<%=prev %>"><%=prev%></a></li>
				     <%} %>
				    <li class="page-item active" aria-current="page">
				      <a class="page-link" href="#"><%=pageActual %> <span class="sr-only"><%=pageActual %></span></a>
				    </li>
				    <%if(next>0){ %>
				    	<li class="page-item"><a class="page-link" href="./?action=paginacion&page=<%=next %>"><%=next %></a></li>
				    <%} %>
				    <li class="page-item">
				      <a class="page-link" href="./?action=paginacion&page=<%=next %>">Next</a>
				    </li>
				  </ul>
				</nav>
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
	
	<script src="./js/jquery-3.4.1.slim.min.js" ></script>
    <script src="./js/popper.min.js" ></script>
    <script src="./js/bootstrap-select.min.js" ></script>
    <script src="./js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    
    	$(document).ready(function(){
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