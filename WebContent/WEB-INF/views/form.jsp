<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<h2>Add Person</h2>
	<table>
		<tr>
			<td>Name</td>
			<td>${user.userName}</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>${user.userId}</td>
		</tr>
		<tr>
			<td>Title</td>
			<td>${user.title}</td>
		</tr>
		<tr>
			<td>Birthday</td>
			<td>${user.birthday}</td>
		</tr>
		<tr>
			<td>Join</td>
			<td>${user.join}</td>
		</tr>
		<tr>
			<td>Status</td>
			<td>${user.status}</td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
	
	
	
</body>
</html>