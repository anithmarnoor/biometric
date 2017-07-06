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
								<th>Basic</th>
								<th>Conveyance</th>
								<th>Provident Fund</th>
								<th>House Rent Allowance</th>
								<th>Leave Travel Allowance</th>
								<th>Medical Reimbursement</th>
								<th>ESI</th>
								<th>Special Allowance</th>
								<th>Income Tax</th>
								<th>Profession Tax</th>
								<th>Attendance</th>
								<th>Total Salary</th>
								<th>Over Time Salary</th>
								<th>Number of Overtime Hours</th>
								<th>Month</th>
								<th>Year</th>
							</tr>
						</thead>
						<tbody>
										<tr>
											<th data-title="User Id">				${paySlip.userId}	</th>
											<th data-title="Basic">					${paySlip.basic}	</th>
											<th data-title="Conveyance">			${paySlip.conveyance}	</th>
											<th data-title="Provident Fund">		${paySlip.pf}	</th>
											<th data-title="House Rent Allowance">	${paySlip.hra}	</th>
											<th data-title="Leave Travel Allowance">${paySlip.lta}	</th>
											<th data-title="Medical Reimbursement">	${paySlip.mr}	</th>
											<th data-title="ESI">					${paySlip.esi}	</th>
											<th data-title="Special Allowance">		${paySlip.sa}	</th>
											<th data-title="Income Tax">			${paySlip.incomeTax}	</th>
											<th data-title="Profession Tax">		${paySlip.pf}	</th>
											<th data-title="Attendance">			${paySlip.attendance}	</th>
											<th data-title="Total Salary">			${paySlip.totalSalary}	</th>
											<th data-title="Over Time Salary">		${paySlip.overTimeAmount}	</th>
											<th data-title="Number of Overtime Hours">${paySlip.overTimeHours}</th>
											<th data-title="Month">					${paySlip.month}	</th>
											<th data-title="Year">					${paySlip.year}	</th>
										</tr>
									
						</tbody>
					</table>
				</div>
			</div>
		</div>
			<div class="well">
				<a href="<c:url value='/download-paySlip-${paySlip.userId}-${paySlip.month}-${paySlip.year}' />">Download PaySlip</a>
			</div>
	</div>
</body>
</html>