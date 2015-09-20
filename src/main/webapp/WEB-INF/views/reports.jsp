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

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/morris/raphael-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/morris/morris.min.js" />"></script>
	
<title>Reporting</title>
	
</head>
<body>
	<div id="layout">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div id="main">
			<div id="content">
			
				<c:forEach items="${all_data}" var="query" varStatus="loop">
				<label>${query.column_name}</label>
				</c:forEach>
				<div id="month-line-chart" style="height: 250px;"></div>


			</div>
		</div>
		<div id="footer">
		
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
		</script>
			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>