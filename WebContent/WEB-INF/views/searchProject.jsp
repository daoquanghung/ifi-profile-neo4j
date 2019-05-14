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
	<h3>Information of ${userName} </h3>
	
		<table border="1px" width="100%" cellspacing="1px">
		
			<tr>
				<td>Project</td>
				<td>Project ID</td>
				<td>Status</td>
				<td>Description</td>
				<td>Domain</td>
				<td>Start Date</td>
				<td>Finish Date</td>
				<td>Customer</td>
			</tr>
		
			<c:forEach var="listValue" items="${lists}">
			<tr>
				<td><c:out value="${listValue.project}"></c:out></td>
				<td><c:out value="${listValue.chargeid}"></c:out></td>
				<td><c:out value="${listValue.proStatus}"></c:out></td>
				<td><c:out value="${listValue.proDescription}"></c:out></td>
				<td><c:out value="${listValue.proDomain}"></c:out></td>
				<td><c:out value="${listValue.startdate}"></c:out></td>
				<td><c:out value="${listValue.finishdate}"></c:out></td>
				<td><c:out value="${listValue.customer}"></c:out></td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
</body>
</html>