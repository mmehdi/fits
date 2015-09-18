<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" />
	
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	
	
<h1 id="logo"><a href="#" title="#">
	Flexible Integrated Transport System</a> 
	<img class = "dotrural" src="<c:url value="/resources/img/dotrural_rgb_transparent.png" />"></img>
</h1> 

<div id="navigation">
	<ul>
		<li id="first"><a href="/home" title="home">Home</a></li>
	  	<li>
	  	<security:authorize ifAnyGranted="ROLE_USER">
	  	<span style = "font-size: 120%; margin: 5px 1px 5px 0px;">Login: <security:authentication property="principal.username"/>,</span>
	  		<a style = "padding-left: 1px; font-size: 95%" href=<c:url value='/logout'/>>Logout</a>
   		</security:authorize>
    	<security:authorize ifNotGranted="ROLE_USER">
    	<a href="login">Login</a>
    	</security:authorize>
	  	</li>
	  	<li><a href="query_fts" title="query">Search for trips</a></li>
	    <li><a href="operator_data_input_constraint" title="data input template">Enter Service Data</a></li>
	    <li><a href="operator_data_input_constraint_update" title="data input template">Update Service Data</a></li>
	  	<li id="last"><a href="about" title="">About us</a></li>
	</ul>
</div>

<!--  ${pageContext.request.servletPath} -->

<nav class="navbar navbar-default" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Home</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/admin">Manage users</a></li>
							<li><a href="${pageContext.request.contextPath}/reporting">Reporting</a></li>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/query_fts">Search for trips</a></li>
					<li class="dropdown">
						<a href="" class="dropdown-toggle" data-toggle="dropdown">Service Data <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/operator_data_input_constraint">Enter Service Data</a></li>
							<li><a href="${pageContext.request.contextPath}/operator_data_input_constraint_update">Update Service Data</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				
					  	<security:authorize ifAnyGranted="ROLE_USER">
					  	<li class="navbar-text">Logged in: <security:authentication property="principal.username"/>,</li> 
					  		<li><a href=<c:url value='${pageContext.request.contextPath}/logout'/>>Logout</a></li>
				   		</security:authorize>
				    	<security:authorize ifNotGranted="ROLE_USER">
				    	<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
				    	</security:authorize>
    					    	<li><a href="${pageContext.request.contextPath}/about">About us</a></li>
    
				</ul>
			</div><!-- /.navbar-collapse -->
		</nav>