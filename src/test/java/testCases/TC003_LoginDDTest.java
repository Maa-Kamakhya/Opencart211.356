package testCases;




import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginpage;
import pageObjects.myAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDTest extends baseClass 
{
	@Test(dataProvider ="LoginData" , dataProviderClass = DataProviders.class , groups = "DataDriven") 
	public void verify_loginDDT(String email, String pwd , String Expresult ) 
	{
		
		logger.info("************TC003_LoginDDTest is started *************");
		homePage hp = new homePage(driver);
		hp.myAccountclick();
		hp.loginclick();
		
		loginpage lp = new loginpage(driver);
		lp.setEmail(email);
		lp.setpassword(pwd);
		lp.login1click();
		 
		myAccountPage macc = new myAccountPage(driver);
		boolean targetpage = macc.ismyaccountpageexist();
		
		
		
		/* Validation for Data Drivern Testing 
		valid credentiols --->successful login--->TC passed -->logout
		valid credentiols --->unsuccessful login--->TC faild 
		
		Invalid credentiols --->successful login--->TC faild -->logout
		Invalid credentiols --->unsuccessful login--->TC passed
		*/
		try {
		if(Expresult.equals("Valid"))
				{
			if(targetpage==true) 
			{
				macc.clicklogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				
				Assert.assertTrue(false);
			}
				}
		
		if(Expresult.equals("Invalid"))
		{
			if(targetpage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
			
		}
			
		
		
		logger.info("**************TC003_LoginDDTest finished ************");
		
		
		
	}
	
	
	
	

}
