package com.group4.macfms.model;



public class Facility {
	private String facility;
	private String facilityName;
	private String maxInterval;
	private String duration;
	private String type;
	private String availability;
	
	public void setFacility (String facility, String facilityName, String maxInterval, String duration,
			String type, String availability)
	{
		setFacility(facility);
		setFacilityName(facilityName);
		setMaxInterval(maxInterval);
		setDuration(duration);
		setType(type);
		setAvailability(availability);		
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getMaxInterval() {
		return maxInterval;
	}

	public void setMaxInterval(String maxInterval) {
		this.maxInterval = maxInterval;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
	public String validatefacilityName(Facility facility, UserErrorMsgs errorMessage) {
		
		if(facility.getFacilityName().equals("") || facility.getFacilityName().length() == 0 || facility.getFacilityName().isEmpty())
		{
			System.out.println("inside validate user else ");
			errorMessage.setFacilityName("Facility Name  cannot be empty");
			return "Facility Name  cannot be empty";
		}
		
		else
		{
			
			   String stringToTest = facility.getFacilityName();
			   boolean hasNonAlphaNumeric = stringToTest.matches("^[a-zA-Z0-9_]*$");
			   if(hasNonAlphaNumeric) {
					errorMessage.setFacilityName("Facility name should be alphanumeric");
				return "Facility name should be alphanumeric"; }

		
	
		}
		return null;

	}

}
