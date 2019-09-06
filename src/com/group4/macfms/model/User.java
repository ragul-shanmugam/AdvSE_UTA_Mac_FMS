package com.group4.macfms.model;

public class User {

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String id;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String role;

	public void setUser(String username, String password, String firstname, String lastname, String id, String phone, 
			String email, String address, String city, String state, String zipcode, String role) {
		setUsername(username);
		
		setPassword(password);
		setFirstname(firstname);
		System.out.println("Printing first inside USER...."+getFirstname());
		setLastname(lastname);
		System.out.println("Printing last inside USER...."+getLastname());
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
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
