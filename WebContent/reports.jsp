<jsp:include page="CommonFiles.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script>	
	/* $(document).ready(function() {
		a();
		$.getAll=function(){
			var method = "getAll";
			$.ajax({
				type : "post",
				url : "TitleServlet",
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
	}); */
	
	/* $(document).ready(function() {
		$('#submit').click(function() {
			error=false;
			if (false === $('#registration').parsley().validate('block1',true))
			error=true;
		    if (false === $('#registration').parsley().validate('block2',true))
		    error=true;
			if(error==false){   
				var code = $("#code").val();
				var description = $("#description").val();
				var status = $("#status").val();
				var method = "create";
				$.ajax({
					type : "post",
					url : "TitleServlet",
					data : {
						code : code,
						description : description,
						status : status,
						method : method
					},
					success : function(data) {
						alert(data);
						$.getAll();		
					}
				});
			}	
		});
	}); */
	
	/* $(document).ready(function() {
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
					url : "TitleServlet",
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
	}); */
	
	
	/* $(document).ready(function() {
		$('#delete').click(function() {
			var titleId = $("#titleId").val();
			var method = "delete";
			$.ajax({
				type : "post",
				url : "TitleServlet",
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
 */

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
	width: 200px;
}

#grid01 .aw-column-1 {
	width: 550px;
}

#grid01 .aw-column-2 {
	width: 180px;
}

#grid01 .aw-grid-cell {
	border-right: 1px solid threedlightshadow;
}

#grid01 .aw-grid-row {
	border-bottom: 1px solid threedlightshadow;
}
</style>
<br>
<legend>&nbsp; Reports</legend>
	<div style="min-width:80%;" align="left" id="table">
		
			
		
		
			<script type="text/javascript">	
			
			function getReport(){
				
				document.getElementById('excelFrame').src="ReportServletS?"+"repNo="+document.getElementById("selected").value+"&method="+"getReport";
/* 				if(!(document.getElementById("selected").value))
					return;
				if(confirm("Confirm to Open the Excel ?")){
					$.ajax({
						type : "post",
						url : "ReportServletS",
						mimeType: "application/vnd.ms-excel",
						data : {
							repNo :document.getElementById("selected").value ,
							method : "getReport"
						},
						success : function(data) {
									
						},
						error: function(){

			                console.log("error while exporting data");
			            }
					});
				} */
			}
			
			
			function setGridData(grid,data){
				grid.setCellText(data);
				grid.setRowCount(data.length);
				grid.refresh();
				grid.setSelectedRows([]);
			}	
			
	    	var myHeaders = ["Report No","Report Code","Description"];

	        // create grid object
	        var grid1 = new AW.UI.Grid;
	        // assign headers text
	        
	        grid1.setHeaderText(myHeaders);
			grid1.setId("grid01");
	        // set number of columns/rows
	        grid1.setColumnCount(3);
	        grid1.setSelectionMode("single-row");
 	        // write grid to the div table
 	        document.getElementById("table").innerHTML = grid1; 	
 	       var data = [["01","Clients","Total client base report"],["02","Users","System users Report"],["03","Userwise performance","System Userwise performance Report"]];
 	        
 	          grid1.onSelectedRowsChanged = function(row){
					document.getElementById("selected").value=this.getCellText(0,row);
      		  };
		
      		  grid1.onRowDoubleClicked = function(event,row){
		      		document.getElementById("selected").value=this.getCellText(0,row);
		      		
		      			//createExcel(document.getElementById('selected').value,0,0)
		      			getReport();
    									
		      		
		      } ;	        
 	        	
      		setGridData(grid1,data);
      		  
      		
      		/* function createExcel(arg,PrintOrNot,testPageOrNot){
				chkBranch= false;
				date = document.getElementById('advertiseDate').value;
				testPageOrNotx = testPageOrNot;
				PrintOrNotx = PrintOrNot;
				
				switch(parseInt(arg)){
					case 40:
						chkBranch= false;
						urlx = "getLeasesClosed";
						//showBranchAndDay();
						$('BranchAndDay').click();
					break;
				}
			 }	 */
	      </script>
	    </div>
	    <br><br>
	               			<input type="hidden" id="selected">
		<form class='form-horizontal' id='registration' onsubmit="" style="min-width: 700px" method='post'>
		<input type="hidden" id="titleId">
			<fieldset >
				<div class='control-group'>
					<label class='control-label'></label>
					<div class='controls' style="margin-right: 10%">
<!-- 						<button type="button" id = 'submit' onclick="getReport()" class='btn btn-one' >Open</button>
 -->					</div>
				</div>
			</fieldset>
		</form>
      <iframe frameborder="0" width="0" height="0" id="excelFrame" name="excelFrame" style="display: none;"></iframe>
<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>