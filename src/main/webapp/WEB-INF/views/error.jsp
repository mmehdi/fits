<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>Resource is expired</title>
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
</head>
<body>

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
        
        <h2>An ERROR happened </h2>
        <p>
         The query result has already expired. 
        </p>
        <br/>
        <br/>
        <p>
          <a href="/home"">Back to home page</a>
          
        </p>
    </div>
     </div>
     <div id="footer">
		<%@ include file="footer.jsp" %>
     </div>
 </div>
 
</body>
</html>