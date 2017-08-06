<jsp:include page="CommonFiles.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script >	
	function loadTitle(data){
		var select = document.getElementById("title");
		for(var i=0; i < data.length; i++) {
			    select.options[select.options.length] = new Option(data[i][1], data[i][0]);
		}
	}
	function loadOfficerType(data){
		var select = document.getElementById("officerType");
		for(var i=0; i < data.length; i++) {
			    select.options[select.options.length] = new Option(data[i][1], data[i][0]);
		}
	}
	//loading titles onload
	$(document).ready(function() {
		$.ajax({
			type : "post",
			url  : "OfficerServlet",
			data : {
				method : "getTitlesAndOfficerTypes",
			},
			success : function(data) {
				loadTitle(data[0]);
				loadOfficerType(data[1]);
			}
		});
	
		$.getAll=function(){				
			var method = "getAll";
			$.ajax({
				type : "post",
				url : "OfficerServlet",
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

		setTimeout(function() {
			$.getAll();      
		}, 300);		
	});
	
	//adding a user
	$(document).ready(function() {
		$('#submit').click(function() {
			var error=false;
			var i=1;
			while(i<7){
				if (false === $('#registration').parsley().validate('block'+i,true))
					error=true;
				i++;
			}
		    if(error==false){
				var titleId = $("#title").val();
				var firstName = $("#firstName").val();
				var surname = $("#surname").val();
				var mobileNo = $("#mobileNo").val();
				var officerTypeId = $("#officerType").val();
				var userName = $("#userName").val();
				var password = $("#password").val();
				var status = $("#status").val();
				$.ajax({
					type : "post",
					url : "OfficerServlet",
					data : {
						method : "create",
						titleId : titleId,
						firstName : firstName,
						surname : surname,
						mobileNo : mobileNo,
						officerTypeId : officerTypeId,
						userName : userName,
						password : password,
						status : status
					},
					success : function(data) {
						alert(data);
						$.getAll();
					}
				});
		    };	
		});
	});
</script>
<style type="text/css">

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
	width: 100px;
}

#grid01 .aw-column-2 {
	width: 100px;
}

#grid01 .aw-column-3 {
	width: 100px;
}

#grid01 .aw-column-4 {
	width: 100px;
}

#grid01 .aw-column-5 {
	width: 100px;
}

#grid01 .aw-column-6 {
	max-width: 100px;
}

#grid01 .aw-column-7 {
	width: 100px;
}

#grid01 .aw-column-8 {
	width: 100px;
}

#grid01 .aw-column-9 {
	width: 100px;
}

#grid01 .aw-column-10 {
	width: 100px;
}
#grid01 .aw-grid-cell {
	border-right: 1px solid threedlightshadow;
}

#grid01 .aw-grid-row {
	border-bottom: 1px solid threedlightshadow;
}
</style>
<br>
<legend>&nbsp; Users</legend>
<div style="min-width:100%;" id="table">
		<script>		
	    	var myHeaders = ["Title","First Name","Surname","Mobile No","Officer Type","User Name","Created User","Created Date","Status"];
	        // create grid object
	        var grid1 = new AW.UI.Grid;
	        // assign headers text
	        grid1.setHeaderText(myHeaders);
			grid1.setId("grid01");
	        // set number of columns/rows
	        grid1.setColumnCount(10);
	        grid1.setSelectionMode("single-row");
 	        // write grid to the div table
 	        document.getElementById("table").innerHTML = grid1; 	                    		            
	      </script>
</div>
<br><br>
		<form class="form-horizontal" id="registration" method='post' >
		<fieldset>
			<div class="control-group">
				<label class="control-label" >Title :</label>
				<div class="controls" >
					<select id="title">
					</select>
				</div>
			</div> 
			<div class="control-group">
				<label class="control-label">First Name :</label>
				<div class="controls">
					<input type="text" id="firstName" data-parsley-maxwords="1" data-parsley-length="[2,100]"  data-parsley-pattern="^[A-za-z\\s]+$" onkeyup="removeSpecialCharacters('firstName')" data-parsley-group="block1" > <label></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Surname :</label>
				<div class="controls">
					<input type="text" id="surname" data-parsley-maxwords="1" data-parsley-length="[1,150]"  data-parsley-pattern="^[A-za-z\\s]+$" onkeyup="removeSpecialCharacters('surname')" data-parsley-group="block2">
				</div>
			</div>
			<!-- <div class="control-group">
				<label class="control-label">Designation :</label>
				<div class="controls">
					<input type="text" id="designation" data-parsley-maxwords="4" data-parsley-length="[1,150]"   onkeyup="removeSpecialCharacters('surname')" data-parsley-group="block3" required>
				</div>
			</div>			 -->
			<div class="control-group">
				<label class="control-label">Mobile No :</label>
				<div class="controls">
					<input type="text" id="mobileNo" data-parsley-pattern="^[0-9]{10,15}$"  onkeyup="removeSpecialCharacters('mobileNo')" data-parsley-group="block4">
				</div>
			</div>			
            <div class="control-group">
            	<label class="control-label">Officer Type :</label>
                <div class="controls">
				<select id="officerType" >
				</select>
                </div>
            </div> 			
			<div class="control-group">
				<label class="control-label">User Name :</label>
				<div class="controls">
					<input type="text" id="userName" data-parsley-maxwords="1" data-parsley-length="[3,60]"   data-parsley-group="block5" required/>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">Password :</label>
				<div class="controls">
					<input type="text" id="password" data-parsley-maxwords="1" data-parsley-length="[2,45]" data-parsley-group="block6" required>
				</div>
			</div>			
		<div class='control-group'>
			<label class='control-label'>Status :</label>
			<div class='controls'>
				<select id="status">
					<option value="Active">Active</option>
					<option value="Inactive">Inactive</option>
				</select>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button type='button' id = 'submit' class='btn btn-one' >Add</button>
	            	<button type="reset" class='btn btn-one' >Cancel</button>
			 	</div>
            </div> 
		</fieldset>
	</form>
<input type='hidden' id='modify' />	<input type='hidden' id='delete' />
<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>