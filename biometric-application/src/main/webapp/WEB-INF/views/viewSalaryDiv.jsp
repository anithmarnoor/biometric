<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salary Divisions</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="row">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">Salary Break Down Components</span>
				</div>
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf" id="thcolor">
							<tr>
								<th>Component ID</th>
								<th>Component Name</th>
								<th>Is Deductible From Salary?</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${salaryDivisionList}" var="division">
								<tr>
									<td data-title="Component ID">${division.componentId}</td>
									<td data-title="Component Name">${division.componentName}</td>
									<td data-title="Is Deductible">${division.isDeductible}</td>
									<td><a
										href="<c:url value='/salaryDivision-edit-${division.componentId}' />">Edit</a></td>
									<td><a
										href="<c:url value='/salaryDivision-delete-${division.componentId}' />">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="alert alert-success lead">${error}${success}</div>
				<span class="lead">Add Salary BreakDown Components</span>
			</div>
			<div class="uploadcontainer">
				<%@include file="addsalarydivision.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>