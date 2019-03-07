package com.JavaScriptExecutor;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.JavascriptExecutor;

public class JavaScriptExecutorDemo
{
	
	public WebDriver driver=null;
	
	@Test
	public void openURL()
	{
		//System.out.println(System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.logfile","E:\\Selenium\\ChromedriverLog.log");
		//System.setProperty("webdriver.chrome.verboselogging","False");
		
		driver=OpenBrowser("Mozila");
			
		//driver.get("https://www.ebix.com/");
		Navigate(driver,"https://www.ebix.com/");
		driver.manage().window().maximize();
				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		closeDriver(driver);
}
	
	public WebDriver OpenBrowser(String browserName)
	{
		 if(browserName.equalsIgnoreCase("IE"))
		 {
		    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Resources\\IEDriverServer.exe");
			 driver=new InternetExplorerDriver();
		 }
		 else if(browserName.equalsIgnoreCase("Chrome"))
		 {
			 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
			 driver=new ChromeDriver();
		 }	
		 else if(browserName.equalsIgnoreCase("Mozila"))
		 {
			 System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Resources\\geckodriver.exe");
			 driver=new FirefoxDriver();
		 }
		 else if(browserName.equalsIgnoreCase("Edge"))
		 {
			 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
			 driver=new ChromeDriver();
		 }
		
		return driver;
	}
	
	public boolean isLoaded()
	{
		boolean result;
		return true;
	}
	
	public void closeDriver(WebDriver driver)
	{
		this.driver.quit();
		
	}
	
	public WebElement findElement(String locators, String locatorType)
	{
		WebElement element=null;
		if(locatorType.equalsIgnoreCase("id"))
		{
			element=driver.findElement(By.id(locators));
		}
		else if(locatorType.equalsIgnoreCase("name"))
		{
			element=driver.findElement(By.name(locators));
		}
		else if(locatorType.equalsIgnoreCase("name"))
		{
			element=driver.findElement(By.name(locators));
		}
		return element;
	}
	
	public void Navigate(WebDriver driver, String appURL)
	{
		this.driver.get(appURL);
	}
	

}
