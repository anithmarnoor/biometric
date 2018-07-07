<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<script type="text/javascript">
	function submitEditAction() {
		document.getElementById("submitForm").action = "editUserView";
		document.getElementById("submitForm").submit();
	}
	function submitDeleteAction() {
		document.getElementById("submitForm").action = "deleteUser";
		document.getElementById("submitForm").submit();
	}
	function submitPayrollAction() {
		document.getElementById("submitForm").action = "addPayRoll";
		document.getElementById("submitForm").submit();
	}
</script>
</head>

<body>
	<%@include file="authheader.jsp"%>

	<%-- 	<div class="generic-container-1">
		<div class="row">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead" style="margin-left: 27px;">Profile</span>
				</div>
				<div class="col-md-12">
					<label>Name: </label><label><label class="col=xs=9">${profile.firstName}</label>
						<label class="col=xs=9">${profile.lastName}</label></label><br> <label>Address: </label><label><label class="col=xs=9">${profile.address}</label></label><br>
					<label>DOB: </label><label><label class="col=xs=9">${profile.dob}</label></label><br> <label>Email
						ID: </label><label><label class="col=xs=9">${profile.email}</label></label><br> <label>Phone
						No: </label><label><label class="col=xs=9">${profile.phoneNo}</label></label><br> <label>Date
						Of Joining: </label><label><label class="col=xs=9">${profile.doj}</label></label><br> <label>ID:
					</label><label><label class="col=xs=9">${profile.id}</label></label><br> <label>User Name: </label><label><label class="col=xs=9">${profile.userName}</label></label><br>
					<label>DL No: </label><label><label class="col=xs=9">${profile.dlNo}</label></label><br>
					<label> <a
						href="<c:url value='/edit-user-<label class="col=xs=9">${profile.userName}</label>' />"
						class="btn btn-success custom-width">Edit</a>
					</label>
				</div>
			</div>
		</div>
	</div> --%>

	<div class="generic-container-2">
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Profile</span>
			</div>
			<div class="alert alert-success lead">${message}</div>

			<div class="uploadcontainer">
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="name">Name</label>
						<div class="col-md-7">${profile.firstName}
							${profile.lastName}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="address">Address</label>
						<div class="col-md-7">${profile.address}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="dob">DOB</label>
						<div class="col-md-7">${profile.dob}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="email">E-mail</label>
						<div class="col-md-7">${profile.email}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="address">Phone
							NO</label>
						<div class="col-md-7">${profile.phoneNo}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="doj">Date of
							Joining</label>
						<div class="col-md-7">${profile.doj}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="userId">User Id</label>
						<div class="col-md-7">${profile.id}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="userName">UserName</label>
						<div class="col-md-7">${profile.userName}</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="designation">Designation</label>
						<div class="col-md-7">${profile.designation.designationName}</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="status">Status</label>
						<div class="col-md-7">${profile.status}</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${profile.status eq 'Left'}">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="status">Left
									Date</label>
								<div class="col-md-7">${profile.leftDate}</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="status">Role</label>
								<div class="col-md-7">${profile.userProfile.type}</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="row">
					<div class="form-actions floatRight">
						<form:form id="submitForm" method="POST" modelAttribute="user">
							<input type="hidden" name="userId" value="${profile.id}" />
							<input type="button"
								class="btn btn-block btn-primary btn-default"
								onclick="submitEditAction()" value="Edit Details">
							<sec:authorize access="hasRole('ADMIN')">

								<input type="button"
									class="btn btn-block btn-primary btn-default"
									onclick="submitDeleteAction()" value="Delete">

								<input type="button"
									class="btn btn-block btn-primary btn-default"
									onclick="submitPayRollAction()" value="Define Payroll">
							</sec:authorize>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>