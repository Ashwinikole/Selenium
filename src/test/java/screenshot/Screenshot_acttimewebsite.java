package screenshot;

import java.io.File;
import java.io.IOException;
//import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Screenshot_acttimewebsite {

	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void captureScreenshotTest() throws IOException {
		driver.get("https://online.actitime.com/speedwaytech1/login.do");
		
		// take screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// Save screenshot
		//Files.copy(screenshot, new File("Screenshots/homepage.png"));
		
        String Target = ".\\Screenshots\\Homepage.png";
		
		Files.copy(screenshot.toPath(), new File(Target).toPath());
		
		System.out.println("Screenshot captures successfully");
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

