	<h1 id="logo"><a href="#" title="#">
	Flexible Integrated Transport System</a> 
	<img class = "dotrural" src="<c:url value="/resources/img/dotrural_rgb_transparent.png" />"></img>
</h1> 

<!--  ${pageContext.request.servletPath} -->
 
<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Home</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				
				<security:authorize ifAnyGranted="ROLE_ADMIN">
					<li class="dropdown">
						<a href="" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/admin">Manage users</a></li>
							<li><a href="${pageContext.request.contextPath}/reports">Reports</a></li>
						</ul>
					</li>
					</security:authorize>
					
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
			</div>
		</nav>