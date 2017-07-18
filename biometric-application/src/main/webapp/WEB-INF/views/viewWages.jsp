<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wages</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<div class="row">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">Per day Salary Details</span>
				</div>
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf" id="thcolor">
							<tr>
								<th>Normal 8hr shift amount</th>
								<th>Per trip Amount</th>
								<th>Overtime Amount</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${wagesList}" var="wages">
								<tr>
									<td data-title="Normal 8hr shift amount">${wages.normalShift}</td>
									<td data-title="Per trip Amount">${wages.perTrip}</td>
									<td data-title="Overtime Amount">${wages.overtime}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${fn:length(wagesList) > 0}">
				<a href="<c:url value='/edit-wages${1}' />">Edit Wage amounts</a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/save-wages' />">Add Wage amounts</a>
			</c:otherwise>
		</c:choose>
	</div>

	</div>
</body>
</html>