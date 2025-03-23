package extentReports;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport2 {
	public class Demo_Extentreport2 {
		//1.
		ExtentSparkReporter htmlReport;

		//2.
		ExtentReports reports;

		//3.
		ExtentTest test;

		@BeforeTest
		public void startReport() {
			htmlReport = new ExtentSparkReporter("ExtentSparkReporter.html");

			reports = new ExtentReports();

			reports.attachReporter(htmlReport);

			//Evn details
			reports.setSystemInfo("COMP", "PC1");
			reports.setSystemInfo("OS", "window11");
			reports.setSystemInfo("User", "deepali");
			reports.setSystemInfo("Browser", "Chrome");

			//configuration to change look and  feel 

			htmlReport.config().setDocumentTitle("Extent Report Demo");
			htmlReport.config().setDocumentTitle("Test Report");
			htmlReport.config().setTheme(Theme.STANDARD);
			htmlReport.config().setTimeStampFormat("EEEE,MMMM,DD,YYYY,hh:mm a'('zzz')' ");

		}
		@Test
		public void creatcustomer() {
			test=reports.createTest("Test customer");
			Assert.assertTrue(true);
		}
		@Test
		public void editcustomer() {
			test=reports.createTest("edit customer");
			Assert.assertTrue(false);
		}
		@Test
		public void deletecustomer() {
			test=reports.createTest("delete customer");
			Assert.assertTrue(true);
		}
		@Test
		public void creatreports() {
			test=reports.createTest("create reports");
			throw new SkipException("skippin these test cases"); 
		}
		@Test
		public void deletereport() {
			test=reports.createTest("Delete report");
			Assert.assertTrue(false);
		}
		
	@AfterMethod
		public void getTestResult(ITestResult result)
		{
			if(result.getStatus()==ITestResult.FAILURE)
			{
				test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"Fail",ExtentColor.RED ));
				//test.fail(result.getThrowable()); // if you want to see fail deatils
			}
			else if(result.getStatus()== ITestResult.SUCCESS)
			{
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"Pass",ExtentColor.GREEN ));
			}
			else if(result.getStatus()== ITestResult.SKIP)
			{
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"Skip",ExtentColor.BROWN ));
			}
		}
		@AfterTest
		public void finish()
		{
			//to generate report
			reports.flush();

		}
	}

}
