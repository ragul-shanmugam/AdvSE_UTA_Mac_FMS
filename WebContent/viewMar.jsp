<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function onPageLoad(){
	$('.datepicker').datepicker({
	    startDate: '-3d'
	});
}
</script>
<title>UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>MAR Details</h1>
		<div>
			<form action="/UTA_Mac_FMS/LoginController?action=login"
				method="POST" name="login_form">
				<div class="row">
					<div class="col">
						<label for="calendar">Choose a date:</label> <input
							name="calendar" class="datepicker form-control" data-date-format="mm/dd/yyyy" >
					</div> 
					<div class="col">
						<input value="Add cout Error here" class="form-control"
							id="login_errorMessage" type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							disabled="disabled" maxlength="60">
					</div>
				</div>
				<br> <br> <input type="submit"
					class="btn btn-primary col-md-3" role="button" value="View MAR Details">
			</form>
		</div>
	</div>

</body>
</html>