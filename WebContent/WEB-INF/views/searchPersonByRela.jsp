<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<h3>Information of person searched </h3>
	
		<table border="1px" width="100%" cellspacing="1px">
		
			<tr>
				<td>Name</td>
				<td>ID</td>
				<td>Title</td>
				<td>Birthday</td>
				<td>Join</td>
				<td>Status</td>
			</tr>
			
			<c:forEach var="listUser" items="${lists}">
			<tr>
				<td><c:out value="${listUser.userName}"></c:out></td>
				<td><c:out value="${listUser.userId}"></c:out></td>
				<td><c:out value="${listUser.title}"></c:out></td>
				<td><c:out value="${listUser.birthday}"></c:out></td>
				<td><c:out value="${listUser.join}"></c:out></td>
				<td><c:out value="${listUser.status}"></c:out></td>
			</tr>
			</c:forEach>
		
			<c:forEach var="listValue" items="${listByPro}">
			<tr>
				<td><c:out value="${listValue.userName}"></c:out></td>
				<td><c:out value="${listValue.userId}"></c:out></td>
				<td><c:out value="${listValue.title}"></c:out></td>
				<td><c:out value="${listValue.birthday}"></c:out></td>
				<td><c:out value="${listValue.join}"></c:out></td>
				<td><c:out value="${listValue.status}"></c:out></td>
			</tr>
			</c:forEach>
			
			<c:forEach var="list" items="${listByTech}">
			<tr>
				<td><c:out value="${list.userName}"></c:out></td>
				<td><c:out value="${list.userId}"></c:out></td>
				<td><c:out value="${list.title}"></c:out></td>
				<td><c:out value="${list.birthday}"></c:out></td>
				<td><c:out value="${list.join}"></c:out></td>
				<td><c:out value="${list.status}"></c:out></td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
</body>
</html>
