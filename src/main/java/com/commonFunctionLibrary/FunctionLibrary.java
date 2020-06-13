package com.commonFunctionLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.PropertyFileUtil;

public class FunctionLibrary {

	static WebDriver driver;
	
	public static WebDriver startBrowser() throws Exception {
		
		String browserName=com.utilities.PropertyFileUtil.getValueForKey("Browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			 driver =new ChromeDriver();	
		}else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			 driver =new FirefoxDriver();	
			
		}else if(browserName.equalsIgnoreCase("ie")){	
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			 driver =new InternetExplorerDriver();	
		}
		
		return driver;
	}
	
	public static void openApplication(WebDriver driver) throws Exception {
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String waittitme) {
		
		WebDriverWait mywait=new WebDriverWait(driver,Integer.parseInt(waittitme));
		
		if(locatortype.equalsIgnoreCase("id")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("name")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("classname")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("cssselector")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("linktext")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("partiallinktext")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("xpath")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
	}
	
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata) {
		
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("classname")){
			driver.findElement(By.className(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("cssselector")){
			driver.findElement(By.cssSelector(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("linktext")){
			driver.findElement(By.linkText(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("partiallinktext")){
			driver.findElement(By.partialLinkText(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
		
	}

	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue) {
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("classname")){
			driver.findElement(By.className(locatorvalue)).click();
		}else if(locatortype.equalsIgnoreCase("cssselector")){
			driver.findElement(By.cssSelector(locatorvalue)).click();
		}else if(locatortype.equalsIgnoreCase("linktext")){
			driver.findElement(By.linkText(locatorvalue)).click();
		}else if(locatortype.equalsIgnoreCase("partiallinktext")){
			driver.findElement(By.partialLinkText(locatorvalue)).click();
		}else if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorvalue)).click();
		}
	}
	
	public static void hitEnter(WebDriver driver,String locatortype,String locatorvalue) {

		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("classname")){
			driver.findElement(By.className(locatorvalue)).sendKeys(Keys.ENTER);
		}else if(locatortype.equalsIgnoreCase("cssselector")){
			driver.findElement(By.cssSelector(locatorvalue)).sendKeys(Keys.ENTER);
		}else if(locatortype.equalsIgnoreCase("linktext")){
			driver.findElement(By.linkText(locatorvalue)).sendKeys(Keys.ENTER);
		}else if(locatortype.equalsIgnoreCase("partiallinktext")){
			driver.findElement(By.partialLinkText(locatorvalue)).sendKeys(Keys.ENTER);
		}else if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorvalue)).sendKeys(Keys.ENTER);
		}
		
	}
	
	public static void selectAction(WebDriver driver,String locatortype,String locatorvalue,String optionToSelect) {
		if(locatortype.equalsIgnoreCase("id")){
			
			WebElement drpdown=driver.findElement(By.id(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
			
		}
		else if(locatortype.equalsIgnoreCase("name")){
			WebElement drpdown=driver.findElement(By.name(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
		}
		else if(locatortype.equalsIgnoreCase("classname")){
			WebElement drpdown=driver.findElement(By.className(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
		}else if(locatortype.equalsIgnoreCase("cssselector")){
			WebElement drpdown=driver.findElement(By.cssSelector(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
		}else if(locatortype.equalsIgnoreCase("linktext")){
			WebElement drpdown=driver.findElement(By.linkText(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
		}else if(locatortype.equalsIgnoreCase("partiallinktext")){
			WebElement drpdown=driver.findElement(By.partialLinkText(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
		}else if(locatortype.equalsIgnoreCase("xpath")){
			WebElement drpdown=driver.findElement(By.xpath(locatorvalue));
			Select s=new Select(drpdown);
			
			s.selectByVisibleText(optionToSelect);
		}
	}
	
	
	
	public static void selectFromCalendar(WebDriver driver,String locatortype,String locatorvalue,String dateToSelect) {
		if(locatortype.equalsIgnoreCase("id")){
			
			WebElement calendarElement=driver.findElement(By.id(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
			
		}
		else if(locatortype.equalsIgnoreCase("name")){
			WebElement calendarElement=driver.findElement(By.name(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
		}
		else if(locatortype.equalsIgnoreCase("classname")){
			WebElement calendarElement=driver.findElement(By.className(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
		}else if(locatortype.equalsIgnoreCase("cssselector")){
			WebElement calendarElement=driver.findElement(By.cssSelector(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
		}else if(locatortype.equalsIgnoreCase("linktext")){
			WebElement calendarElement=driver.findElement(By.linkText(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
		}else if(locatortype.equalsIgnoreCase("partiallinktext")){
			WebElement calendarElement=driver.findElement(By.partialLinkText(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
		}else if(locatortype.equalsIgnoreCase("xpath")){
			WebElement calendarElement=driver.findElement(By.xpath(locatorvalue));
			calendarElement.clear();
			
			String[] splitfromDate=dateToSelect.split("/");
			
			String requiredDateToSelect=splitfromDate[2]+"-"+splitfromDate[0]+"-"+splitfromDate[1];
			
			calendarElement.sendKeys(requiredDateToSelect);
		}
	}
	
	
	public static String generateDate(){
		
		Date d=new Date();
			
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_hh_mm_ss");
		
		String requiredDate= sdf.format(d);
		
		return requiredDate;
		
	}

	public static void closeBrowser(WebDriver driver){
		driver.close();
	}
}
