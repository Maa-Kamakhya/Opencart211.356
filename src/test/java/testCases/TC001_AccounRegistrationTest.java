package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.registrationaccountPage;

public class TC001_AccounRegistrationTest extends baseClass
{
	//public WebDriver driver;
	
	
	@Test(groups = {"Regression","Master"})
	public void Verify_Account_Registrtion()
	{
		System.out.println("driver="+driver);
		
		
		logger.info("*********Strting TC001_AccounRegistrationTest **********");
		try
		{
		homePage hp =new homePage(driver);
		hp.myAccountclick();
		logger.info("**Clicked on My Account link** ");
		
		hp.registerclick();
		logger.info("**Clicked on Register link** ");
		
		registrationaccountPage rp = new registrationaccountPage(driver);
		logger.info("**Providing customer details** ");
		
		rp.setfirstname(randomString());
		rp.setlastname(randomString());
		rp.setemail(randomString()+"@gmail.com");
		rp.settelephone(randomnumeric());
		
		String Password = alphanumeric();
		rp.setpassword(Password);
		rp.setconfirmpass(Password);
		
		rp.subscribeclick();
		rp.agreebutton();
		rp.btncontinue();
		
		logger.info("**validating expected massage** ");
		
		String Confirmsg=rp.msgconfirmation();
		if(Confirmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed...");
			logger.debug("Debug Logs .....");
			Assert.assertTrue(false);
		}
		
		
		
		}
		
		catch(Exception e)
		{
		
		Assert.fail();
		}
		
		logger.info("*********Finidhed  TC001_AccounRegistrationTest **********");
	}
	
	
	
	
}


