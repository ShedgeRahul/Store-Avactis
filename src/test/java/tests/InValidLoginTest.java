package tests;

import org.testng.annotations.Test;

import homepage.HomePO;
import homepage.LoginPO;

import utility.ConfigProperties;
import utility.CustomListener;
import utility.Initialisation;
import utility.Log;
import utility.XlsDP;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners (CustomListener.class)
public class InValidLoginTest 
{
	HomePO homePage;
	LoginPO loginPage;

	
  @Test(dataProvider = "xlsdata")
  public void validLogin(String user,String pwd) 
  {
	Log.info("Test method Started");
	homePage.clickSignIn();
	loginPage.setEmail(user);
	loginPage.setPassword(pwd);
	loginPage.clickSignIn();
	
	String expectedMessage = "Account and password could not be identified. Try again or create an account.";
	
	Assert.assertEquals(loginPage.getInvalidLogInMessage(), expectedMessage);
	Log.info("Actual and Expected Invalid Messages matched");
  }
  
  @DataProvider (name = "xlsdata")
  public Object[][] getTestData()
  {
	  Log.info("In Dataprovider method"); 
	ConfigProperties.loadProperties();
  	String[][] testData = XlsDP.getDataFromXLSUsingJXL(ConfigProperties.getProperty("File_Path"), ConfigProperties.getProperty("Shee3_Name"), ConfigProperties.getProperty("Table_Name"));
  	return(testData);
  }

 
  @Parameters({"Browser","TestEnv"})
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod(@Optional ("CH") String browser, @Optional ("http://localhost/Avactis/") String testEnv) 
  {
	  Log.info("Before method completed");
	  Initialisation.setBrowser(browser, testEnv);
	  
	  homePage = new HomePO();
	  loginPage = new LoginPO();
	  DOMConfigurator.configure("log4j-config.xml");
	  Log.info("Before method completed");
  }

  @AfterMethod
  public void afterMethod() 
  {
	  Initialisation.quitBrowser();
	  Log.info("After method completed");
  }
}
