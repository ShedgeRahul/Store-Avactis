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

public class RegistrationFormPO extends LoadableComponent<RegistrationFormPO>
{
	@FindBy (name = "customer_info[Customer][Email]")
	WebElement email;
	
	@FindBy (name = "customer_info[Customer][Password]")
	WebElement password;
	
	@FindBy (name = "customer_info[Customer][RePassword]")
	WebElement rePassword;
	
	@FindBy (name = "customer_info[Customer][FirstName]")
	WebElement firstName;
	
	@FindBy (name = "customer_info[Customer][LastName]")
	WebElement lastName;
	
	@FindBy (name = "customer_info[Customer][Country]")
	WebElement country;
	
	@FindBy (name = "customer_info[Customer][State]")
	WebElement state;
	
	@FindBy (name = "customer_info[Customer][ZipCode]")
	WebElement zip;
	
	@FindBy (name = "customer_info[Customer][City]")
	WebElement city;
	
	@FindBy (name = "customer_info[Customer][Streetline1]")
	WebElement address1;
	
	@FindBy (name = "customer_info[Customer][Streetline2]")	
	WebElement address2;
	
	@FindBy (name = "customer_info[Customer][Phone]")
	WebElement phone;
	
	@FindBy (xpath = "//input[@type='submit']")
	WebElement register;
	

	@FindBy (xpath = "//div[text()='The password you entered is incorrect. Please enter the correct password.']")
	WebElement incorrectPasswordMessage;
	
	@FindBy (xpath = "//div[@class='note note-danger']['contains(text(),Invalid data in field E-mail.)'][2]")
	WebElement incorrectEmailMessage;
	
	@FindBy (xpath = "//div[@class='note note-danger']['contains(text(),Invalid data in field E-mail.)'][3]")
	WebElement incorrectFirstNameMessage;
	
	@FindBy (xpath = "//div[@class='note note-danger']['contains(text(),Invalid data in field E-mail.)'][4]")
	WebElement incorrectLastNameMessage;
	
	private WebDriver driver;
	private String expectedTitle = "Avactis Demo Store";
	private WebDriverWait wait;
	
	public RegistrationFormPO()
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,10);
		isLoaded();
	}
	
	
	public void setEmail(int num)
	{
		wait.until(ExpectedConditions.visibilityOf(email)).sendKeys("username"+num+"@test.com");
		//email.sendKeys("username"+num+"@test.com");
	}
	
	public void setPassword(String pwd)
	{
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pwd);
		//password.sendKeys(pwd);
	}
	
	public void setRePassword(String repwd)
	{
		wait.until(ExpectedConditions.visibilityOf(rePassword)).sendKeys(repwd);
		//rePassword.sendKeys(repwd);
	}
	
	public void setFirstName(String fname)
	{
		wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fname);
		//firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lname);
		//lastName.sendKeys(lname);
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
		//zip.sendKeys(postalcode);
	}

	
	public void setCity(String cityname)
	{
		wait.until(ExpectedConditions.visibilityOf(city)).sendKeys(cityname);
		//city.sendKeys(cityname);
	}

	public void setAddress1(String firstAddress)
	{
		wait.until(ExpectedConditions.visibilityOf(address1)).sendKeys(firstAddress);
		//address1.sendKeys(firstAddress);
	}
	
	public void setAddress2(String secoundAddress)
	{
		wait.until(ExpectedConditions.visibilityOf(address2)).sendKeys(secoundAddress);
		//address2.sendKeys(secoundAddress);
	}
	
	public void setPhone(String phonenumber)
	{
		wait.until(ExpectedConditions.visibilityOf(phone)).sendKeys(phonenumber);
		//phone.sendKeys(phonenumber);
	}
	
	public void clickRegister()
	{
		wait.until(ExpectedConditions.visibilityOf(register)).click();
		//register.click();
	}
	
	public String getIncorrectPasswordMessage()
	{
		return (wait.until(ExpectedConditions.visibilityOf(incorrectPasswordMessage))).getText();
	}

	public String getIncorrectEmailMessage()
	{
		return (wait.until(ExpectedConditions.visibilityOf(incorrectEmailMessage))).getText();
	}
	
	public String getIncorrectFirstNameMessage()
	{
		return (wait.until(ExpectedConditions.visibilityOf(incorrectFirstNameMessage))).getText();
	}
	
	public String getIncorrectLastNameMessage()
	{
		return (wait.until(ExpectedConditions.visibilityOf(incorrectLastNameMessage))).getText();
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
	protected void isLoaded() throws Error 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void load() 
	{
		// TODO Auto-generated method stub
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
}
