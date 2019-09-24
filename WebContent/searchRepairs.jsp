<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
 -->
 <script type="text/javascript" src="bootstrap-datepicker.de.js"></script>

 
<script type="text/javascript">
$(function () {
    $("#datepicker").datepicker();
});
</script>
<title>Search Assigned Repairs - UTA Mac FMS</title>
</head>
<body><br>
<div class="button-box col-lg-12 offset-md-1">
		<h1>
			Search for Assigned Repair <a class="btn btn-primary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/UserRoleController" method="GET" name="get_user_role">
				<div class="row">
				<div class="col">
					<label for="date">Choose a date</label> 
					<input type="text" name="datepicker" id="datepicker" class="form-control" placeholder="MM/DD/YYYY">
				</div>
				<div class="col">
				<br> <input value='${repairNotExist}' class="form-control" id = "repair_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>	
			<br> <input type="submit" class="btn btn-primary col-md-3" role="button" value="Search for Repairs"> 
			</form>
		</div>
	</div>
</body>

</html>