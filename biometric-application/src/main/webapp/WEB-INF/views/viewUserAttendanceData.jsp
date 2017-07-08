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
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Attendance</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>No.</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th>User ID</th>
							</sec:authorize>
							<th>Date</th>
							<th>Logged Hours</th>
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
								<td>${log.noOfHours} hrs and ${log.noOfMins} mins</td>
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
					<%@include file="searchAttendance.jsp"%>
				</div>
			</div>

	</div>
</body>
</html>