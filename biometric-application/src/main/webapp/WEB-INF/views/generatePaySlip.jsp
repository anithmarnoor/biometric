<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PaySlip Generation</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<div class="well lead">PaySlip Generation</div>
		<form:form method="POST" modelAttribute="userBiometricData"
			class="form-horizontal">

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userId">User Id</label>
					<div class="col-md-7">
						<form:input path="userId" id="userId" />
						<div class="has-error">
							<form:errors path="userId" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="year">Year</label>
					<div class="col-md-7">
						<form:input path="year" id="year" />
						<div class="has-error">
							<form:errors path="year" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="month">Month</label>
					<div class="col-md-7">
						<form:input path="month" id="month" />
						<div class="has-error">
							<form:errors path="month" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row" style="color: red">${error}</div>

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Generate PaySlip"
						class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-paySlipGen' />">Cancel</a>
				</div>
			</div>
		</form:form>


	</div>
</body>
</html>