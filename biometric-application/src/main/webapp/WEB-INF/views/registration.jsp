<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>

<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src='<c:url value='/static/jQuery/jQuery.js'/>'></script>

<title>Spring MVC</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">


		<div class="well lead">Registration Form</div>
		<form:form method="POST" modelAttribute="user" class="form-horizontal">
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
						<%-- 	<c:choose>
							<c:when test="${edit}">
								<form:radiobutton path="gender" id="gender" value="Male"
									disabled="true" label="Male" />
								<form:radiobutton path="gender" id="gender" value="Female"
									disabled="true" label="Female" />
							</c:when>
							<c:otherwise> --%>
						<form:radiobutton path="gender" id="gender" value="Male"
							label="Male" />
						<form:radiobutton path="gender" id="gender" value="Female"
							label="Female" />
						<div class="has-error">
							<form:errors path="gender" class="help-inline" />
						</div>
						<%-- </c:otherwise>
						</c:choose> --%>

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
					<label class="col-md-3 control-lable" for="doj">Date Of
						Joining</label>
					<div class="col-md-7">
						<%-- <c:choose>
							<c:when test="${edit}">
								<form:input path="doj" id="doj" class="form-control input-sm"
									disabled="true" />
							</c:when>
							<c:otherwise> --%>
						<form:input path="doj" id="doj" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="doj" class="help-inline" />
						</div>
						<%-- </c:otherwise>
						</c:choose>
 --%>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userName">Choose
						UserName</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="userName" id="userName"
									class="form-control input-sm" disabled="true" />
							</c:when>
							<c:otherwise>
								<form:input type="text" path="userName" id="userName"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="userName" class="help-inline" />
								</div>
							</c:otherwise>
						</c:choose>
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
					<label class="col-md-3 control-lable" for="phoneNo">Phone No</label>
					<div class="col-md-7">
						<form:input type="text" path="phoneNo" id="phoneNo"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="phoneNo" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<sec:authorize access="hasRole('ADMIN') ">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
						<div class="col-md-7">
							<form:select path="userProfiles" items="${roles}"
								multiple="false" itemValue="id" itemLabel="type"
								class="form-control input-sm" id="roles" />
							<div class="has-error">
								<form:errors path="userProfiles" class="help-inline" />
							</div>
						</div>
					</div>
				</sec:authorize>
			</div>

			<div class="row">
				<div class="is2">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="password">Driving
							License Number</label>
						<div class="col-md-7">
							<%-- <c:choose>
							<c:when test="${edit}">
								<form:input type="dlNo" path="dlNo" id="dlNo"
									class="form-control input-sm" disabled="true" />
							</c:when>
							<c:otherwise> --%>
							<form:input type="dlNo" path="dlNo" id="dlNo"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="dlNo" class="help-inline" />
							</div>
							<%-- </c:otherwise>
						</c:choose>
 --%>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/list' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/list' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>