<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>

<title>Biometric Machine</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Biometric Machine Details</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>Machine ID</th>
							<th>Machine Name</th>
							<th>Machine Model</th>
							<th>Oracle Support</th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${machineList}" var="machine"
							varStatus="counter">
							<tr>
								<td>${machine.machineId}</td>
								<td>${machine.machineName}</td>
								<td>${machine.machineModel}</td>
								<td>${machine.oracleSupport}</td>
								<sec:authorize access="hasRole('ADMIN') ">
									<td><a
										href="<c:url value='/machine-delete-${machine.machineId}' />"
										class="btn btn-danger custom-width">Delete</a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<sec:authorize access="hasRole('ADMIN') ">
			<div>
				<c:choose>
					<c:when test="${edit}">
						<c:set var="action" value="machine-edit-${bioMachine.machineId}" />
					</c:when>
					<c:otherwise>
						<c:set var="action" value="machine-save-" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel panel-default">

				<div class="panel-heading">
					<span class="lead">Add Machine</span>
				</div>
				<div class="alert alert-success lead">${message}</div>
				<div class="uploadcontainer">
					<form:form method="POST" modelAttribute="bioMachine"
						action="${action}" class="form-horizontal">

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="machineName">Machine
									Name</label>
								<div class="col-md-7">
									<form:input path="machineName" id="machineName"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="machineName" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="machineModel">Machine
									Model</label>
								<div class="col-md-7">
									<form:input path="machineModel" id="machineModel"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="machineModel" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="oracleSupport">Is
									Oracle Supported?</label>
								<div class="col-md-7">
									<form:radiobutton path="oracleSupport" value="Yes" />
									Yes
									<form:radiobutton path="oracleSupport" value="No" />
									No
									<div class="has-error">
										<form:errors path="oracleSupport" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-actions floatRight">
								<input type="submit" value="Add" class="btn btn-primary btn-sm" />
								or <a href="<c:url value='machines-list' />">Cancel</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</sec:authorize>
	</div>
</body>
</html>