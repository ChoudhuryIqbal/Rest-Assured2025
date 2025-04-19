package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import io.reactivex.rxjava3.exceptions.Exceptions;

public class DataProviders {
	@DataProvider (name="Data")
	public String [][] getAllData(){
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		int rownum=xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1",1);
		String apidata[][]=new String[rownum][colcount];
		for (int i=1;i<=rownum;i++) {
			for (int j=1;j<colcount;j++) {
				apidata[i-1][j]=xl.getCellData("Sheet1",i,j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider (name="UserNames")
	public String[] getUserNames(){
		String path=System.getProperty("user.dir")+"//testData//userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		int rownum=xl.getRowCount("Sheet1");
		String apidata[]=new String [rownum];
		for (int i=1;i<=rownum;i++) {
			apidata[i-1]=xl.getCellData("Sheet1",i, 1);
		}
		
		
		return apidata;
		
		
	}

}
