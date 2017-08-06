<jsp:include page="CommonFiles.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script>	
	function loadRanges(data){
		var select = document.getElementById("range");
		for(var i=0; i < data.length; i++) {
			    select.options[select.options.length] = new Option(data[i][1], data[i][0]);
		}
	}
	
	$(document).ready(function() {
		$.ajax({
			type : "post",
			url  : "ProTypesServlet",
			data : {
				method : "getProductRanges",
			},
			success : function(data) {
				loadRanges(data[0]);
			}
		});
	});
	
	$(document).ready(function() {
		a();
		$.getAll=function(){
			var method = "getAll";
			$.ajax({
				type : "post",
				url : "ProTypesServlet",
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
			error=false;
			if (false === $('#registration').parsley().validate('block1',true))
			error=true;
			if(error==false){   
				var code = $("#code").val();
				var description = $("#description").val();
				var status = $("#status").val();
				var range = $("#range").val();
				var amt = $("#amt").val();
				var method = "create";
				$.ajax({
					type : "post",
					url : "ProTypesServlet",
					data : {
						code : code,
						description : description,
						status : status,
						amt:amt,
						range:range,
						method : method
					},
					success : function(data) {
						alert(data);
						$.getAll();		
					}
				});
			}	
		});
	});
	
	$(document).ready(function() {
		$('#modify').click(function() {
			error=false;
			if (false === $('#registration').parsley().validate('block1',true))
			error=true;
		    if (false === $('#registration').parsley().validate('block2',true))
		    error=true;
			if(error==false){   
				var code = $("#code").val();
				var description = $("#description").val();
				var status = $("#status").val();
				var titleId = $("#titleId").val();
				var method = "modify";
				$.ajax({
					type : "post",
					url : "ProTypesServlet",
					data : {
						code : code,
						description : description,
						status : status,
						method : method,
						titleId : titleId
					},
					success : function(data) {
						alert(data);
						$.getAll();		
					}
				});
			};	
		});
	});
	
	
	$(document).ready(function() {
		$('#delete').click(function() {
			if(!confirm("Confirm to delete record"))
				return;
			
			var titleId = $("#titleId").val();
			var method = "delete";
			$.ajax({
				type : "post",
				url : "ProTypesServlet",
				data : {
					method : method,
					titleId : titleId
				},
				success : function(data) {
					alert(data);
					$.getAll();		
					document.getElementById("submit").disabled=false;
		    	    document.getElementById("modify").disabled=true;
		    	    document.getElementById("delete").disabled=true;
				}
			});
		});
	});


</script>		

<style>
.btn-one{
	color:white;
	        border-radius: 4px;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
             background: #043a81; 
             hover;
             
	
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
	width: 100px;
}

#grid01 .aw-column-1 {
	width: 300px;
}

#grid01 .aw-column-2 {
	width: 100px;
}

#grid01 .aw-column-3 {
	width: 300px;
}

#grid01 .aw-column-4 {
	width: 100px;
}
#grid01 .aw-column-5 {
	width: 50px;
}
#grid01 .aw-grid-cell {
	border-right: 1px solid threedlightshadow;
}

#grid01 .aw-grid-row {
	border-bottom: 1px solid threedlightshadow;
}
</style>
<br>
<legend>&nbsp; Product Range</legend>
	<div style="min-width:80%;" align="left" id="table">
		<script>
		
	    	var myHeaders = ["ProductRange Code","ProductRange Description","ProductType Code","ProductType Description","Product Amount","status"];

	        // create grid object
	        var grid1 = new AW.UI.Grid;
	        // assign headers text
	        
	        grid1.setHeaderText(myHeaders);
			grid1.setId("grid01");
	        // set number of columns/rows
	        grid1.setColumnCount(6);
	        grid1.setSelectionMode("single-row");
 	        // write grid to the div table
 	        document.getElementById("table").innerHTML = grid1; 	
 	         
 	       grid1.onRowClicked = function(event, rowIndex){
 	    	 document.getElementById("code").value = grid1.getCellText(2,rowIndex);
 	    	 document.getElementById("description").value = grid1.getCellText(3,rowIndex);
 	    	 document.getElementById("amt").value = grid1.getCellText(4,rowIndex);
// 	    	 document.getElementById("status").value = grid1.getCellText(5,rowIndex);
				
 	    	 var objSelect = document.getElementById("range");
 	    	setSelectedValue(objSelect,grid1.getCellText(0,rowIndex));	
 	    	 
	    	 var objSelect1 = document.getElementById("status");
	 	     setSelectedValue(objSelect1,grid1.getCellText(5,rowIndex));			    		
 	    	
 	    	 document.getElementById("titleId").value = grid1.getCellText(6,rowIndex);
 	    	if(user==1){
	 	    	 document.getElementById("submit").disabled=true;
		    	 document.getElementById("modify").disabled=false; 
		    	 document.getElementById("delete").disabled=false;
 	    	};	 
 	      };  
 	      
 	
	 	  function setSelectedValue(selectObj, valueToSet) {
	 	      for (var i = 0; i < selectObj.options.length; i++) {
	 	    	  if (selectObj.options[i].text== valueToSet) {
	 	    		  selectObj.options[i].selected = true;
	 	              return;
	 	          }
	 	      }
	 	  }
 	      
	      </script>
	    </div>
	    <br><br>
		<form class='form-horizontal' id='registration' onsubmit="" style="min-width: 700px" method='post'>
		<input type="hidden" id="titleId">
			<fieldset >
				<div class='control-group'>
					<label class='control-label'>Product Range :</label>
					<div class='controls'>
						<select id="range"  data-parsley-group="block1" data-parsley-required="true">
						</select>
					</div>
				</div>	
				<div class='control-group'>
					<label class='control-label'>Code :</label>
					<div class='controls'>
					<p style="color:red">
						<input type='text' data-parsley-length="[1, 15]" onkeyup="removeSpecialCharacters('code')" data-parsley-group="block1" id='code' name='code' required>
					</p>
					</div>
				</div>
				<div class='control-group' >
					<label class='control-label'>Description :</label>
					<div class='controls' >
						<textarea style="min-width: 400px;min-height:150px" id='description' onkeyup="removeSpecialCharacters('description')" data-parsley-length="[1, 500]" data-parsley-group="block2"  draggable="false" maxlength="500"  name='description' required="required"></textarea>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Amount :</label>
					<div class='controls'>
					<p style="color:red">
						<input type='text' data-parsley-type="number" onkeyup="removeSpecialCharacters('amt')"  data-parsley-group="block1" id='amt' name='amt' required>
					</p>
					</div>
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
						<button type="button" id = 'submit' class='btn btn-one' >Add</button>
						<button type="button" class='btn btn-one' id="modify">Modify</button>
						<button type="button" class='btn btn-one' id="delete">Delete</button>
						<button style="float: right;" type='reset' class='btn btn-one' id="cancel">Cancel</button>
					</div>
				</div>
	</fieldset>
</form>
<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>