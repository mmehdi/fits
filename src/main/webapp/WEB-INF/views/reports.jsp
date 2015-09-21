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
					    <input id="start_date" class="date"/>
					    <input id="end_date" class="date"/>
						<a onClick="submitDates();"class="btn btn-default">Reload</a>
					</p>

					<script>
					   
					    $('#start_date').datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true
					    });
					    $('#end_date').datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true
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
					<label>${query.count}</label>				
				</c:forEach>
				
				<c:forEach items="${age_group_data}" var="query" varStatus="loop">
					<label>${query.count}</label>				
				</c:forEach>
				
				<c:forEach items="${purpose_data}" var="query" varStatus="loop">
					<label>${query.count}</label>				
				</c:forEach>
				
				<div id="month-line-chart" style="height: 250px;"></div>

				<div id="age-group-chart-donut" style="height: 250px;"></div>

			</div>
		</div>
		<div id="footer">
		//http://stackoverflow.com/questions/9361977/how-to-access-model-attribute-in-javascript
		<c:forEach items="${age_group_data}" var="query" varStatus="loop">
				<script type="text/javascript">
					<label>${query.count}</label>				
				</script>
		</c:forEach>
				
		<script type="text/javascript">
		var newData = new Array();
		newData.push({day:'2012-02-24', value:1});
		newData.push({day:'2012-02-25', value:2});
		newData.push({day:'2012-02-26', value:1});

		new Morris.Line({
			  // ID of the element in which to draw the chart.
			  element: 'month-line-chart',
			  // Chart data records -- each entry in this array corresponds to a point on
			  // the chart.
			  data: newData,
			  // The name of the data record attribute that contains x-values.
			  xkey: 'day',
			  // A list of names of data record attributes that contain y-values.
			  ykeys: ['value'],
			  // Labels for the ykeys -- will be displayed when you hover over the
			  // chart.
			  labels: ['Queries']
			});
		
		Morris.Donut({
		    element: 'age-group-chart-donut',
		    data: [
		      {label: 'Jam', value: 25 },
		      {label: 'Frosted', value: 40 },
		      {label: 'Custard', value: 25 },
		      {label: 'Sugar', value: 10 }
		    ],
		    formatter: function (y) { return y + "%" }
		  });
		</script>
			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>