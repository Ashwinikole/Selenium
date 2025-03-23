package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Search_URLtest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
		
		driver.manage().window().maximize();
		
		 driver.findElement(By.id("input-email")).sendKeys("ashumg@gmail.com");
	     driver.findElement(By.id("input-password")).sendKeys("Ashwini@0987");
	     driver.findElement(By.xpath("//input[@value='Login']")).click();
	     
	     SoftAssert Assert = new SoftAssert();
	     
	     String ActualURL = driver.getCurrentUrl();
			String ExpectedURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
			Assert.assertEquals(ActualURL, ExpectedURL);
			System.out.println(ActualURL);
			
			driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
			
			String ActualText = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
			String ExpectedText = "Search - phone"; 
			Assert.assertEquals(ActualText, ExpectedText, "Textmaching");
			System.out.println(ActualText);
			
			Thread.sleep(2000);
			
			WebElement checkbox = driver.findElement(By.xpath("//input[@name='sub_category']"));
			
			boolean result = checkbox.isEnabled();
			
			System.out.println("Checkbox is :" +result);
			
			Assert.assertAll();
			
			driver.close();
	}
	

}
