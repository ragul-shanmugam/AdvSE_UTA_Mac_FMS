<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>User Registration</title>
</head>
<body>
	<h1>User Registration to UTA Mac FMS</h1>
	<div class="offset-md-1">

		<form action="/UTA_Mac_FMS/LoginController?action=register"
			method="POST" name="registration_form">
			<div class="row">
				<div class="col">
					<label for="username">User Name</label> <input name="username"
						type="text" class="form-control" placeholder="User name">
				</div>
				<div class="col">
					<label for="usernameError"> <br>
					</label> <input name="usernameError" type="text" class="form-control"
						placeholder="User name">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="passowrd">Password</label> <input name="password"
						type="password" class="form-control" placeholder="Password">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="confirm">Confirm Password</label> <input name="confirm"
						type="password" class="form-control"
						placeholder="Confirm Password">
				</div>
				<div class="col">
					<div id="msg"></div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="fname">First Name</label> <input name="fname"
						type="text" class="form-control" placeholder="First name">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="lname">Last Name</label> <input name="lname"
						type="text" class="form-control" placeholder="Last name">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="name">UTA ID</label> <input name="id" type="text"
						class="form-control" placeholder="UTA ID">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="phone">Phone</label> <input name="phone" type="text"
						class="form-control" placeholder="Phone">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="username">Email Address</label> <input name="email"
						type="email" class="form-control" placeholder="Email">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="address">Address</label> <input name="address"
						type="text" class="form-control" placeholder="Address">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="city">City</label> <input name="city" type="text"
						class="form-control" placeholder="City">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="state">State</label> <input name="state" type="text"
						class="form-control" placeholder="State (For Example., TX)">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="zip">Zip Code</label> <input name="zip" type="text"
						class="form-control" placeholder="Zip Code">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="role">User Role</label> <select name="role"
						class="form-control">
						<option selected>Choose...</option>
						<option>User</option>
						<option>Facility Manager</option>
						<option>Repairer</option>
						<option>Admin</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Register</button>
			<br>
		</form>
	</div>
</body>
</html>