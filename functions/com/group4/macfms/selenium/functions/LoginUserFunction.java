package com.group4.macfms.selenium.functions;


import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginUserFunction {

	  public static WebDriver driver;
	  public static Properties prop;	
	   
	   public String loginError(WebDriver driver, String sUserName, String sPassword ) {

			// Provide user name.
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);;

			 // Provide Password.
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);

			 // Click on Login button.
			driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();
			
			String emptyError = driver.findElement(By.xpath(prop.getProperty("Txt_Login_LoginError"))).getAttribute("value");
			String incorrectError = driver.findElement(By.xpath(prop.getProperty("Txt_Login_PasswordError"))).getAttribute("value");
			if (emptyError != null && !emptyError.equals("")) 
				return emptyError;
			else
				return incorrectError;
		  }	
	   
	   public void loginSuccess(WebDriver driver, String sUserName, String sPassword ) {
			
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