package homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.Initialisation;
import utility.Log;

public class HomePO extends LoadableComponent<HomePO>

{
	@FindBy (xpath = "//a[text()='Sign In']")
	private WebElement signin;
	
	@FindBy (xpath = "//a[text()='My Account']")
	private WebElement myaccount;
	
	@FindBy (xpath = "//a[text()='My cart']")
	private WebElement mycart;
	
	@FindBy (xpath = "//a[text()='Checkout']")
	private WebElement checkout;
	
	private WebDriver driver;
	private String expectedTitle = "Avactis Demo Store";
	private WebDriverWait wait;
	public HomePO()
	{	
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,10);
		isLoaded();
	}
	
	public void clickSignIn()
	{
		wait.until(ExpectedConditions.visibilityOf(signin)).click();
		//signin.click();
		Log.debug("Clicked on Homepage Signin ");
	}
	
	public void clickMyAccount()
	{
		wait.until(ExpectedConditions.visibilityOf(myaccount)).click();
		//myaccount.click();
	}

	public void clickMyCart()
	{
		wait.until(ExpectedConditions.visibilityOf(mycart)).click();
		//mycart.click();
	}

	public void clickCheckout()
	{
		wait.until(ExpectedConditions.visibilityOf(checkout)).click();
		//checkout.click();
	}

	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public void close()
	{
		driver.quit();
	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		driver.get("http://localhost/Avactis/");
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		Assert.assertEquals(driver.getTitle(), expectedTitle) ;
		
	}

}



















