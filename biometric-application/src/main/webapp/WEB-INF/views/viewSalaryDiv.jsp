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
	<div class="generic-container">
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
								<th>Basic</th>
								<th>Conveyance</th>
								<th>HRA</th>
								<th>LTA</th>
								<th>Medical Reimbursement</th>
								<th>ESI</th>
								<th>Special Allowance</th>
								<th>Income Tax</th>
								<th>Provident Fund</th>
								<th>Profession Tax</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${salaryDivision}" var="division">
								<tr>
									<td data-title="Basic">${division.basicPercentage}</td>
									<td data-title="Conveyance">${division.conveyancePercentage}</td>
									<td data-title="HRA">${division.hraPercentage}</td>
									<td data-title="LTA">${division.ltaPercentage}</td>
									<td data-title="Medical Reimbursement">${division.mrPercentage}</td>
									<td data-title="ESI">${division.esiPercentage}</td>
									<td data-title="Special Allowance">${division.saPercentage}</td>
									<td data-title="Income Tax">${division.incomeTaxPercentage}</td>
									<td data-title="Provident Fund">${division.pfPercentage}</td>
									<td data-title="Profession Tax">${division.ptPercentage}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${fn:length(salaryDivision) > 0}">
				<a href="<c:url value='/edit-salaryDivision${1}' />">Edit Salary
					Break Down</a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/save-salaryDivision' />">Add Salary
					Break Down</a>
			</c:otherwise>
		</c:choose>
	</div>

	</div>
</body>
</html>