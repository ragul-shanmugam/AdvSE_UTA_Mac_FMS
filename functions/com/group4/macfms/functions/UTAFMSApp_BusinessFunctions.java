package com.group4.macfms.functions;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public class UTAFMSApp_BusinessFunctions {

	  public static WebDriver driver;
	  public static Properties prop;

	   public void takeScreenshot(WebDriver driver, String screenshotname) {
			  try
			  {
				  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
				  FileUtils.copyFile(source, new File("./screenShots/" + screenshotname +".png"));
			  }
			  catch(IOException e) {}
			  try {
//				  Change the delay value to 1_000 to insert a 1 second delay after 
//				  each screenshot
				  Thread.sleep(1_000);
			} catch (InterruptedException e) {}
	   }

	   public void HA_BF_Login (WebDriver driver, String sUserName, String sPassword ) {
		
		// Provide user name.
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);;

		 // Provide Password.
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);

		 // Click on Login button.
		driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();

		// We will put the verification of the Welcome message in the JUnit test file instead of here
	  }	
}