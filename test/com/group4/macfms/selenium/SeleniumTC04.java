package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;
import com.group4.macfms.selenium.functions.LoginUserFunction;
import com.group4.macfms.selenium.functions.RegisterUserFunction;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC04 extends SeleniumTestBase {

	public static String sAppURL, sSharedUIMapPath;
	// for distinguishing row > 1
	int r = 0;
	static LoginUserFunction loginUser;
	static RegisterUserFunction registerUser;
	static SeleniumTestBase seleniumTestBase;

	// Add this for Jenkins to get rid of the
	// org.openqa.selenium.remote.ProtocolHandshake createSession
	// which causes it to report the test as failed due to "error"
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
		 seleniumTestBase = new SeleniumTestBase();
		 loginUser = new LoginUserFunction();
		 registerUser = new RegisterUserFunction();
		 setDriver();
	}
	
	@AfterClass
	public static void tearDownAfterTest() throws Exception {
		driver.close();
	}

	@Test
	@FileParameters("./excel/allUserErrorTestRegisterData.csv")
	public void a_registerErrorValidationsTest(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String commonErrorMsg, String usernameErr, String passwordErr,
			String confirmPwdErr, String firstnameErr, String lastnameErr, String utaidErr, String phoneErr,
			String emailErr, String addressErr, String cityErr, String zipErr) throws Exception {
		driver.findElement(By.xpath(prop.getProperty("Btn_Home_Register"))).click();
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		UserErrorMsgs expectedErrorMsg = new UserErrorMsgs();
		expectedErrorMsg.setUsernameError(usernameErr);
		expectedErrorMsg.setPasswordError(passwordErr);
		expectedErrorMsg.setConfirmPasswordError(confirmPwdErr);
		expectedErrorMsg.setFnameError(firstnameErr);
		expectedErrorMsg.setLnameError(lastnameErr);
		expectedErrorMsg.setIdError(utaidErr);
		expectedErrorMsg.setPhoneError(phoneErr);
		expectedErrorMsg.setEmailError(emailErr);
		expectedErrorMsg.setAddressError(addressErr);
		expectedErrorMsg.setCityError(cityErr);
		expectedErrorMsg.setZipCodeError(zipErr);

		UserErrorMsgs actualErrorMsg = registerUser.registerUserError(user);
		validateRegisterErrMsgs(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/repairerUserSuccessTestRegisterData.csv")
	public void b_registerSuccess(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String expectedErrorMsg) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		// driver.findElement(By.xpath(prop.getProperty("Btn_Home_Register"))).click();
		registerUser.registerUserSuccess(user);
	}

	private void validateRegisterErrMsgs(UserErrorMsgs expectedErrorMsg, UserErrorMsgs actualErrorMsg) {
		assertEquals(expectedErrorMsg.getUsernameError(), actualErrorMsg.getUsernameError());
		assertEquals(expectedErrorMsg.getPasswordError(), actualErrorMsg.getPasswordError());
		assertEquals(expectedErrorMsg.getConfirmPasswordError(), actualErrorMsg.getConfirmPasswordError());
		assertEquals(expectedErrorMsg.getFnameError(), actualErrorMsg.getFnameError());
		assertEquals(expectedErrorMsg.getLnameError(), actualErrorMsg.getLnameError());
		assertEquals(expectedErrorMsg.getIdError(), actualErrorMsg.getIdError());
		assertEquals(expectedErrorMsg.getPhoneError(), actualErrorMsg.getPhoneError());
		assertEquals(expectedErrorMsg.getEmailError(), actualErrorMsg.getEmailError());
		assertEquals(expectedErrorMsg.getAddressError(), actualErrorMsg.getAddressError());
		assertEquals(expectedErrorMsg.getCityError(), actualErrorMsg.getCityError());
		assertEquals(expectedErrorMsg.getZipCodeError(), actualErrorMsg.getZipCodeError());
	}

	@Test
	@FileParameters("./excel/allUserErrorTestLoginData.csv")
	public void c_loginErrorValidationsTest(int testCaseNo, String userName, String password, String expectedErrorMsg)
			throws Exception {
		String actualErrorMsg = "";

		actualErrorMsg = loginUser.loginError(userName, password);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/repairerUserSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		loginUser.loginSuccess(username, password);
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ViewRepairs"))).click();
		e_viewAssignedMarDetailsTest();
	}

	public void e_viewAssignedMarDetailsTest() throws InterruptedException {
		List<WebElement> rows= driver.findElement(By.xpath("html/body/div[1]/form/div")).findElements(By.tagName("tr"));
		int i=0;
		  if((rows.size())>0){
			  i++;
			  r++;
		  }
		if(i == 0)
		{
			driver.findElement(By.xpath(prop.getProperty("Btn_List_Homepage"))).click();
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='radioMar1']")).click();
		    driver.findElement(By.xpath(prop.getProperty("Btn_List_ViewMarDetails"))).click();			
		    driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_Homepage"))).click();
		    f_logout();
		}
	}

	/*@Test
	@FileParameters("./excel/reportMarSuccessTestData.csv")
	public void f_createMarSuccess(int testCaseNo, String facilityName, String description, String expectedErrorMsg) {
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Report_FacilityName"))))
				.selectByVisibleText(facilityName);

		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).sendKeys(description);

		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Report"))).click();

		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Success"))).click();

		f_logout();

	}*/

	/*private String validateMarError(String facilityName, String description) {

		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Report_FacilityName"))))
				.selectByVisibleText(facilityName);
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).sendKeys(description);

		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Report"))).click();

		String emptyError = driver.findElement(By.xpath(prop.getProperty("Txt_Report_DescriptionError")))
				.getAttribute("value");
		return emptyError;
	}*/

	private void f_logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
	}

}
