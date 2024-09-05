package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends basePage {
	
	public myAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//h2[text()=\"My Account\"]") WebElement msgheading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement linklogout;
	
	public boolean ismyaccountpageexist() 
	{
		try {
		return(msgheading.isDisplayed()); // bases on true or false we do validation in testcase 
		}
		catch(Exception e ) 
		{
			return false;
		}
	}
	
	public void clicklogout() 
	{
		linklogout.click();
	}
	
}
