package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basePage
{
	   public WebDriver driver;
	 
	public basePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
		
	}

}
