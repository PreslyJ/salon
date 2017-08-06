<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-button.min.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-custom.css" />
<link type="text/css" rel="stylesheet" href="css/aw/aw.css" />
<link type="text/css" rel="stylesheet" href="css/aqua/theme.css" />
<link type="text/css" rel="stylesheet" href="css/button.css" />
<script src="js/aw.js" type="text/javascript"></script>
<script src="js/jquery-1.10.min.js" type="text/javascript"></script>

<noscript>
<style>
body{
	display:none;
}
</style>
</noscript>
<link rel='stylesheet' href='js1/calendar/calendar.css' title='calendar'>
<script type="text/javascript" src="js1/calendar/calendar.js"></script>

<script type="text/javascript" >

//***** Function formating the Date for inputbox *****
function fnSetDateFormat(oDateFormat)
{
  oDateFormat['FullYear'];		//Example = 2007
  oDateFormat['Year'];		//Example = 07
  oDateFormat['FullMonthName'];	//Example = January
  oDateFormat['MonthName'];	//Example = Jan
  oDateFormat['Month'];		//Example = 01
  oDateFormat['Date'];		//Example = 01
  oDateFormat['FullDay'];		//Example = Sunday
  oDateFormat['Day'];		//Example = Sun
  oDateFormat['Hours'];		//Example = 01
  oDateFormat['Minutes'];		//Example = 01
  oDateFormat['Seconds'];		//Example = 01

  var sDateString;
  sDateString = oDateFormat['FullYear']+"/"+oDateFormat['Month']+"/"+oDateFormat['Date'];
  return sDateString;
}
function a (){
	user = "";
	type = ${sessionScope.OfficerTypeId};
	if(type!=""){
		user = type;
	}else{
		 window.location.assign("index.jsp");
	}	
	if(user!=1){
		 document.getElementById("submit").disabled=true;
		 document.getElementById("modify").disabled=true;
		 document.getElementById("delete").disabled=true;
	};
};
</script>
<script src="js1/parsley.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById("delete").disabled=true;
		document.getElementById("modify").disabled=true;
		$('#cancel').click(function() {
			if(user==1){
				 document.getElementById("submit").disabled=false;
				 document.getElementById("modify").disabled=true;
				 document.getElementById("delete").disabled=true;
			}
		});		
	});	
	function removeSpecialCharacters(x){
		var code=document.getElementById(x).value;
		var name = code.replace(/[!@#<>+~`=$%^&*]/g, "");
		document.getElementById(x).value=name;
	};
</script>	
