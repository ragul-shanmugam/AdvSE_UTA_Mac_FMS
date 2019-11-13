package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;
import com.group4.macfms.selenium.functions.LoginUserFunction;
import com.group4.macfms.selenium.functions.RegisterUserFunction;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC03 extends SeleniumTestBase {

	public static String sAppURL, sSharedUIMapPath, username, password;
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
	@FileParameters("./excel/adminUserSuccessTestRegisterData.csv")
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
	@FileParameters("./excel/adminUserSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		loginUser.loginSuccess(username, password);
		driver.findElement(By.xpath("html/body/div[1]/a[3]")).click();
	}

	@Test
	@FileParameters("./excel/searchUserAdminErrorTestData.csv")
	public void e_searchUserErrorValidationsTest(int testCaseNo, String lastName, String userRole, String expectedErrorMsg) {
		String actualErrorMsg = "";
		actualErrorMsg = validateUserError(lastName, userRole);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}
	
	@Test
	@FileParameters("./excel/searchUserAdminSuccessTestData.csv")
	public void f_searchUserSuccessTest(int testCaseNo, String lastName, String userRole) {
		driver.findElement(By.xpath(".//*[@id='lastname']")).clear();
		driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys(lastName);
		
		new Select(driver.findElement(By.xpath(".//*[@id='role']"))).selectByVisibleText(userRole);
		
		driver.findElement(By.xpath("html/body/div[1]/div/form/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/h1[2]/a[1]")).click();	
		driver.findElement(By.xpath("html/body/div[1]/a[2]")).click();	
	}
	
	@Test
	@FileParameters("./excel/searchUsernameAdminErrorTestData.csv")
	public void g_searchUsernameErrorValidationsTest(int testCaseNo, String username, String expectedErrorMsg) {	
		String actualErrorMsg = "";
		actualErrorMsg = validateUsernameError(username);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}
	

	@Test
	@FileParameters("./excel/searchUsernameAdminSuccessTestData.csv")
	public void h_searchUsernameSuccessTest(int testCaseNo, String username, String userRole) {
		driver.findElement(By.xpath(".//*[@id='username']")).clear();
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(username);
		
		driver.findElement(By.xpath("html/body/div[1]/div/form/input")).click();
		
		new Select(driver.findElement(By.xpath(".//*[@id='role']"))).selectByVisibleText(userRole);
		driver.findElement(By.xpath(".//*[@id='update']")).click();
			
		driver.findElement(By.xpath(".//*[@id='userhome']")).click();	
		logout();		
	}

	private String validateUserError(String lastName, String userRole) {

		driver.findElement(By.xpath(".//*[@id='lastname']")).clear();
		driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys(lastName);
		
		new Select(driver.findElement(By.xpath(".//*[@id='role']"))).selectByVisibleText(userRole);

		driver.findElement(By.xpath("html/body/div[1]/div/form/input")).click();

		String userError = driver.findElement(By.xpath(".//*[@id='common_errorMessage']"))
				.getAttribute("value");
		return userError;
	}
	
	private String validateUsernameError(String username) {
		driver.findElement(By.xpath(".//*[@id='username']")).clear();
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("html/body/div[1]/div/form/input")).click();

		String usernameError = driver.findElement(By.xpath(".//*[@id='username_errorMessage']"))
				.getAttribute("value");
		return usernameError;
	}

	private void logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
	}

}
