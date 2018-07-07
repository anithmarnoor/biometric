<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Biometric Log</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Logs</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>No.</th>
							<th>Year</th>
							<th>Month</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${biometricDataList}" var="doc"
							varStatus="counter">
							<tr>
								<td>${counter.index + 1}</td>
								<td>${doc.year}</td>
								<td>${doc.month}</td>
								<td><a
									href="<c:url value='/download-document-${doc.id}' />"
									class="btn btn-success custom-width">download</a></td>
								<td><a
									href="<c:url value='/delete-BiometricData-${doc.id}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Upload New Log</span>
			</div>

			<div class="uploadcontainer">
				<%@include file="addBiometricData.jsp"%>
			</div>
		</div>

	</div>
</body>
</html>