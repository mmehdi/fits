<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>Home page</title>
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
	
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	
	<style>
	h1 { font-size:200%; } /*  #93b21a;*/
	h2 { font-size:160%; font-weight: normal; } /*  #93b21a;*/
	</style>
	
	<script>

	$(document).ready(function() {
		
		$('#send_mail').click(function(e){
			  e.preventDefault();
			  console.log("link a is clicked ");
			  $.ajax({
				    url: "sendMailSimple",  //   sendmail
				    type: 'POST',
				    dataType: 'json',
				    data: "",
				    contentType: 'application/json',
				    mimeType: 'application/json',
				    success: function(data) {
//				    	alert("success");
				    },
				    error:function(data,status,er) {
				        console.log(data);
				        console.log(er);
				    }
				});

			});
		
		
	});
	</script>
	
</head>
<body>

<div id="layout">
       <div id="header">
         <%@ include file="header.jsp" %>
      </div>
      <div id="main">
        <div id="content">
        	 <h1 >People</h1>
                <h2>Investigators:</h2>
					<p><strong>Principal Investigator:</strong> <a href="http://www.abdn.ac.uk/cops/people/collapp/profiles/j.d.nelson" target="_blank">Professor John D. Nelson</a></p>
					<p><strong>Co-Investigator:</strong> <a href="http://www.abdn.ac.uk/cops/people/hod/profiles/t.j.norman" target="_blank">Professor Timothy Norman</a></p>
					<p><strong>Co-Investigator:</strong> <a href="http://www.abdn.ac.uk/cops/people/profiles/j.farrington" target="_blank">Professor John Farrington</a></p>
					<p><strong>Associate Investigator:</strong> <a href="https://www.abdn.ac.uk/business/profiles/tim.barmby" target="_blank">Professor Tim Barmby</a>;<a href="http://www.abdn.ac.uk/geosciences/people/profiles/s.d.wright" target="_blank"> Dr. Steve Wright</a>; <a href="http://www.abdn.ac.uk/ncs/profiles/n.oren/" target="_blank">Dr. Nir Oren</a></p>
				<h2>Project Staff</h2>
				<p><strong>Research Fellow (ITS):</strong> <a href="http://www.abdn.ac.uk/geosciences/people/profiles/r.mounce/" target="_blank">Dr. Richard Mounce</a>  <!--  a href="http://www.civil.iitb.ac.in/~velaga/">Dr. Nagendra R Velaga(IIT Bombay)</a></p-->
				<p><strong>Research Fellow (Computing science):</strong> <a href="http://homepages.abdn.ac.uk/c.emele/pages/davidsite/index.html" target="_blank">Dr. Chukwuemeka  David Emele</a></p>
				<p><strong>Research Assistant (Computing science): </strong><a href="http://www.abdn.ac.uk/ncs/profiles/c.zeng/" target="_blank">Cheng Zeng</a></p>
				<p><strong>Research Assistant (Computing science): </strong><a href="mailto:mmehdi@abdn.ac.uk">Mujtaba Mehdi</a>
				<!--  h2>Ph.D Students</h2>
					<p>Robert Henry Craig</p-->
				<!-- h2>Other</h2>
					<p><strong>Projects Manager, Centre for Transport Research:</strong><a href="http://www.abdn.ac.uk/cops/people/collapp/profiles/brian.masson"> Brian Masson</a></p -->
					<p> </p>
					<a id="send_mail" href="#" title="top" style = "float: right; width: 100%; text-align: right; font-size: 13px; padding-right: 10px;">top send mail</a>
				<br/>
        </div>
	</div>
    <div id="footer">
		<%@ include file="footer.jsp" %>
    </div>
</div>
        
</body>
</html>