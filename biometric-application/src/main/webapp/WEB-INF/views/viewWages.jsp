<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salary Details</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
	<div class="alert alert-success lead">${error}</div>
		<div class="row">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">Salary Details</span>
				</div>
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf" id="thcolor">
							<tr>
								<th>Normal 8hr shift amount</th>
								<th>CTC Per Annum</th>
								<th>UserName</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${wagesList}" var="wages">
								<tr>
									<td data-title="Normal 8hr shift amount">${wages.normalShift}</td>
									<td data-title="Overtime Amount">${wages.ctc}</td>
									<td data-title="Overtime Amount">${wages.user.userName}</td>
									<td><a href="<c:url value='/edit-wages-${wages.id}' />">Edit
											Amounts</a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<a href="<c:url value='/save-wages' />">Add Wage amounts</a>
	</div>

</body>
</html>