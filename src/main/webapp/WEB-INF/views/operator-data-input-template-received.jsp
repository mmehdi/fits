<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>Operator data input template</title>
	<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
	<link href="<c:url value="/resources/css/messages/messages.css" />" rel="stylesheet"  type="text/css" />
	
	 <style TYPE="text/css">
		.errormessage { color:red}
		.successmessage { color:red}
	</style>
	
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=drawing,geometry"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.9/jquery-1.9.1.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/geoxml/polys/geoxml3.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/geoxml/ProjectedOverlay.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/jscolor/jscolor.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/json2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/xmlwriter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/blitz.gmap3.js" />"></script>
	<script type="text/javascript">
	function init() {
		BlitzMap.setMap( 'myMap', true, 'mapData' );
		BlitzMap.init();// initialize BlitzMap
		BlitzMap.toggleEditable();// swith not to be editable
	}
	</script>
	
	
</head>
<body onload = "init()">

<div id="layout">
	<div id="header">
		<%@ include file="header.jsp" %>
    </div>
    <div id="main">
    	<div id="main-box">
        	<div id="quote">
        	"If we could get a system in place...with a common booking entry, 
        	so that somebody could call up a vehicle that was available, irrespective
        	 of whether that was actually in the ownership of the health board, the council, 
        	 the third sector, then it might lead to more viability, more sustainability 
        	 all round...That is what we should be aiming for."
		        <span style = "font-size: 14px;">---  Audit Scotland, 2011.</span>
        	</div>
        </div>
        <div id="content">
			<div id="formsContent">
					<h2>Your operating area has been saved.</h2>
					<div id="myMap" style="height:400px; width:100%;"></div>
				
					<textarea id="mapData" style="width:100%; height:300px;display:none;">
					${jsonStr}
					</textarea>
					
			</div>
			
			
			
	<input type="submit" value="Draw it again" 
    onclick="window.location='operator_data_input';" style="margin-left: 5px;margin-top:12px;"/>      
			
	<input type="submit" value="Go to next" 
    onclick="window.location='operator_data_input_constraint';" style="margin-left: 550px;margin-top:12px;" />     
        </div>
	</div>
    <div id="footer">
		<%@ include file="footer.jsp" %>
   	</div>
</div>
       
</body>
</html>