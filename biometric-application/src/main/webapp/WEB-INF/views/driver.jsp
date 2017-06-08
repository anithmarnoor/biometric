<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Driver Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Driver
</h1>

<c:url var="addAction" value="/driver/add" ></c:url>

<form:form action="${addAction}" commandName="driver">
<table>
	<c:if test="${!empty driver.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="dlNo">
				<spring:message text="DL No"/>
			</form:label>
		</td>
		<td>
			<form:input path="dlNo" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty driver.name}">
				<input type="submit"
					value="<spring:message text="Edit Driver"/>" />
			</c:if>
			<c:if test="${empty driver.name}">
				<input type="submit"
					value="<spring:message text="Add Driver"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Drivers List</h3>
<c:if test="${!empty listDrivers}">
	<table class="tg">
	<tr>
		<th width="80">Driver ID</th>
		<th width="120">Driver Name</th>
		<th width="120">Driver Country</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listDrivers}" var="driver">
		<tr>
			<td>${driver.id}</td>
			<td>${driver.name}</td>
			<td>${driver.dlNo}</td>
			<td><a href="<c:url value='/driver/edit/${driver.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/driver/remove/${driver.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>