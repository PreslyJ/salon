<jsp:include page="CommonFiles.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script >	
	function loadTitle(data){
		var select = document.getElementById("title");
		for(var i=0; i < data.length; i++) {
			    select.options[select.options.length] = new Option(data[i][1], data[i][0]);
		}
	}

	$(document).ready(function() {
		$.ajax({
			type : "post",
			url  : "ApplicantRegistrationServlet",
			data : {
				method : "getTitlesPostalCodesCountries",
			},
			success : function(data) {
				loadTitle(data[0]);
			}
		});
	});
	//adding applicant
	$(document).ready(function() {
		$('#submit').click(function() {
			var error=false;
			
			var i=8;
			while(i<16){
				if (false === $('#reg2').parsley().validate('block'+i,true))
					error=true;
				i++;
			}
		    if(error==false){   			
				var titleId = $("#title").val();
				var fsName = $("#fsName").val();
				var surname = $("#surname").val();
				var mobileNo = $("#mobileNo").val();			
				$.ajax({
					type : "post",
					url : "ApplicantRegistrationServlet",
					data : {
						method : "create",	
						titleId : titleId,
						fsName:fsName,
						surname : surname,
						mobileNo : mobileNo
					},
					success : function(data) {
						alert(data);
					}
				});
		    };	
		});
	});
</script>
<div>
<div style="float: left;">
		<form class='form-horizontal' id='reg'  style="min-width: 500px" 
		method='post'>
		<br>
		<legend> &nbsp; Stakeholder Creation</legend>
		<fieldset>	
		<br>
			
			<div class='control-group'>
				<label class='control-label'>Title :</label>
				<div class='controls'>
					<select id="title"  data-parsley-group="block1" data-parsley-required="true">
					</select>
				</div>
			</div>
		</fieldset>	
		</form>
		<form class='form-horizontal' id='reg1' 
		method='post'>
		<fieldset>
			<div class='control-group'>
				<label class='control-label'>First Name :</label>
				<div class='controls'>
					<input type='text' id='fsName' data-parsley-required="true" data-parsley-pattern="^[A-Z][/.]$" data-parsley-length="[1,15]" onkeyup="removeSpecialCharacters('fsName')" name="fsName" data-parsley-group="block2" required>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Surname :</label>
				<div class='controls'>
					<input type='text' id='surname' data-parsley-maxwords="1" data-parsley-length="[1,100]" data-parsley-pattern="^[A-za-z\\s]+$" onkeyup="removeSpecialCharacters('surname')" data-parsley-group="block4"  required>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Mobile No :</label>
				<div class='controls'>
				<input type='text' id='mobileNo' data-parsley-pattern="^[0-9]{10,15}$"  onkeyup="removeSpecialCharacters('mobileNo')" data-parsley-group="block14" required>
				</div>
			</div>			
			
			<br><br>			
			<div class='control-group'>
				<label class='control-label'></label>
				<div class='controls'>
					<button type='button' id='submit'  class='btn btn-one'>Add</button>
					<button type='reset' id='cancel' class='btn btn-one'>Cancel</button>
				</div>
			</div>
			
			
			</fieldset>
		</form>			
	</div>
	<div style="float: inherit;">
	<br>
		<form class='form-horizontal' id='reg2' 
		method='post'>
		<fieldset>
			<legend> &nbsp; <br></legend>
			
		</fieldset>

	</form>
	</div>
	<input type='hidden' id='modify' />&nbsp;
	<input type='hidden' id='delete' />&nbsp;
</div>	
<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>