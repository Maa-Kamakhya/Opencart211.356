package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginpage;
import pageObjects.myAccountPage;

public class TC002_LoginTest extends baseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void verify_login()
	{
		
		logger.info("*********Starting TC002_LoginTest *************");
		try {
		//Home page
		homePage hp = new homePage(driver);
		hp.myAccountclick();
		hp.loginclick();
		
		//login page 
		loginpage lp = new loginpage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("passwrd"));
		lp.login1click();
		
		//myaccountpage
		
		myAccountPage macc=new myAccountPage(driver);
		boolean targetpage = macc.ismyaccountpageexist();
		Assert.assertTrue(targetpage);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*Finished TC002_LoginTest******** ");
		
		
		
	}
	
	
	
	
	

}
