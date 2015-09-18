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


				<div id="myfirstchart" style="height: 250px;"></div>


			</div>
		</div>
		<div id="footer">
		
		<script type="text/javascript">
		new Morris.Line({
			  // ID of the element in which to draw the chart.
			  element: 'myfirstchart',
			  // Chart data records -- each entry in this array corresponds to a point on
			  // the chart.
			  data: [
			    { year: '2008', value: 20 },
			    { year: '2009', value: 10 },
			    { year: '2010', value: 5 },
			    { year: '2011', value: 5 },
			    { year: '2012', value: 20 }
			  ],
			  // The name of the data record attribute that contains x-values.
			  xkey: 'year',
			  // A list of names of data record attributes that contain y-values.
			  ykeys: ['value'],
			  // Labels for the ykeys -- will be displayed when you hover over the
			  // chart.
			  labels: ['Value']
			});
		</script>
			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>