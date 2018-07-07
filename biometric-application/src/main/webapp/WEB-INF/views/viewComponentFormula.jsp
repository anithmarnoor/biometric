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
					<span class="lead">Salary Break Down Components with
						Percentages</span>
				</div>
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf" id="thcolor">
							<tr>
								<th>Formula Id</th>
								<th>Formula</th>
								<th>Component Name</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${salDivPercentList}" var="divPercent">
								<tr>
									<td data-title="Formula ID">${divPercent.formulaId}</td>
									<td data-title="Formula">${divPercent.formula}</td>
									<td data-title="Component">${divPercent.component.componentName}</td>
									<td><a
										href="<c:url value='/salDivPercent-edit-${divPercent.formulaId}' />">Edit</a></td>
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
				<span class="lead">Add Formula</span>
			</div>
			<div class="uploadcontainer">
				<%@include file="addFormula.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>