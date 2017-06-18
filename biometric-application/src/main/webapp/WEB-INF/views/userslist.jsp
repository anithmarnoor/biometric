<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<sec:authorize access="hasRole('ADMIN') ">
					<span class="lead">Drivers </span>
				</sec:authorize>
				<sec:authorize access="hasRole('USER')">
					<span class="lead">Your Profile</span>
				</sec:authorize>
			</div>
			<table>
				<thead>
					<tr>
						<sec:authorize access="hasRole('ADMIN')">
							<th>Driver Name</th>
						</sec:authorize>
						<sec:authorize access="hasRole('USER')">
							<th>Name</th>
						</sec:authorize>
						<th>Email</th>
						<th>UserName</th>
						<th>User ID</th>
						<th>DL NO.</th>
						<th>Birth Date</th>
						<th>Address</th>
						<th>Joining Date</th>
						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<sec:authorize access="hasRole('ADMIN')">
						<c:forEach items="${drivers}" var="user">
							<tr>
								<td>${user.firstName} ${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.userName}</td>
								<td>${user.id}</td>
								<td>${user.dlNo}</td>
								<td>${user.dob}</td>
								<td>${user.address}</td>
								<td>${user.doj}</td>
								<sec:authorize access="hasRole('ADMIN')">
									<td><a
										href="<c:url value='/edit-user-${user.userName}' />"
										class="btn btn-success custom-width">edit</a></td>
								</sec:authorize>
								<sec:authorize access="hasRole('ADMIN')">
									<td><a
										href="<c:url value='/delete-user-${user.userName}' />"
										class="btn btn-danger custom-width">delete</a></td>

								</sec:authorize>
							</tr>
						</c:forEach>
					</sec:authorize>
					<sec:authorize access="hasRole('USER')">
						<c:forEach items="${drivers}" var="user">
							<c:if test="${user.userName eq loggedinuser}">
								<tr>
									<td>${user.firstName} ${user.lastName}</td>
									<td>${user.email}</td>
									<td>${user.userName}</td>
									<td>${user.id}</td>
									<td>${user.dlNo}</td>
									<td>${user.dob}</td>
									<td>${user.address}</td>
									<td>${user.doj}</td>
									<td><a
										href="<c:url value='/edit-user-${user.userName}' />"
										class="btn btn-success custom-width">edit</a></td>
								</tr>
							</c:if>
						</c:forEach>
					</sec:authorize>
				</tbody>
			</table>
			<sec:authorize access="hasRole('ADMIN')">
			</br>
			</br>
				<div class="panel-heading">
					<span class="lead">Admins</span>
				</div>
				<table >
					<thead>
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>UserName</th>
							<th>User ID</th>
							<th>DL NO.</th>
							<th>Birth Date</th>
							<th>Address</th>
							<th>Joining Date</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<sec:authorize access="hasRole('ADMIN')">
							<c:forEach items="${adminUsers}" var="user">
								<tr>
									<td>${user.firstName} ${user.lastName}</td>
									<td>${user.email}</td>
									<td>${user.userName}</td>
									<td>${user.id}</td>
									<td>${user.dlNo}</td>
									<td>${user.dob}</td>
									<td>${user.address}</td>
									<td>${user.doj}</td>

									<td><a
										href="<c:url value='/edit-user-${user.userName}' />"
										class="btn btn-success custom-width">edit</a></td>
									<c:if test="${user.userName ne loggedinuser}">
										<td><a
											href="<c:url value='/delete-user-${user.userName}' />"
											class="btn btn-danger custom-width">delete</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</sec:authorize>
					</tbody>
				</table>
			</sec:authorize>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="well">
				<a href="<c:url value='/newuser' />">Add New User</a>
			</div>
		</sec:authorize>
	</div>
</body>
</html>