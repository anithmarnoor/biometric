<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance Log</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Attendance</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>No.</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th>User ID</th>
							</sec:authorize>
							<th>Date</th>
							<th>Logged Hours</th>
							<th>Status</th>
							<!-- <th width="100"></th>
							<th width="100"></th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userAttendanceLog}" var="log"
							varStatus="counter">
							<tr>
								<td>${counter.index + 1}</td>
								<sec:authorize access="hasRole('ADMIN')">
									<td>${log.userId}</td>
								</sec:authorize>
								<td>${log.year}/${log.month}/${log.date}</td>
								<c:choose>
									<c:when test="${log.status ne 'WEEKEND'}">
										<td>${log.noOfHours} hrs ${log.noOfMins} mins</td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>	
								<td>${log.status}</td>
								<%-- <td><a
									href="<c:url value='/download-document-${doc.id}' />"
									class="btn btn-success custom-width">download</a></td> --%>
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
				<span class="lead">Search Attendance</span>
			</div>

			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="userBiometricData"
					class="form-horizontal" action="view-Attendance">

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="userId">User</label>
							<div class="col-md-7">
								<form:select path="userId" items="${usersList}"
									multiple="false" itemValue="id"
									itemLabel="userName" class="form-control input-sm"
									id="roles" />
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
								<form:input path="year" id="year" placeholder="YYYY" />
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
								<form:input path="month" id="month" placeholder="MM" />
								<div class="has-error">
									<form:errors path="month" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row" style="color: red">${error}</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Search Attendance"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='view-searchAttendance' />">Cancel</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>