package pageObjects;

import java.time.Duration;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;



public class registrationaccountPage extends basePage
{
	// WebDriver driver;
	public registrationaccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id=\"input-firstname\"]") WebElement firstNameTxt;
	@FindBy(xpath="//input[@id=\"input-lastname\"]") WebElement lasttNameTxt;
	@FindBy(xpath="//input[@id=\"input-email\"]") WebElement emailTxt;
	
	
	@FindBy(xpath="//input[@id=\"input-telephone\"]") WebElement phonenumbertxt;
	@FindBy(xpath="//input[@id=\"input-password\"]") WebElement passwordTxt;
	@FindBy(xpath="//input[@id=\"input-confirm\"]") WebElement confirmpasswordtxt;
	
	@FindBy(xpath="//input[@value=\"0\"]") WebElement subscribenewsteller;
	//@FindBy(xpath="//input[@id=\"input-newsletter\"]") WebElement privacypolicytoggle;
	@FindBy(xpath="//input[@value=\"Continue\"]") WebElement continuebtn;
	@FindBy(xpath="//input[@name=\"agree\"]")WebElement agreebtn;
	@FindBy(xpath="//div[@id=\"content\"]//h1") WebElement Confirmmsg;
	
	
	public void setfirstname(String fname)
	{
		firstNameTxt.sendKeys(fname);
	}
	public void setlastname(String lname)
	{
		lasttNameTxt.sendKeys(lname);
	}
	public void setemail(String email)
	{
		emailTxt.sendKeys(email);
	}
	public void settelephone(String phno)
	{
		phonenumbertxt.sendKeys(phno);
	}
	public void setpassword(String pass)
	{
		passwordTxt.sendKeys(pass);
	}
	public void setconfirmpass(String confirmpass)
	{
		confirmpasswordtxt.sendKeys(confirmpass);
	}
	public void subscribeclick()
	{
		subscribenewsteller.click();
	}
	
	public void agreebutton()
	{
		agreebtn.click();
	}
	
	
	public void btncontinue() 
	{
		//Approch1
		continuebtn.click();
		
		//Approch2
		//Actions a = new Actions(driver);
		//a.moveToElement(continuebtn).click().perform();
		//Thread.sleep(3000);
		//Approch3
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.elementToBeClickable(continuebtn)).click();
		
		//approch4
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click()", continuebtn);
		
		//Aproch5
		//continuebtn.submit();
		
		//Approch6
		//continuebtn.sendKeys(Keys.RETURN);
	}
	
	
	public String msgconfirmation()
	{
		try {
		return(Confirmmsg.getText());
		}
		catch (Exception e) {
			return (e.getMessage());
		}
	}
	
	
	
}


