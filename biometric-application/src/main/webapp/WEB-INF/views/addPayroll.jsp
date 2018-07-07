<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User PayRoll Form</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src='<c:url value='/static/jQuery/bootstrap.min.js'/>'></script>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="well lead" style="margin-left: 4px;">Payroll
			Settings</div>
		<form:form method="POST" modelAttribute="user" class="form-horizontal"
			action="addPayRoll">
			<input type="hidden" name="userId" value="${userId}" />
			<div class="alert alert-success lead">${success}</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">Components</label>
					<div class="col-md-7">
						<%-- <c:if test="${!isComponentsPresent}">
							<span class="well floatRight"> Already Payroll data
								defined. Click <a href="list">Go Back</a> and
								update.

							</span>
						</c:if> --%>
						<c:forEach items="${salaryComponents}" var="component">
							${component.componentName} : <input type="text"
								name="${component.componentName}" />
							<br>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Add" class="btn btn-primary btn-sm" />
					or <a href="<c:url value='/list' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>