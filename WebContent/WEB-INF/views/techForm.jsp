<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Technology</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<h1>Information of Technology ${tech.techName }</h1>
	<table>
		<tr>
			<td>Tech Name</td>
			<td>${tech.techName}</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${tech.techDescription}</td>
		</tr>
		<tr>
			<td>Category</td>
			<td>${tech.techCategory}</td>
		</tr>
		<tr>
			<td>Domain</td>
			<td>${tech.techDomain}</td>
		</tr>
	</table>
</body>
</html>