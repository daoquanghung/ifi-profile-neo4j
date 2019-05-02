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
		<button class="tablinks" onclick="openTab(event, 'Projects')" >Projects</button>
		<button class="tablinks" onclick="openTab(event, 'Staff')">Staff</button>
		<button class="tablinks" onclick="openTab(event, 'Technologies')">Technologies</button>
		<button class="tablinks" onclick="openTab(event, 'Relationship')">Relationship</button>
	</div>
	
	<!-- Home page -->
	<div id="Home" class="tabcontent">
		<h1>Add department</h1>
		<p></p>
		<p>The time on the server is ${serverTime}.</p>
		
		<form action="department" method="get">
			Department	<br><input type="text" name="department"><br>
			Description	<br><input type="text" name="description"><br>
			<input type="submit" value="submit"><br>
		</form>
	</div>
	
	
	<!-- Person page -->
	<div id="Staff" class="tabcontent">
		<p>The time on the server is ${serverTime}.</p>
	
		<h3>List of Staff</h3>
		<form action="user" method="post">
			<input type="text" name="userName"><br> 
			<input type="submit" value="Get List People"><br>
		</form>
	
		
		
		<div>
    		 
        	<h2>Add Person</h2>
        		<div class="container">
        			<form name="myForm" action="form" onsubmit="return validate()" method="get">
        			<!-- Person name -->
        			<div class="row">
        				<div class="col-25">
        					<label for="name">Person Name</label>
        				</div>
        				<div class="col-75">
        					<input type="text" id="name" name="userName" placeholder="Enter person name...">
        				</div>
        			</div>
        			
        			<!-- Person Id -->
        			<div class="row">
        				<div class="col-25">
        					<label for="id">Person Id</label>
        				</div>
        				<div class="col-75">
        					<input type="text" id="id" name="userId" placeholder="Enter person id...">
        				</div>
        			</div>
        			
        			<!-- Person title -->
        			<div class="row">
        				<div class="col-25">
        					<label for="title">Title</label>
        				</div>
        				<div class="col-75">
        					<input type="text" id="title" name="title">
        				</div>
        			</div>
        			
        			<!-- Person birthday -->
        			<div class="row">
        				<div class="col-25">
        					<label for="birthday">Birthday</label>
        				</div>
        				<div class="col-75">
        					<input type="text" id="birthday" name="birthday">
        				</div>
        			</div>
        			
        			<!-- Join -->
        			<div class="row">
        				<div class="col-25">
        					<label for="join">Join</label>
        				</div>
        				<div class="col-75">
        					<input type="text" id="join" name="join">
        				</div>
        			</div>
        			
        			<!-- Status -->
        			<div class="row">
        				<div class="col-25">
        					<label for="status">Status</label>
        				</div>
        				<div class="col-75">
        					<input type="text" id="status" name="status">
        				</div>
        			</div>
        			<div class = "row">
        				<input type="submit" value="Submit">
        			</div>
        			</form>
        		</div>
		</div>
	</div>
	
	<!-- Technology page -->
	<div id="Technologies" class="tabcontent">
		<p>The time on the server is ${serverTime}.</p>
		
		<div>
			<h2>Add Technology</h2>
			<form action="techForm" method="get">
				Name		<br><input type="text" name="techName"><br>
				Description <br><input type="text" name="techDescription"><br>
				Category	<br><input type="text" name="techCategory"><br>
				Domain		<br><input type="text" name="techDomain"><br>
				<input type="submit" value="submit"><br>
			</form>
		</div>
		
	</div>
	
	<!-- Project page -->
	<div id="Projects" class="tabcontent">
		<p>The time on the server is ${serverTime}.</p>
		
		<h2>Add Project</h2>
        	<form action="project" method="get">
        		Project Name <br><input type="text" name="project"><br>
        		Project ID 	 <br><input type="text" name="chargeId"><br>
        		Status 		 <br><input type="text" name="proStatus"><br>
        		Description  <br><input type="text" name="proDescription"><br>
        		Domain 		 <br><input type="text" name="proDomain"><br>
        		Start Date 	 <br><input type="text" name="startDate"><br>
        		Finish Date	 <br><input type="text" name="finishDate"><br>
        		Customer	 <br><input type="text" name="customer"><br>
        		<input type="submit" value="submit"><br>
        	</form>
		
	</div>
	
	<!-- Relationship page -->
	<div id="Relationship" class="tabcontent">
		<form action="relation" method="get">
			<p>Relation between Person and Department</p>
			Person Name	<input type="text" name="userName">
			Department	<input type="text" name="department">
			<input type="submit" value="submit"><br>
			<p>Relation between Project and Technology</p>
			Project		<input type="text" name="project">
			Technology	<input type="text" name="techName">
			<input type="submit" value="submit"><br>
			<p>Relation between Person and Project(work in)</p>
			Person Name	<input type="text" name="userName">
			Project		<input type="text" name="project">
			<input type="submit" value="submit"><br>
			<p>Relation between Person and Project(lead)</p>
			Project		<input>
			Project		<input>
			<input type="submit" value="submit"><br>
			<p>Relation between Person and Technology</p>
			Person Name	<input type="text" name="userName">
			Technology	<input type="text" name="techName">
			<input type="submit" value="submit"><br>
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