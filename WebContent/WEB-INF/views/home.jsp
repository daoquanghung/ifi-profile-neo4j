<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
* {box-sizing: border-box}

body {
  font-family: "Lato", sans-serif;
  height: 100%;
  margin: 0px;

}

/* Style the tab */
.tab {
  float: left;
  border: 1px solid white;
  background-color: #003333;
  width: 20%;
  height: 100%;
  position: fixed;
}

/* Style the buttons inside the tab */
.tab button {
  display: block;
  background-color: #003333;
  color: white;
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
  background-color: #00cccc;
}

/* Create an active/current "tab button" class */
.tab button.active {
  background-color: #00cccc;
}

/* Style the tab content */
.tabcontent {
  float: right;
  margin: 0;
  background-color: #e6e6e6;
  padding: 0;
  width: 80%;
 /* overflow: auto; */
  border-left: none;
  height: 100%;
}
</style>
</head>

<body>
	<jsp:include page="_header.jsp"></jsp:include>
	
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'Home')" id="defaultOpen">Home</button>
		<button class="tablinks" onclick="openTab(event, 'Department')" >Department</button>
		<button class="tablinks" onclick="openTab(event, 'Projects')" >Projects</button>
		<button class="tablinks" onclick="openTab(event, 'Staff')">Staff</button>
		<button class="tablinks" onclick="openTab(event, 'Technologies')">Technologies</button>
	</div>
	
		<!-- Home page -->
	<div id="Home" class="tabcontent">
		<div class="container">
		<h1>IFI Profile</h1>
			<div class="row">
				<div>
				<form action="search" id="search" method="post">
					<input type="text" name="labelNode">
					<input type="submit" value="Search">
				</form>
				</div>
				<div class="col-sm-6">
				<form id="form" action="add" method="post">
					<input type="submit" value="Create Node"><br>
					<p>Type Node:</p>
					<input type="text" name="typeNode"><br>
					<p>Label Node:</p>
					<input type="text" name="labelNode"><br>
					<p>Properties:</p>
				</form>
				<button id="addBtn" onclick="addField()">Add Field</button><br>
				</div>
				<div class="col-sm-6">
				<c:if test="${not empty lists}">
					<h4>List Nodes:</h4>
					<table class="table table-hover table-bordered table-striped">
					<thead>
				      <tr>
				        <th>No</th>
				        <th class="col-md-1">Name</th>
				      </tr>
				    </thead>
				    <tbody>
				    <!-- use two-dimensional array to get value of node and field -->
				      <c:forEach var="listValue" items="${lists}" varStatus="count">
				      <tr data-toggle="modal" data-target="#ifiModal" class="idClass" data-id="${listValue.labelNode}" 
	      					data-list="<c:forEach var="field" items="${listValue.listFields}">${field.key}:${field.value}*+*+</c:forEach>">
						  <td>${count.index+1}</td>
						  <td>${listValue.labelNode}</td>
						  <td><button class="btn-remove"><i class="fas fa-trash-alt"></i></button></td>
						  <td><button class="btn-update"><i class="fas fa-pen"></i></button></td>
				      </tr>
				      </c:forEach>
				    </tbody>
					</table>
				</c:if>
				</div>
				
				<div>
					
				</div>
			</div>
		</div>
	</div>
	
	<div id="Department" class="tabcontent">
	</div>
	
	<div id="Projects" class="tabcontent">
	</div>
	
	<div id="Staff" class="tabcontent">
	</div>
	
	<div id="Technology" class="tabcontent">
	</div>
	
	<script type="text/javascript">
	var i = 0;
	function addField() {
		
		var x = document.createElement("INPUT");
		tmpName = "listFields["+i+"].";
		x.setAttribute("type", "text");
		x.setAttribute("name", tmpName+"key");
		x.setAttribute("id","property-key"+i);
		
		var y = document.createElement("INPUT");
		tmpName = "listFields["+i+"].";
		y.setAttribute("type", "text");
		y.setAttribute("name", tmpName+"value");
		y.setAttribute("id","property-value"+i);

		var elem = document.createElement('br');
		elem.setAttribute("id","property-br"+i); 

		var form = document.getElementById("form");
		form.appendChild(x);
		form.appendChild(y);
		var newlabel = document.createElement("Label");
	    newlabel.innerHTML = "Delete";
	    newlabel.setAttribute("onclick","deleteField("+i+")");
	    newlabel.setAttribute("id","property-label"+i);
	    form.appendChild(newlabel);
		form.appendChild(elem);
		i++;
	}
	function deleteField(i) {
		
		document.getElementById("property-label"+i).remove();
		document.getElementById("property-key"+i).remove();
		document.getElementById("property-value"+i).remove();
		document.getElementById("property-br"+i).remove();
	}
	$(function () {
        $(".idClass").click(function () {
        	var my_id_value = $(this).data('id');
            var list = $(this).data('list');
            $("#name-node").text(my_id_value + ' detail');

            var body = document.getElementById("modal-body");    	    
    	    body.innerHTML = '';
            
    	    var tbl  = document.createElement('table');
    	    tbl.style.border = '1px solid gray';
    	    
            while (list.length > 0){
            	var n = list.indexOf("*+*+");
				var rowText = list.substring(0, n);
            	
            	var tr = tbl.insertRow();
                var td = tr.insertCell();
                var m = list.indexOf(":");
                td.appendChild(document.createTextNode(rowText.substring(0, m)));
                td.style.border = '1px solid gray';
//                 td.style.width  = '100px';
                rowText = rowText.substring(m+1, rowText.length);
                var td = tr.insertCell();
                td.appendChild(document.createTextNode(rowText));
                td.style.border = '1px solid gray';

				list = list.substring(n+4, list.length);
            }
            body.appendChild(tbl);
        }) 
          
        // update node
        $(document).on('click','.btn-update',function(event){
        	
        	$('#ifiModal').modal('show');
        })
        $(".btn-save").click(function(event){
        	
        })
        
        // remove node
        $(document).on('click','.btn-remove',function(event){
        	var tr = $(this).parents("tr");
        	var result = confirm("Do you want to delete?");
        	if(result){
        		tr.remove();
        	}
        })
    });
	</script>

	<!-- The Modal -->
	<div class="modal fade" id="ifiModal">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 id="name-node" class="modal-title">Node Detail</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div id="modal-body" class="modal-body">
	        <label id="labelNode"></label>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary btn-save" data-dismiss="modal">Save</button>
	      </div>
	
	    </div>
	  </div>
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