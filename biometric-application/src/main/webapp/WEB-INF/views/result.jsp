<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
  <%@include file="includeLibs.jsp" %>
 
 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Result</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<%@include file="authheader.jsp" %>
    <div class="generic-container-2">
        
         
        <div class="alert alert-success lead">
            ${success}
        </div>
         
        <span class="well floatRight">
            <a href="<c:url value='${url}'/>">Go Back</a>
        </span>
    </div>
</body>
 
</html>