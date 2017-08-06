<jsp:include page="CommonFiles.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<style type="text/css">
.alert-box {
	color: #555;
	border-radius: 10px;
	font-family: Tahoma, Geneva, Arial, sans-serif;
	font-size: 11px;
	padding: 10px 36px;
	margin: 10px;
}

.alert-box span {
	font-weight: bold;
	text-transform: uppercase;
}

.error {
	background: #ffecec url('images/msg/error.png') no-repeat 10px 50%;
	border: 1px solid #f5aca6;
}

.aw-header-0 .aw-item-box {background:#cdcdcd;border-bottom-color:#c4c4c4;}
.aw-header-0 .aw-grid-header {background:#b7b7b7!important;border-bottom-color:#adadad;}

#grid01 .aw-grid-control {height: 100%; width: 90%; margin: 0px; font: menu;}

#grid01 {
	height: 210px;
	width: 90%;
}

#grid01 .aw-row-selector {
	text-align: center
}

#grid01 .aw-column-0 {
	width: 250px;
}

#grid01 .aw-column-1 {
	width: 500px;
}

#grid01 .aw-column-2 {
	width: 250px;
}

#grid01 .aw-grid-cell {
	border-right: 1px solid threedlightshadow;
}

#grid01 .aw-grid-row {
	border-bottom: 1px solid threedlightshadow;
}
</style>

<script>

	$(document).ready(function() {
		a();
		$.getAll=function(){
			var method = "getAll";
			$.ajax({
				type : "post",
				url : "OfficerType",
				data : {
					method : method
				},
				success : function(data){
					grid1.setCellText(data);
					grid1.setRowCount(data.length);
					grid1.refresh();
				}
			});
		};			
		$.getAll();
	});


	$(document).ready(function() {
		$('#submit').click(function() {
			var error=false;
			var i=1;
			while(i<3){
				if (false === $('#officerTypeForm').parsley().validate('block'+i,true))
					error=true;
				i++;
			}
		    if(error==false){   			
				var code = $("#code").val();
				var description = $("#description").val();
				var status = $("#status").val();
	
				$.ajax({
					type : "post",
					url : "OfficerType",
					data : {
						method:"create",
						code : code,
						description :description,
						status : status
					},
					success : function(data) {
						$.getAll();				
						alert(data);
					}
	
				});
		    };	
		});
	});
		
	$(document).ready(function() {
		$('#modify').click(function() {
			var error=false;
			var i=1;
			while(i<3){
				if (false === $('#officerTypeForm').parsley().validate('block'+i,true))
					error=true;
				i++;
			}
		    if(error==false){  	
				var code = $("#code").val();
				var description = $("#description").val();
				var status = $("#status").val();
				var officerTypeId = $("#officerTypeId").val();
				var method = "modify";
				$.ajax({
					type : "post",
					url : "OfficerType",
					data : {
						code : code,
						description : description,
						status : status,
						method : method,
						officerTypeId : officerTypeId
					},
					success : function(data) {
						$.getAll();		
						alert(data);
					}
				});
		    };	
		});
	});
	
	
	$(document).ready(function() {
		$('#delete').click(function() {
			var officerTypeId = $("#officerTypeId").val();
			var method = "delete";
			$.ajax({
				type : "post",
				url : "OfficerType",
				data : {
					method : method,
					officerTypeId : officerTypeId
				},
				success : function(data) {
					$.getAll();		
					alert(data);
					 document.getElementById("submit").disabled=false;
		    	     document.getElementById("modify").disabled=true;
		    	     document.getElementById("delete").disabled=true;
				}
			});
		});
	});
</script>
<style>
.btn-one {
	color: white;
	border-radius: 4px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
	background: #043a81;
	hover;
}
</style>
<br>
<legend>&nbsp; Officer Types</legend>
<div style="min-width: 100%;" id="table">
	<script>
		
	                    		var myHeaders = ["code","description","status"];
	                    		var grid1 = new AW.UI.Grid;
	                    		grid1.setHeaderText(myHeaders);
								grid1.setId("grid01");
	                    		grid1.setColumnCount(3);
	                    		grid1.setSelectionMode("single-row");
 	                    		document.getElementById("table").innerHTML = grid1;
 	                    		
 	                    		grid1.onRowClicked = function(event, rowIndex){
 	                  	    	  	document.getElementById("code").value = grid1.getCellText(0,rowIndex);
 	                  	    	 	document.getElementById("description").value = grid1.getCellText(1,rowIndex);
 	                  	    	 	document.getElementById("status").value = grid1.getCellText(2,rowIndex);
 	                  	    	 	document.getElementById("officerTypeId").value = grid1.getCellText(3,rowIndex);
 	                  	    	 	if(user==1){
 	  	                   	    	 document.getElementById("submit").disabled=true;
 	          		    	    	 document.getElementById("modify").disabled=false;
 	          		    	    	 document.getElementById("delete").disabled=false;
 	  	                   	  	};
 	                  	      };  
	  </script>
</div>

<form class='form-horizontal' id='officerTypeForm' style="min-width: 700px" method='post'>
<input type="hidden" id="officerTypeId">
	<fieldset>
		<legend></legend>
		<div class='control-group'>
			<label class='control-label'>Code :</label>
			<div class='controls'>
				<input type='text' id='code' name='code' data-parsley-length="[1,15]" onkeyup="removeSpecialCharacters('code')" data-parsley-group="block1" required>
			</div>
		</div>
		<div class='control-group'>
			<label class='control-label'>Description :</label>
			<div class='controls'>
				<textarea id='description' style="min-width: 400px;min-height:150px"  data-parsley-required="true" data-parsley-length="[1,500]" onkeyup="removeSpecialCharacters('description')" data-parsley-group="block2" name='description' required></textarea>
			</div>
		</div>
		<div id="codeError" class="alert-box error"
			style="width: 20%; display: none;">
			<span>Error: </span>Code is required
		</div>
		<div class='control-group'>
			<label class='control-label'>status :</label>
			<div class='controls'>
				<select id="status">
					<option value="Active">Active</option>
					<option value="Inactive">Inactive</option>
				</select>
			</div>

		</div>
		<div class='control-group'>
					<label class='control-label'></label>
					<div class='controls' style="margin-right: 10%">
						<button type='button' id = 'submit' class='btn btn-one' >Add</button>
						<button type="button" class='btn btn-one' id="modify">Modify</button>
						<button type="button" class='btn btn-one' id="delete">Delete</button>
						<button style="float: right;" type='reset' id="cancel" class='btn btn-one' >Cancel</button>
					</div>
				</div>
	</fieldset>
</form>
<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>