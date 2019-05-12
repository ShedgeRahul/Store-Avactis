package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Initialisation 
{
	public static WebDriver driver; 
	
	public static void setBrowser(String browser, String testEnv)
	{
		
		switch(browser)
		{
		
		case "CH" : 
			try {
				System.setProperty("webdriver.chrome.driver","C:\\Workspace\\Avactis\\src\\test\\java\\resources\\chromedriver.exe");
				}
			catch (Exception e) 
				{
					Log.fatal("Please check diver name and driver exe path once ");
				}
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(testEnv);
				Log.debug("Chrome browser open ");
				break;
				
		case "SF" :
			try {
			 	driver = new SafariDriver();
				}
			catch(Exception e)
				{
					Log.fatal("Please check driver settings for Safari driver ");
				}
			 	driver.get(testEnv);
			 	Log.debug("Safari browser open ");
			 	break;
				
		case "IE" :
			try
			{
			System.setProperty("webdriver.ie.driver","C:\\Workspace\\Avactis\\src\\test\\java\\resources\\IEDriverServer.exe");
			}
			catch (Exception e) {
				Log.fatal("Please check diver name and driver exe path once ");
			}
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(testEnv);
			Log.debug("IE browser open ");
			break;
			
		case "FF": 
			try
			{
			System.setProperty("webdriver.gecko.driver","C:\\Workspace\\Avactis\\src\\test\\java\\resources\\geckodriver-32bit.exe");
			}
			catch (Exception e) {
				Log.fatal("Please check diver name and driver exe path once ");
			}
			 
			  driver = new FirefoxDriver();
			  driver.manage().window().maximize();
			  driver.get(testEnv);
			Log.debug("Default browser Firefox is open ");
			break;
		
		}
		
	}	
		
	
	public static WebDriver getDriver()
		{
			return driver;
		}
		
	
	public static void refreshBrowser()
		{
			driver.navigate().refresh();
		}
	
	
	
	public void failed(String testMethodName)
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		
			String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
			 File dest = new File("C:\\Workspace\\Avactis\\ScreenShots\\"+testMethodName+"_"+filename);
			FileUtils.copyFile(srcFile, dest);
			 //	FileUtils.copyFile(srcFile, new File("C:\\Workspace\\Avactis\\ScreenShots\\"+System.currentTimeMillis()+".png"));
		//(srcFile, new File("C:\\Workspace\\Avactis\\ScreenShots\\"+testMethodName+".jpg"));
			//+this.getClass().getName()+"_"
		} catch (IOException e) {

		Log.info("File not found in FileUtils please find stack trace - ");
		e.printStackTrace();
		}
	}
	
	
	public static void quitBrowser()
	{
		driver.quit();
	}
	
}
