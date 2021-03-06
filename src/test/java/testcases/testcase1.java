package testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class testcase1 {
	
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setReport() {
		htmlReporter =new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("CI Automation Report");
		htmlReporter.config().setReportName("ExtentReport 4");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Tester", "Naresh Prajapati");
		extent.setSystemInfo("Company Name", "BusyQA");
		extent.setSystemInfo("Build Number", "2.1");
		}
	@AfterTest
	public void endReport() {
		extent.flush();
		
	}
	
	/*
	 * Pass, Fail, Skip
	 */
	
	 
	 @Test
	 public void verifyLogin() {
		 test = extent.createTest("Verify Login Test");
		 System.out.println("Executing verify login Test");
		 
	 }
	 @Test
	 public void verifyAddPayee() {
		 test = extent.createTest("Verify Add Payee Test");
		 Assert.assertTrue(false);
		 
	 }
	 @Test
	 public void isSkip() {
		 test = extent.createTest("Skip Test");
		 throw new SkipException("Skipping the Test Case");
		 
	 }
	 
	 @AfterMethod
	 public void tearDown(ITestResult result) {
		 if(result.getStatus()==ITestResult.FAILURE) {
			 String methodName= result.getMethod().getMethodName();
			 String logText = "<b>" + "	TEST CASE: - 		" + methodName.toUpperCase()+"   FAILED" + "</b>";
			 Markup m=MarkupHelper.createLabel(logText, ExtentColor.RED);
			 test.fail(m);
		 }else if(result.getStatus()==ITestResult.SKIP) {
			 String methodName= result.getMethod().getMethodName();
			 String logText = "<b>" + "	TEST CASE: - 		" + methodName.toUpperCase()+"   SKIPPED" + "</b>";
			 Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			 test.skip(m);
		 }else if(result.getStatus()==ITestResult.SUCCESS) {
			 
			String methodName= result.getMethod().getMethodName();
			String logText = "<b>" + "	TEST CASE: - 		" + methodName.toUpperCase()+"   PASSED" + "</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);
		 }
	 }
	
}
