package extentReports;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {

	WebDriver driver;
    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    @BeforeTest
    public void startReport() {
        htmlReporter = new ExtentSparkReporter("ExtentReportDemo.html");
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        // Add Environmental details
        reports.setSystemInfo("COMP", "Laptop");
        reports.setSystemInfo("OS", "Windows 11");
        reports.setSystemInfo("User", "Ashwini");
        reports.setSystemInfo("Browser", "Chrome");

        // Configuration
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openBrowser() {
        test = reports.createTest("Launch Browser and open URL");
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");

        driver.findElement(By.id("input-email")).sendKeys("ashumg@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Ashwini@0987");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void checkURL() {
        test = reports.createTest("Check URL after login");
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
        Assert.assertEquals(actualURL, expectedURL);
        throw new SkipException("Skipping this test intentionally.");
    }

    @Test
    public void searchText() {
        test = reports.createTest("Search Functionality Test");
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);

        String actualText = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
        String expectedText = "Search - phone";
        Assert.assertEquals(actualText, expectedText, "Text Matching Failed");
    }

    @Test
    public void checkboxTest() {
        test = reports.createTest("Checkbox Selection Test");
        driver.get("https://tutorialsninja.com/demo/");
        boolean isChecked = driver.findElement(By.xpath("//input[@name='sub_category']")).isSelected();
        Assert.assertTrue(isChecked, "Checkbox is not selected.");
    }

    @AfterMethod
    public void getTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - FAILED", ExtentColor.RED));
            test.fail(result.getThrowable());  // Logs failure reason
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - PASSED", ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - SKIPPED", ExtentColor.ORANGE));
        }
        driver.quit();
    }

    @AfterTest
    public void finish() {
        reports.flush();
    }
}

