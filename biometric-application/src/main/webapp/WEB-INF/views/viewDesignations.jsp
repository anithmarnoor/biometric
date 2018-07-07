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
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead" style="margin-left: 326px;">List of Designations and its corresponding
					department</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>ID.</th>
							<th>Designation Name</th>
							<th>Department ID</th>
							<th>Department Name</th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${designations}" var="designation">
							<tr>
								<td>${designation.designationId}</td>
								<td>${designation.designationName}</td>
								<td>${designation.department.departmentId}</td>
								<td>${designation.department.departmentName}</td>

								<td><a
									href="<c:url value='/delete-designation-${designation.designationId}' />"
									class="btn btn-danger custom-width">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<%-- <div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Add Department</span>
			</div>

			<div class="uploadcontainer">
				<%@include file="adddepartment.jsp"%>
			</div>
		</div> --%>

	</div>
</body>
</html>