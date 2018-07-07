<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leave</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src='<c:url value='/static/jQuery/bootstrap.min.js'/>'></script>
<script type="text/javascript">
$.datepicker.setDefaults({
    firstDay: 1,
    dateFormat: 'dd/mm/yy',
    beforeShowDay: $.datepicker.noWeekends
	});
</script>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Apply Leave</span>
			</div>
			<div class="alert alert-success lead">${message}</div>

			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="leaves"
					action="apply-leave" class="form-horizontal">

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="leaveTypes">Leave
								Type</label>
							<div class="col-md-7">
								<form:select path="leaveTypes" items="${leaveTypesList}"
									multiple="false" itemValue="leaveTypeId" itemLabel="leaveType"
									class="form-control input-sm" id="designation" />
								<div class="has-error">
									<form:errors path="leaveTypes" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<sec:authorize access="hasRole('ADMIN') ">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="user">User</label>
								<div class="col-md-7">
									<form:select path="user" items="${usersList}" multiple="false"
										itemValue="id" itemLabel="userName"
										class="form-control input-sm" id="designation" />
									<div class="has-error">
										<form:errors path="user" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
					</sec:authorize>
					<sec:authorize access="hasRole('USER') ">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="user">User</label>
								<div class="col-md-7">
									<form:select path="user" items="${userList}" multiple="false"
										itemValue="id" itemLabel="userName"
										class="form-control input-sm" id="designation" />
									<div class="has-error">
										<form:errors path="user" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
					</sec:authorize>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="fullOrHalf">Full Or Half Day</label>
							<div class="col-md-7">
								<form:radiobutton path="fullOrHalf" id="fullOrHalf" value="Full"
									label="Full" />
								<form:radiobutton path="fullOrHalf" id="fullOrHalf" value="Half"
									label="Half" />
								<div class="has-error">
									<form:errors path="fullOrHalf" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="startDate">Start
								Date</label>
							<div class="col-md-7">
								<form:input path="startDate" id="startDate"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="startDate" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="endDate">End
								Date</label>
							<div class="col-md-7">
								<form:input path="endDate" id="endDate"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="endDate" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="comment">Reason
								for Applying Leave*</label>
							<div class="col-md-7">
								<form:input type="text" path="comment" id="comment"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="comment" class="help-inline" />
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Add" class="btn btn-primary btn-sm" />
							or <a href="<c:url value='overtimes-list' />">Cancel</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>