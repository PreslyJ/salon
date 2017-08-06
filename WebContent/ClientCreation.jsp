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
		$("#businessName").prop( "disabled", true );
		$("#businessRegNo").prop( "disabled", true );
		$( "#applicantType" ).change(function() {
			var applicantType=$("#applicantType").val();
			if(applicantType=="Individual"){
				$("#title").prop( "disabled", false );
				$("#initials").prop( "disabled", false );
				$("#surname").prop( "disabled", false );
				$("#NIC").prop( "disabled", false );
				$("#initialsFull").prop( "disabled", false );
				$("#businessName").prop( "disabled", true );
				$("#businessRegNo").prop( "disabled", true );
			}else if(applicantType=="Corporate"){
				$("#title").prop( "disabled", true );
				$("#initials").prop( "disabled", true );
				$("#surname").prop( "disabled", true );
				$("#NIC").prop( "disabled", true );
				$("#initialsFull").prop( "disabled", true );
				$("#businessName").prop( "disabled", false );
				$("#businessRegNo").prop( "disabled", false );
			};
		});				
		$.ajax({
			type : "post",
			url  : "ApplicantRegistrationServlet",
			data : {
				method : "getTitlesPostalCodesCountries",
			},
			success : function(data) {
				loadTitle(data[0]);
				loadPostalCode(data[2]);
			}
		});
	});
	//adding applicant
	$(document).ready(function() {
		$('#submit').click(function() {
			var error=false;
			var applicType=$("#applicantType").val();
			if(applicType=="Individual"){
				$('#reg1').parsley().reset();
				var y=1;
				while(y<6){
					if (false === $('#reg1').parsley().validate('block'+y,true))
						error=true;
					y++;
				}
			}else if(applicType=="Corporate"){
				$('#reg1').parsley().reset();
				var x=6;
				while(x<8){
					if (false === $('#reg1').parsley().validate('block'+x,true))
						error=true;
					x++;
				}				
			}
			var i=8;
			while(i<16){
				if (false === $('#reg2').parsley().validate('block'+i,true))
					error=true;
				i++;
			}
		    if(error==false){   			
				var titleId = $("#title").val();
				var initials = $("#initials").val();
				var initialsFull = $("#initialsFull").val();
				var surname = $("#surname").val();
				var NIC = $("#NIC").val();
				var businessName = $("#businessName").val();
				var businessRegNo = $("#businessRegNo").val();
				var address1 = $("#address1").val();			
				var address2 = $("#address2").val();
				var address3 = $("#address3").val();
				var city = $("#city").val();
				var mobileNo = $("#mobileNo").val();
				var officeNo = $("#officeNo").val();			
				$.ajax({
					type : "post",
					url : "ApplicantRegistrationServlet",
					data : {
						method : "create",	
						titleId : titleId,
						initials:initials,
						initialsFull:initialsFull,
						surname : surname,
						NIC : NIC,
						businessName : businessName,
						businessRegNo : businessRegNo,
						address1 : address1,
						address2 : address2,
						address3 : address3,
						city : city,
						mobileNo : mobileNo,
						officeNo : officeNo
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
				<label class='control-label'>Applicant Type :</label>
				<div class='controls'>
					<select id="applicantType" >
						<option value="Individual">Individual</option>
						<option value="Corporate">Corporate</option>
					</select>
				</div>
			</div>
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
				<label class='control-label'>Initials :</label>
				<div class='controls'>
					<input type='text' id='initials' data-parsley-required="true" data-parsley-pattern="^[A-Z][/.]$" data-parsley-length="[1,15]" onkeyup="removeSpecialCharacters('initials')" name="initials" data-parsley-group="block2" required>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Initials In Full :</label>
				<div class='controls'>
					<input type='text' id='initialsFull'  data-parsley-pattern="^[A-Za-z ]*$" data-parsley-length="[1,255]" onkeyup="removeSpecialCharacters('initialsFull')" data-parsley-group="block3" required>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Surname :</label>
				<div class='controls'>
					<input type='text' id='surname' data-parsley-maxwords="1" data-parsley-length="[1,100]" data-parsley-pattern="^[A-za-z\\s]+$" onkeyup="removeSpecialCharacters('surname')" data-parsley-group="block4"  required>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>NIC :</label>
				<div class='controls'>
					<input type='text' id='NIC'  data-parsley-pattern="^[0-9]{9}[XV]{1}$" onkeyup="removeSpecialCharacters('NIC')" data-parsley-group="block5" required>				
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Business Name :</label>
				<div class='controls'>
					<input type='text' id='businessName'  data-parsley-pattern="^[A-Za-z ]*$" data-parsley-length="[1,255]" onkeyup="removeSpecialCharacters('businessName')" data-parsley-group="block6"  required>			
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Business Reg No :</label>
				<div class='controls'>
				<input type='text' id='businessRegNo' onkeyup="removeSpecialCharacters('businessRegNo')" data-parsley-length="[1,255]" data-parsley-group="block7" required>
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
			<div class='control-group'>
				<label class='control-label'>Address Line 1 :</label>
				<div class='controls'>
					<input type='text' id='address1' onkeyup="removeSpecialCharacters('address1')" data-parsley-length="[1,100]" data-parsley-group="block8" required>			
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Address Line 2 :</label>
				<div class='controls'>
				<input type='text' id='address2' onkeyup="removeSpecialCharacters('address2')" data-parsley-length="[1,100]" data-parsley-group="block9" required >
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>Address Line 3 :</label>
				<div class='controls'>
				<input type='text' id='address3' onkeyup="removeSpecialCharacters('address3')" data-parsley-length="[1,100]" data-parsley-group="block9" required >
				</div>
			</div>
		<br>
			<div class='control-group'>
				<label class='control-label'>City :</label>
				<div class='controls'>
				<input type='text' id='city' onkeyup="removeSpecialCharacters('city')" data-parsley-length="[1,60]" data-parsley-group="block10" required >
				</div>
		</div>
			
			<div class='control-group'>
				<label class='control-label'>Mobile No :</label>
				<div class='controls'>
				<input type='text' id='mobileNo' data-parsley-pattern="^[0-9]{10,15}$"  onkeyup="removeSpecialCharacters('mobileNo')" data-parsley-group="block14" required>
				</div>
		</div>
			<div class='control-group'>
				<label class='control-label'>Office No :</label>
				<div class='controls'>
				<input type='text' id='officeNo' data-parsley-pattern="^[0-9]{10,15}$"  onkeyup="removeSpecialCharacters('officeNo')" data-parsley-group="block15" required>
				</div>
		</div>
		</fieldset>

	</form>
	</div>
	<input type='hidden' id='modify' />&nbsp;
	<input type='hidden' id='delete' />&nbsp;
</div>	
<c:if test='<%=request.getSession().getAttribute("OfficerTypeId") == null%>'>
	<c:redirect url="/blank.html"></c:redirect>
</c:if>