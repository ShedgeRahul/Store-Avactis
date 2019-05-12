package homepage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.Initialisation;
import utility.WebTable;

public class AdminOrdersPO extends LoadableComponent<AdminOrdersPO> 
{
	@FindBy (xpath = "//table[@id='datatable_orders']")
	WebElement orderTable;
	
	@FindBy (xpath = "//span[text()=' Admin Name ']")
	WebElement adminName;
	
	private WebDriver driver;
	private WebDriverWait wait;
	private String expectedTitle = "Avactis Demo Store";
	
	public AdminOrdersPO()
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		isLoaded();
		wait = new WebDriverWait(driver, 10);
	}
	
	public String getAmount(String orderID, String colName)
	{
		//WebTable table = new WebTable(wait.until(ExpectedConditions.visibilityOf(orderTable)));
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",orderTable);
		
		WebTable table = new WebTable(wait.until(ExpectedConditions.visibilityOf(orderTable)));
		return table.getData(orderID, colName);
	}
	

	public void clickAdminName()
	{
		wait.until(ExpectedConditions.visibilityOf(adminName)).click();
	}
	
	
	
	@Override
	protected void isLoaded() throws Error 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() 
	{
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}

}
