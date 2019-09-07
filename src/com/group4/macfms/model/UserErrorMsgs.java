package com.group4.macfms.model;

public class UserErrorMsgs {
	
	private String commonerrorMsg = "";
	private String usernameError = "";
	private String passwordError = "";
	private String fnameError = "";
	private String lnameError = "";
	private String idError = "";
	private String emailError = "";
	private String addressError = "";
	private String cityError = "";
	private String stateError = "";
	private String zipCodeError = "";
	private String roleError = "";
	
	public String getCommonerrorMsg() {
		return commonerrorMsg;
	}
	public void setCommonerrorMsg(String commonerrorMsg) {
		if(!usernameError.equals("") || !passwordError.equals("") || !fnameError.equals("") || !lnameError.equals("") 
				|| !idError.equals("") || !emailError.equals("") || !addressError.equals("") || !cityError.equals("") 
				|| !stateError.equals("") || !zipCodeError.equals("") || !roleError.equals(""))
			commonerrorMsg = "Please correct the following:";
		else
			commonerrorMsg = "";
	}
	public String getUsernameError() {
		return usernameError;
	}
	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	public String getFnameError() {
		return fnameError;
	}
	public void setFnameError(String fnameError) {
		this.fnameError = fnameError;
	}
	public String getLnameError() {
		return lnameError;
	}
	public void setLnameError(String lnameError) {
		this.lnameError = lnameError;
	}
	public String getIdError() {
		return idError;
	}
	public void setIdError(String idError) {
		this.idError = idError;
	}
	public String getEmailError() {
		return emailError;
	}
	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}
	public String getAddressError() {
		return addressError;
	}
	public void setAddressError(String addressError) {
		this.addressError = addressError;
	}
	public String getCityError() {
		return cityError;
	}
	public void setCityError(String cityError) {
		this.cityError = cityError;
	}
	public String getStateError() {
		return stateError;
	}
	public void setStateError(String stateError) {
		this.stateError = stateError;
	}
	public String getZipCodeError() {
		return zipCodeError;
	}
	public void setZipCodeError(String zipCodeError) {
		this.zipCodeError = zipCodeError;
	}
	public String getRoleError() {
		return roleError;
	}
	public void setRoleError(String roleError) {
		this.roleError = roleError;
	}
	
}
