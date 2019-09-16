package com.group4.macfms.model;

public class Mar {
	private String marNumber;
	private String facilityType;
	private String reservationId;
	private String reportedBy;
	private String urgency;
	private String description;
	private String dateCreated;
	private String assignedTo;
	private String assignedDate;
	private String estimatedTime;
	private String marStatus;
	
	public void setMar(String marNumber, String facilityType, String reservationId, String reportedBy,
			String urgency, String description, String dateCreated, String assignedTo, String assignedDate,
			String estimatedTime, String marStatus) {
		setMarNumber(marNumber);
		setFacilityType(facilityType);
		setReservationId(reservationId);
		setReportedBy(reportedBy);
		setUrgency(urgency);
		setDescription(description);
		setDateCreated(dateCreated);
		setAssignedTo(assignedTo);
		setAssignedDate(assignedDate);
		setEstimatedTime(estimatedTime);
		setMarStatus(marStatus);		
	}
	
	public String getMarNumber() {
		return marNumber;
	}
	public void setMarNumber(String marNumber) {
		this.marNumber = marNumber;
	}
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getMarStatus() {
		return marStatus;
	}
	public void setMarStatus(String marStatus) {
		this.marStatus = marStatus;
	}

	public void validateDescription(Mar mar, MarErrorMsgs errorMsg) {
		errorMsg.setDescriptionError(validateDescription(mar.getDescription()));
		errorMsg.setCommonerrorMsg();
		
	}

	private String validateDescription(String description) {
		String result = "";
		if(description == null || description.isEmpty()) {
			result = "Please provide us the description of the problem";
		}
		return result;
	}
}