package com.group4.macfms.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import com.group4.macfms.model.Facility;
import com.group4.macfms.model.UserErrorMsgs;

@RunWith(JUnitParamsRunner.class)
public class FacilityTest {

	

	@Test
	@FileParameters("./testdata/addFacilityTestData.csv")
	public void validateAddFacilityTest(int testno,String facilityName,String facilityNameErr) {
		Facility facility = new Facility();
		FacilityErrorMsgs errorMessage=new FacilityErrorMsgs();
		//FacilityErrorMsgs addparkErr = new ParkingAreaErrorMsgs();
		facility.setFacilityName(facilityName);	
		//facility.validateFacilityName(facility.getFacilityName(), errorMessage);
		facility.validatefacilityName(facility, errorMessage);
		//assertEquals(facilityNameErr,facility.validateFacilityName(facility.getFacilityName(), errorMessage));
		assertEquals(facilityNameErr, errorMessage.getFacilityNameError());
	}
	
//	@Test
//	@FileParameters("./testdata/setFacilityTestData.csv")
//	public void validateSetFacilityTest(int testno,String facility, String facilityName, String maxInterval, String duration,
//			String type, String availability) {
//		Facility facilityTest = new Facility();
//		//FacilityErrorMsgs errorMessage=new FacilityErrorMsgs();
//		//FacilityErrorMsgs addparkErr = new ParkingAreaErrorMsgs();
//		facilityTest.setFacility(facility, facilityName, maxInterval, duration, type, availability);		
//		//assertEquals(facilityNameErr,facilityTest.validateFacilityName(facility.getFacilityName(), errorMessage));
//	}

}
