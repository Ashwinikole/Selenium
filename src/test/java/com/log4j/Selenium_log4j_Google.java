package com.log4j;

import org.apache.logging.log4j.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Selenium_log4j_Google {

	WebDriver driver;
	
	// Create a logger instance
	private static final Logger Logger = LogManager.getLogger("Selenium_log4j_Google.class");
	
	@BeforeClass
	public void setup()
	{
	driver = new ChromeDriver();
	Logger.info("Chrome browser launched successfully");
	}
	
	@Test
	public void Browser_Launch()
	{
		Logger.info("This is Google home page");
		driver.get("https://www.google.com/");
		
		Logger.info("Maximize the page");
		driver.manage().window().maximize();
		
		}
	
	@Test
	public void Googletitle()
	{
		String Actualtitle = driver.getTitle();
		Logger.info("This is the Actual title of the home page :" +Actualtitle);
		
		String Expectedtitle = "Google";
		
		if(Actualtitle.contains("Google"))
		{
			Logger.info("This is the correct title and The test case passed");
		}
		else {
			Logger.error("This is the incorrect title and Expected title is "+Expectedtitle+" But got :" +Actualtitle);
		}
		
	}
	@AfterClass
	public void teardown() {
	driver.close();
	Logger.info("Browser closed");
			}
	
}
