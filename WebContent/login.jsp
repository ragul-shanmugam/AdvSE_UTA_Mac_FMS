<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>User Login</title>
</head>
<body>
	<div class="offset-md-1">
		<h1>Login to UTA Mac FMS</h1>
		<div>
			<form action="/UTA_Mac_FMS/LoginController?action=login"
				method="POST" name="login_form">
				<br> Username: <input type="text" name="username"><br>
				<br> Password: <input type="password" name="password"><br>
				<div class="button-box col-lg-12 offset-md-1">
					<br> <input type="submit" class="btn btn-primary"
						role="button" value="Login" onclick="showError()"> 
						<input type="reset" class="btn btn-primary" role="button" value="Clear"> <br>
				</div>
			</form>
		</div>
		<br></br>
		<div id="error" class="alert alert-danger alert-dismissible d-none">
			<strong>Error!</strong> You're not a registered user. Please click
			here to Register! <br> <br> <a class="btn btn-primary"
				href="/UTA_Mac_FMS/register.jsp" role="button">Register</a>
		</div>
	</div>
<script>
function showError() {
	if('${nouser}' == "usererror")
		{
			$("#error").removeClass('d-none');
		}
}
</script>	
</body>
</html>