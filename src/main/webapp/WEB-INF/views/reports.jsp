<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://www.springframework.org/security/tags'
	prefix='security'%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="content-language" content="cs" />
	
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/morris/morris.css" />" rel="stylesheet" type="text/css" />

	<link href="<c:url value="/resources/css/bootstrap-datepicker.css" />" rel="stylesheet"  type="text/css" />	

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/morris/raphael-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/morris/morris.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
	
	
<title>Reporting</title>
	
</head>
<body>
	<div id="layout">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div id="main">
			<div id="content">
			
					<p id="datepair_rtn1">
					    <label class="control-label">Date from: </label>&nbsp;&nbsp;<input id="start_date" class="date"/>
					    <label class="control-label">Date to: </label>&nbsp;&nbsp;<input id="end_date" class="date"/>
						<a onClick="submitDates();"class="btn btn-default">Reload</a>
					</p>

					<script>
					var date_data = JSON.parse('${date_data_json}');

					
					    $('#start_date').datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true,
					    });
					    $('#end_date').datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true,
					    });

					    //on clicking reload button
					    function submitDates(){
					    	var start_date = $('#start_date').val();
					    	var end_date = $('#end_date').val();

					    	window.location.href = 'reports?start='+start_date+'&end='+end_date;

					    }
					    // initialize datepair
					    
					</script>
					
				<c:forEach items="${mobility_data}" var="query" varStatus="loop">
				</c:forEach>
				
				<c:forEach items="${age_group_data}" var="query" varStatus="loop">
				</c:forEach>
				
				<c:forEach items="${purpose_data}" var="query" varStatus="loop">
				</c:forEach>
				
				<div class="panel panel-default">
				  <!-- Default panel contents -->
				<div class="panel-heading" style="font-weigth:bold !important; font-size:15px;">Summary<c:if test="${start_date_descriptive!= null}"> from ${start_date_descriptive}</c:if><c:if test="${end_date_descriptive!= null}"> to ${end_date_descriptive}</c:if></div>
				  <ul class="list-group">
				    <li class="list-group-item">Total queries: <strong>${total_journeys}</strong></li>
				    <li class="list-group-item">Outward journeys: <strong>${outward_journeys}</strong></li>
				    <li class="list-group-item">Return journeys: <strong>${return_journeys}</strong></li>
				    <li class="list-group-item"><button onclick="window.location.href='downloadCSV?start=${start_date}&end=${end_date}'; return false;" class="btn btn-xs btn-success">Download CSV</button></li>
				  </ul>
				</div>

								
					<div class="panel panel-default">
				<div class="panel-heading" style="font-weigth:bold !important; font-size:15px;">Queries per day</div>
					  <div class="panel-body">
							<div id="month-line-chart"><p class="no-data">No data found</p></div>
					  </div>
					</div>
				
				<!-- div id="month-line-chart" style="height: 250px;"></div> -->
				<div class="row">
					<div class="col-md-4">

					<div class="panel panel-default">
					  <div class="panel-heading">Mobility Status</div>
					  <div class="panel-body">
							<div id="mobility-status-chart-donut"><p class="no-data">No data found</p></div>
					  </div>
					</div>
				
					</div>
					
					<div class="col-md-4">

					<div class="panel panel-default">
					  <div class="panel-heading">Age Group</div>
					  <div class="panel-body">
							<div id="age-group-chart-donut"><p class="no-data">No data found</p></div>
					  </div>
					</div>
				
					</div>
					
					<div class="col-md-4">

					<div class="panel panel-default">
					  <div class="panel-heading">Journey Purpose</div>
					  <div class="panel-body">
							<div id="purpose-chart-donut"><p class="no-data">No data found</p></div>
					  </div>
					</div>
				
					</div>
					
				</div>
				
			</div>
		</div>
		<div id="footer">				
		<script type="text/javascript">
		
		//alert('${mobility_data_json}');
		var date_data = JSON.parse('${date_data_json}');
		
		if(date_data.length)
			$(".no-data").remove();
		
		new Morris.Line({
			  element: 'month-line-chart',
			  data: date_data,
			  xkey: 'day',
			  ykeys: ['value'],
			  labels: ['Queries']
			});
		
		var mobility_data = JSON.parse('${mobility_data_json}');
		var age_data = JSON.parse('${age_group_data_json}');
		var purpose_data = JSON.parse('${purpose_data_json}');
		 var colors_array= ["blue", "green", "grey","yellow"];

		Morris.Donut({
		    element: 'mobility-status-chart-donut',
		    data: mobility_data,
		    colors:colors_array,
		    formatter: function (y) { return y }
		  });
		
		Morris.Donut({
		    element: 'age-group-chart-donut',
		    data: age_data,
		    formatter: function (y) { return y }
		  });
		
		Morris.Donut({
		    element: 'purpose-chart-donut',
		    data: purpose_data,
		    formatter: function (y) { return y }
		  });
		</script>
			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>