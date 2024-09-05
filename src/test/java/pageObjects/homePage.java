package pageObjects;
/*
1)create cunstructor and initioalise driver pagefactory 
2)locators using findby
3)action methods 

*/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;




public class homePage extends basePage 
    {
	
	//public WebDriver driver;
	
	public homePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement myAccountLink;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement loginlink;
	@FindBy(xpath="(//a[normalize-space()='Register'])[1]") WebElement registerLink;
	
	public void myAccountclick() 
	{
		
		myAccountLink.click();
	}
	
	public void registerclick() 
	{
		
		registerLink.click();
		
	}
	public void loginclick() 
	{
		
		loginlink.click();
		
	}
	



}
