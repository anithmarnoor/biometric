<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<div class="row">
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
				<div id="no-more-tables">
					<table
						class="col-md-12 table-bordered table-striped table-condensed cf">
						<thead class="cf">
							<tr>
								<th>Name</th>
								<th>Email</th>
								<th>Phone No</th>
								<th>UserName</th>
								<th>ID</th>
								<th>DL NO.</th>
								<th>DOB</th>
								<th>Address</th>
								<th>Joining Date</th>
								<sec:authorize access="hasRole('ADMIN')">
									<th width="100"></th>
								</sec:authorize>
							</tr>
						</thead>
						<tbody>
							<sec:authorize access="hasRole('ADMIN')">
								<c:forEach items="${drivers}" var="user">
									<tr>
										<td data-title="Name">${user.firstName} ${user.lastName}</td>
										<td data-title="Email">${user.email}</td>
										<td data-title="Phone No">${user.phoneNo}</td>
										<td data-title="UserName">${user.userName}</td>
										<td data-title="Id">${user.id}</td>
										<td data-title="DL No">${user.dlNo}</td>
										<td data-title="DOB">${user.dob}</td>
										<td data-title="Address">${user.address}</td>
										<td data-title="Joining Date">${user.doj}</td>
										<td><sec:authorize access="hasRole('ADMIN')">
												<a href="<c:url value='/edit-user-${user.userName}' />"
													class="btn btn-success custom-width">edit</a>
											</sec:authorize> <sec:authorize access="hasRole('ADMIN')">
												<a href="<c:url value='/delete-user-${user.userName}' />"
													class="btn btn-danger custom-width">delete</a>
											</sec:authorize></td>
									</tr>
								</c:forEach>
							</sec:authorize>
							<sec:authorize access="hasRole('USER')">
								<c:forEach items="${drivers}" var="user">
									<c:if test="${user.userName eq loggedinuser}">
										<tr>
											<td data-title="Name">${user.firstName} ${user.lastName}</td>
											<td data-title="Email">${user.email}</td>
											<td data-title="Phone No">${user.phoneNo}</td>
											<td data-title="UserName">${user.userName}</td>
											<td data-title="Id">${user.id}</td>
											<td data-title="DL No">${user.dlNo}</td>
											<td data-title="DOB">${user.dob}</td>
											<td data-title="Address">${user.address}</td>
											<td data-title="Joining Date">${user.doj}</td>
											<td><a
												href="<c:url value='/edit-user-${user.userName}' />"
												class="btn btn-success custom-width">edit</a></td>
										</tr>
									</c:if>
								</c:forEach>
							</sec:authorize>
						</tbody>
					</table>
				</div>
				<sec:authorize access="hasRole('ADMIN')">
					</br>
					</br>
					<div class="panel-heading">
						<span class="lead">Admins</span>
					</div>
					<div id="no-more-tables">
						<table
							class="col-md-12 table-bordered table-striped table-condensed cf">
							<thead class="cf">
								<tr>
									<th>Name</th>
									<th>Email</th>
									<th>Phone No</th>
									<th>UserName</th>
									<th>ID</th>
									<th>DL NO.</th>
									<th>DOB</th>
									<th>Address</th>
									<th>Joining Date</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<sec:authorize access="hasRole('ADMIN')">
									<c:forEach items="${adminUsers}" var="user">
										<c:if test="${user.userName ne loggedinuser}">
											<tr>
												<td data-title="Name">${user.firstName}
													${user.lastName}</td>
												<td data-title="Email">${user.email}</td>
												<td data-title="Phone No">${user.phoneNo}</td>
												<td data-title="UserName">${user.userName}</td>
												<td data-title="Id">${user.id}</td>
												<td data-title="DL No">${user.dlNo}</td>
												<td data-title="DOB">${user.dob}</td>
												<td data-title="Address">${user.address}</td>
												<td data-title="Joining Date">${user.doj}</td>

												<td><a
													href="<c:url value='/edit-user-${user.userName}' />"
													class="btn btn-success custom-width">Edit</a> <a
													href="<c:url value='/delete-user-${user.userName}' />"
													class="btn btn-danger custom-width">Delete</a></td>
											</tr>
										</c:if>
									</c:forEach>
								</sec:authorize>
							</tbody>
						</table>
					</div>
				</sec:authorize>
			</div>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="well">
				<a href="<c:url value='/newuser' />">Add New User</a>
			</div>
		</sec:authorize>
	</div>
</body>
</html>