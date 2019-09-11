<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Repairer - UTA Mac FMS</title>
</head>
<body>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>Hello, ${user.lastname},${user.firstname}! <a class="btn btn-danger offset-md-1"
			href="/UTA_Mac_FMS/LogoutController">Logout</a></h1>  <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/ProfileController"
			role="button">View/Modify Profile Details</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/MarController"
			role="button">View a MAR</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">Request Repairs</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">View Assigned Repairs</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">Modify Assigned Repairs</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">Cancel Assigned Repairs</a> <br> <br>
	</div>
</body>
</html>