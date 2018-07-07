<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Designations</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">


		<div class="well lead" style="margin-left: 4px;">Designation
			</div>
		<div class="panel panel-default">
			<div class="alert alert-success lead">${success}</div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="designation"
					class="form-horizontal">
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="designationName">Designation
								Name</label>
							<div class="col-md-6">
								<form:input type="text" path="designationName"
									id="designationName" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="designationName" class="help-inline" />
								</div>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="department">Department</label>
							<div class="col-md-6">
								<form:select path="department" items="${departments}"
									multiple="false" itemValue="departmentId"
									itemLabel="departmentName" class="form-control input-sm"
									id="department" style="margin-left: -97px;"/>
								<div class="has-error">
									<form:errors path="department" class="help-inline" />
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
										href="<c:url value='viewDesignations' />">Cancel</a>
								</c:when>
								<c:otherwise>
									<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='viewDesignations' />">Cancel</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>