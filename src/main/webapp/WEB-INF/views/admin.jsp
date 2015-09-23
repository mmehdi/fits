<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://www.springframework.org/security/tags'
	prefix='security'%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="content-language" content="cs" />
	
	<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/jquery.dataTables.min.css" />" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
		
	
		<style>
		table.mytable{border:0px;}
		.mytable thead th{}
		.mytable tbody tr{border:0px;}
		.mytable tbody td{border:0px;}	
		

	</style>	
<title>Admin Dashboard</title>
	
</head>
<body>
	<div id="layout">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div id="main">
			<div id="content">


				<div class="row">


					<div class="col-md-10">
					
					<c:if test="${success != null}">
					    <c:when test="${success}.equals('yes')">
					    	<div class="alert alert-success">
					    		<strong>Success!</strong> Indicates a successful or positive action.
					    	</div>
					    </c:when>

					    <c:when test="${success}.equals('no')">
					    	<c:forEach items="${messages}" var="message" varStatus="loop">
					    		<div class="alert alert-danger"> <strong>Error!</strong> ${user}
					    		</div>
					    	</c:forEach>
					    </c:when>
				</c:if>
					    
						<h4>Manage Users</h4>
							<table id="usersTable" class="stripe mytable" cellspacing="0" width="100%">

								<thead>
									<th>No</th>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email</th>
									<th>Phone no</th>
									<th>Role</th>
									<th>Edit</th>

								</thead>
								<tbody>
									<c:forEach items="${Users}" var="user" varStatus="loop">

										<tr>
											<td style="border:0px;">${loop.index+1}</td>
											<td>${user.username}</td>
											<td>${user.fname}</td>
											<td>${user.lname}</td>
											<td>${user.email}</td>
											<td>${user.phone_number}</td>
											<td>${user.role.role}</td>
											<td><a href="edit_user/${user.id}" class="btn btn-default btn-xs">Edit</a>
												</p></td>
										</tr>
									</c:forEach>

								</tbody>

							</table>

							<div class="clearfix"></div>

					</div>
				</div>

			</div>
		</div>
		<div id="footer">
		
		<script type="text/javascript">
		$(document).ready(function() {
			    $('#usersTable').DataTable();
			} );
		</script>
			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>