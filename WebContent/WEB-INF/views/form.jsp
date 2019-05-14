<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'Home')" id="defaultOpen">Home</button>
		<button class="tablinks" onclick="openTab(event, 'Projects')" >Projects</button>
		<button class="tablinks" onclick="openTab(event, 'Staff')">Staff</button>
		<button class="tablinks" onclick="openTab(event, 'Technologies')">Technologies</button>
		<button class="tablinks" onclick="openTab(event, 'Relationship')">Relationship</button>
	</div>
	
	<div class="tabcontent" id="Staff">
	<h3>Information of ${user.userName} </h3>
	
		<table border="1px" width="100%" cellspacing="1px">
		
			<tr>
				<td>Name</td>
				<td>ID</td>
				<td>Title</td>
				<td>Birthday</td>
				<td>Join</td>
				<td>Status</td>
			</tr>
			
			<tr>
				<td><c:out value="${user.userName}"></c:out></td>
				<td><c:out value="${user.userId}"></c:out></td>
				<td><c:out value="${user.title}"></c:out></td>
				<td><c:out value="${user.birthday}"></c:out></td>
				<td><c:out value="${user.join}"></c:out></td>
				<td><c:out value="${user.status}"></c:out></td>
			</tr>
		
		</table>
	</div>
</body>
</html>
