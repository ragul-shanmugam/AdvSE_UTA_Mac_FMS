package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;
import com.group4.macfms.selenium.functions.LoginUserFunction;
import com.group4.macfms.selenium.functions.RegisterUserFunction;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC01 {
	private static WebDriver driver;
	private static Properties prop;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath, username, password;
	// for distinguishing row > 1
	int r = 0;
	RegisterUserFunction registerUser = new RegisterUserFunction();
	LoginUserFunction loginUser = new LoginUserFunction();

	// Add this for Jenkins to get rid of the
	// org.openqa.selenium.remote.ProtocolHandshake createSession
	// which causes it to report the test as failed due to "error"
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
	}

	@Before
	public void setUp() throws Exception {
		// MAGIC CODE GOES HERE
		System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		prop = new Properties();

		// Load Configuration file
		prop.load(new FileInputStream("./config/UTAFMSApp_Config.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("sSharedUIMapPath");

		// Load Shared UI Map
		prop.load(new FileInputStream(sSharedUIMapPath));
		driver.get(sAppURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

/*	@Test
	@FileParameters("./excel/userErrorTestRegisterData.csv")
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

		UserErrorMsgs actualErrorMsg = registerUser.registerUserError(driver, prop, user);
		validateRegisterErrMsgs(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/userSuccessTestRegisterData.csv")
	public void b_registerSuccess(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String expectedErrorMsg) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		// driver.findElement(By.xpath(prop.getProperty("Btn_Home_Register"))).click();
		registerUser.registerUserSuccess(driver, prop, user);
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
	@FileParameters("./excel/userErrorTestLoginData.csv")
	public void c_loginErrorValidationsTest(int testCaseNo, String userName, String password, String expectedErrorMsg)
			throws Exception {
		String actualErrorMsg = "";

		actualErrorMsg = loginUser.loginError(driver, prop, userName, password);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}*/

	@Test
	@FileParameters("./excel/userSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		loginUser.loginSuccess(driver, prop, username, password);
		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Problem"))).click();
	}

	@Test
	@FileParameters("./excel/marErrorTestData.csv")
	public void e_createMarErrorValidationsTest(int testCaseNo, String facilityName, String description,
			String expectedErrorMsg) {
		String actualErrorMsg = "";
		actualErrorMsg = validateMarError(facilityName, description);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/marSuccessTestData.csv")
	public void f_createMarSuccess(int testCaseNo, String facilityName, String description, String expectedErrorMsg) {
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Report_FacilityName"))))
				.selectByVisibleText(facilityName);

		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).sendKeys(description);

		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Report"))).click();

		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Success"))).click();

		f_logout();

	}

	private String validateMarError(String facilityName, String description) {

		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Report_FacilityName"))))
				.selectByVisibleText(facilityName);
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).sendKeys(description);

		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Report"))).click();

		String emptyError = driver.findElement(By.xpath(prop.getProperty("Txt_Report_DescriptionError")))
				.getAttribute("value");
		if (emptyError != null && !emptyError.equals(""))
			return emptyError;
		return emptyError;
	}

	private void f_logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
