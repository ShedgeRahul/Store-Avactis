package tests;

import org.testng.annotations.Test;

import utility.CustomListener;
import utility.Initialisation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

@Listeners (CustomListener.class)
public class DemoTest 
{
RemoteWebDriver driver;	
	
	@Test
  public void demoTest() throws InterruptedException
  {
		driver.get("https://www.google.com");
		Thread.sleep(2000);
		  Assert.assertEquals(true, true);
  }
	
  @BeforeMethod
  public void beforeMethod()   
  {
	  System.out.println("Before Test");
	  DesiredCapabilities capabilities = new DesiredCapabilities();
	  capabilities.setBrowserName("chrome");
	  
	  try {
		driver = new RemoteWebDriver(new URL("http://192.168.0.104:4444/wd/hub"), capabilities);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 // Initialisation.setBrowser("CH","https://www.google.com");
	  
  }

  @AfterMethod
  public void afterMethod() 
  {
	  
  }

}
