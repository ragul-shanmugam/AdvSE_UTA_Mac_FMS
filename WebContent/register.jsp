<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="offset-md-1">
	<h1>User Registration to UTA Mac FMS</h1>
		<form action="/UTA_Mac_FMS/LoginController?action=register"
			method="POST" name="registration_form">
			<div class="row">
				<div class="col">
					<label for="username">User Name</label> <input name="username"
						type="text" class="form-control" placeholder="User name">
				</div>
				<div class="col">
					<label for="usernameError"> <br>
					</label> <input name="usernameError" type="text" class="form-control" placeholder="Add Error message in this 'div'">
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
					<label for="role">User Role</label> <select name="role"
						class="form-control">
						<!-- <option selected>Choose...</option> -->
						<option value="User">User</option>
						<option value="Manager">Facility Manager</option>
						<option value="Repairer">Repairer</option>
						<option value="Admin">Admin</option>
					</select>
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
						type="text" class="form-control" placeholder="Email">
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
					<label for="rostatele">State</label> <select name="state"
						class="form-control">
						<!-- <option selected>Choose...</option> -->
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DE">Delaware</option>
						<option value="DC">District Of Columbia</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="IA">Iowa</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="ME">Maine</option>
						<option value="MD">Maryland</option>
						<option value="MA">Massachusetts</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MS">Mississippi</option>
						<option value="MO">Missouri</option>
						<option value="MT">Montana</option>
						<option value="NE">Nebraska</option>
						<option value="NV">Nevada</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NY">New York</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VT">Vermont</option>
						<option value="VA">Virginia</option>
						<option value="WA">Washington</option>
						<option value="WV">West Virginia</option>
						<option value="WI">Wisconsin</option>
						<option value="WY">Wyoming</option>
					</select>
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
			<button type="submit" class="btn btn-primary">Register</button>
			<button type="reset" class="btn btn-primary">Reset</button>
			<br>
		</form>
	</div>
</body>
</html>