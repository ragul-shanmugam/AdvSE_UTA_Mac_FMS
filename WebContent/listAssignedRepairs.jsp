<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function onPageLoad() {
		$(document).ready(function() {
			$("#submit :input").prop("disabled", true);
		});
	}
</script>
<title>Assigned Repairs - UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'><br>
		<h1>
			My Repairs <a class="btn btn-secondary offset-md-1 "
				href='${homePage}'>Home Page</a>  <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/MarController?action=listSpecificRepair" method="POST">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col" style="text-align: center">Select Repair</th>
								<th scope="col">MAR number</th>
								<th scope="col">Facility Type</th>
								<th scope="col">Urgency</th>
								<th scope="col">Description</th>
								<th scope="col">Date created</th>
								<th scope="col">Assigned Date</th>
								<th scope="col">Estimated Time</th>
								<th scope="col">Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${MARS}" var="mar" varStatus="status">
								<tr>
									<td style="text-align: center"><input onclick="document.getElementById('submit').disabled = false;" type="radio" id="radioMar${status.count}" name="radioMar" value="${status.count}"></td>
									<td><c:out value="${mar.marNumber}" /></td>
									<td><c:out value="${mar.facilityType}" /></td>
									<td><c:out value="${mar.urgency}" /></td>
									<td><c:out value="${mar.description}" /></td>
									<td><c:out value="${mar.dateCreated}" /></td>
									<td><c:out value="${mar.assignedDate}" /></td>
									<td><c:out value="${mar.estimatedTime}" /></td>
									<td><c:out value="${mar.marStatus}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br> <input type="submit" class="btn btn-primary col-md-3 offset-md-3" id="submit" value="View Repair Details" disabled> <br> <br>
			</form>
		</div>
</body>
</html>