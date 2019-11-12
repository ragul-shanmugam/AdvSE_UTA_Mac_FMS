package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.group4.macfms.selenium.functions.LoginUserFunction;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class LoginUser extends LoginUserFunction {
	//  private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath, username, password;
	// for distinguishing row > 1
	int r = 0;
	LoginUserFunction loginUser = new LoginUserFunction();
	

	//Add this for Jenkins to get rid of the org.openqa.selenium.remote.ProtocolHandshake createSession
	//which causes it to report the test as failed due to "error"
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
	}

	@Before
	public void setUp() throws Exception {
		//	MAGIC CODE GOES HERE 
		System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		prop = new Properties();
		
		//	Load Configuration file
		prop.load(new FileInputStream("./config/UTAFMSApp_Config.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("sSharedUIMapPath");

		//	Load Shared UI Map
		prop.load(new FileInputStream(sSharedUIMapPath));
		driver.get(sAppURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	@FileParameters("./excel/userErrorTestLoginData.csv")
	public void loginErrorValidationsTest(int testCaseNo, String userName, String password, String expectedErrorMsg)
			throws Exception {
		String actualErrorMsg = "";

		actualErrorMsg = loginUser.loginError(driver, prop, userName, password);
		//Thread.sleep(5_000);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/userSuccessTestLoginData.csv")
	public void loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg) throws Exception {
		loginUser.loginSuccess(driver, prop, username, password);
		//Thread.sleep(5_000);
		logout();
	}

	private void logout() {
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
