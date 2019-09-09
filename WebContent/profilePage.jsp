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
<script type="text/javascript">
function onPageLoad(){
	$(document).ready(function(){
        $("#profile_form :input").prop("disabled", true);
    });
}
function editDetails(){
	 document.getElementById('username').style.background ="#e6e6e6";
	 document.getElementById('username').style.color ="#666666";
	 document.getElementById('password').disabled=false;
	 document.getElementById('firstname').disabled=false;
	 document.getElementById('lastname').disabled=false;
	 document.getElementById('id').disabled=false;
	 document.getElementById('role').style.background ="#e6e6e6";
	 document.getElementById('role').style.color ="#666666";
	 document.getElementById('phone').disabled=false;
	 document.getElementById('email').disabled=false;
	 document.getElementById('address').disabled=false;
	 document.getElementById('city').disabled=false;
	 document.getElementById('state').disabled=false;
	 document.getElementById('zip').disabled=false;
	 document.getElementById('update').disabled=false;
	 document.getElementById('reset').disabled=false;
	 document.getElementById('edit').disabled=true;
}
</script>
<title>Profile - UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'>
	<div class="offset-md-1">
	<h1>My Profile <input id="edit" type="button" class="btn btn-primary col-md-3 offset-md-2 col-auto" value = "Edit Details" onclick="editDetails();" style="color:white; cursor: pointer;"/> 
	<a class="btn btn-danger col-md-1 offset-md-1 col-auto" href="/UTA_Mac_FMS/LogoutController">Logout</a></h1>
		<form action="/UTA_Mac_FMS/UpdateProfileController"	method="POST" id="profile_form">
			<div class="row">
				<div class="col">
					<label for="username">User Name</label> <input name="username" id="username" value= "advse" class="form-control" READONLY>
				</div>
				<div class="col">
				<br> <input value='${my_profile.username}' class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="passowrd">Password</label> <input name="password" id='password' type='password' value='${my_profile.password}' class="form-control" disabled>
				</div>
				<div class="col">
				<br> <input value='${my_profile.password}' class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> 
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="fname">First Name</label> <input name="fname" id="firstname" type="text" class="form-control" value='${my_profile.firstname}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="lname">Last Name</label> <input name="lname" id="lastname" type="text" class="form-control" value='${my_profile.lastname}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="name">UTA ID</label> <input name="id" id="id" type="text" class="form-control" value='${my_profile.id}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="role">User Role</label> <select name="role" id="role" class="form-control" value='${my_profile.role}' READONLY>
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
					<label for="phone">Phone</label> <input name="phone" type="text" id="phone" class="form-control" value='${my_profile.phone}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60" disabled>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="username">Email Address</label> <input name="email" id="email" type="text" class="form-control" value='${my_profile.email}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="address">Address</label> <input name="address" id="address" type="text" class="form-control" value='${my_profile.address}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="city">City</label> <input name="city" type="text" id="city" class="form-control" value='${my_profile.city}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="rostatele">State</label> <select name="state" id="state" class="form-control" value='${my_profile.state}' disabled>
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
					<label for="zip">Zip Code</label> <input name="zip" id="zip" type="text" class="form-control" value='${my_profile.zip}' disabled>
				</div>
				<div class="col">
				<br> <input value="Add cout Error here" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
			<div class="col">
			<input id="update" type="submit" class="btn btn-primary col-md-3" value="Update Details" disabled/>
			<button id="reset" type="reset" class="btn btn-primary col-md-3" onClick="window.location.reload();" disabled>Reset</button>
			</div>
			</div>
			<br>
		</form>
	</div>
</body>
</html>