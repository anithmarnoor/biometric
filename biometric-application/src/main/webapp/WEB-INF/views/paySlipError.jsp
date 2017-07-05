<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Biometric Data Uploaded Successfully</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">


		<div class="alert alert-success lead">${error}</div>

		<span class="well floatRight"> 
			<sec:authorize access="hasRole('ADMIN') ">
            	Go to <a href="<c:url value='view-wages' />">View Wages</a> OR <a
					href="<c:url value='view-salaryDivision' />">View Salary
					Divisions</a>
			</sec:authorize>
			<sec:authorize access="hasRole('USER') ">
				Go to <a href="<c:url value='/PAM/myProfile' />">Profile</a>
			</sec:authorize>
		</span>
	</div>
</body>

</html>