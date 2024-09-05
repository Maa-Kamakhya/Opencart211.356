package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String [][] getdata() throws IOException
	{
		String path=".\\testData\\Opencart_login_data.xlsx"; // taking xlfile from testData
		
		//String path=".//testData//Opencart_login_data.xlsx";
		//String path=System.getProperty("user.dir")+"\\testData\\Opencart_login_data.xlsx";
		
		ExcelUtilites xlutil = new ExcelUtilites(path); //creating an object of xlutlities 
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols]; // created 2D array which can store
		
		for(int i=1;i<=totalrows;i++) 
		{
			for(int j=0;j<totalcols;j++) 
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
				
			}
			
		}
		
		return logindata;
		
	}
	
	//Dataprovider2
	//Dataprovider3
	//Dataprovider4
	

}
