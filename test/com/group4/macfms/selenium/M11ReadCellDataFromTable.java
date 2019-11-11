package com.group4.macfms.selenium;

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

import com.group4.macfms.functions.UTAFMSApp_BusinessFunctions;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class M11ReadCellDataFromTable extends UTAFMSApp_BusinessFunctions{
//  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sAppURL, sSharedUIMapPath,username,password;
  //for distinguishing row > 1
  int r=0;
  
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
    prop.load(new FileInputStream("./Login/Login.properties"));
	username = prop.getProperty("username");
	password = prop.getProperty("password");
//	Load Configuration file
    prop.load(new FileInputStream("./configuration/HA_Config.properties"));
	sAppURL = prop.getProperty("sAppURL");
	sSharedUIMapPath = prop.getProperty("SharedUIMap");	  
	
//	Load Shared UI Map
	prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void myParameterizationTest() throws Exception {
	    driver.get(sAppURL);

    HA_BF_Login(driver, username,password);
    driver.findElement(By.xpath(prop.getProperty("Btn_userHome_logout"))).click();
//  Get method name for screenshot
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