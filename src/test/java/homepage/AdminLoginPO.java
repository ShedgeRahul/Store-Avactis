package homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigProperties;
import utility.Initialisation;

public class AdminLoginPO extends LoadableComponent<AdminLoginPO>
{
	private WebDriver driver;
	private WebDriverWait wait;
	private String expectedPageTitle = "Avactis Shopping Cart";
	
	@FindBy (xpath = "//input[@name='AdminEmail'][@class='form-control placeholder-no-fix']")
	WebElement userID;
	
	@FindBy (xpath = "//input[@name='Password'][@class='form-control placeholder-no-fix']")
	WebElement userPassword;
	
	@FindBy (xpath = "//button[text()='Sign In ']")
	WebElement signIn;
	
	@FindBy (xpath = "//a[text()='Orders']")
	WebElement titleOrders;
	
	public AdminLoginPO()
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}

	public void openAdminSite()
	{		
		driver.get(ConfigProperties.getProperty("Admin_URL"));
	}
	
	public void setUserID(String email)
	{
		wait.until(ExpectedConditions.visibilityOf(userID)).sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		wait.until(ExpectedConditions.visibilityOf(userPassword)).sendKeys(pwd);
	}
	
	public void clickSignIn()
	{
		wait.until(ExpectedConditions.visibilityOf(signIn)).click();
	}
	
	public void clickTitleOrders()
	{
		wait.until(ExpectedConditions.visibilityOf(titleOrders)).click();
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() 
	{
		// TODO Auto-generated method stub
		Assert.assertEquals(driver.getTitle(), expectedPageTitle);
	}
	
	
}
