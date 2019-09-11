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
<title>UTA Mac FMS</title>
</head>
<body>
	<%-- <c:if test="${'Repairer'==loggedinuserrole}"></c:if> --%>
	<div class="button-box col-lg-12 offset-md-1">
		<h1><a	class="btn btn-secondary " href='${backListPage}'>Back</a> MAR Details for ${mar.marNumber} <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/MarController?action=updateMarDetails" method="POST" name="mar_form">
				<div class="row">
					<div class="col">
						<label for="marnumber">MAR number</label> <input type="text" name="marnumber" id="marnumber" class="form-control" value='${mar.marNumber}'>
					</div>
					<div class="col"></div>
				</div> <br>
				<div class="row">
					<div class="col">
						<label for="type">Facility Type</label> <input type="text" name="type" id="type" class="form-control"	value='${mar.facilityType}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="rid">Reservation ID</label> <input type="text" name="rid" id="rid" class="form-control"	value='${mar.reservationId}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="reportedby">Reported By</label> <input type="text" name="reportedby" id="reportedby" class="form-control"	value='${mar.reportedBy}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="urgency">Urgency</label> <input type="text" name="urgency" id="urgency" class="form-control"	value='${mar.urgency}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="description">Description</label> <input type="text" name="description" id="description" class="form-control"	value='${mar.description}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="datecreated">Date Created</label> <input type="date" name="datecreated" id="datecreated" class="form-control"	value='${mar.dateCreated}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="assignedto">Assigned To</label> <input type="text" name="assignedto" id="assignedto" class="form-control"	value='${mar.assignedTo}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="assigneddate">Assigned Date</label> <input type="date" name="assigneddate" id="assigneddate" class="form-control"	value='${mar.assignedDate}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="time">Estimated Time</label> <input type="text" name="time" id="time" class="form-control"	value='${mar.estimatedTime}'>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="status">MAR Status</label> <input type="text" name="status" id="status" class="form-control"	value='${mar.marStatus}'>
					</div>
					<div class="col"></div>
				</div>
				<br> <input type="submit"
					class="btn btn-primary col-md-3" role="button" value="Update MAR Details">
			</form>
		</div>
	</div>

</body>
</html>