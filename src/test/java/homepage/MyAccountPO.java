package homepage;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.Initialisation;

public class MyAccountPO extends LoadableComponent<MyAccountPO>
{
	@FindBy (xpath = "//a[text()='Sign Out']")
	WebElement signOut;
	
	@FindBy (xpath = "//div[text()='Account created successfully. You are now registered.']")
	WebElement successfullyRegisteredMessage;
	
	private WebDriver driver;
	private String expectedTitle = "Avactis Demo Store";
	private WebDriverWait wait;
	
	public MyAccountPO()
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		isLoaded();
		wait = new WebDriverWait(driver, 10);
	}
	
	public void clickSignOut()
	{
		wait.until(ExpectedConditions.visibilityOf(signOut)).click();
	}
	
	public String getLinkTitle()
	{
		
	try {
		return wait.until(ExpectedConditions.visibilityOf(signOut)).getText();
		}
	catch (NoSuchElementException e) 
		{
			Assert.fail("Element not found");
		}
	catch (Exception e) 
		{
			Assert.fail();
		}
	return "fail";
	}
	
	public String getSuccessMessage()
	{
	
		return (wait.until(ExpectedConditions.visibilityOf(successfullyRegisteredMessage))).getText();
	}

	@Override
	protected void isLoaded() throws Error 
	{
		// TODO Auto-generated method stub
		driver.get("http://localhost/Avactis/");
	}

	@Override
	protected void load() 
	{
		// TODO Auto-generated method stub
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
}
