package com.driverScript;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.commonFunctionLibrary.FunctionLibrary;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.ExcelFileUtil;

public class AppTest {
  @Test
  public void f() throws Exception {
	  ExcelFileUtil excel=new ExcelFileUtil();
			WebDriver driver = null;
			
			for(int i=1;i<=excel.rowCount("MasterTestCases");i++){
				
				String ModuleStatus="";
				
				if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y")){
					
					String testCaseName=excel.getData("MasterTestCases", i, 1);
					
					ExtentReports report=new ExtentReports(System.getProperty("user.dir")+"\\Reports\\"+testCaseName+"_"+FunctionLibrary.generateDate()+".html");
					 ExtentTest test=report.startTest(testCaseName);
					
					for(int j=1;j<=excel.rowCount(testCaseName);j++){
						
						String description=excel.getData(testCaseName, j, 0);
						String keyword=excel.getData(testCaseName, j, 1);
						String locator_Type=excel.getData(testCaseName, j, 2);
						String locator_Value=excel.getData(testCaseName, j, 3);
						String test_Data=excel.getData(testCaseName, j, 4);
						
						try {
							
							if(keyword.equalsIgnoreCase("startBrowser")) {
								 driver=FunctionLibrary.startBrowser();
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("openApplication")){
								 FunctionLibrary.openApplication(driver);
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("waitForElement")){
								FunctionLibrary.waitForElement(driver, locator_Type, locator_Value, test_Data);		
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("typeAction")){
								FunctionLibrary.typeAction(driver,locator_Type, locator_Value, test_Data);		
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("clickAction")){
								FunctionLibrary.clickAction(driver,locator_Type, locator_Value);	
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("selectAction")){
								FunctionLibrary.selectAction(driver,locator_Type, locator_Value, test_Data);
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("selectFromCalendar")){
								FunctionLibrary.selectFromCalendar(driver,locator_Type, locator_Value, test_Data);
								 test.log(LogStatus.INFO,description);
							}else if(keyword.equalsIgnoreCase("hitEnter")){
								FunctionLibrary.hitEnter(driver,locator_Type, locator_Value);
								 test.log(LogStatus.INFO,description);
							}
							else if(keyword.equalsIgnoreCase("closeBrowser")){
								FunctionLibrary.closeBrowser(driver);
								 test.log(LogStatus.INFO,description);
							}	
							
							ModuleStatus="PASS";
							excel.setData(testCaseName, j, 5, "PASS");
							test.log(LogStatus.PASS,description);
							
					   }catch(Exception e) {
						    ModuleStatus="FAIL";
						    excel.setData(testCaseName, j, 5, "FAIL");
						    test.log(LogStatus.FAIL,description);
						    
						    File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						    
						    Files.copy(srcFile,new File(System.getProperty("user.dir")+"\\Screenshots\\"+description+"_"+FunctionLibrary.generateDate()+".png"));
						    
						    e.printStackTrace();
						    
						    driver.close();
						    break;
					   }
						
					}
					
					if(ModuleStatus.equalsIgnoreCase("PASS")) {
						 excel.setData("MasterTestCases", i, 3, "PASS");	
						
					}else if(ModuleStatus.equalsIgnoreCase("FAIL")) {
						excel.setData("MasterTestCases", i, 3, "FAIL");
					}
					
					report.endTest(test);
					report.flush();
					
					
				}else {
					excel.setData("MasterTestCases", i, 3, "Not Executed");
				}	
				
			}	
  }
}
