package com.AutomationFramework.ExtendReports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;


public class BaseTest{
	
	public ExtentHtmlReporter extHtmlReporter;
	public ExtentReports extReport;
	public ExtentTest extTest;
	public WebDriver driver;
	
	@BeforeTest
	public void init()
	{
		extHtmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"\\Reports\\MyReports"+System.currentTimeMillis()+".html"));
			
		extHtmlReporter.config().setDocumentTitle("Automation Report");
		extHtmlReporter.config().setReportName("Functional Testing");
		extHtmlReporter.config().setTheme(Theme.DARK);
		
		extReport=new ExtentReports();
		
		extReport.attachReporter(extHtmlReporter);
		
		extReport.setSystemInfo("QA Name","Manikandan");
		extReport.setSystemInfo("Environment","QA");
		extReport.setSystemInfo("OS","Windows");
		extReport.setSystemInfo("Browser","Chrome");
	}
	
	@Test(priority=1)
	public void navigate()
	{
		extTest=extReport.createTest("Open URL");
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\Chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.google.co.in");
		
	}
	@Test(priority=2)
	 public void verifyTitle()
	 {
		extTest=extReport.createTest("Verify Title");
		//String Title=driver.getTitle();
		 //Assert.assertTrue(true);
		 }
	
	@AfterMethod
	public void tearDown(ITestResult result)throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extTest.log(Status.FAIL,"Test Case Failed: "+result.getName());
			extTest.log(Status.FAIL,"Test Case Failed: "+result.getThrowable());
			String screenShotPath=BaseTest.getScreenshot(driver, result.getName());
			
			System.out.println("screenshot path===="+screenShotPath);
			
			extTest.addScreenCaptureFromPath(screenShotPath);
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extTest.log(Status.SKIP, "Test Case Skipped: "+result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extTest.log(Status.PASS, "Test Case got Passed: "+result.getName());
		}
		
		driver.quit();
		
	}
	
	public static String getScreenshot(WebDriver driver,String result) throws IOException
	{
		//File scrShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String Destination=System.getProperty("user.dir")+"\\Screenshots\\"+System.currentTimeMillis()+".png";
		
		//FileUtils.copyFile(scrShot, new File(Destination));
	    	
		System.out.println(Destination);	
		return Destination;
	}
	
	@AfterTest
	public void endReport()
	{
		extReport.flush();
	}
	

}
