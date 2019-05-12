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

public class LoginPO extends LoadableComponent<LoginPO>
{
	@FindBy (xpath = "//button[text()='Register']")
	WebElement register;

	@FindBy (id = "account_sign_in_form_email_id")
	WebElement email;
	
	@FindBy (name = "passwd")
	WebElement password;
	
	@FindBy (xpath = "//input[@class='btn btn-primary input_submit']")
	WebElement signIn;
	
	@FindBy (xpath = "//div[text()='Account and password could not be identified. Try again or create an account.']")
	WebElement invalidLoginMessage;
	
	@FindBy (xpath = "//input[@name='remember_me']")
	WebElement rememberMe;
	
	private WebDriver driver;
	private String expectedTitle = "Avactis Demo Store";
	private WebDriverWait wait;
	
	public LoginPO()
	{
		driver = Initialisation.getDriver();
		PageFactory.initElements(driver, this);
		isLoaded();
		wait = new WebDriverWait(driver, 10);
	Log.info("Driver passed to LoginPO");
	}
	
	public void clickRegister()
	{
	wait.until(ExpectedConditions.visibilityOf(register)).click();
		//register.click();
	}

	public void setEmail(String mail)
	{
		wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(mail);
		//email.sendKeys(mail);
		Log.info("Email set ");
	}
	
	public void setPassword(String pwd)
	{
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pwd);
		//password.sendKeys(pwd);
		Log.info("Password set  ");
	}
	
	public void clickSignIn()
	{
		wait.until(ExpectedConditions.visibilityOf(signIn)).click();
		//signIn.click();
		Log.warn("Clicked on signIn button");
	}
	
	public String getInvalidLogInMessage()
	{
		String text = wait.until(ExpectedConditions.visibilityOf(invalidLoginMessage)).getText();
		return text;
	}
	
	public void checkRememberMe()
	{
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOf(rememberMe));
		if(checkbox.isSelected())
		{
			checkbox.click();
		}
	}
	
	public void close()
	{
		driver.quit();
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
