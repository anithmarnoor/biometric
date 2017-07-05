<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salary Division</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<div class="alert alert-success lead">${error}</div>
		<div class="well lead">Please enter Salary break down data in %</div>
		<form:form method="POST" modelAttribute="salaryDivision"
			class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="basicPercentage">Basic</label>
					<div class="col-md-7">
						<form:input type="text" path="basicPercentage"
							id="basicPercentage" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="basicPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="conveyancePercentage">Conveyance</label>
					<div class="col-md-7">
						<form:input type="text" path="conveyancePercentage"
							id="conveyancePercentage" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="conveyancePercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="hraPercentage">House
						Rent Allowance</label>
					<div class="col-md-7">
						<form:input type="text" path="hraPercentage" id="hraPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="hraPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ltaPercentage">Leave
						Travel Allowance</label>
					<div class="col-md-7">
						<form:input path="ltaPercentage" id="ltaPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="ltaPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="mrPercentage">Medical
						Reimbursement</label>
					<div class="col-md-7">
						<form:input path="mrPercentage" id="mrPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="mrPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="esiPercentage">ESI</label>
					<div class="col-md-7">
						<form:input path="esiPercentage" id="esiPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="esiPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="saPercentage">Special
						Allowance</label>
					<div class="col-md-7">
						<form:input path="saPercentage" id="saPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="saPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="incomeTaxPercentage">Income
						Tax</label>
					<div class="col-md-7">
						<form:input path="incomeTaxPercentage" id="incomeTaxPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="incomeTaxPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="pfPercentage">Provident
						Fund</label>
					<div class="col-md-7">
						<form:input path="pfPercentage" id="pfPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="pfPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ptPercentage">Profession
						Tax</label>
					<div class="col-md-7">
						<form:input path="ptPercentage" id="ptPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="ptPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='view-salaryDivision' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='view-salaryDivision' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>