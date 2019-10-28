package com.group4.macfms.model;

import java.util.Objects;
import java.util.regex.Pattern;

import com.group4.macfms.data.LoginDAO;

public class User {

	private String userName;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String id;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String role;
	
	public User() {}
	
	public User(String firstname, String lastname, String username, String password, String confirmPassword, String uta_id, String role,
			String phone, String email, String street_add, String city, String state, String zip) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.userName = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.id = uta_id;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.address = street_add;
		this.city = city;
		this.state = state;
		this.zipCode = zip;
		
	}

	/*public void setUser(String username, String password, String confirmPassword, String firstname, String lastname, String id, String phone, 
			String email, String address, String city, String state, String zipcode, String role) {
		setUsername(username);
		setPassword(password);
		setConfirmPassword(confirmPassword);
		setFirstname(firstname);
		setLastname(lastname);
		setId(id);
		setPhone(phone);
		setEmail(email);
		setAddress(address);
		setCity(city);
		setState(state);
		setZipcode(zipcode);
		setRole(role);
	}*/
	

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipCode;
	}

	public void setZipcode(String zipcode) {
		this.zipCode = zipcode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void validateUser(User loginUser, UserErrorMsgs errorMsg) {
		if((loginUser.getUsername().equals("")) || (loginUser.getPassword().equals("")))
		{
			errorMsg.setLoginErrMsg("Username or Password cannot be empty");
		}
	}

	public void validateUserPassword(User loginUser, UserErrorMsgs errorMsg) {
		LoginDAO regDb = new LoginDAO();
		User userInDB = regDb.userCheck(loginUser.getUsername());
		if(!(userInDB.getPassword().equals(loginUser.getPassword())))
		{
			errorMsg.setLoginErrMsg("Incorrect Username or Password");
		}		
	}


	public void validateUserDetails(User user, UserErrorMsgs errorMsg) {
		//LoginDAO checkUsername = new LoginDAO();
		errorMsg.setUsernameError(validateUsername(user.getUsername()));
		errorMsg.setFnameError(validateFirstName(user.getFirstname()));
		errorMsg.setLnameError(validateLastName(user.getLastname()));
		errorMsg.setPasswordError(validatePassword(user.getPassword()));
		errorMsg.setIdError(validateId(user.getId()));
		errorMsg.setEmailError(validateEmail(user.getEmail()));
		errorMsg.setPhoneError(validatePhone(user.getPhone()));
		errorMsg.setConfirmPasswordError(validateConfirmPassword(user.getConfirmPassword(), user.getPassword()));
		errorMsg.setAddressError(validateAddress(user.getAddress()));
		errorMsg.setCityError(validateCity(user.getCity()));
		errorMsg.setZipCodeError(validateZipCode(user.getZipcode()));
		errorMsg.setCommonerrorMsg();
	}
	
	public void validateUserDetailsAdmin(User user, UserErrorMsgs errorMsg) {
		//LoginDAO checkUsername = new LoginDAO();
		errorMsg.setFnameError(validateFirstName(user.getFirstname()));
		errorMsg.setLnameError(validateLastName(user.getLastname()));
		errorMsg.setPasswordError(validatePassword(user.getPassword()));
		errorMsg.setIdError(validateId(user.getId()));
		errorMsg.setEmailError(validateEmail(user.getEmail()));
		errorMsg.setPhoneError(validatePhone(user.getPhone()));
		errorMsg.setConfirmPasswordError(validateConfirmPassword(user.getConfirmPassword(), user.getPassword()));
		errorMsg.setAddressError(validateAddress(user.getAddress()));
		errorMsg.setCityError(validateCity(user.getCity()));
		errorMsg.setZipCodeError(validateZipCode(user.getZipcode()));
		errorMsg.setCommonerrorMsg();
	}
	
	public void validateUserExistsAdmin(boolean usersInDB, UserErrorMsgs errorMsg) {
		if(usersInDB)
		{
			errorMsg.setUserNotExistError("User not exists with search criteria");
		}
		else
		{
			//do nothing
		}
	}
	
	public void validateUserExists(String userName, UserErrorMsgs errorMsg) {
		errorMsg.setUserNotExistError(validateUserExistsUsername(userName));
	}

	
	private String validateUserExistsUsername(String userName2) {
		boolean hasChar = false;
		boolean hasNumber = false;
		String result="";
		char[] array=userName2.toCharArray();
		if (userName2.equals(""))
			result= "Please enter a Username to search";
		else{
			if (LoginDAO.checkUniqueUsername(userName2))
				result="Username not exists in system! Please try a different Username";
			else if(!stringSize(userName2,3,16))
				result = "Username should be 3 and 16 characters long";
			
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				else if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Username should not contain special characters or numeric characters";
		}
		}
		return result;
	}

	private String validateZipCode(String zipcode) {
		String result = "";
		if(isTextAnInteger(zipcode) == false) {
		result = "Zip code should be a 5-digit number";
		}
		else if(zipcode.length()!=5)
		{
		result = "Zip code should be a 5-digit number";
		} 
		return result;
	}

	private String validateCity(String city) {
		String result="";
		if(city.length() == 0)
			result = "City cannot be empty";
		else if(!stringSize(city,1,21))
			result = "City name should be between 2 and 20 characters long";
		else if(hasChar(city) || isCharAnInteger(city))
			result = "City name should only have alphabets";
		return result;
	}


	private String validateAddress(String address) {
		String result;
		if(address.equals(""))
			result = "Address cannot be empty";
		else if(!stringSize(address,4,31))
			result = "Street address should be between 5 and 30 characters long";
		else
			result = "";
		return result;
	}

	private String validateConfirmPassword(String confirmPassword, String password) {
		String result="";
		if(!Objects.equals(confirmPassword, password)) {
			result = "The passwords don't match";
		}
		else
			result = "";
		return result;
	}


	private String validateEmail(String email) {
		String result="";
		if(email.equals("")) {
			result = "Email cannot be empty";
		}
		else{
		 String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                 "[a-zA-Z0-9_+&*-]+)*@" + 
                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                 "A-Z]{2,7}$"; 
		 Pattern p = Pattern.compile(regex);
		 if(! p.matcher(email).matches()) {
			 result = "Please enter a valid email address";
		 }
		 else
			 result="";
		 }
		 return result;
	}


	private String validatePhone(String phone) {
		String result="";
		if(isTextAnInteger(phone) == false || phone.length() != 10) {
			result = "Please enter a valid 10-digit phone number";
		}
		else
			result = "";
		
		return result;
	}


	private String validateId(String id) {
		String result="";
		if(isTextAnInteger(id) == false) {
			result = "UTA ID should be a 10-digit number";
		}
		else if(id.length() != 10) {
			result="UTA ID should be a 10-digit number";
		}
		else
			result="";
		return result;
	}
	
	private String validatePassword(String password) {
		boolean hasChar = false;
		boolean hasNumber = false;
		char[] array=password.toCharArray();
		String result="";
		if (!stringSize(password,7,13)) {
			result= "Password should have atleast 8 characters but not exceed 12";
		}
		else{
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				else if(Character.isDigit(b)) {
					hasNumber = true;
				}	
			}
		}
		if(!hasChar && !hasNumber) {
			result = "Your password must contain atleast 1 special character & 1 number";
		}
		else if(!hasChar) {
			result = "Password should contain atleast 1 special character";
		}
		else if(!hasNumber) {
			result = "Password should include atleast 1 number";
		}
		
		else {
			return result;
		}
		}
		return result;
	}


	private String validateLastName(String lastname) {
		String result="";
		String expression = "^[a-zA-Z]*";
		if (lastname.equals(""))
			result= "Last Name cannot be empty";
		else if(!stringSize(lastname,3,30))
			result = "Last Name should be between 3 and 30 characters long";
		else if(!(lastname.matches(expression)))
			result = "Last Name should only have alphabets";
		return result;
	}


	private String validateFirstName(String firstname) {
		String result="";
		String expression = "^[a-zA-Z]*";
		if (firstname.equals(""))
			result= "First Name cannot be empty";
		else if(!stringSize(firstname,3,30))
			result = "First Name should be between 3 and 30 characters long";
		else if(!(firstname.matches(expression)))
			result = "First Name should only have alphabets";
		return result;
	}


	private String validateUsername(String username) {
		boolean hasChar = false;
		boolean hasNumber = false;
		String result="";
		char[] array=username.toCharArray();
		if (username.equals(""))
			result= "Your Username cannot be empty";
		else{
			if (LoginDAO.checkUniqueUsername(username))
				result="Username already exists! Please try a different Username";
			else if(!stringSize(username,2,17))
				result = "Username should be 3 and 16 characters long";
			
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Username should not contain special characters or numeric characters";
		}
		}
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>min && string.length()<max; 
	}
	private boolean isTextAnInteger (String string) {
        boolean result = false;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result; 
	}
	private boolean isCharAnInteger (String string) {
        boolean result = false;
        char[] array=string.toCharArray();
        for(int i=0;i<array.length;i++) {
			if(Character.isDigit(array[i]))
					result = true;
		}
		return result;
	}

	private boolean hasChar(String input) {
		boolean result = false;
		char[] array=input.toCharArray();
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					result = true;
				}
				
			}
			
		}
		return result;
	}
}
