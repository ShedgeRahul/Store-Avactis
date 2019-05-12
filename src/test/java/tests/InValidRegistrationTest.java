package tests;

import org.testng.annotations.Test;

import homepage.HomePO;
import homepage.LoginPO;
import homepage.MyAccountPO;
import homepage.RegistrationFormPO;
import utility.ConfigProperties;
import utility.CustomListener;
import utility.Initialisation;
import utility.Log;
import utility.RandomData;
import utility.SystemDateAndTime;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.ArrayList;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

@Listeners (CustomListener.class)
public class InValidRegistrationTest 
{
	
	HomePO homePage;
	LoginPO loginPage;
	RegistrationFormPO registrationFormPage;
	MyAccountPO myAccountPage;
	
 @Test 
  public void validRegistration() 
  {
	Log.info("Test Started");  
	homePage.clickSignIn();
	loginPage.clickRegister();
	registrationFormPage.clickRegister();
	
	String expectedPwdMsg = "The password you entered is incorrect. Please enter the correct password.";
	String expectedEmailMsg = "Invalid data in field 'E-mail'.";
	String expectedFNamemsg = "Invalid data in field 'First Name'.";
	String expectedLNameMsg = "Invalid data in field 'Last Name'.";
	
	ArrayList<String> expetctedMessages = new ArrayList<String>();
	expetctedMessages.add(expectedPwdMsg);
	expetctedMessages.add(expectedEmailMsg);
	expetctedMessages.add(expectedFNamemsg);
	expetctedMessages.add(expectedLNameMsg);
		
	ArrayList<String> actualMessages = new ArrayList<String>();
	actualMessages.add(registrationFormPage.getIncorrectPasswordMessage());
	actualMessages.add(registrationFormPage.getIncorrectEmailMessage());
	actualMessages.add(registrationFormPage.getIncorrectFirstNameMessage());
	actualMessages.add(registrationFormPage.getIncorrectLastNameMessage());
	
	
	Assert.assertEquals(actualMessages, expetctedMessages);
	Log.info("Actual and Expected messages ArrayLists are matched");
	
	Initialisation.refreshBrowser();
	
/*	
	registrationFormPage.setEmail(RandomData.randomNumber()); 
	registrationFormPage.setPassword(ConfigProperties.getProperty("REG_PWD")); 
	registrationFormPage.setRePassword(ConfigProperties.getProperty("REG_RePWD")); 
	registrationFormPage.setFirstName(SystemDateAndTime.getSystemDateTime()); 
	registrationFormPage.setLastName(ConfigProperties.getProperty("REG_LName")); 
	registrationFormPage.selectCountry(ConfigProperties.getProperty("REG_County"));
	registrationFormPage.selectState(ConfigProperties.getProperty("REG_State"));
	registrationFormPage.setZip(ConfigProperties.getProperty("REG_Zip"));
	registrationFormPage.setCity(ConfigProperties.getProperty("REG_City"));
	registrationFormPage.setAddress1(ConfigProperties.getProperty("REG_Address1"));
	registrationFormPage.setAddress2(ConfigProperties.getProperty("REG_Address2"));
	registrationFormPage.setPhone(ConfigProperties.getProperty("REG_Phone"));

	registrationFormPage.clickRegister();
	
	String expectedMessage = "Account created successfully. You are now registered.";
	
	Assert.assertEquals(myAccountPage.getSuccessMessage(), expectedMessage);
	Log.info("Expected and Actual Registration Success message matched");
*/
  }
  
  @Parameters({"Browser","TestEnv"})
  @BeforeMethod (alwaysRun=true)
  public void beforeMethod(@Optional ("CH") String browser, @Optional ("http://localhost/Avactis/") String testEnv) 
  {
	  Initialisation.setBrowser(browser, testEnv);
	  ConfigProperties.loadProperties();
	  homePage = new HomePO();
	  loginPage = new LoginPO();
	  registrationFormPage = new RegistrationFormPO();
	  myAccountPage = new MyAccountPO();
	  DOMConfigurator.configure("log4j-config.xml");
	  Log.info("Before Methode Completed");
  }

  @AfterMethod
  public void afterMethod() 
  {
	  Initialisation.quitBrowser();
	  Log.info("After method completed");
  }

}
