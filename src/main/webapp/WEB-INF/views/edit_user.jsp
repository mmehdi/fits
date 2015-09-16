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
<title>Edit User</title>
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


				
					</div>
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
			<%@ include file="footer.jsp"%>
		</div>
	</div>

</body>
</html>