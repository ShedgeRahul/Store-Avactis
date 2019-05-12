package homepage;


import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.Initialisation;
import utility.Log;

public class AddToCartPO extends LoadableComponent<AddToCartPO>
{
	private String category=null;
	private String subcategory=null;
	private String product=null;
	private String price=null;
	

	@FindBy (xpath = "//i[@class='fa fa-search search-btn']")	
	WebElement search;
	
	@FindBy (xpath = "//input[@name='search_pattern']")
	WebElement searchtext;
	
	@FindBy (xpath = "//button[text()='Search']")
	WebElement searchbutton;
	
	@FindBy (xpath = "//a[@class='top-cart-info-value']")
	WebElement minicart;
	
	@FindBy (xpath = "//a[@class='btn btn-primary']")
	WebElement checkout;
	
	@FindBy (xpath = "//li//a[text()='Checkout']")
	WebElement checkoutOnTop;
	
	private WebDriver driver;
	private String expectedTitle = "http://localhost/Avactis/";
	private WebDriverWait wait;
	
	
	public AddToCartPO()
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		isLoaded();
		wait = new WebDriverWait(driver, 10);
		System.out.println("PO constructor");
		Log.info("driver instance pass to AddToCartPO ");
	}
	
	public void setProductDetails(String category, String subcategory, String product, String price)
	{
		System.out.println("inside setProductDetails");
		this.category = category;
		this.subcategory = subcategory;
		this.product = product;
		this.price = price;
		Log.info("Category, Subcatedory and Product values set ");
	}

	public void selectProducts() throws InterruptedException
	{
		Log.info("inside selectProducts");
		System.out.println("category-"+category+" subcategory-"+subcategory+" product-"+product+" price-"+price);
		if(!category.equalsIgnoreCase("NA") && !subcategory.equalsIgnoreCase("NA") && !product.equalsIgnoreCase("NA"))
		{
		
			try
			{
				Actions builder = new Actions(driver);
				
				WebElement cat = driver.findElement(By.xpath("//a[contains(text(),'"+category+"')]"));
				builder.moveToElement(wait.until(ExpectedConditions.visibilityOf(cat))).build().perform();;
				
				WebElement subcat = driver.findElement(By.xpath("//a[contains(text(),'"+subcategory+"')]"));
				builder.moveToElement(wait.until(ExpectedConditions.visibilityOf(subcat))).click().build().perform();
			
				Thread.sleep(5000);
				Log.info("Subcategory is selected");
		
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/h3[contains(text(),'"+product+"')]")))).click();
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@value='Add To Cart']")))).click();
				Log.info("Product is selected");
			}
			catch (Exception e) 
			{
				
				Log.info("Driver not able to handle Actions class hence directly seraching product instead of hover over ");
				
				wait.until(ExpectedConditions.visibilityOf(search)).click();
				wait.until(ExpectedConditions.visibilityOf(searchtext)).sendKeys(product);
				wait.until(ExpectedConditions.visibilityOf(searchbutton)).click();
				
				Log.debug("Search succesfully completed");
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/h3[contains(text(),'"+product+"')]")))).click();
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@value='Add To Cart']")))).click();
				Log.info("Product is selected");
				
				String cost = minicart.getText();
				System.out.println("value of selected product in minicart is - "+cost);
				Log.info("Dispalying cost from minicart"+minicart.getText());
			}
						
		}				
	
		else if (!category.equalsIgnoreCase("NA") && subcategory.equalsIgnoreCase("NA") && !product.equalsIgnoreCase("NA")) 
		{
			Log.info("Subcategory is not present hence secound if block is getting exicuted");
			WebElement cat = driver.findElement(By.xpath("//a[contains(text(),'"+category+"')]"));
			wait.until(ExpectedConditions.visibilityOf(cat)).click();
			
			try
			{
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
				Log.info("Alert handleded");
			}
			catch (NoAlertPresentException e) 
			{
				Log.error("Expected window alert is not displayed");
			}
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/h3[contains(text(),'"+product+"')]")))).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@value='Add To Cart']")))).click();
			Log.info("Product is selected");
		}
		else if (category.equalsIgnoreCase("NA") && subcategory.equalsIgnoreCase("NA") && !product.equalsIgnoreCase("NA"))
		{
			Log.info("Category and Subcategory are not present hence third if block is getting exicuted");
			
			wait.until(ExpectedConditions.visibilityOf(search)).click();
			wait.until(ExpectedConditions.visibilityOf(searchtext)).sendKeys(product);
			wait.until(ExpectedConditions.visibilityOf(searchbutton)).click();
			
			Log.debug("Search succesfully completed");
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/h3[contains(text(),'"+product+"')]")))).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@value='Add To Cart']")))).click();
			Log.info("Product is selected");
			
			String cost = minicart.getText();
			System.out.println("value of selected product in minicart is - "+cost);
			Log.info("Dispalying cost from minicart"+minicart.getText());
		}

	}
	
		public int minicartIteamsSize()
		{
			//minicart.findElements(By.tagName("li"));
			
			List<WebElement> allListIteams = driver.findElements(By.xpath("//div[@class='top-cart-content-wrapper  minicart']//ul//li"));
			int iteamsCount = allListIteams.size()+1;
			System.out.println("Iteams in minicart - "+iteamsCount);
			return iteamsCount;
		}
		
		public void minicartIteams()
		{
			//minicart.findElements(By.tagName("li"));
			//getAttribute("value") getAttribute("value")
			
			List<WebElement> allListIteams = driver.findElements(By.xpath("//div[@class='top-cart-content-wrapper  minicart']//ul//li"));
				for(WebElement oneIteam: allListIteams)
				{
					Log.info("inside loop ");
					System.out.println(oneIteam.getText()+" - "+oneIteam.getAttribute("href"));
				}
		}
		
		public void checkout()
		{
			try
			{
			Actions builder = new Actions(driver);
			
			builder.moveToElement(wait.until(ExpectedConditions.visibilityOf(minicart))).build().perform();
			builder.moveToElement(wait.until(ExpectedConditions.visibilityOf(checkout))).click().build().perform();
			}
			catch (Exception e) 
			{
			
				wait.until(ExpectedConditions.visibilityOf(checkoutOnTop)).click();
			}
			
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
