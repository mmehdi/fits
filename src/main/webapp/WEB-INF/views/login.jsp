<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>User Login</title>
	<link href="<c:url value="/resources/css/messages/messages.css" />" rel="stylesheet"  type="text/css" />
	<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
		
	
	
	 <style TYPE="text/css">
		.errormessage { color:red;}
		.successmessage{color:red;}
	</style>
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
			<div id="formsContent">
				<form:form id="login" method="post" action = "j_spring_security_check" modelAttribute="loginFormBean" cssClass="cleanform">
					<div class="header">
				  		<c:if test="${not empty param.authfailed}">
						    <span id="infomessage" class="errormessage" >
						    Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
						    </span>
						</c:if>
						<c:if test="${not empty param.loggedout}">
						    <span id="infomessage" class="successmessage">
						    You have been successfully logged out.
						    </span>
						</c:if>
					</div>
				  	<fieldset>
				  		<legend>Login</legend>
				  		<form:label path="j_username"  >
				  			User name <form:errors path="j_username" cssClass="error" />
				 		</form:label>
				  		<form:input path="j_username" />
				  		<form:label path="j_password" >
				  			Password <form:errors path="j_password" cssClass="error" />
				 		</form:label>
				  		<form:password path="j_password"/>
				  		<c:if test="${not empty message}">
							<div id="message" class="success">${message}</div>	
				  		</c:if>
				  	</fieldset>
					<p><button type="submit">Submit</button></p>
					<!--label>Don't have a user account? <a href = "register">Sign up now</a></label>-->
				</form:form>
			</div>
        </div>
	</div>
    <div id="footer">
		<%@ include file="footer.jsp" %>
   	</div>
</div>
       
</body>
</html>