package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.baseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repname;
	
	public void onStart(ITestContext testcontext) {
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss");
		Date dt = new Date();
		String timestamp = df.format(dt);
		*/
		String timestamp = new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());// we can write above code in single line 
		
		repname="Test-Report-" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repname);
		
		sparkReporter.config().setDocumentTitle("Opencart Application Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule","Customers");
		extent.setSystemInfo("User Name ", System.getProperty("user.name"));
		extent.setSystemInfo("Envirnoment","QA");
		
		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system", os);
		
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		}
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To display groups in report
		test.log(Status.PASS,result.getName()+"Got successfuly Executed ");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To display groups in report
		
		test.log(Status.FAIL,result.getName()+"Got Faild ");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgpath= new baseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To display groups in report
		
		test.log(Status.SKIP,result.getName()+"Got Skipped ");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testcontext)  {
		extent.flush();
		
		//To Open report automatically we need this code 
		String pathOfExtentReport = System.getProperty("user.dir")+	"\\reports\\"+repname;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			}
		catch(IOException e ) {
			e.printStackTrace();
		}
		
		/*
		try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repname);
		
		//Create the email msg
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jadhavneelam967@gmail.com","Chikku@280260"));
		email.setSSLOnConnect(true);
		email.setFrom("jadhavneelam967@gmail.com");//sender
		email.setSubject("Test Result");
		email.setMsg("Please find attached Report ..");
		email.addTo("neelamjadhav115@gmail.com"); // receiver
		email.attach(url,"extent report","Please check report...");
		email.send();//send email
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		*/
	}
	

}
