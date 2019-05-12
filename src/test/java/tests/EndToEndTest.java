package tests;

import org.testng.annotations.Test;
import utility.CustomListener;
import homepage.AddToCartPO;
import homepage.AdminLoginPO;
import homepage.AdminOrdersPO;
import homepage.CheckoutPO;
import homepage.HomePO;
import homepage.LoginPO;
import homepage.MyAccountPO;
import homepage.RegistrationFormPO;
import homepage.ThankYouPO;
import utility.ConfigProperties;
import utility.Initialisation;
import utility.Log;
import utility.RandomData;
import utility.SystemDateAndTime;
import utility.XlsDP;


import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners (CustomListener.class)
public class EndToEndTest 
{
	String finalOrderTotalWithDollar;
	String orderID;
	 double actualOrdetTotal;
	double shippingCost;
	 int expectedProductCount = 0;
	double expectedOrdetTotal;
	
	int currentDateTime;
	String userpwd;
	HomePO homePage;
	LoginPO loginPage;
	RegistrationFormPO registrationFormPage;
	MyAccountPO myAccountPage;

	AddToCartPO addToCart;
	CheckoutPO checkoutPage;
	ThankYouPO thankYouPage;
	AdminLoginPO adminLoginPage;
	AdminOrdersPO adminOrdersPage;
  @Parameters({"Browser","TestEnv"})	
  @BeforeClass
  public void beforeClass(@Optional ("CH") String browser, @Optional ("http://localhost/Avactis/") String testEnv)
  {
	  Initialisation.setBrowser(browser, testEnv);
	  ConfigProperties.loadProperties();
	  Log.info("Inside Before Class");
	  addToCart = new AddToCartPO();  
	  checkoutPage = new CheckoutPO();
	  thankYouPage = new ThankYouPO();
	  adminLoginPage = new AdminLoginPO();
	  adminOrdersPage = new AdminOrdersPO();
	  DOMConfigurator.configure("log4j-config.xml");
	  Log.info("Before method completed");
	  homePage = new HomePO();
	  loginPage = new LoginPO();
	  registrationFormPage = new RegistrationFormPO();
	  myAccountPage = new MyAccountPO();
  }
	
  
  @Test(priority = 0)
  public void e2eRegistrationTest()
  {
	  Log.info("Test Started");  
		homePage.clickSignIn();
		loginPage.clickRegister();
		
		currentDateTime = RandomData.randomNumber();
		registrationFormPage.setEmail(currentDateTime); 
		
		userpwd = ConfigProperties.getProperty("REG_PWD");
		registrationFormPage.setPassword(userpwd); 
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
		
		myAccountPage.clickSignOut();
  }
  
  @Test (dependsOnMethods = {"e2eRegistrationTest"}, priority = 1)
  public void e2eLogInTest()
  {
	  	homePage.clickSignIn();
		loginPage.setEmail("username"+currentDateTime+"@test.com");
		loginPage.setPassword(userpwd);
		loginPage.clickSignIn();
		
		Assert.assertEquals(myAccountPage.getLinkTitle() ,"Sign Out");
		Log.info("Test method completed");
  }
  
  
  @Test(dataProvider = "productsDetails", dependsOnMethods = {"e2eLogInTest"}, priority = 2)
  public void guestSelectProductTest(String menu, String subMenu, String product, String cost) throws InterruptedException 
  {	    	
	  
	  expectedProductCount++;
	  System.out.println("category-"+menu+" subcategory-"+subMenu+" product-"+product+" price-"+cost);
	  Log.info("Inside Test Method");
	  addToCart.setProductDetails(menu, subMenu, product, cost);
	  addToCart.selectProducts();
	
	  String newCost = cost.replaceAll("[$,]", "");
	  System.out.println("Cost after removing doller - "+newCost);
	  System.out.println("Value after parsing from String to double -"+Double.parseDouble(newCost));  
	  expectedOrdetTotal = expectedOrdetTotal + Double.parseDouble(newCost);
	  System.out.println("Expected total after removing doller - "+expectedOrdetTotal);
	  Log.info("Test Method Completed");
  
  }
  
  @Test (dependsOnMethods = {"guestSelectProductTest"}, priority = 3)
  public void guestVerifyMinicartCountTest()
  {
	 int selectedIteamsCount = addToCart.minicartIteamsSize();
	 Assert.assertEquals(selectedIteamsCount, expectedProductCount);
	 Log.info("Expected and actual product count matched ");
  }
  
