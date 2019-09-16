package com.group4.macfms.model;

public class MarErrorMsgs {
	private String commonerrorMsg = "";
	private String descriptionError = "";
	
	public String getCommonerrorMsg() {
		return commonerrorMsg;
	}
	public void setCommonerrorMsg() {
		if(!descriptionError.equals(""))
			commonerrorMsg = "Please correct the following:";
		else
			commonerrorMsg = "";
	}
	public String getDescriptionError() {
		return descriptionError;
	}
	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}

}
