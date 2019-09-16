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

}