  @Test (dependsOnMethods = {"guestVerifyMinicartCountTest"}, priority = 4)
  public void guestSetShippingBillingAddressTest()
  {
	  addToCart.checkout();

	   
//	  checkoutPage.setEmail(currentDateTime);
	  
	  
	  checkoutPage.clickContinueCheckout();

  Assert.assertEquals(checkoutPage.readLineOfAddress(), ConfigProperties.getProperty("REG_Address1"));
  Log.info("Entered first line of Address is verified ");
  }
  
  
  @Test (dependsOnMethods = {"guestSetShippingBillingAddressTest"}, priority = 5)
  public void  guestReviewAndPlaceOrderTest()
  {
	checkoutPage.selectThirdShippingOption();
	String shippingCostWithDoller = checkoutPage.getShippingCost();
	System.out.println("Shipping cost with doller - "+shippingCostWithDoller);
	 
	String newshippingCost = shippingCostWithDoller.replaceAll("[$,]","");
	System.out.println("After removing Doller from shipping cost - "+newshippingCost);
	shippingCost = Double.parseDouble(newshippingCost);
	System.out.println("Shipping cost after parsing string to double - "+shippingCost);
	
	System.out.println("Expected Order Total before adding shipping cost - "+expectedOrdetTotal);
	expectedOrdetTotal = expectedOrdetTotal + shippingCost;
	System.out.println("Expected Total Cost after adding shipping cost - "+expectedOrdetTotal);
	 
	
	 checkoutPage.clickLastContinueCheckout();
	 
	 String actualOrdetTotalWithDoller = checkoutPage.getOrderTotal();
	 System.out.println("Actual Oreder Total with Doller - "+actualOrdetTotalWithDoller);
	 String newActualOrdetTotal = actualOrdetTotalWithDoller.replaceAll("[$,]","");
	 System.out.println("Actual Order Total Cost after removing doller - "+newActualOrdetTotal);
	 actualOrdetTotal = Double.parseDouble(newActualOrdetTotal);
	 System.out.println("Actual order total after parsing from Sring to Double - "+actualOrdetTotal);
	 
	Assert.assertEquals(actualOrdetTotal,expectedOrdetTotal);
	Log.info("On Place Order page Actual and Expected Order Total is matched");
	
	//checkoutPage.getCellData(3, 1);
	
	checkoutPage.clickPlaceOrder();
	
	orderID = thankYouPage.getOrderID();
	orderID = orderID.replaceAll("[#]", "");
	Log.info("orderID after replaceing # is:"+orderID);
	finalOrderTotalWithDollar = thankYouPage.getFinalOrderTotal();
	Log.info("Final order total with Doller - "+finalOrderTotalWithDollar);
	String finalOrderTotalwithoutDoller = finalOrderTotalWithDollar.replaceAll("[$,]","");
	double finalOrderTotal = Double.parseDouble(finalOrderTotalwithoutDoller);
	
	Assert.assertEquals(finalOrderTotal, expectedOrdetTotal);
	Log.info("On Thank You page Final Order and Expected Order total is matched ");
	
  }
  
  
  @Test(dependsOnMethods = {"guestReviewAndPlaceOrderTest"}, priority = 6)
  public void adminVerification()
  {
	  adminLoginPage.openAdminSite();
	  adminLoginPage.setUserID(ConfigProperties.getProperty("Admin_Email"));
	  adminLoginPage.setPassword(ConfigProperties.getProperty("Admin_Password"));
	  adminLoginPage.clickSignIn();
	  adminLoginPage.clickTitleOrders();
	  //String expectedValueOfCol = "Amount";
	 String adminAmount = adminOrdersPage.getAmount(orderID,"Amount");
	 
	 Assert.assertEquals(adminAmount, finalOrderTotalWithDollar);
	 Log.info("Admin orderID is matched with user orderID ");
  }
  
  @AfterClass
  public void afterClass()
  {	  
	// Log.info("Driver Close");
	//Initialisation.quitBrowser();
  }
  
  @AfterMethod
  public void afterMethod() 
  {
	 // Log.info("Driver Close");
	  //Initialisation.quitBrowser();
  }


  @DataProvider(name = "productsDetails")
  public Object[][] getTestData()
  {
	  Log.info("Inside dataprovider ");
	ConfigProperties.loadProperties();
  	String[][] testData = XlsDP.getDataFromXLSUsingJXL(ConfigProperties.getProperty("File_Path"), ConfigProperties.getProperty("Sheet2_Name"), ConfigProperties.getProperty("Table_Name"));
  	return(testData);
  }

}
