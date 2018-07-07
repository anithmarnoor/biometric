<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PaySlip</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="alert alert-success lead">${error}</div>
		<div class="row">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">PaySlip Data </span>
				</div>
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf">
							<tr>
								<th>Component</th>
								<th>Amount</th>
							</tr>
						<c:forEach items="${paySlips}" var="slip" varStatus="counter">
							<tr>
								<td>${slip.componentName}</td>
								<td>${slip.value}</td>
							</tr>
						</c:forEach>
						<%-- <tr>
							<th>Month</th>
							<th>Year</th>
							<th>Attendance</th>
							<th>Over Time Hours</th>
						</tr>
						<tr>
						<td>${paySlips.get(0).month}</td>
						<td>${paySlips.get(0).year}</td>
						<td>${paySlips.get(0).attendance}</td>
						<td>${paySlips.get(0).totalSalary}</td>
						<td>${paySlips.get(0).salaryAfterDecuction}</td>
						<td>${paySlips.get(0).overTimeAmount}</td>
						<td>${paySlips.get(0).overTimeHours}</td>
						<td>${paySlips.get(0).salaryAfterDecuction + paySlips.get(0).overTimeAmount}</td>
						</tr> --%>
						
						</thead>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>