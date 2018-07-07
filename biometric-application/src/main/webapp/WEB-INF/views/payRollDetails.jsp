<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PayRoll Details</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Profile</span>
			</div>
			<div class="alert alert-success lead">${message} ${success}</div>

			<div class="wrapper">
				<div id="no-more-tables">
					<table id="tblSearch"
						class="col-md-12 table-bordered table-striped table-condensed cf"
						border="0" cellpadding="0" cellspacing="0" width="60%"
						class="scrollTable">
						<thead class="cf" id="thcolor">
							<tr>
								<th><span>Id</span></th>
								<th><span>Component Name</span></th>
								<th><span>Value</span></th>
								<th><span>Is Deductible</span></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${payRolls}" var="payRoll">
								<tr>
									<td>${payRoll.payRollId }</td>
									<td>${payRoll.component.componentName }</td>
									<td>${payRoll.componentValue }</td>
									<td>${payRoll.component.isDeductible }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row">
						<div class="form-actions floatRight">
							<form:form id="submitForm" method="POST" action ="payRollDetails-edit">
								<input type="hidden" name="userId" value="${userId}" />
								<input type="submit"
									class="btn btn-block btn-primary btn-default"
									 value="Edit Details">
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>