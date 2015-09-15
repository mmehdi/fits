
<h1 id="logo"><a href="#" title="#">
	Flexible Integrated Transport System</a> 
	<img class = "dotrural" src="<c:url value="/resources/img/dotrural_rgb_transparent.png" />"></img>
</h1> 

<div id="navigation">
	<ul>
		<li id="first"><a href="home" title="home">Home</a></li>
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