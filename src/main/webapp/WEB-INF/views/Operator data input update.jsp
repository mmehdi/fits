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
	<title>Update Operator data input template</title>
	<!-- link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui/1.10.3/jquery-ui.css" />" -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
    <script src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
    
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
        
        <h4>Update Service Data</h4>
      
          <br/>
         
         
         
         <c:if test="${not empty warning_no_operators}">
         	
         	<p style="color:red;margin-top:0;" >Warning: There is no service data associated with your account. Please create the service data for your service first.</p>
         
         	<div class="form-group">
	    	<label for="operator">Please select the operator</label>
	      	<select name="operator"  class="form-control" >
	      		<c:if test="${not empty operators}">
					<c:forEach items="${operators}" var="operator_name">
						<option value="${operator_name}">${operator_name}</option>
					</c:forEach>	
				</c:if>	
	        </select>
	  		</div>
	  		<button type="submit" class="btn btn-default">Submit</button>
	  		<button type="reset"  class="btn btn-default">Reset</button>
         </c:if>
         <c:if test="${empty warning_no_operators}">
	       	<form action="selectOperatorUpdate" method="post">
	  		<div class="form-group">
	    	<label for="operator">Please select the operator</label>
	      	<select name="operator"  class="form-control" >
	      		<c:if test="${not empty operators}">
					<c:forEach items="${operators}" var="operator_name">
						<option value="${operator_name}">${operator_name}</option>
					</c:forEach>	
				</c:if>	
	        </select>
	  		</div>
	 
	  		<button type="submit" class="btn btn-default">Submit</button>
	  		<button type="reset"  class="btn btn-default">Reset</button>
			</form>
		</c:if>
        
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
                
                
       
    </div>
     </div>
     <div id="footer">
		<%@ include file="footer.jsp" %>
     </div>
 </div>
 
</body>
</html>