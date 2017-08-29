<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html >
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>InventryPro</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<script src="js/jquery-1.4.2.min.js"></script>
<link href="styleBody.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="css/smoothness/jquery-ui.css" />
<script src="js/jquery-1.10.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<!-- for footer -->
<link rel='stylesheet' id='custom-css' href='front-css/custom.css'
	type='text/css' media='all' />
<script>
$(function() {
$( "#accordion" ).accordion({
	heightStyle: "content",
    autoHeight: false,
clearStyle: true,
collapsible: true
});
});
</script>
<script type="text/javascript">

$(document).ready(function() {
	$.getAll=function(){
		var method = "getAllMenus";
		$.ajax({
			type : "post",
			url : "MenuServletS",
			data : {
				method : method
			},
			success : function(data){
				var fieldData=new Array();
				fieldData=data["menu1"];
				var listItem = document.getElementById("ref");
				for( var i =  0 ; i < fieldData.length ; i++){
                    listItem.innerHTML = listItem.innerHTML + "<a class='list'  id='"+fieldData[i][1]+"'>"+fieldData[i][0]+"</a> <br>";
            	}
				
				var fieldData2=new Array();
				fieldData2=data["menu2"];
				var listFront = document.getElementById("front");
				for( var i =  0 ; i < fieldData2.length ; i++){
					listFront.innerHTML = listFront.innerHTML + "<a class='list'  id='"+fieldData2[i][1]+"'>"+fieldData2[i][0]+"</a><br>";
            	}
								
				
				var fieldData5=new Array();
				fieldData5=data["menu3"];
				var listApprove = document.getElementById("UserAccessControls");
				for( var i =  0 ; i < fieldData5.length ; i++){
					listApprove.innerHTML = listApprove.innerHTML + "<a class='list'  id='"+fieldData5[i][1]+"'>"+fieldData5[i][0]+"</a> <br>";
            	}
				
				var fieldData6=new Array();
				fieldData6=data["menu4"];
				var listReport = document.getElementById("cusCare");
				for( var i =  0 ; i < fieldData6.length ; i++){
					listReport.innerHTML = listReport.innerHTML + "<a class='list'  id='"+fieldData6[i][1]+"'>"+fieldData6[i][0]+"</a><br>";
            	}
				
				var fieldData7=new Array();
				fieldData7=data["menu5"];
				var listInfo = document.getElementById("report");
				for( var i =  0 ; i < fieldData7.length ; i++){
					listInfo.innerHTML = listInfo.innerHTML + "<a class='list'  id='"+fieldData7[i][1]+"'>"+fieldData7[i][0]+"</a><br>";
            	}
				var fieldData8=new Array();
				fieldData8=data["menu6"];
				var listInfo = document.getElementById("info");
				for( var i =  0 ; i < fieldData8.length ; i++){
					listInfo.innerHTML = listInfo.innerHTML + "<a class='list'  id='"+fieldData8[i][1]+"'>"+fieldData8[i][0]+"</a><br>";
            	}
			}
		});
	};			
	$.getAll();
});
  	$(document).ready(function(){
  		$(document.body).on('click', '#titleWindow' ,function(){
  			$("#contentPanel").load("title.jsp");
  		});
  		$(document.body).on('click', '#clientRegistrationWindow' ,function(){
  			$("#contentPanel").load("ClientCreationS.jsp");
  		});
  		$(document.body).on('click', '#officerTypeWindow' ,function(){
  			$("#contentPanel").load("OfficerTypeS.jsp");
  		});
  		$(document.body).on('click', '#OfficerWindow' ,function(){
  			$("#contentPanel").load("officerS.jsp");
  		});
  		$(document.body).on('click', '#accessGroupWindow' ,function(){
  			$("#contentPanel").load("AccessGroupsS.jsp");
  		});
  		$(document.body).on('click', '#inquiryWindow' ,function(){
  			$("#contentPanel").load("Inquiry.jsp");
  		});
  		$(document.body).on('click', '#rangeWindow' ,function(){
  			$("#contentPanel").load("ranges.jsp");
  		});
  		$(document.body).on('click', '#protypeWindow' ,function(){
  			$("#contentPanel").load("protypes.jsp");
  		});
  		$(document.body).on('click', '#reportsWindow' ,function(){
  			$("#contentPanel").load("reports.jsp");
  		});  		
  		
  	}); 	
</script>

</head>
<body>
<noscript>
<style>
body{
	display:none;
}
</style>
</noscript>
<div id="wrapper">
	<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
		<c:redirect url="/index.jsp"></c:redirect>
	</c:if>
	<div id="header-wrapper">
		<div id="header" style="float:left;width: 100%" >
			<div id="logo" > 
				<h1 style="font-style: italic; font-family: Georgia, Times New Roman, Times, serif;"><a style="color:white;">Inventry Management</a></h1>
			</div>
			<div style="float:right;margin-right:40px;color:#e5f99a;font-size: 15px;"> 
	            	<strong style="margin-right: 4px"><c:out value="${sessionScope.firstName}"/></strong>&nbsp;
					&nbsp;|
					 <a href="LogoutServlet" style="color: #e5f99a;"><b>Logout</b></a> 
            	</div>
		</div>
		
	</div>
	<!-- end #header -->
	<div style="display: inline-block;min-width:100%; " >
<div style="width:15% ;height:100%; display: inline-block; ">	
<div id="accordion" style="width: 93%">
<h3>Reference Data</h3>
<div id="ref">
    <!-- <a class="list"  id="titleWindow">Titles</a>
    <a class="list" id="countryWindow" >Countries</a>
    <a class="list"  id="loanSchemeWindow">Loan Schemes</a>
    <a class="list"  id="loanTypeWindow">Loan Types</a>
	<a class="list"  id="postalCodeWindow">Postal Codes</a>    
	<a class="list"  id="officerTypeWindow">Officer Types</a>     -->
</div>
<h3>Front Office</h3>
<div id="front">
   <!--  <a class="list"  id="clientRegistrationWindow">Applicant Registration</a>
    <a class="list"  id="applicationsWindow">Applications</a> -->
</div>
<h3>User and Access Controls</h3>
<div id="UserAccessControls">
   <!--  <a class="list"  id="appraisalWindow" >Application Appaisal</a> -->    
</div>
<h3>Customer Care</h3>
<div id="cusCare">
  <!--   <a class="list"  id="applicationApprovalWindow" >Application Approval</a>     -->
</div>
<h3>Report Center</h3>
<div id="report">
<!--     <a class="list"  id="pipelineReportWindow" >Application Pipeline</a>    
    <a class="list"  id="repayScheduleReportWindow" >Loan Repayment Schedule</a>     -->
</div>
<h3>Information Center</h3>
<div id="info">  
    <!-- <a class="list"  id="pipelineWindow" >Application Pipeline</a>    
    <a class="list"  id="repayScheduleWindow" >Loan Repayment Schedule</a>    
    <a class="list"  id="inquiryWindow" >Inquiries</a>   -->
</div>
</div>
</div>
<div id="contentPanel" style = "width:85%;float:right; min-height:800px;border-right:1px solid #e5e5e5 ;border-left:1px solid #e5e5e5 ;">
</div>
</div>
<div id="copyright">
	<div class="container">
		<div class="row">
			<div class="left span6">
				<p>
					<a>
					</a>
				</p>
			</div>
			<div class="right span6"></div>
		</div>
	</div>
</div>
<!-- <div id="footer">
	<p>Copyright (c) 2014 OpenArc All rights reserved</p>
</div> -->
<!-- end #footer -->
</div>
</body>
</html>
