package com.group4.macfms.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class AssignMarTest {

	@Test
	@FileParameters("./testdata/assignMarTest.csv")
	public void validateAssignMarTest(int testno, int StatusCode, String MarStatusErrorMsg) {
		Mar mar = new Mar();
		MarErrorMsgs errorMessage=new MarErrorMsgs();
		mar.setMarStatusCode(StatusCode);	
		mar.validateAssignedToStatus(mar, errorMessage, mar.getMarStatusCode());
		assertEquals(MarStatusErrorMsg, errorMessage.getAssignMarError());
	}
	

}
