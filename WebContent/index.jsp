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
	<div class="button-box col-lg-12 offset-md-1">
		<h1>Login to UTA Mac FMS</h1>
		<div>
			<form action="/UTA_Mac_FMS/LoginController?action=login"
				method="POST" name="login_form">
				<br> Username: <input type="text" name="username"><br>
				<br> Password: <input type="password" name="password"><br>
					<br> <input type="submit" class="btn btn-primary offset-md-1"
						role="button" value="Login"> 
						<input type="reset" class="btn btn-primary" role="button" value="Clear"> <br> <br>
						<a class="btn btn-success col-md-4" href="/UTA_Mac_FMS/register.jsp" role="button">Register here!!!</a> <br> <br>
			</form>
		</div>
	</div>
</body>
</html>