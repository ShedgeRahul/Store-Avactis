package homepage;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import utility.Initialisation;
import utility.Log;
import utility.WebTable;

public class CheckoutPO extends LoadableComponent<CheckoutPO> 
{
	
	@FindBy (name = "billingInfo[Firstname]")
	WebElement firstName;
	
	@FindBy (name = "billingInfo[Lastname]")
	WebElement lastName;
	
	@FindBy (name = "billingInfo[Email]")
	WebElement email;
	
	@FindBy (name = "billingInfo[Country]")
	WebElement country;
	
	@FindBy (name = "billingInfo[Statemenu]")
	WebElement state;
	
	@FindBy (name = "billingInfo[Postcode]")
	WebElement zip;
	
	@FindBy (name = "billingInfo[City]")
	WebElement city;
	
	@FindBy (name = "billingInfo[Streetline1]")
	WebElement address1;
	
	@FindBy (name = "billingInfo[Streetline2]")	
	WebElement address2;
	
	@FindBy (name = "billingInfo[Phone]")
	WebElement phone;
	
	@FindBy (name = "checkbox_shipping_same_as_billing")
	WebElement checkShippingAddresSame;
	
	@FindBy (xpath = "//input[@class='en btn btn-primary button_continue_checkout']")
	WebElement continueCheckout;
	
	@FindBy (xpath = "//label[@class='control-label col-lg-10'][2]")
	WebElement firstLineOfAddress;
	
	@FindBy (xpath = "//label[text()=' Delivery on Next Business Day']")
	WebElement thirdShippingOption;
	
	@FindBy (xpath = "//label[text()=' Ground Shipping']/following::div[@class='shipping_method_cost'][2]")
	WebElement shippingCost;
	
	@FindBy (xpath = "//div[@class='checkout_rule step_2_active']/following::input[@class='en btn btn-primary button_continue_checkout']")
	WebElement lastContinueCheckout;
	
	@FindBy (xpath = "//em[text()='Order Total:']/following::strong[@class='price']")
	WebElement orderTotal;
	
	@FindBy (xpath = "//table[@class='order_items without_images']")
	WebElement webTable;
	
	@FindBy (xpath = "//input[@class='en btn btn-primary button_place_order input_submit']")
	WebElement placeOrder;
	
	private WebTable table;
	private String expectedTitle = "Avactis Demo Store";
	private WebDriver driver;
	private WebDriverWait wait;	

	
	public CheckoutPO() 
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		isLoaded();
		wait = new WebDriverWait(driver, 10);
		Log.info("driver instance pass to BillingShippingAddressesPO ");
	}
	
		
	public void setFirstName(String fname)
	{
		wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lname);
	}
	
	
	public void setEmail(int num)
	{
		wait.until(ExpectedConditions.visibilityOf(email)).sendKeys("username"+num+"@test.com");
	}

	
	public void selectCountry(String countryname)
	{
		
		Select countrydropdown = new Select(wait.until(ExpectedConditions.visibilityOf(country)));
		countrydropdown.selectByVisibleText(countryname);
	}

	
	public void selectState(String statename)
	{
		Select statedropdown = new Select(wait.until(ExpectedConditions.visibilityOf(state)));
		statedropdown.selectByVisibleText(statename);		
	}
	
	public void setZip(String postalcode)
	{
		wait.until(ExpectedConditions.visibilityOf(zip)).sendKeys(postalcode);
	}

	
	public void setCity(String cityname)
	{
		wait.until(ExpectedConditions.visibilityOf(city)).sendKeys(cityname);
	}

	public void setAddress1(String firstAddress)
	{
		wait.until(ExpectedConditions.visibilityOf(address1)).sendKeys(firstAddress);
	}
	
	public void setAddress2(String secoundAddress)
	{
		wait.until(ExpectedConditions.visibilityOf(address2)).sendKeys(secoundAddress);
	}
	
	public void setPhone(String phonenumber)
	{
		wait.until(ExpectedConditions.visibilityOf(phone)).sendKeys(phonenumber);
	}
	
	public void selectSameShippingAddress()
	{
		boolean selected = wait.until(ExpectedConditions.visibilityOf(checkShippingAddresSame)).isSelected();
		if(selected!=true)
		{
			wait.until(ExpectedConditions.visibilityOf(checkShippingAddresSame)).click();
		}
		else
		{
		 Log.warn("Checkbox of 'Shipping address is the same' alredy selected ");	
		}
	}
	
	
	public void clickContinueCheckout()
	{
		wait.until(ExpectedConditions.visibilityOf(continueCheckout)).click();
	}
	
	public String readLineOfAddress() 
	{
		System.out.println(wait.until(ExpectedConditions.visibilityOf(firstLineOfAddress)).getText());
		return wait.until(ExpectedConditions.visibilityOf(firstLineOfAddress)).getText(); 
	}
	
	public void selectThirdShippingOption()
	{
		
	/*	JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",thirdShippingOption);*/
/*		WebElement element = wait.until(ExpectedConditions.visibilityOf(thirdShippingOption));

		WebElement element = driver.findElement(By.id("id_of_element"));*/
/*		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thirdShippingOption);
		Thread.sleep(5000);*/
		
	/*	Actions builder = new Actions(driver);
		builder.keyDown(Keys.ARROW_UP).build().perform();
		
		builder.keyUp(Keys.ARROW_UP).build().perform();
	*/	
		wait.until(ExpectedConditions.visibilityOf(thirdShippingOption)).click();
	
	}
	
	public String getShippingCost()
	{
		//System.out.println(wait.until(ExpectedConditions.visibilityOf(shippingCost)).getText());
		return wait.until(ExpectedConditions.visibilityOf(shippingCost)).getText();
	}
	
	public void clickLastContinueCheckout()
	{
		wait.until(ExpectedConditions.visibilityOf(lastContinueCheckout)).click();
	}
	
	
	public String getOrderTotal()
	{
		return wait.until(ExpectedConditions.visibilityOf(orderTotal)).getText();
	}
	
	
	public void getCellData(int row, int col)
	{
		table = new WebTable(webTable);
		System.out.println(table.getCellData(row, col));
	}
	
	
	public void clickPlaceOrder()
	{
		wait.until(ExpectedConditions.visibilityOf(placeOrder)).click();
	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	

}
