<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>TechnoWhiz</title>

  <link rel="stylesheet" href="css/reset.css">

  <link rel="stylesheet" href="css/lgstyle.css" media="screen" type="text/css" />
  <!-- <script src="js/jquery-1.10.min.js">
  </script>
  <script type="text/javascript">
  	$(document).ready(function() {
		$('#submit').click(function() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			
			$.ajax({
				type : "post",
				url : "LoginServlet",
				data : {
					method : "mainLogin",
					username:username,
					password : password
				},
				success : function(data) {
					alert(data);
				}
			});
		});
	});
  
  </script> -->
  
</head>

<body>

  <div class="wrap">
		<div class="avatar">
		<img src="images/b.jpg"> 
		</div>
		<form action="LoginServlet" method="post">
			<input type="text" id="username"   name="username"  placeholder="username" required>
			<div class="bar">
				<i></i>
			</div>
			<input type="password" id="password" name="password"  placeholder="password" required>
			<button type="submit" id="submit">Sign in</button>
		</form>
	</div>

  <script src="js/index.js"></script>

</body>

</html>