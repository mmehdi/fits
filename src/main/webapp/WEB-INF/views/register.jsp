<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="false" %>

<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
    <meta name="description" content="..." />
    <meta name="keywords" content="..." />
    <meta name="robots" content="all,follow" />
	<title>User Register</title>
	
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
	<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/messages/messages.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
	
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	
</head>
<body>


<div id="layout">
      
       <div id="header">
         <%@ include file="header.jsp" %>
      </div>
          
        <div id="main">        
        <div id="content">
        	<div id="formsContent">
        	
        	<c:if test="${not empty suc}">
        	<p>Dear ${name}</p>
        	<p>You have registered with us. Your username is <span style="font-weight: bold;">${username}.</span> Please go to 
        	<a href="login" title="login">Login/Register</a> page to login.</p>
        	<br/>
        	<p>FITS administrator</p>
        	
        	<br/>
        	<br/>
        	<br/>
        	<br/>
        	<br/>
        	<br/>
        	<br/>
        	
        	
        	</c:if>
        	<c:if test="${ empty suc}">
        		<form:form id="register" method="post" modelAttribute="registerFormBean" action = "register" cssClass="cleanform">
					<div class="header">
				  		<h2>Register Form</h2>
				  		<c:if test="${not empty message}">
							<div id="message" class="success">${message}</div>	
				  		</c:if>
					</div>
				  	<fieldset>
				  		<legend>Personal Info</legend>
				  		<form:label path="fname">
				  			First Name <form:errors path="fname" cssClass="error" />
				 		</form:label>
				  		<form:input path="fname" />
				  		
				  		<form:label path="lname">
				  			Last Name <form:errors path="lname" cssClass="error" />
				 		</form:label>
				  		<form:input path="lname" />
			
				  		<form:label path="email">
				  			Email <form:errors path="email" cssClass="error" />
				 		</form:label>
				  		<form:input path="email" value = ""/>
				  		
				  	
				  		<form:label path="phone">
				  			Telephone <form:errors path="phone" cssClass="error" />
				  		</form:label>
				  		<form:input path="phone" />
			
				  	</fieldset>
				  	
				  	<fieldset>
				  	<legend>User Account</legend>
				  	
				  		<form:label path="userName">
				  			Username <form:errors path="userName" cssClass="error" />
				 		</form:label>
				  		<form:input path="userName" />
			
				  		<form:label path="password">
				  			Password <form:errors path="password" cssClass="error" />
				 		</form:label>
				  		<form:password path="password" />
				  		
				  		<form:label path="passwordRepeat">
				  			Re-enter password <form:errors path="passwordRepeat" cssClass="error" />
				 		</form:label>
				  		<form:password path="passwordRepeat" />
				  	</fieldset>
		
					<p><button type="submit">Submit</button></p>
				</form:form>
        	</c:if>
        	
				
				
				
				
			</div>
			
         </div>
        
        
        </div>
        
        <div id="footer">
        	<%@ include file="footer.jsp" %>
        </div>
        
        </div>
        
        

</body>
</html>