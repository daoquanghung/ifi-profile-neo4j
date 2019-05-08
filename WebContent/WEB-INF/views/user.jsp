<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Viewport" content="width=device-width, initial-scale=1"> 

<style>
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
<title>User Home Page</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<h3>List people start by "${userName}":</h3>
	<c:if test="${not empty lists}">

		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>${listValue.userName}</li>
			</c:forEach>
		</ul>

	</c:if>

</body>
</html>