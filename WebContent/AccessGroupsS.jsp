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

<script src="js/jquery-1.10.min.js">
</script>


<script >
	$.getAllSelectedMenusByNode=function(){ 
		var arr = [];
		var method = "getAllSelectedMenusByNode";
		var OfficerTypeId = document.getElementById("officerType").value;
		var nodeId = document.getElementById("menuNode").value;
		$.ajax({
			type : "post",
			url : "MenuServletS",
			async:   false,
			data : {
				nodeId : nodeId,
				OfficerTypeId : OfficerTypeId,
				method : method
			},
			success : function(data){
				var count =grid1.getRowCount();
				for (var j = 0; j < count; j++) {
					for (var int = 0; int < data.length; int++) {
						if(grid1.getCellText(1,j)== data[int][0]){
							arr.push(j);
							break;
						}
					}
				}
				grid1.setSelectedRows(arr);
				grid1.refresh();
				
				if(data.length>0){
					document.getElementById("submit").disabled = true;
				}else{
					document.getElementById("submit").disabled = false;
				}
			}
		});
	};
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
			url  : "AccessGroupSServlet",
			data : {
				method : "getOfficerTypes",
			},
			success : function(data) {
				loadOfficerType(data[1]);
			}
		});
	
		$('#menuNode').change(function() {
	    	 document.getElementById("modify").disabled=false;
			var menuNode = document.getElementById("menuNode").value;
			var method = "getAll";
			$.ajax({
				type : "post",
				url : "AccessGroupSServlet",
				data : {
					method : method,
					node : menuNode
				},
				success : function(data){
					grid1.setCellText(data);
					grid1.setRowCount(data.length);
					grid1.refresh();
				}
			});
			$.getAllSelectedMenusByNode();
		});		

		/* setTimeout(function() {
			$.getAll();      
		}, 300); */		
	});
	
	
	
	$(document).ready(function() {
		$('#submit').click(function() {
			var officerType = document.getElementById("officerType").value;
			var nodeId = document.getElementById("menuNode").value;
			var gridData =grid1.getSelectedRows();
			
			var selectedMenu='[';
			for(var i=0;i<gridData.length;i++){
				selectedMenu+='['+grid1.getCellText(1,i)+']';
				if(gridData.length>i+1)
					selectedMenu+=',';
			}
			selectedMenu+=']';
			
			$.ajax({
				type : "post",
				url : "AccessGroupSServlet",
				data : {
					method : "create",
					nodeId:nodeId,
					officerType : officerType,
					selectedMenu : selectedMenu
				},
				success : function(data) {
					alert(data);
				}
			});
		});
	});
	

	$(document).ready(function() {
		$('#modify').click(function() {
			var officerType = document.getElementById("officerType").value;
			var nodeId = document.getElementById("menuNode").value;
			var jsonStr = "";
			var gridData =grid1.getSelectedRows();
			/* for(var i=0; i<a.length; i++){
				alert(grid1.getCellText(1,i));
			}
			 */
			
			var selectedMenu='[';
			for(var i=0;i<gridData.length;i++){
				selectedMenu+='['+grid1.getCellText(1,i)+']';
				if(gridData.length>i+1)
					selectedMenu+=',';
			}
			selectedMenu+=']';
			
			$.ajax({
				type : "post",
				url : "AccessGroupSServlet",
				data : {
					method : "modify",
					nodeId : nodeId,
					officerType : officerType,
					selectedMenu : selectedMenu
				},
				success : function(data) {
					alert(data);
				}
			});
		});
	});
</script>


<script>
	 function test(){
		 var a = grid1.getCellText(0,0);
		 alert(a);
		 /* alert('d');
		 grid1.setSelectedRows([0,1,2]); */
		
	 }
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
<c:if test='<%=(Integer)request.getSession().getAttribute("OfficerTypeId") != 1%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>
<br>
<form class='form-horizontal' id='registration' style="min-width: 700px"
	method='post'>
	<fieldset>
		<legend>&nbsp; Access Groups</legend>
		<div class='control-group'>
			<label class='control-label'>Officer Type :</label>
			<div class='controls'>
				<select id="officerType">
					<option>Select</option>
				</select>
			</div>

		</div>
		<div class='control-group'>
			<label class='control-label'> Menu Node :</label>
			<div class='controls'>
				<select id="menuNode">
					<option value=0>Select</option>
					<option value=1>Reference Data</option>
					<option value=2>Front Office</option>
					<option value=3>User and Access Controls</option>
					<option value=4>Customer Care</option>
					<option value=5>Report Center</option>
					<option value=6>Information Center</option>
					
				</select>
			</div>

		</div>
		<div style="min-width: 100%;" id="table">
	<script>
		
	                    		var myHeaders = ["Menu Item"];
	                    		// create grid object
	                    		var grid1 = new AW.UI.Grid;
	                    		// assign cells and headers text
	                    		grid1.setHeaderText(myHeaders);
								grid1.setId("grid01");
	                    		// set number of columns/rows
	                    		grid1.setColumnCount(1);
	                    		grid1.setSelectionMode("multi-row-marker");
 	                    		// write grid to the div table
 	                    		document.getElementById("table").innerHTML = grid1; 	                    		            
	  </script>
</div>
<br>
		<div class='control-group'>
					<label class='control-label'></label>
					<div class='controls' style="margin-right: 10%">
						<button type='button' id = 'submit' class='btn btn-one' >Add</button>
						<button type="button" class='btn btn-one' id="modify">Modify</button>
						<button style="float: right;" type='reset' class='btn btn-one' >Cancel</button>
					</div>
				</div>
	</fieldset>
</form>
<input type='hidden' id='delete' />

