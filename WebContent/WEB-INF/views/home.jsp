<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>Home</title>
<meta name="Viewport" content="width=device-width, initial-scale=1"> 

<style>
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

/* Style all the table in home page */
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}

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


/* Form */
input[type = text], select, textarea{
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	resize: vertical;
}

label{
	padding: 12px 12px 12px 0;
	display: inline-block;
}

input[type = submit]{
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	float: right;
}

input[type = submit]:hover{
	background-color: #45a049;
}

.container{
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

.col-25{
	float: left;
	width: 75%;
	margin-top: 6px;
}

.col-75{
	float: left;
	width: 75%;
	margin-top: 6px;
}

.row:after{
	content: "";
	display: table;
	clear: both;
}

@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
}

</style>

<script>
	function validate(){
		var x = document.forms["myForm"]["userName"].value;
		var y = document.forms["myForm"]["userId"].value;
		if(x == "" || y == ""){
			alert("Name and Id must be filled out");
			return false;
		}
	}
</script>

</head>

<body>
	
	<jsp:include page="_header.jsp"></jsp:include>
	
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'Home')" id="defaultOpen">Home</button>
		<button class="tablinks" onclick="openTab(event, 'Department')" >Department</button>
		<button class="tablinks" onclick="openTab(event, 'Projects')" >Projects</button>
		<button class="tablinks" onclick="openTab(event, 'Staff')">Staff</button>
		<button class="tablinks" onclick="openTab(event, 'Technologies')">Technologies</button>
		<button class="tablinks" onclick="openTab(event, 'Relationship')">Relationship</button>
	</div>
	
	<!-- Home page -->
	<div id="Department" class="tabcontent">
		<table id="customers">
			<tr>
				<th>Department</th>
				<th>Description</th>	
			</tr>
		
			<c:forEach var="listOff" items="${listOff}">
			<tr>
				<td><c:out value="${listOff.name}"></c:out></td>
				<td><c:out value="${listOff.description}"></c:out></td>			
			</tr>
			</c:forEach>
		</table>
	</div>
	
	
	<!-- Person page -->
	<div id="Staff" class="tabcontent">
		<!--<p>The time on the server is ${serverTime}.</p>-->
		<div>
			<table id="customers">
				<tr>
					<th>Name</th>
					<th>ID</th>
					<th>Title</th>
					<th>Birthday</th>
					<th>Join</th>
					<th>Status</th>
				</tr>
			
				<c:forEach var="listUser" items="${printPerson}">
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
	</div>
	
	<!-- Technology page -->
	<div id="Technologies" class="tabcontent">
		<table id="customers">
			<tr>
				<th>Technology name</th>
				<th>Description</th>
				<th>Category</th>
				<th>Domain</th>
				
			</tr>
		
			<c:forEach var="listTech" items="${listTech}">
			<tr>
				<td><c:out value="${listTech.techName}"></c:out></td>
				<td><c:out value="${listTech.techDescription}"></c:out></td>
				<td><c:out value="${listTech.techCategory}"></c:out></td>
				<td><c:out value="${listTech.techDomain}"></c:out></td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
		
		
	
	<!-- Project page -->
	<div id="Projects" class="tabcontent">
		<table id="customers">	
			<tr>
				<th>Project</th>
				<th>Project ID</th>
				<th>Status</th>
				<th>Description</th>
				<th>Domain</th>
				<th>Start Date</th>
				<th>Finish Date</th>
				<th>Customer</th>
			</tr>
		
			<c:forEach var="listTask" items="${listTask}">
			<tr>
				<td><c:out value="${listTask.project}"></c:out></td>
				<td><c:out value="${listTask.chargeid}"></c:out></td>
				<td><c:out value="${listTask.proStatus}"></c:out></td>
				<td><c:out value="${listTask.proDescription}"></c:out></td>
				<td><c:out value="${listTask.proDomain}"></c:out></td>
				<td><c:out value="${listTask.startdate}"></c:out></td>
				<td><c:out value="${listTask.finishdate}"></c:out></td>
				<td><c:out value="${listTask.customer}"></c:out></td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
	
	<!-- Relationship page -->
	<div id="Relationship" class="tabcontent">
		<form action="relation" method="get">
			<p>Relation between Person and Department</p>
			Person ID	<input type="text" name="userId">
			Department	<input type="text" name="department">
			<input type="submit" value="submit"><br>
		</form>
		<form action="relation" method="get">
			<p>Relation between Project and Technology</p>
			Technology	<input type="text" name="techName">
			Project	ID	<input type="text" name="chargeId">
			<input type="submit" value="submit"><br>
		</form>
		<form action="relation" method="get">
			<p>Relation between Person and Project(work in)</p>
			Person ID	<input type="text" name="userId">
			Project	ID	<input type="text" name="chargeId">
			<input type="submit" value="submit"><br>
		</form>
		<form action="relation"	method="get">
			<p>Relation between Person and Technology</p>
			Person ID	<input type="text" name="userId">
			Technology	<input type="text" name="techName">
			<input type="submit" value="submit"><br>
		</form><br>
		
		<!-- Search person by relationship -->
		<p>Search person by relationship</p>
		<form action="searchPersonByRela" method="get">
			<select name="relation">
				<option value="BELONG_TO">BELONG_TO</option>
				<option value="WORK_IN">WORK_IN</option>
				<option value="HAS_EXPERIENCE">HAS_EXPERIENCE</option>
			</select>
			Department 	<input type="text" name="department">
			Project		<input type="text" name="chargeId">
			Technology	<input type="text" name="techName"><br>
			<input type="submit" value="Search"><br>
		</form>
		
		<!-- Search Technology by relationship -->
		<p>Search Technology by relationship</p>
		<form action="searchTechByRela" method="get">
			<select name="relation">
				<option value="USED_IN">USED_IN</option>
				<option value="HAS_EXPERIENCE">HAS_EXPERIENCE</option>
			</select>
			Project		<input type="text" name="chargeId">
			Person		<input type="text" name="userName"><br>
			<input type="submit" value="Search"><br>
		</form>
		
		<!-- Search Project by relationship -->
		<p>Search Project by relationship</p>
		<form action="searchProjectByRela" method="get">
			<select name="relation">
				<option value="WORK_IN">WORK_IN</option>
				<option value="USED_IN">USED_IN</option>
			</select>
			Person		<input type="text" name="userName">
			Technology	<input type="text" name="techName"><br>
			<input type="submit" value="Search"><br>
		</form>
		
	</div>
	
	<script>
		function openTab(evt, tabName){
 		 var i, tabcontent, tablinks;
 		 tabcontent = document.getElementsByClassName("tabcontent");
 		 for (i = 0; i < tabcontent.length; i++) {
  		  tabcontent[i].style.display = "none";
 		 }
  		tablinks = document.getElementsByClassName("tablinks");
  		for (i = 0; i < tablinks.length; i++) {
   		 tablinks[i].className = tablinks[i].className.replace(" active", "");
  		}
  		document.getElementById(tabName).style.display = "block";
  		evt.currentTarget.className += " active";
		}

	document.getElementById("defaultOpen").click();
	</script>
	
</body>

</html>