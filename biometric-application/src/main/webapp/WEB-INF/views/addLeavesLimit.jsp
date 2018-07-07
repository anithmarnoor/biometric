<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leaves Limit</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Leaves Limit</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>ID</th>
							<th>Leave Type</th>
							<th>Limit</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${leavesLimitList}" var="leavesLimit"
							varStatus="counter">
							<tr>
								<td>${leavesLimit.leavesLimitId}</td>
								<td>${leavesLimit.leaveType.leaveType}</td>
								<td>${leavesLimit.limit}</td>
								<td><a
									href="<c:url value='/leavesLimit-edit-${leavesLimit.leavesLimitId}' />"
									class="btn btn-success custom-width">Edit</a></td>
								<td><a
									href="<c:url value='/leavesLimit-delete-${leavesLimit.leavesLimitId}' />"
									class="btn btn-danger custom-width">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Add Leaves Limit</span>
			</div>
			<div class="alert alert-success lead">${message}</div>
			<div class="uploadcontainer">
				<c:choose>
					<c:when test="${edit}">
						<c:set var="action" value="leavesLimit-edit-${leavesLimitId}" />
					</c:when>
					<c:otherwise>
						<c:set var="action" value="leavesLimit-save-" />
					</c:otherwise>
				</c:choose>
				<form:form method="POST" modelAttribute="leavesLimit"
					action="${action}" class="form-horizontal">
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="leaveType">Leave
								Type</label>
							<div class="col-md-7">
								<form:select path="leaveType" items="${leaveTypesList}"
									multiple="false" itemValue="leaveTypeId" itemLabel="leaveType"
									class="form-control input-sm" id="designation" />
								<div class="has-error">
									<form:errors path="leaveType" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="limit">Limit</label>
							<div class="col-md-7">
								<form:input type="text" path="limit" id="limit"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="limit" class="help-inline" />
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
										href="<c:url value='leavesLimit-list' />">Cancel</a>
								</c:when>
								<c:otherwise>
									<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='leavesLimit-list' />">Cancel</a>
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