<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<title>Project</title>

</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<h2>Add Project</h2>
	<table>
		<tr>
			<td>Project</td>
			<td>${task.project}</td>
		</tr>
		<tr>
			<td>Project Id</td>
			<td>${task.chargeId}</td>
		</tr>
		<tr>
			<td>Status</td>
			<td>${task.proStatus}</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${task.proDescription}</td>
		</tr>
		<tr>
			<td>Domain</td>
			<td>${task.proDomain}</td>
		</tr>
		<tr>
			<td>Customer</td>
			<td>${task.customer}</td>
		</tr>
		<tr>
			<td>Start date</td>
			<td>${task.startDate}</td>
		</tr>
		<tr>
			<td>Finish date</td>
			<td>${task.finishDate}</td>
		</tr>
	</table>
	
	
	
</body>
</html>