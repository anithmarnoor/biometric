<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exams</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container-2">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="alert alert-success lead">${error}${success}</div>
					<span class="lead">Add Exam</span>
				</div>
				<div class="uploadcontainer">
					<div>
						<c:choose>
							<c:when test="${edit}">
								<c:set var="action" value="exam-edit-${exams.examId}" />
							</c:when>
							<c:otherwise>
								<c:set var="action" value="exam-save" />
							</c:otherwise>
						</c:choose>
					</div>
					<form:form method="POST" modelAttribute="exams" action="${action}"
						class="form-horizontal">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="examName">Exam
									Name</label>
								<div class="col-md-7">
									<form:input type="text" path="examName" id="examName"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="examName" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<%-- <div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="isDeductible">Should
							be deducted from salary?</label>
						<div class="col-md-7">
							<form:radiobutton path="isDeductible" value="Yes" />
							Yes
							<form:radiobutton path="isDeductible" value="No" />
							No
							<div class="has-error">
								<form:errors path="isDeductible" class="help-inline" />
							</div>
						</div>
					</div>
				</div> --%>

						<div class="row">
							<div class="form-actions floatRight">
								<c:choose>
									<c:when test="${edit}">
										<input type="submit" value="Update"
											class="btn btn-primary btn-sm" /> or <a
											href="<c:url value='view-exams' />">Cancel</a>
									</c:when>
									<c:otherwise>
										<input type="submit" value="Add"
											class="btn btn-primary btn-sm" /> or <a
											href="<c:url value='view-exams' />">Cancel</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="generic-container-2">
		<div class="row">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">Exams</span>
				</div>
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf" id="thcolor">
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${examsList}" var="exam">
								<tr>
									<td data-title="ID">${exam.examId}</td>
									<td data-title="Name">${exam.examName}</td>
									<td data-title=""></td>
									<td><a href="<c:url value='/exam-edit-${exam.examId}' />">Edit</a></td>
									<td><a
										href="<c:url value='/exam-delete-${exam.examId}' />">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>