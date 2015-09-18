<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>Home</title>
	
		<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/jquery.dataTables.min.css" />" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

	
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
        	<h2>Flexible Integrated Transport Systems</h2>
        	
				There is strong evidence that the lack of an integrated
				approach to passenger transport has been consistently
				raised as a problem, resulting in a number of issues such
				as duplication of provision, badly informed choices, limited
				 <img width="180" height="180" alt="" src="<c:url value="/resources/img/home1.jpg" />" style="float: right;"></img>
				options and delays for patients trying to access healthcare,
				difficulty in connecting shopping destinations, etc.
				<br />
	      		<br />
	      		Given this context, how can intelligent agents support the decision-making and
			    information seeking activities of individuals and groups to best meet their transport needs? There are many research challenges here. 
			    Information from various sources may be more or less trusted by the user; how can evidence from such varied sources be combined so that an agent can best advise a user of the transport options that meet their needs with justifications for these options. 
	     		how can an agent best advise the user on the implications of the constraints they impose? 
	     		<h3>Problem </h3>
			    There is limited conventional (fixed route) public transport in rural areas. Numerous flexible transport providers do operate in rural areas but most of these flexible services have been introduced as stand-alone services often to cater for a specific group of the population or to fill a specific need.  Eligibility criteria often apply resulting in limited options for passengers.
		     	Large public subsidies go into supporting many of these services
     
	    		<h3>Approach</h3>
	     		The technological approach that is proposed here is the use of intelligent agents that act
	        	on behalf of the stakeholders within the system, and the use of argumentation mechanisms to identify options open to users, weighing the evidence for desirability of each option given a model of the user's priorities, and to drive dialogue among agents in aiding users to solve their individual (or collective) transport goals. Existing research in agent support for transport resource management has typically been focussed on the provider. 
	        	Working with the Grampian Health Transport Action Plan (HTAP), team, our vision is to explore both the efficient use of limited transport resources, but also to support the passenger in the decision-making process.
	        	<br/>
	        	<h3>Objectives</h3>
	        	Working closely with Aberdeenshire Council and HTAP, the FITS will aim 
	        	<ul>
	        		<li>
	        		to increase choice for passengers within existing funding constraints;
	        		</li>
	        		<li>to explore the possibilities to relax tight restrictions on who they carry by allowing allocation of passengers to services;</li>
	   
	   				<li>to investigate in what manner this relaxation can be done that is fair and equitable to both passengers and operators while still respecting the underlying ethos of the operator/service provider.</li>
	        	
	        	</ul>
				<a href="home#" title="top" style = "float: right; width: 100%; text-align: right; font-size: 13px; padding-right: 10px;">top</a>
				<br/>
        </div>
     </div>
     <div id="footer">
		<%@ include file="footer.jsp" %>
     </div>
 </div>
 
</body>
</html>