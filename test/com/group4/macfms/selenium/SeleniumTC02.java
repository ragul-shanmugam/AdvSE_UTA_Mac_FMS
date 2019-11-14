package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;
import com.group4.macfms.selenium.functions.LoginUserFunction;
import com.group4.macfms.selenium.functions.RegisterUserFunction;
import com.group4.macfms.selenium.functions.SnapshotFunction;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC02 extends SeleniumTestBase {

	public static String sAppURL, sSharedUIMapPath, username, password;
	// for distinguishing row > 1
	int r = 0;
	static LoginUserFunction loginUser;
	static RegisterUserFunction registerUser;
	static SnapshotFunction snapShot;
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
		snapShot = new SnapshotFunction();
		setDriver();
		String screenShotName = "TC02_Homepage";
		snapShot.takeScreenshot(screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Home_Register"))).click();
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
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		UserErrorMsgs actualErrorMsg = registerUser.registerUserError(user, screenShotName);
		validateRegisterErrMsgs(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/managerUserSuccessTestRegisterData.csv")
	public void b_registerSuccess(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String expectedErrorMsg) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		registerUser.registerUserSuccess(user, screenShotName);
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
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		actualErrorMsg = loginUser.loginError(userName, password, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/managerUserSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_FMShome_SearchUnassignedMar"))).click();
		viewUnAssignedMarDetailsTest();
	}

	public void viewUnAssignedMarDetailsTest() throws InterruptedException {
		List<WebElement> rows = driver.findElement(By.xpath("html/body/div[1]/form/div"))
				.findElements(By.tagName("tr"));
		int i = 0;
		if ((rows.size()) > 1) {
			i++;
			r++;
		}
		if (i == 0) {
			driver.findElement(By.xpath(prop.getProperty("Btn_UnassignedMars_HomePage"))).click();
		} else {
			driver.findElement(By.xpath(prop.getProperty("Link_UnassignedMars_View"))).click();
			driver.findElement(By.xpath(prop.getProperty("Btn_MARDetails_EditDetails"))).click();
		}
	}

	@Test
	@FileParameters("./excel/assignMarErrorRuleCheckData.csv")
	public void e_assignMarErrorRuleCheck(int testCaseNo, String repName, String urgency, String assignedToError,
			String estimatedTime, String marStatusErr) {
		String actualError = "";
		driver.findElement(By.xpath("//input[@id='edit']")).click();
		driver.findElement(By.xpath("//input[@id='assignedto']")).sendKeys(repName);
		driver.findElement(By.xpath("//input[@id='updatemar']")).click();
		if (repName.equals("")) {
			String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
			actualError = driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_AssignedToError")))
					.getAttribute("value");
			assertEquals(assignedToError, actualError);
			snapShot.takeScreenshot(screenShotName);	
		} 
		else {
			String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
			actualError = driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_MARStatus_Errormsg")))
					.getAttribute("value");
			assertEquals(marStatusErr, actualError);
			snapShot.takeScreenshot(screenShotName);
		}
	}

	@Test
	@FileParameters("./excel/assignMarErrorRuleCheckDayData.csv")
	public void f_assignMarSuccessRuleCheckDay(int testCaseNo, String urgency, String assignedTo, String estimatedTime,
			String marStatusErr) {
		driver.findElement(By.xpath("//input[@id='edit']")).click();
		driver.findElement(By.xpath("//input[@id='assignedto']")).clear();
		driver.findElement(By.xpath("//input[@id='assignedto']")).sendKeys("repairerFMS");
		new Select(driver.findElement(By.xpath("//select[@id='time']"))).selectByVisibleText("30 Minutes");
		driver.findElement(By.xpath("//input[@id='updatemar']")).click();
		String actualError = "";
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		actualError = driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_MARStatus_Errormsg")))
				.getAttribute("value");
		assertEquals(marStatusErr, actualError);
		snapShot.takeScreenshot(screenShotName);
	}
	
	@Test
	@FileParameters("./excel/assignMarErrorRuleCheckWeekData.csv")
	public void g_assignMarSuccessRuleCheckWeek(int testCaseNo, String repName, String urgency, String assignedTo,
			String estimatedTime, String marStatusErr) {
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		driver.findElement(By.xpath("//input[@id='edit']")).click();
		driver.findElement(By.xpath("//input[@id='assignedto']")).clear();
		driver.findElement(By.xpath("//input[@id='assignedto']")).sendKeys(repName);
		new Select(driver.findElement(By.xpath("//select[@id='time']"))).selectByVisibleText(estimatedTime);
		driver.findElement(By.xpath("//input[@id='updatemar']")).click();
		String actualError = "";
		actualError = driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_MARStatus_Errormsg")))
				.getAttribute("value");
		assertEquals(marStatusErr, actualError);
		snapShot.takeScreenshot(screenShotName);
	}

	@Test
	@FileParameters("./excel/assignMarSuccessRuleCheckTestData.csv")
	public void h_assignMarSuccessRuleCheckTest(int testCaseNo, String urgency, String assignedTo, String estimatedTime,
			String expectedSuccessMsg) throws InterruptedException {
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName() + "_" + testCaseNo;
		driver.findElement(By.xpath(prop.getProperty("Btn_MARDetails_EditDetails"))).click();
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_MARDetails_Urgency"))))
				.selectByVisibleText(urgency);
		driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_AssignedTo"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_AssignedTo"))).sendKeys(assignedTo);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_MARDetails_EstimatedTime"))))
				.selectByVisibleText(estimatedTime);
		driver.findElement(By.xpath(prop.getProperty("Btn_MARDetails_UpdateMARDetails"))).click();
		snapShot.takeScreenshot(screenShotName);
		String actualSuccessMsg = driver.findElement(By.xpath(prop.getProperty("Txt_MARDetails_MARStatus_Errormsg")))
				.getAttribute("value");
		assertEquals(expectedSuccessMsg, actualSuccessMsg);
		logout();
	}

	private void logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_MARDetails_Logout"))).click();
		String screenShotName = "TC02_" + new Throwable().getStackTrace()[0].getMethodName();
		snapShot.takeScreenshot(screenShotName);
	}

}
