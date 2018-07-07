<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leaves</title>

</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead" style="margin-left: 326px;">Leave Requests</span>
			</div>
			<div class="alert alert-success lead">${success}</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>ID</th>
							<th>Leave Type</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Number of Days</th>
							<th>Reason for taking leave</th>
							<th>Status</th>
							<sec:authorize access="hasRole('ADMIN') ">
								<th>User Id</th>
								<th width="100">Action</th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${leavesList}" var="leave">
							<tr>
								<td>${leave.leaveId}</td>
								<td>${leave.leaveTypes.leaveType}</td>
								<td>${leave.startDate}</td>
								<td>${leave.endDate}</td>
								<td>${leave.noOfDays}</td>
								<td>${leave.comment}</td>
								<td>${leave.leaveStatus}</td>
								<sec:authorize access="hasRole('ADMIN') ">
									<td>${leave.user.id}</td>
									<c:set var="pendingStatus" value="Pending for Approval"></c:set>
									<td><c:if test="${leave.leaveStatus eq pendingStatus}">
											<a href="<c:url value='/Cancel-leaves-${leave.leaveId}' />"
												class="btn btn-danger custom-width">Cancel</a>
											<a href="<c:url value='/Approve-leaves-${leave.leaveId}' />"
												class="btn btn-danger custom-width">Approve</a>
										</c:if></td>

								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>