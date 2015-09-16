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
<title>Admin Dashboard</title>
<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/resources/css/screen.css" />" type="text/css"
	rel="stylesheet" />

<link href="<c:url value="/resources/css/bootstrap.css" />"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
</head>
<body>
	<div id="layout">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div id="main">
			<div id="main-box">
				<div id="quote">
					"If we could get a system in place...with a common booking entry,
					so that somebody could call up a vehicle that was available,
					irrespective of whether that was actually in the ownership of the
					health board, the council, the third sector, then it might lead to
					more viability, more sustainability all round...That is what we
					should be aiming for." <span style="font-size: 14px;">---
						Audit Scotland, 2011.</span>
				</div>
			</div>
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
						<div class="table-responsive">


							<table id="usersTable" class="table table-bordred table-striped">

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

										<tr data-toggle="modal" data-username="${user.username}" data-id="${user.id}" data-email="${user.email}" data-fname="${user.fname}" data-lname="${user.lname}" data-phone="${user.phone_number}" data-password="${user.password}">
											<td>${loop.index+1}</td>
											<td>${user.username}</td>
											<td>${user.fname}</td>
											<td>${user.lname}</td>
											<td>${user.email}</td>
											<td>${user.phone_number}</td>
											<td>${user.role.role}</td>
											<td><a href="edit_user/${user.id}" class="btn btn-primary btn-sm">Edit</a>
												<!--  
												<p data-placement="top" data-toggle="tooltip"
													title="Edit">
												 <button class="btn btn-primary btn-xs" data-title="Edit"
														data-toggle="modal" data-target="#userEditModal" 
														data-backdrop="true" data-keyboard="true">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>-->
												</p></td>
										</tr>
									</c:forEach>

								</tbody>

							</table>

							<div class="clearfix"></div>
						</div>

					</div>
				</div>



				<div class="modal fade" id="userEditModal1" tabindex="-1"
					role="dialog" aria-labelledby="userEditModal" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>
								<h4 class="modal-title custom_align" id="Heading">Edit user
									details</h4>
							</div>
							<div class="modal-body">
								<fieldset>
								
									<form class="form-horizontal" method="post" modelAttribute="editUserFormBean" name="userEditForm" id="userEditForm">
									
									<input type="hidden" name="user_id" id="user_id"
													value="">
													
										<div class="control-group">
											<label class="control-label">First Name</label>
											<div class="controls">
												<input type="text" name="firstName" id="firstName"
													title="First Name" value="" required>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Last Name</label>
											<div class="controls">
												<input type="text" name="lastName" id="lastName"
													title="Last Name" value="" required>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Username</label>
											<div class="controls">
												<input type="text" name="username" id="username" title="Username"
													value="" readonly>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Email</label>
											<div class="controls">
												<input type="text" name="email" id="email" title="Email"
													value="" readonly>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Phone number</label>
											<div class="controls">
												<input type="text" name="phone" id="phone"
													title="Phone Number" value="" required>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">password</label>
											<div class="controls">
												<input type="password" name="password" id="password"
													title="Password" value="">
											</div>
										</div>
										<div class="form-actions">
											<button type="submit" class="btn btn-success">Submit</button>
											<button type="button" class="btn">Cancel</button>
										</div>
									</form>
								</fieldset>
							</div>
							<div class="modal-footer ">
								<!-- <button type="button" class="btn btn-warning btn-lg"
									style="width: 100%;">
									<span class="glyphicon glyphicon-ok-sign"></span> Update
								</button> -->
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>



				<div class="modal fade" id="delete" tabindex="-1" role="dialog"
					aria-labelledby="edit" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>
								<h4 class="modal-title custom_align" id="Heading">Delete
									this user</h4>
							</div>
							<div class="modal-body">

								<div class="alert alert-danger">
									<span class="glyphicon glyphicon-warning-sign"></span> Are you
									sure you want to delete this user?
								</div>

							</div>
							<div class="modal-footer ">
								<button type="button" class="btn btn-success">
									<span class="glyphicon glyphicon-ok-sign"></span> Yes
								</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> No
								</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>



			</div>
		</div>
		<div id="footer">
		
		<script type="text/javascript">
	$(document).ready(function() {
				$('#userEditModal').modal({
					show : false,

				}).on('show', function() {
							var userID = $(event.target).closest(
							'tr').data('id');
							var username = $(event.target).closest(
							'tr').data('username'); 
							var firstNameFromRow = $(event.target).closest(
									'tr').data('fname'); 
							var lastNameFromRow = $(event.target).closest(
							'tr').data('lname'); 
							var phone = $(event.target).closest(
							'tr').data('phone'); 
							var email = $(event.target).closest(
							'tr').data('email'); 
							var password = $(event.target).closest(
							'tr').data('password'); 
								
								$('#user_id').val(userID);
								$('#username').val(username);
								$('#firstName').val(firstNameFromRow);
								$('#lastName').val(lastNameFromRow);
								$('#phone').val(phone);
								$('#email').val(email);
								$('#password').val(password);
						});
			});
		</script>


			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>