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

public class ThankYouPO extends LoadableComponent<ThankYouPO> 
{
	private WebDriver driver;
	private WebDriverWait wait;
	private String expectedTitle = "Avactis Demo Store";
	
	@FindBy (xpath = "//label[text()='Order Id:']/following::div[@class='col-lg-6'][1]")
	WebElement orderID;
	
	@FindBy (xpath = "//em[text()='Order Total:']/following::strong")
	WebElement finalOrderTotal;
	
	public ThankYouPO() 
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		isLoaded();
		wait = new WebDriverWait(driver, 10);
	}
	
	public String getOrderID()
	{
		return wait.until(ExpectedConditions.visibilityOf(orderID)).getText();
	}
	
	public String getFinalOrderTotal()
	{
		return wait.until(ExpectedConditions.visibilityOf(finalOrderTotal)).getText();
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
