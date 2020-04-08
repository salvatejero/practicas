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
				
				<table id="carsTable" class="table table-bordered">
					<thead class="thead-light">
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
    			ajax: {
    		        url: './carsJSON',
    		        dataSrc: 'cars'
    		    },
    		    columns: [
    		        { data: 'pk' },
    		        { data: 'identification.id' }
    		    ]
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