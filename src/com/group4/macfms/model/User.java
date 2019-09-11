package com.group4.macfms.model;

public class User {

	private String userName;
	private String password;
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

	public void setUser(String username, String password, String firstname, String lastname, String id, String phone, 
			String email, String address, String city, String state, String zipcode, String role) {
		setUsername(username);
		setPassword(password);
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
	}
	

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

	public void validateUser() {

	}
	

}
