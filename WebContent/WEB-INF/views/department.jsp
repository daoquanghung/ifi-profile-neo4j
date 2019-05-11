<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
	<h1>Information of department ${office.name }</h1>
	<table>
		<tr>
			<td>Department</td>
			<td>${office.name}</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${office.description}</td>
		</tr>
	</table>
</body>
</html>