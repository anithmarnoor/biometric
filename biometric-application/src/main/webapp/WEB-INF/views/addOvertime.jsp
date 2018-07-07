<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Overtime Details</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Overtime Details</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>ID</th>
							<th>Overtime Amount</th>
							<th>Designation</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${overtimesList}" var="overtime"
							varStatus="counter">
							<tr>
								<td>${overtime.overTimeId}</td>
								<td>${overtime.overtimeAmount}</td>
								<td>${overtime.designation.designationName}</td>
								<td><a
									href="<c:url value='/overtimes-edit-${overtime.overTimeId}' />"
									class="btn btn-success custom-width">Edit</a></td>
								<%-- <td><a
									href="<c:url value='/delete-BiometricData-${doc.id}' />"
									class="btn btn-danger custom-width">delete</a></td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Add Overtime</span>
			</div>

			<div class="uploadcontainer">
				<c:choose>
					<c:when test="${edit}">
						<c:set var="action" value="overtimes-edit-${overtimeId}" />
					</c:when>
					<c:otherwise>
						<c:set var="action" value="overtimes-save-" />
					</c:otherwise>
				</c:choose>
				<form:form method="POST" modelAttribute="overtime"
					action="${action}" class="form-horizontal">
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="overtimeAmount">Amount</label>
							<div class="col-md-7">
								<form:input type="text" path="overtimeAmount"
									id="overtimeAmount" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="overtimeAmount" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<c:choose>
								<c:when test="${edit}">
									<label class="col-md-2 control-lable" for="designation">Designation</label>
									<div class="col-md-7">
										<form:select path="designation" items="${designations}"
											multiple="false" itemValue="designationId"
											itemLabel="designationName" class="form-control input-sm"
											id="designation" />
										<div class="has-error">
											<form:errors path="designation" class="help-inline" />
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<label class="col-md-2 control-lable" for="designation">Designation</label>
									<div class="col-md-7">
										<form:select path="designation" items="${designations}"
											multiple="false" itemValue="designationId"
											itemLabel="designationName" class="form-control input-sm"
											id="designation" />
										<div class="has-error">
											<form:errors path="designation" class="help-inline" />
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<c:choose>
								<c:when test="${edit}">
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='overtimes-list' />">Cancel</a>
								</c:when>
								<c:otherwise>
									<c:if test="${designations.size() > 0}">
										<input type="submit" value="Add"
											class="btn btn-primary btn-sm" /> or <a
											href="<c:url value='overtimes-list' />">Cancel</a>
									</c:if>

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