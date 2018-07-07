<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src='<c:url value='/static/jQuery/bootstrap.min.js'/>'></script>
<script type="text/javascript">
	$.datepicker.setDefaults({
		firstDay : 1,
		dateFormat : 'dd/mm/yy',
		beforeShowDay : $.datepicker.noWeekends
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Holidays</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Holidays List</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead id="thcolor">
						<tr>
							<th>ID</th>
							<th>Holiday Date</th>
							<th>Reason</th>

							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${holidaysList}" var="holiday"
							varStatus="counter">
							<tr>
								<td>${holiday.holidayId}</td>
								<td>${holiday.holidayDate}</td>
								<td>${holiday.holidayReason}</td>
								<sec:authorize access="hasRole('ADMIN') ">
									<td><a
										href="<c:url value='/holidays-delete-${holiday.holidayId}' />"
										class="btn btn-danger custom-width">Delete</a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<sec:authorize access="hasRole('ADMIN') ">
			<div class="panel panel-default">

				<div class="panel-heading">
					<span class="lead">Add Holidays</span>
				</div>
				<div class="alert alert-success lead">${message}</div>
				<div class="uploadcontainer">
					<form:form method="POST" modelAttribute="holidays"
						action="holidays-save" class="form-horizontal">

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="holidayDate">Holiday
									Date</label>
								<div class="col-md-7">
									<form:input path="holidayDate" id="holidayDate"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="holidayDate" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="holidayReason">Reason
									for Holiday*</label>
								<div class="col-md-7">
									<form:input type="text" path="holidayReason" id="holidayReason"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="holidayReason" class="help-inline" />
									</div>
								</div>
							</div>
						</div>


						<div class="row">
							<div class="form-actions floatRight">
								<input type="submit" value="Add" class="btn btn-primary btn-sm" />
								or <a href="<c:url value='holidays-list' />">Cancel</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</sec:authorize>
	</div>
</body>
</html>