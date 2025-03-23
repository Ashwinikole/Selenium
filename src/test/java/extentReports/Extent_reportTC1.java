package extentReports;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_reportTC1 {
  
	WebDriver driver; 
	SoftAssert Assert = new SoftAssert();
	
	
	// We have 3 in built classes in extent report lab so we need to
	// create variable for them.
	
	// 1.
	ExtentSparkReporter htmlRepoter;
	// 2. Extent Report
	ExtentReports reports;
	
	// 3. Extent Test
	ExtentTest test;
	
	// we need to create object and initialise above the varaible
	
	@BeforeTest
	public void startReport()
	{
	htmlRepoter = new ExtentSparkReporter("ExtentReportDemo.html");
	reports = new ExtentReports();
	reports.attachReporter(htmlRepoter);
	
	// Add Environmental details
	reports.setSystemInfo("COMP","Laptop");
	reports.setSystemInfo("OS","windows11");
	reports.setSystemInfo("User","Ashwini");
	reports.setSystemInfo("Browser","Chrome");
	
	// configuration to change look and feel
	htmlRepoter.config().setDocumentTitle("Extend Report Demo");
	htmlRepoter.config().setReportName("Test Report");
	htmlRepoter.config().setTheme(Theme.STANDARD);
	htmlRepoter.config().setTimeStampFormat("EEEE,MMMM DD,YYYY,hh:mm a'('zzz')'");
	
	}
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@Test(priority=1)
	public void openBrowser() throws InterruptedException
	{
		test = reports.createTest("Launch Browser and open URL");
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
			
		driver.manage().window().maximize();
		
		 driver.findElement(By.id("input-email")).sendKeys("ashumg@gmail.com");
	     driver.findElement(By.id("input-password")).sendKeys("Ashwini@0987");
	     driver.findElement(By.xpath("//input[@value='Login']")).click();
	     
	     Thread.sleep(3000);
	}
	
	@Test(priority=2)
	public void checkURL()
	{
		 test = reports.createTest("Check URL after login");
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
		Assert.assertEquals(ActualURL, ExpectedURL);
		throw new SkipException("Skip this test");
	}
	
	@Test(priority=3)
	public void searchText()
	{
		 test = reports.createTest("Search Functionality Test");
		driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		
		String ActualText = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		String ExpectedText = "Search - phone"; 
		Assert.assertEquals(ActualText, ExpectedText, "Textmaching");
		Assert.assertAll();
	}
	
	@Test(priority=4)
	public void checkbox()
	{
		 test = reports.createTest("Checkbox Selection Test");
		 WebElement checkbox = driver.findElement(By.xpath("//input[@name='sub_category']"));
		 boolean result = checkbox.isEnabled();
 		 System.out.println("Checkbox is :" +result);
 		Assert.assertAll();
	}
		
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	
	@AfterMethod
	public void getTestResult(ITestResult result)
	{
	if(result.getStatus()==ITestResult.FAILURE)
	{
	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"Fail",ExtentColor.RED ));
	//test.fail(result.getThrowable()); // if you want to see fail details
	}
	else if(result.getStatus()== ITestResult.SUCCESS)
	{
	test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"Pass",ExtentColor.GREEN ));
	}
	else if(result.getStatus()== ITestResult.SKIP)
	{
	test.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"Skip",ExtentColor.BROWN ));
	}
	}
	
	@AfterTest
	public void finish()
	{
	reports.flush();
	}
	}


