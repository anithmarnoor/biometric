<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salary Division</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<div class="alert alert-success lead">${error}</div>
		<div class="well lead">Please enter Daily Normal 8hr shift and Overtime wages. Based on this, monthly salary will be calculated.</div>
		<form:form method="POST" modelAttribute="wages"
			class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="normalShift">Normal 8hr shift</label>
					<div class="col-md-7">
						<form:input type="text" path="normalShift"
							id="normalShift" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="normalShift" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="perTrip">Per Trip</label>
					<div class="col-md-7">
						<form:input type="text" path="perTrip"
							id="perTrip" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="perTrip" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="overtime">Overtime</label>
					<div class="col-md-7">
						<form:input type="text" path="overtime"
							id="overtime" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="overtime" class="help-inline" />
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
								href="<c:url value='view-wages' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='view-wages' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>