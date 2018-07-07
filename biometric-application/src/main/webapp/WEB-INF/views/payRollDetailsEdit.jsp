<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PayRoll Details Edit</title>
<script type="text/javascript">
	function submitUpdateAction() {
		document.getElementById("submitForm").action = "updatePayRoll";
		document.getElementById("submitForm").submit();
	}
	function enableTextField(value){
		document.getElementById("payRoll-"+value).disabled = false;
	}
</script>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Profile</span>
			</div>
			<div class="alert alert-success lead">${message}</div>
			<form:form id="submitForm" method="POST">
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
										<td><input type="checkbox" name="payRollIds"
											value="${payRoll.payRollId }" onclick="enableTextField(this.value)" /></td>
										<td>${payRoll.component.componentName }</td>
										<td><input type="text" id ="payRoll-${payRoll.payRollId}" disabled="disabled" 
											name="payRoll-${payRoll.payRollId}"
											value="${payRoll.componentValue }" /></td>
										<td>${payRoll.component.isDeductible }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="row">
							<div class="form-actions floatRight">

								<input type="hidden" name="userId" value="${userId}" /> <input
									type="button" class="btn btn-block btn-primary btn-default"
									onclick="submitUpdateAction()" value="Update">
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>