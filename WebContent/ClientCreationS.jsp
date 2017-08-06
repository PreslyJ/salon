<jsp:include page="CommonFiles.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script >	
	
	$(document).ready(function() {
		a();
		$.getAll=function(){
			var method = "getAll";
			$.ajax({
				type : "post",
				url : "clientRegSServlet",
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
	
	
	//adding applicant
	$(document).ready(function() {
		$('#submit').click(function() {
			var error=false;
		
			if (false === $('#reg1').parsley().validate('block4',true))
				error=true;
			
			if($("#email").val())
				if (false === $('#reg1').parsley().validate('block45',true))
					error=true;
		    if(error==false){   			
				var gen = $("#gen").val();
				var fsName = $("#fsName").val();
				var surname = $("#surname").val();
				var mobileNo = $("#mobileNo").val();
				var email = $("#email").val();	
				$.ajax({
					type : "post",
					url : "clientRegSServlet",
					data : {
						method : "create",	
						gen : gen,
						fsName:fsName,
						surname : surname,
						mobileNo : mobileNo,
						email:email
					},
					success : function(data) {
						alert(data);
					}
				});
		    };	
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
<legend>&nbsp; Client Creation</legend>
<div>
<div style="min-width:80%;" align="left" id="table">
		<script>
		
	    	var myHeaders = ["Phone No","First Name","Last Name","Gender","email"];

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
<br>
<div >
		<form class='form-horizontal' id='reg1' method='post'>
		<fieldset>
			<div class='control-group'>
				<label class='control-label'>First Name :</label>
				<div class='controls'>
					<input type='text' id='fsName' data-parsley-required="true" data-parsley-pattern="^[A-za-z\\s]+$" data-parsley-length="[1,80]" onkeyup="removeSpecialCharacters('fsName')" name="fsName" data-parsley-group="block4" required>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Surname :</label>
				<div class='controls'>
					<input type='text' id='surname' data-parsley-maxwords="1" data-parsley-length="[1,100]" data-parsley-pattern="^[A-za-z\\s]+$" onkeyup="removeSpecialCharacters('surname')" data-parsley-group="block4"  >
				</div>
			</div>
			
			<div class='control-group'>
				<label class='control-label'>Gender :</label>
				<div class='controls'>
					<select id="gen">
					<option value="Female">Female</option>
					<option value="Male">Male</option>					
				</select>
				</div>
			</div>
			
			<div class='control-group'>
				<label class='control-label'>Mobile No :</label>
				<div class='controls'>
				<input type='text' id='mobileNo' data-parsley-pattern="^[0-9]{10,15}$"  onkeyup="removeSpecialCharacters('mobileNo')" data-parsley-group="block4" autocomplete="off" required>
				</div>
			</div>			
			
			<div class='control-group'>
				<label class='control-label'>email :</label>
				<div class='controls'>
				<input type="email" id="email"	data-parsley-type="email" data-parsley-required="false" autocomplete="off" data-parsley-group="block45" >
				</div>
			</div>	
			
			<br>			
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
	</div>
	
</div>	

<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>