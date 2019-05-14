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
		<p>The time on the server is ${serverTime}.</p>
		<div class="container">
		<form action="department" method="get">
			<div class="row">
        		<div class="col-25">
        			<label for="name">Department</label>
        		</div>
        		<div class="col-75">
        			<input type="text" id="name" name="name" placeholder="Enter department...">
        		</div>
        	</div>
        	
        	<div class="row">
        		<div class="col-25">
        			<label for="description">Description</label>
        		</div>
        		<div class="col-75">
        			<input type="text" id="description" name="description" placeholder="Enter department description...">
        		</div>
        	</div>
        	
        			
			<input type="submit" value="submit"><br>
		</form>
		</div>
		
		<!-- Remove department -->
		<div>
			<h2>Remove department</h2>
			<form action="removeDepartment" method="get">
				Department name:	<input type="text" name="name">
				<input type="submit" value="Remove"><br>
			</form>
		</div>
		
		<!-- Search department -->
		<div>
			<h2>Search department</h2>
			<form action="searchDep" method="get">
				Department name: <input type="text" name="name">
				<input type="submit" value="Search">
			</form>
		</div>
		
		<!-- Update department -->
		<h1>Update department</h1>
		<div class="container">
		<form action="department" method="get">
			<div class="row">
        		<div class="col-25">
        			<label for="department">Department</label>
        		</div>
        		<div class="col-75">
        			<input type="text" id="department" name="name" placeholder="Enter department...">
        		</div>
        	</div>
        	
        	<div class="row">
        		<div class="col-25">
        			<label for="description">Description</label>
        		</div>
        		<div class="col-75">
        			<input type="text" id="description" name="description">
        		</div>
        	</div>
        	
        			
			<input type="submit" value="submit"><br>
		</form>
		</div>
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
        			<form name="myForm" action="form" onsubmit="return validate()" method="post">
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
		
		<!-- Search person -->
		<div>
			<h2>Search person</h2>
			<form action="searchPerson" method="get">
				Name: <input type="text" name="userName">
				<input type="submit" value="Submit">
			</form>
		</div>
		
		<!-- Update person -->
		<h2>Update person</h2>
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
        			<input type="submit" value="Update"><br>
        			</form>
		</div>
		
		<!-- Remove person -->
		<div>
			<h2>Remove person</h2>
			<form action="remove" method="get">
				Person Id:	<input type="text" name="userId">
				<input type="submit" value="Remove"><br>
			</form>
		</div>
	</div>
	
	<!-- Technology page -->
	<div id="Technologies" class="tabcontent">
		<p>The time on the server is ${serverTime}.</p>
		
		<div>
			<h2>Add Technology</h2>
			<div class="container">
			<form action="techForm" method="get">
				<!-- Tech name -->
				<div class="row">
        			<div class="col-25">
        				<label for="techName">Technology name</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techName" name="techName" placeholder="Enter name of technology...">
        			</div>
        		</div>
        	
				<!-- Tech description -->
				<div class="row">
        			<div class="col-25">
        				<label for="techDescription">Description</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techDescription" name="techDescription" placeholder="Enter technology description...">
        			</div>
        		</div>
				<!-- Tech category -->
				<div class="row">
        			<div class="col-25">
        				<label for="techCategory">Category</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techCategory" name="techCategory" placeholder="Enter technology category...">
        			</div>
        		</div>
				<!-- Tech domain -->
				<div class="row">
        			<div class="col-25">
        				<label for="techDomain">Domain</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techDomain" name="techDomain" placeholder="Enter technology domain...">
        			</div>
        		</div>
				<input type="submit" value="submit"><br>
			</form>
			</div>
		</div>
		
		<!-- Remove Technology -->
		<div>
			<h2>Remove technology</h2>
			<form action="removeTech" method="get">
				Technology Name:	<input type="text" name="techName">
				<input type="submit" value="Remove"><br>
			</form>
		</div>
		
		<!-- Search tech -->
		<div>
			<h2>Search Technology</h2>
			<form action="searchTech" method="get">
				Name: <input type="text" name="techName">
				<input type="submit" value="Search">
			</form>
		</div>
		
		<!-- Update Technology -->
		<div>
			<h2>Update Technology</h2>
			<div class="container">
			<form action="techForm" method="get">
				<!-- Tech name -->
				<div class="row">
        			<div class="col-25">
        				<label for="techName">Technology name</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techName" name="techName" placeholder="Enter name of technology...">
        			</div>
        		</div>
        	
				<!-- Tech description -->
				<div class="row">
        			<div class="col-25">
        				<label for="techDescription">Description</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techDescription" name="techDescription">
        			</div>
        		</div>
				<!-- Tech category -->
				<div class="row">
        			<div class="col-25">
        				<label for="techCategory">Category</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techCategory" name="techCategory">
        			</div>
        		</div>
				<!-- Tech domain -->
				<div class="row">
        			<div class="col-25">
        				<label for="techDomain">Domain</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="techDomain" name="techDomain">
        			</div>
        		</div>
				<input type="submit" value="submit"><br>
			</form>
			</div>
		</div>
		
	</div>
		
		
	
	<!-- Project page -->
	<div id="Projects" class="tabcontent">
		<p>The time on the server is ${serverTime}.</p>
		
		<h2>Add Project</h2>
		<div class="container">
        	<form action="project" method="get">
        		<!-- project name -->
        		<div class="row">
        			<div class="col-25">
        				<label for="project">Project</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="project" name="project" placeholder="Enter project...">
        			</div>
        		</div>
        		<!-- project id -->
        		<div class="row">
        			<div class="col-25">
        				<label for="chargeId">Project Id</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="chargeId" name="chargeid" placeholder="Enter project Id...">
        			</div>
        		</div>
        		<!-- project status -->
        		<div class="row">
        			<div class="col-25">
        				<label for="proStatus">Status</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="proStatus" name="proStatus" placeholder="Enter project status...">
        			</div>
        		</div>
        		<!-- project description -->
        		<div class="row">
        			<div class="col-25">
        				<label for="proDescription">Description</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="proDescription" name="proDescription" placeholder="Enter project description...">
        			</div>
        		</div>
        		<!-- project domain -->
        		<div class="row">
        			<div class="col-25">
        				<label for="proDomain">Domain</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="proDomain" name="proDomain" placeholder="Enter project domain...">
        			</div>
        		</div>
        		<!-- project start date -->
        		<div class="row">
        			<div class="col-25">
        				<label for="startdate">Start Date</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="startdate" name="startdate" placeholder="Project start from...">
        			</div>
        		</div>
        		<!-- project finish date -->
        		<div class="row">
        			<div class="col-25">
        				<label for="finishDate">Finish Date</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="finishdate" name="finishdate" placeholder="To...">
        			</div>
        		</div>
        		<!-- customer -->
        		<div class="row">
        			<div class="col-25">
        				<label for="customer">Customer</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="customer" name="customer" placeholder="Customer...">
        			</div>
        		</div>
        		<input type="submit" value="submit"><br>
        	</form>
		</div>
		
		<!-- Remove project -->
		<div>
			<h2>Remove project</h2>
			<form action="removeProject" method="get">
				Project name:	<input type="text" name="project">
				<input type="submit" value="Remove"><br>
			</form>
		</div>
		
		<!-- Search project -->
		<div>
			<h2>Search Project</h2>
			<form action="searchProject" method="get">
				ID of project: <input type="text" name="chargeid">
				<input type="submit" value="Search">
			</form>
		</div>
		
		<!-- Update project -->
		<h2>Update Project</h2>
		<div class="container">
        	<form action="project" method="get">
        		<!-- project name -->
        		<div class="row">
        			<div class="col-25">
        				<label for="project">Project</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="project" name="project" placeholder="Enter project...">
        			</div>
        		</div>
        		<!-- project id -->
        		<div class="row">
        			<div class="col-25">
        				<label for="chargeid">Project Id</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="chargeid" name="chargeid" placeholder="Enter project Id...">
        			</div>
        		</div>
        		<!-- project status -->
        		<div class="row">
        			<div class="col-25">
        				<label for="proStatus">Status</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="proStatus" name="proStatus">
        			</div>
        		</div>
        		<!-- project description -->
        		<div class="row">
        			<div class="col-25">
        				<label for="proDescription">Description</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="proDescription" name="proDescription">
        			</div>
        		</div>
        		<!-- project domain -->
        		<div class="row">
        			<div class="col-25">
        				<label for="proDomain">Domain</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="proDomain" name="proDomain">
        			</div>
        		</div>
        		<!-- project start date -->
        		<div class="row">
        			<div class="col-25">
        				<label for="startdate">Start Date</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="startdate" name="startdate">
        			</div>
        		</div>
        		<!-- project finish date -->
        		<div class="row">
        			<div class="col-25">
        				<label for="finishdate">Finish Date</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="finishdate" name="finishdate">
        			</div>
        		</div>
        		<!-- customer -->
        		<div class="row">
        			<div class="col-25">
        				<label for="customer">Customer</label>
        			</div>
        			<div class="col-75">
        				<input type="text" id="customer" name="customer">
        			</div>
        		</div>
        		<input type="submit" value="submit"><br>
        	</form>
		</div>
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
<!-- 			<select name="carlist" form="carform"> -->
<!-- 			  <option value="volvo">Volvo</option> -->
<!-- 			  <option value="saab">Saab</option> -->
<!-- 			  <option value="opel">Opel</option> -->
<!-- 			  <option value="audi">Audi</option> -->
<!-- 			</select> -->
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