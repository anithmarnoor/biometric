<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src='<c:url value='/static/jQuery/bootstrap.min.js'/>'></script>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="well lead" style="margin-left: 4px;">Edit Details</div>
		<form:form method="POST" modelAttribute="editUser" class="form-horizontal" action="editUser">
			<form:input type="hidden" path="id" id="id" />

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">First
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Last
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="gender">Gender</label>
					<div class="col-md-7">
						<form:radiobutton path="gender" id="gender" value="Male"
							label="Male" />
						<form:radiobutton path="gender" id="gender" value="Female"
							label="Female" />
						<div class="has-error">
							<form:errors path="gender" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="dob">Date Of
						Birth</label>
					<div class="col-md-7">
						<form:input path="dob" id="dob" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="dob" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="address">Address</label>
					<div class="col-md-7">
						<form:textarea path="address" id="address" rows="4" cols="30"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userName">Choose
						UserName</label>
					<div class="col-md-7">
						<form:input type="text" path="userName" id="userName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="phoneNo">Phone
						No</label>
					<div class="col-md-7">
						<form:input type="text" path="phoneNo" id="phoneNo"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="phoneNo" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<sec:authorize access="hasRole('ADMIN')">
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="doj">Date Of
							Joining</label>
						<div class="col-md-7">
							<form:input path="doj" id="doj" class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="doj" class="help-inline" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="userProfile">Roles</label>
						<div class="col-md-7">
							<form:select path="userProfile" items="${roles}" multiple="false"
								itemValue="id" itemLabel="type" class="form-control input-sm"
								id="roles" />
							<div class="has-error">
								<form:errors path="userProfile" class="help-inline" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="designation">Designation</label>
						<div class="col-md-7">
							<form:select path="designation" items="${designations}"
								multiple="false" itemValue="designationId"
								itemLabel="designationName" class="form-control input-sm"
								id="roles" />
							<div class="has-error">
								<form:errors path="designation" class="help-inline" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="overTime">OVERTIME</label>
						<div class="col-md-7">
							<form:radiobutton path="overTime" id="overTime" value="Full"
								label="Full" />
							<br>
							<form:radiobutton path="overTime" id="overTime" value="Half"
								label="Half" />
							<br>
							<form:radiobutton path="overTime" id="overTime"
								value="One And Half" label="One And Half" />
							<div class="has-error">
								<br>
								<form:errors path="overTime" class="help-inline" />
							</div>
						</div>
					</div>
				</div>
			</sec:authorize>
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Update" class="btn btn-primary btn-sm" />
					or <a href="<c:url value='/list' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>