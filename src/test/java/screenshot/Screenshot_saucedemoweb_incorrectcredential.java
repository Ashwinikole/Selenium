package screenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Screenshot_saucedemoweb_incorrectcredential {

	WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		driver = new ChromeDriver();
	}
	
	@Test
	public void homepage() throws IOException
	{
		driver.get("https://www.saucedemo.com/");
		
		// take screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
				// Save screenshot
				//Files.copy(screenshot, new File("Screenshots/homepage.png"));
				
		       String Target = ".\\Screenshots\\LoginHomepage.png";
				
				Files.copy(screenshot.toPath(), new File(Target).toPath());
				
				System.out.println("Screenshot captures successfully");
			}
	
	@Test
	public void inccorectcredential() throws IOException
	{
		//pass incorrect credentials
		
		driver.findElement(By.id("user-name")).sendKeys("abcd");
		driver.findElement(By.id("password")).sendKeys("dsf");
		
		driver.findElement(By.id("login-button")).click();
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// Save screenshot
				
        String Target = ".\\Screenshots\\incorrectcredentials.png";
		
		Files.copy(screenshot.toPath(), new File(Target).toPath());
		
		System.out.println("Screenshot captures successfully");
		
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	}
	

