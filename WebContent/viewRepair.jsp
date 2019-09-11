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
<script type="text/javascript">
	function onPageLoad() {
		$(document).ready(function() {
			$("#mar_form :input").prop("disabled", true);
		});
	}
</script>
<title>UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'>
	<div class="button-box col-lg-12 offset-md-1">
	<h1><a	class="btn btn-secondary " href='${backListPage}'>Back</a> </h1>
		<h1> Repair Details for ${mar.marNumber}  <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/MarController" method="POST" name="mar_form">
				<div class="row">
					<div class="col">
						<label for="marnumber">MAR number</label> <input type="text" name="marnumber" id="marnumber" class="form-control" value='${mar.marNumber}' disabled>
					</div>
					<div class="col"></div>
				</div> <br>
				<div class="row">
					<div class="col">
						<label for="type">Facility Type</label> <input type="text" name="type" id="type" class="form-control" value='${mar.facilityType}' disabled>
					</div>
					<div class="col"></div>
				</div><br>

				<div class="row">
					<div class="col">
						<label for="urgency">Urgency</label> <input type="text" name="urgency" id="urgency" class="form-control" value='${mar.urgency}' disabled>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="description">Description</label> <input type="text" name="description" id="description" class="form-control" value='${mar.description}' disabled>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="datecreated">Date Created (MM/DD/YYYY)</label> <input type="text" name="datecreated" id="datecreated" class="form-control" value='${mar.dateCreated}' disabled>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="time">Estimated Time</label> <input type="text" name="time" id="time" class="form-control"	value='${mar.estimatedTime}' disabled>
					</div>
					<div class="col"></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>