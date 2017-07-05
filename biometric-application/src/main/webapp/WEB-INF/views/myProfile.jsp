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
					<span class="lead">My Profile</span>
				</div>
				<div class="col-md-12">
					<label>Name: </label><label>${profile.firstName} ${profile.lastName}</label><br>
					<label>Address: </label><label>${profile.address}</label><br>
					<label>DOB: </label><label>${profile.dob}</label><br>
					<label>Email ID: </label><label>${profile.email}</label><br>
					<label>Phone No: </label><label>${profile.phoneNo}</label><br>
					<label>Date Of Joining: </label><label>${profile.doj}</label><br>
					<label>ID: </label><label>${profile.id}</label><br>
					<label>User Name: </label><label>${profile.userName}</label><br>
					<label>DL No: </label><label>${profile.dlNo}</label><br>
					<label>
						<a href="<c:url value='/edit-user-${profile.userName}' />"
													class="btn btn-success custom-width">Edit</a>
					</label>
				</div>
			</div>
		</div>
	</div>
</body>
</html>