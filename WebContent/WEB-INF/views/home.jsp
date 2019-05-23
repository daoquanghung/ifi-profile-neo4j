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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>IFI Profile</h1>
	<div class="row">
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
	      <c:forEach var="listValue" items="${lists}" varStatus="count">
	      <tr data-toggle="modal" data-target="#ifiModal" class="idClass" data-id="${listValue.labelNode}">
			  <td>${count.index+1}</td>
			  <td>${listValue.labelNode}</td>
	      </tr>
	      </c:forEach>
	    </tbody>
		</table>
	</c:if>
	</div>
	</div>
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
            $("#hiddenValue").val(my_id_value);
            $("#labelNode").text(my_id_value);
        })
    });
	</script>

<!-- The Modal -->
<div class="modal fade" id="ifiModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Node Detail</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <label id="labelNode"></label>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
</body>
</html>