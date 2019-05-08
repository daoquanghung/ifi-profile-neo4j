<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Search person by Name</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}



/* Style the tab */
.tab {
  float: left;
  background-color: #f1f1f1;
  width: 30%;
  height: 100%;
}

/* Style the buttons inside the tab */
.tab button {
  display: block;
  background-color: inherit;
  color: black;
  padding: 22px 16px;
  width: 100%;
  border: none;
  outline: none;
  text-align: left;
  cursor: pointer;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  float: left;
  background-color: #e6e6e6;
  padding: 0px 12px;
  overflow: auto;
  width: 70%;
  border-left: none;
  height: 1000px;
}

.btn {
  background-color: DodgerBlue;
  border: none;
  color: white;
  padding: 12px 30px;
  cursor: pointer;
  font-size: 20px;
}
</style>

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
		
		</table>
	</div>
</body>
</html>