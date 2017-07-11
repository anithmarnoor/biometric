<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
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
								<th>User Id</th>
								<td data-title="User Id">${paySlip.userId}</td>
							</tr>
							<tr>
								<th>Basic</th>
								<td data-title="Basic">${paySlip.basic}</td>
							</tr>
							<tr>
								<th>Conveyance</th>
								<td data-title="Conveyance">${paySlip.conveyance}</td>
							</tr>
							<tr>
								<th>Provident Fund</th>
								<td data-title="Provident Fund">${paySlip.pf}</td>
							</tr>
							<tr>
								<th>House Rent Allowance</th>
								<td data-title="House Rent Allowance">${paySlip.hra}</td>
							</tr>
							<tr>
								<th>Leave Travel Allowance</th>
								<td data-title="Leave Travel Allowance">${paySlip.lta}</td>
							</tr>
							<tr>
								<th>Medical Reimbursement</th>
								<td data-title="Medical Reimbursement">${paySlip.mr}</td>
							</tr>
							<tr>
								<th>ESI</th>
								<td data-title="ESI">${paySlip.esi}</td>
							</tr>
							<tr>
								<th>Special Allowance</th>
								<td data-title="Special Allowance">${paySlip.sa}</td>
							</tr>
							<tr>
								<th>Income Tax</th>
								<td data-title="Income Tax">${paySlip.incomeTax}</td>
							</tr>
							<tr>
								<th>Profession Tax</th>
								<td data-title="Profession Tax">${paySlip.pf}</td>
							</tr>
							<tr>
								<th>Attendance</th>
								<td data-title="Attendance">${paySlip.attendance}</td>
							</tr>
							<tr>
								<th>Total Salary</th>
								<td data-title="Total Salary">${paySlip.totalSalary}</td>
							</tr>
							<tr>
								<th>Over Time Salary</th>
								<td data-title="Over Time Salary">
									${paySlip.overTimeAmount}</td>
							</tr>
							<tr>
								<th>Number of Overtime Hours</th>
								<td data-title="Number of Overtime Hours">${paySlip.overTimeHours}</td>
							</tr>
							<tr>
								<th>Month</th>
								<td data-title="Month">${paySlip.month}</td>
							</tr>
							<tr>
								<th>Year</th>
								<td data-title="Year">${paySlip.year}</td>
							</tr>
						</thead>

					</table>
				</div>
			</div>
		</div>
		<div class="well">
			<a
				href="<c:url value='/download-paySlip-${paySlip.userId}-${paySlip.month}-${paySlip.year}' />">Download
				PaySlip</a>
		</div>
	</div>
</body>
</html>