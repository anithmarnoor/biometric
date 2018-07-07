<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department</title>

</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">


		<div class="well lead" style="margin-left: 4px;">Department</div>
		<div class="panel panel-default">
			<div class="alert alert-success lead">${success}</div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="department"
					class="form-horizontal">
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="departmentName">Department
								Name</label>
							<div class="col-md-3">
								<form:input type="text" path="departmentName"
									id="departmentName" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="departmentName" class="help-inline" />
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
										href="<c:url value='listDesignations' />">Cancel</a>
								</c:when>
								<c:otherwise>
									<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='listDesignations' />">Cancel</a>
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