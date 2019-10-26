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
	private int marStatusCode;
	
	

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
	
	public int getMarStatusCode() {
		return marStatusCode;
	}

	public void setMarStatusCode(int marStatusCode) {
		this.marStatusCode = marStatusCode;
	}

	public void validateDescription(Mar mar, MarErrorMsgs errorMsg) {
		errorMsg.setDescriptionError(validateDescription(mar.getDescription()));
		
	}
	
	public  String validateDescription(String description) {
		String result = "";
		if(description.equals("")) {
			result = "Please provide us the description of the problem";
//			return "Please provide us the description of the problem";
		}
		return result;
	}
	
	
	public void validateAssignedTo(Mar mar, MarErrorMsgs errorMsg) {
		errorMsg.setAssignedToError(validateAssignedTo(mar.getAssignedTo()));
		
	}
	
	public  String validateAssignedTo(String assignedTo) {
		String result = "";
		if(assignedTo.equals(""))
			result =  "Please provide the repairer name to assign the MAR";
		return result;
	}
	
	public void validateAssignedToStatus(Mar mar, MarErrorMsgs errorMsg, int status) {
		errorMsg.setAssignMarError(validateAssignedToStatus(mar,status));
		
	}
	
	public  String validateAssignedToStatus(Mar mar,int assignedToStatus) {
		String result = "";
		if(assignedToStatus == 0) {
			mar.setMarStatus("Unassigned");
			result =  "Please enter a valid repairer";
		}
		else if(assignedToStatus == 1) {
			mar.setMarStatus("Assigned");
			result =  "MAR Assigned successfully";
		}
//		if(assignedToStatus == 2)
		else  {
			mar.setMarStatus("Unassigned");
			result = "MAR cannot be assigned as it exceeds its Daily Weekly Count";
		} 
		
		return result;
	}
	
	
	
}
