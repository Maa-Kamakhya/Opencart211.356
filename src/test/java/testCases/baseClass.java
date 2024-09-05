package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class baseClass 
{ 
	public static WebDriver driver;
	public Logger logger;  // adding this for log4j
	public Properties p;
	
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"browser","os"})
	public void setup(String br , String os ) throws IOException
	{
		//Loding congig.properties file approch1 
		
		//FileInputStream fis = new FileInputStream("./src//test//resources//config.properties");
		
		//Loding congig.properties file approch2
		FileReader file = new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass()); //log4j2
		  
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Unable to make connection");
				return;
			}
			
			
			//browser
			
			switch(br.toLowerCase()) {
			
			case "chrome" : capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox": capabilities.setBrowserName("firefox");break;
			default: System.out.println("No matching browser");return;
			
			}
			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase()) 
			{
			case "chrome":driver=new ChromeDriver();
			break;
			case "edge":driver=new EdgeDriver();
			break;
			case "firefox":driver=new FirefoxDriver();
			break;
			default:System.out.println("Browser is invalid");
			return;
			}
			
		}
		
		//System.out.println("driver="+driver); 
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Reading URL from properties file 
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
		
		
		
		
	}
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}

	// if there is a case that password and confirm password two feilds are there the use below code
	
	public String randomString()
	{
		String random_string=RandomStringUtils.randomAlphabetic(6);
		return random_string;
	}
	
	//Used for contact no 
	public String randomnumeric()
	{
		String random_number=RandomStringUtils.randomNumeric(10);
		return random_number;
	}
	
	//for password 
	
	public String alphanumeric()
	{
		String random_string=RandomStringUtils.randomAlphabetic(6);
		String random_number=RandomStringUtils.randomNumeric(10);
		return(random_string+random_number);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		
		String timeStmp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshort\\" + tname +"_" + timeStmp + ".png"; 
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
		
		
		
	}
	
	
	
}
