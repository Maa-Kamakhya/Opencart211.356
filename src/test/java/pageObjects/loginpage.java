package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage extends basePage {
	
	public loginpage(WebDriver driver){
		super(driver);
		
		}
	
	@FindBy(xpath="//input[@id=\"input-email\"]")WebElement textemailadd;
	@FindBy(xpath="//input[@id=\"input-password\"]")WebElement textpassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btnlogin;
	
	
	public void setEmail(String email) 
	{
		textemailadd.sendKeys(email);
	}
	public void setpassword(String pass)
	{
		textpassword.sendKeys(pass);
	}
	public void login1click()
	{
		btnlogin.click();
	}
	
	
	
}
