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

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
		
	
	
<title>Edit User</title>
</head>
<body>
	<div id="layout">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div id="main">
			<div id="content">
			<h4>Edit user</h4>
				<div class="row">


					<div class="col-md-10">
					
					<c:if test="${success != null}">
						<c:choose>
                			 <c:when test="${success eq true}">
						    	<div class="alert alert-success">
						    		<strong>Success!</strong> User successfully updated.
						    	</div>                    		
						    </c:when>
                			 <c:when test="${success eq false}">						    		
						    	<c:forEach items="${messages}" var="message" varStatus="loop">
						    		<div class="alert alert-danger"> <strong>Error!</strong> ${message}
						    		</div>
						    	</c:forEach>
					    	</c:when>
              			  </c:choose>
              		</c:if>
					    	
					    	<c:if test="${user_id!= null}">
					    	
									<form class="form-horizontal" method="post" modelAttribute="editUserFormBean" name="editUserForm" id="editUserForm">
											
											<input type="hidden" name="user_id" id="user_id"
													value="${user_id}">
													
										<div class="control-group">
											<label class="control-label">Username</label>
											<div class="controls">
												<input type="text" name="userName" id="userName" title="Username"
													value="${username}" readonly>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Email</label>
											<div class="controls">
												<input type="text" name="email" id="email" title="Email"
													value="${email}" readyonly>
											</div>
										</div>
															
										<div class="control-group">
											<label class="control-label">First Name</label>
											<div class="controls">
												<input type="text" name="fname" id="fname"
													title="First Name" value="${fname}">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Last Name</label>
											<div class="controls">
												<input type="text" name="lname" id="lname"
													title="Last Name" value="${lname}" required>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">Phone number</label>
											<div class="controls">
												<input type="text" name="phone" id="phone"
													title="Phone Number" value="${phone}" required>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">password</label>
											<div class="controls">
												<input type="password" name="password" id="password"
													title="Password" value="${password}">
											</div>
										</div>
										<div class="form-actions">
											<button type="submit" class="btn btn-success">Submit</button>
											<a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/admin">Cancel</a>
										</div>
									</form>
				</c:if>
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