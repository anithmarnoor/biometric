<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leaves Available</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Leaves Available</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>ID</th>
							<th>User ID</th>
							<th>Leave Type</th>
							<th>Remaining Leaves</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${leavesAvailableList}" var="availableLeaves">
							<tr>
								<td>${availableLeaves.availableLeavesId}</td>
								<td>${availableLeaves.user.userId}</td>
								<td>${availableLeaves.leaveType.leaveType}</td>
								<td>${availableLeaves.availability}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>