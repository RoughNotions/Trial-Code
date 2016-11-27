package com.apc.PGportal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import com.apc.PGRequestHandle.PGhandler.AutoTestFacade;
import com.apc.PGRequestHandle.PGservice.ResponseParser;
import com.apc.common.Excel;
import com.apc.common.ProcessPGExcelData;
import com.apc.common.UpdatePGExcel;
import com.thoughtworks.selenium.Selenium;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

 
public class TestSuite_PGportal extends BaseTestPG 
{

	
	 
	
	@BeforeTest
		public static void setup() throws FileNotFoundException, IOException, InvalidFormatException
		{	
			UpdatePGExcel.updatePGdata();
			System.out.println("PG test suite initializing.......");
			BaseSetup();
			Init_ConfigFile();
		}
	
	
	
	
	@Test(description = "To verify on hitting url, user gets login page")
		public  static void Test_LaunchPage() 
		{	
			driver.get(Conf.getProperty("PGUrl"));
			assert driver.findElement(By.id("login-box")).isDisplayed();	
		}

	
	
	@Test(description = "To verify user is able to login with valid username and password", dependsOnMethods = { "Test_LaunchPage" })
		public static void Test_Login() throws FileNotFoundException, IOException, InterruptedException 
		{	
			// login to the portal
			login();
	
			
			Thread.sleep(2000);
			// verify that url contains dashboard
			assert (driver.getCurrentUrl().contains("dashboard")|| driver.getPageSource().contains("Dashboard"));
		}

	
 	@Test(description = "To verify ADD Bank functionality", dependsOnMethods = { "Test_Login" })
	public static void Test_AddBank() throws FileNotFoundException,IOException, InvalidFormatException, InterruptedException 
	{
		 
		// go to add bank page
		String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Banks"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
		 
		//wait for some time
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		
		boolean isGot_Country = false;
		
		while(isGot_Country == false)
		{
		
			// Click on add bank		
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[1]"))).click();
	
			select("AddJQ");
	
			// enter details
			
			Thread.sleep(5000);
	
			input("bankName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 0));
			// System.out.println(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx",
			// 1, 0));
	
			input("bankCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 1));
	
			input("contactPerson", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 2));
	
			select_Dropdown("language", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 3));
	
			input("address1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 4));
	
			input("address2", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 5));

			
			List<WebElement> pg = new Select(driver.findElement(By.id("country"))).getOptions();   //Get all options in dropdown
			int i = 0;
			
			for(WebElement eachPage:pg)
			{
				i =i +1;
				String option_text = eachPage.getText();	//Save text of each option
			}
			
			System.out.println("The number of options get from country drop down is : " + i);
			
			if(i >= 2)
			{
				isGot_Country = true;
			
			}
			else
			{			
				driver.navigate().refresh();
				Thread.sleep(4000);
			
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("AddJQ")));
			}
			
		
		}
		
		select_Dropdown("country", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 6));
		
		select("fade");

		Thread.sleep(2000);

		if (driver.findElement(By.id("state")).isEnabled()) 
		{
			select_Dropdown("state", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 7));
		}

		input("cityname", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 8));
		
		String ZipCode = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 9);
		
		System.out.println("The value of ZipCode is : " + ZipCode);
		
		
		input("zipcode", ZipCode);

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1,10).equalsIgnoreCase("yes")) 
		{
			select("sponsored");
		}

		input("email", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 11));

		select_Dropdown("countryAreaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 12));

		input("areaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 13));

		input("contactNumber", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 14));

		select_Dropdown("currency", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 15));

		select_Dropdown("timezone", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 16));

		// Click on save button
		driver.findElement(By.xpath("/html/body/div[8]/div[2]/form/div/input")).click();

		// verify that newly added bank is in list
	
		Thread.sleep(10000);

		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[1]"))).getText(),Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank",1, 0));

		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[6]"))).getText(),"ACTIVE");
		
		// select the added bank

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[1]"))).click();

		// click on bin configuration
		select("BinJQ");

		// click on add
		select("AddBinJQ");

		// enter data
		input("binId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","BankBinConfig", 1, 0));

		//select_Dropdown("paymentMethodList", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "BankBinConfig", 1, 1));
		
		//modified by hari 24-AUG-14
		// start
		
		String cctype = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "BankBinConfig", 1, 1);
		Select dd_select1 = new Select(driver.findElement(By.id("paymentMethodList")));
		
		List<WebElement> pageOptions1 = new Select(driver.findElement(By.id("paymentMethodList"))).getOptions();//Get all options in dropdown
		boolean find1 = false;
		
		for(WebElement eachPage1:pageOptions1){
		    
			String option_val1 = (eachPage1.getText());//Save text of each option
			System.out.println("The Cur Option value of Credit Card Type is : " + option_val1);
			if(cctype.compareToIgnoreCase(option_val1) == 0)
			{
				find1 =true;
				System.out.println("Got the Req Currency in Currency drop down");
				String val_atrib1 = eachPage1.getAttribute("value");
				dd_select1.selectByValue(val_atrib1);				
			}
			
			if(find1)
			break;					
			
		}
		
		if(!find1)
		{
			System.out.println("Test_AddBank() ->  The Req Currency  not found in Currency Drop down : " + cctype);
		}
		//end
		Thread.sleep(2000);
		
		
		String Req_Currency = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "BankBinConfig", 1, 2);
		Select dd_select = new Select(driver.findElement(By.id("currency")));
				
		List<WebElement> pageOptions = new Select(driver.findElement(By.id("currency"))).getOptions();//Get all options in dropdown
		boolean find = false;
		
		for(WebElement eachPage:pageOptions){
		    
			String option_val = (eachPage.getText());//Save text of each option
			System.out.println("The Cur Option value of Currency is : " + option_val);
			if(Req_Currency.compareToIgnoreCase(option_val) == 0)
			{
				find =true;
				System.out.println("Got the Req Currency in Currency drop down");
				String val_atrib = eachPage.getAttribute("value");
				dd_select.selectByValue(val_atrib);				
			}
			
			if(find)
				break;					
			
		}
		
		if(!find)
		{
			System.out.println("Test_AddBank() ->  The Req Currency  not found in Currency Drop down : " + Req_Currency);
		}
		
		
		
		// click on save
		select("Binsave");
		
		Thread.sleep(10000);
		
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[1]"))).getText(),Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "BankBinConfig",1, 0));
		
	}

  @Test(description = "To verify Add Corporate functionality", dependsOnMethods = { "Test_AddBank" })
	public static void Test_AddCorporate() throws FileNotFoundException,IOException, InvalidFormatException, InterruptedException 
	{

		// login();

		// navigate to manage corporate
		String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Corporates"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
	 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		
		
		boolean isGot_Country = false;
		
		while(isGot_Country == false)
		{
			
			select("AddJQ");
	
			// enter details
			input("corporateName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 0));
	
			input("corporateCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 1));
	
			input("contactPerson", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 2));
	
			Thread.sleep(8000);
			
			// picking bank id from ADD bank sheet
			String BankID = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 1)+ "-"+ Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddBank", 1, 0);
			System.out.println("Ad Corporate() ->  The Bank ID from excel contatenate is : " + BankID);
			
			Select dd_select = new Select(driver.findElement(By.id("bankID")));
			
			
			List<WebElement> pageOptions = new Select(driver.findElement(By.id("bankID"))).getOptions();//Get all options in dropdown
			boolean find = false;
			
			for(WebElement eachPage:pageOptions)
			{
			    
				String option_val = (eachPage.getText());//Save text of each option
				System.out.println("The Cur Option value of Bank ID is : " + option_val + " , And the Req BankID is : " + BankID);
				if(BankID.compareToIgnoreCase(option_val) == 0)
				{
					find = true;
					System.out.println("Got the bank id from bank id drop down");
					String val_atrib = eachPage.getAttribute("value");
					dd_select.selectByValue(val_atrib);				
				}
				if(find == false)
				{
					System.out.println("find become true");
					break;
				}						
			}
			
			if(!find)
			{
				System.out.println("Test_AddCorporate() ->  The Req Bank Id not found in BankID Drop down : " + BankID);
			}
			
			
			
			select_Dropdown("bankID", BankID);
	
			input("address1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 3));
	
			input("address2", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 4));
			
			List<WebElement> pg = new Select(driver.findElement(By.id("country"))).getOptions();   //Get all options in dropdown
			int i = 0;
			
			for(WebElement eachPage:pg)
			{
				i =i +1;
				String option_text = eachPage.getText();	//Save text of each option
			}
			
			System.out.println("The number of options get from country drop down is : " + i);
			
			if(i >= 2)
			{
				isGot_Country = true;
			
			}
			else
			{			
				driver.navigate().refresh();
				Thread.sleep(4000);
			
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("AddJQ")));
			}
			
			
			//select_Dropdown("country", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 5));
		}
		
		select_Dropdown("country", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 5));
		
	
		select("fade");

		Thread.sleep(2000);

		if (driver.findElement(By.id("state")).isEnabled()) 
		{
			select_Dropdown("state",Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 6));
		}

		input("city", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 7));

		input("zipcode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 8));

		input("emailAddress", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 9));

		select_Dropdown("countryAreaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 10));

		input("areaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 11));

		input("contactNumber", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 12));

		select_Dropdown("currency", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 13));

		select_Dropdown("timezone", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 14));

		select_Dropdown("language", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 15));

		driver.findElement(By.xpath(".//*[@id='addCorporateForm']/div/input[1]")).click();

		Thread.sleep(10000);

		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[1]"))).getText(),Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddCorporate", 1, 0));

		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[7]"))).getText(),"ACTIVE");

	}

@Test(description = "TO verify Add Merchant functionality", dependsOnMethods = { "Test_AddCorporate" })
	public static void Test_AddMerchant() throws FileNotFoundException,IOException, InvalidFormatException, InterruptedException 
	{
			
		// go to manage merchant page
		String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Merchants"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
		//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Merchant")));

		//wait for some time for page load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MerchantAddJQ"))).isDisplayed();
		
		// click on add merchant
		select("MerchantAddJQ");
		
		Thread.sleep(5000);

		// enter business name
		input("businessName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 0));

		// enter display name
		input("dispalyName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 1));

		// select bank id
		select_Dropdown("bankId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddBank", 1, 1));

		// wait
		select("dispalyName");
		
		Thread.sleep(2000);

		// select corporate id

		select_Dropdown("corporateId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 1));

		// select preferred currency

		select_Dropdown("currencyId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 13));

		// select language

		select_Dropdown("languageId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate", 1, 15));

		// enter website url
		input("webSiteURL", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 2));

		// select merchant time zone
		//input("merchantTimeZoneId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 3));
		
		//above commented and maked below by hari
		select_Dropdown("merchantTimeZoneId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 3));
		// check/uncheck DST
		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate",1, 15).equalsIgnoreCase("yes")) 
		{
			select("dst");
		}

		// enter MCC
		input("mcc", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 5));

		// check/uncheck below option as per exel data
		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 6).equalsIgnoreCase("yes")) 
		{
			select("mcp");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 7).equalsIgnoreCase("yes")) 
		{
			select("STANDARD");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 8).equalsIgnoreCase("yes")) 
		{
			select("SPECIAL");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 9).equalsIgnoreCase("yes")) 
		{
			select("threedSecure");
		}

		if (driver.findElement(By.id("responseUrl")).isEnabled()) 
		{
			input("responseUrl",Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 10));
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 11).equalsIgnoreCase("yes")) 
		{
			select("virtualPos");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 12).equalsIgnoreCase("yes")) 
		{
			select("fss");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 13).equalsIgnoreCase("yes")) 
		{
			select("hostedPayPageCustNo");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 14).equalsIgnoreCase("yes")) 
		{
			select("dcc");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 15).equalsIgnoreCase("yes")) 
		{
			select("creditData");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 16).equalsIgnoreCase("yes")) 
		{
			select("levelTwoThreeData");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 17).equalsIgnoreCase("yes")) 
		{
			select("airlineData");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 18).equalsIgnoreCase("yes")) 
		{
			select("encryptReqResp");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 19).equalsIgnoreCase("yes")) 
		{
			select("checksumEnabled");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 20).equalsIgnoreCase("yes")) 
		{
			select("addressValidation");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 21).equalsIgnoreCase("yes")) 
		{
			select("cardSoreOption");
		}

		// ///////////////// Licence Validity //
		// ////////////////////////////////////

		input("validFrom", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 22));

		input("validTo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 23));

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 24).equalsIgnoreCase("yes"))
		{
			select("midGenerated1");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 25).equalsIgnoreCase("yes")) 
		{
			select("midGenerated2");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 26).equalsIgnoreCase("yes")) 
		{
			select("autoRenew");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 25).equalsIgnoreCase("yes")) 
		{
			input("customName",Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 27));
		}

		// ////////////////// Soft Descriptor /////////////////////////////

		select_Dropdown("softDesriptorType", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 28));

		input("softDesriptorValue", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 29));

		input("chargeDescriptor", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 30));

		// /////////////// Contact Details /////////////////////////////////

		input("cntctTitle", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 31));

		input("cntctFirstName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 32));

		input("cntctLastName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 33));

		input("cntctUserName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 34));

		select_Dropdown("mobileCountryAreaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 35));

		input("cntctMobileNo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 36));

		select_Dropdown("countryAreaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 37));

		input("areaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 38));

		input("cntctPhoneNo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 39));

		input("cntctPrimaryEmail", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 40));

		input("cntctSecondaryEmail", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 41));

		input("address1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 42));

		input("address2", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 43));

		select_Dropdown("countryId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 44));

		select("address1");

		Thread.sleep(5000);

		if (driver.findElement(By.id("state")).isEnabled()) 
		{
			select_Dropdown("state",Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 45));
		}

		input("city", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 46));

		input("zipcode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 47));

		// /////////////////////////// Contact Details Level 1 //
		// ////////////////////////////

		input("levelOneName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 48));

		input("levelOneEmailAddress", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 49));

		select_Dropdown("levelOneCountryAreaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 50));

		input("levelOneareaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 51));

		input("levelOneContactNo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 52));

		// /////////////////////////// Contact Details Level 2 //
		// /////////////////////////////

		input("levelTwoName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 53));

		input("levelTwoEmailAddress", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 54));

		select_Dropdown("levelTwoCountryAreaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 55));

		input("levelTwoareaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 56));

		input("levelTwoContactNo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 57));

		// Click on save
		select("save");

		// /////////////////////// Company Document ////////////////////////////

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 58).equalsIgnoreCase("yes")) 
		{
			select("isKyvByBank1");
		}
		else
		{
			select("isKyvByBank2");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 59).equalsIgnoreCase("yes")) 
		{
			select("isPublicQuotedCompany");
		}

		if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 59).equalsIgnoreCase("yes")) 
		{
			input("stockSymbol",Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 60));

			input("stockExchange",Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 61));
		}

		input("companyRegistrationNumber", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 62));

		input("vatRegistrationNo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 63));

		// //////////////////////// Authorised Person //
		// //////////////////////////////

		input("director1Name", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 64));

		select_Dropdown("director1DocumentType", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 65));

		input("director1Description", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 66));

		input("director2Name", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 67));

		select_Dropdown("director2DocumentType", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 68));

		input("director2Description", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 69));

		// ///////////////////////// Benificial Owner //
		// /////////////////////////

		input("beneficialOwnerName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 70));

		input("beneficialOwnerAddress", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 71));

		// ///////////////////////// KYC //
		// //////////////////////////////////////

		input("kycDocumentLocation", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 72));

		input("kycRemarks", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant", 1, 73));

		// Click on SAVE
		select("addSave");

		// verify merchant is added successfully

		Thread.sleep(10000);

		String MIDprefix = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 27);

		// check business name
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'"+ MIDprefix + "'" + ")]/td[2]"))).getText(), Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 0));
		
		// here we need to save the Merchant ID created to the Paypage Excel sheet so that it can be use by Paypage Test cases
		String Merchant_ID = driver.findElement(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix+ "'" + ")]/td[1]")).getText();
	
		System.out.println("AddMerchant() ->  The Merchant Prefix created is : " +  Merchant_ID);
		Excel.setData("Resourses\\Data\\PG_Paypage_data.xlsx", "Sheet1", 1, 0, Merchant_ID);
		
		
		// check status

		Assert.assertEquals(driver.findElement(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix+ "'" + ")]/td[6]")).getText(),"PENDING_APPROVAL");
	}

	
	@Test(description = "To verify Approve Merchant functionality", dependsOnMethods = { "Test_AddMerchant" })
		public static void Test_ApproveMerchant() throws InvalidFormatException,IOException, InterruptedException 
		{
			
			// Go to Manage Merchant page
			String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Merchants"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Merchant")));
	
			//wait for some time for page load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MerchantAddJQ"))).isDisplayed();
			
			// select the created merchant
			String MIDprefix = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 27);
			
			// wait for page to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).isDisplayed();
			
			// click on row
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).click();
			
			// ///////////////// save MID to
			// excel/////////////////////////////////////////////////////////////////
	
			String MID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).getText();
	
			Excel.setData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 0,MID);
	
			// ///////////////// save MID to
			// excel////////////////////////////////////////////////////////////////
			// click on Approve/Reject
	
			select("ApproveRejectJQ");
	
			// click next
			select("saveApproveRejectMerchant");
	
			// check verified
			select("verified");
	
			// check directors name and address
			select("directorsNameAndAddress");
	
			//check benificial owner identified
			select("beneficialOwnerIdentifier");
	
			// enter comment
			input("comments1", "Automated test comment");
	
			// click approve
			select("approve");
	
			// verify merchant has been approved
			Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'"+ MIDprefix + "'" + ")]/td[6]"))).getText(), "APPROVED");
	
		}
	

	@Test(description = "To verify MerchantConfiguration functionality", dependsOnMethods = { "Test_ApproveMerchant" })
		public static void Test_MerchantConfiguration() throws FileNotFoundException, IOException, InvalidFormatException,InterruptedException 
		{
			// login();
			
			//go to Manage Merchant page
			String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Merchants"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
		
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Merchant")));
	
			// select the created merchant
			String MIDprefix = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 27);
			
			//wait for page to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).isDisplayed();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).click();
	
			//select("ManageBlockJQ");
			
			// click on configuration
			select("ConfigurationJQ");
	
			//for (int i = 1; i <= Excel.get_rowCount(
			//		"Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig") -1; i++)
			//{
	
				// Click on add
	
				select("AddJQ");
	
				Thread.sleep(2000);
	
				// Select processor			
				String ProcessID = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,0);
				
				System.out.print("The Processor ID is : " + ProcessID);
				
				select_Dropdown("addProcessorId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,0));
	
				select("fade");
	
				Thread.sleep(2000);
	
				// Select payment method
				//new Select(driver.findElement(By.id("addPaymentId"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 1));
				String Req_PaymentID = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 1);
				Select dd_select = new Select(driver.findElement(By.id("addPaymentId")));
				boolean find =false;				
				List<WebElement> pageOptions = new Select(driver.findElement(By.id("addPaymentId"))).getOptions();//Get all options in dropdown
				
				for(WebElement eachPage:pageOptions){
				    
					String option_val = (eachPage.getText());//Save text of each option
					System.out.println("The Cur Option value of PaymentId is : " + option_val);
					if(Req_PaymentID.compareToIgnoreCase(option_val) == 0)
					{
						find =true;
						System.out.println("Got the Req_PaymentID from PaymentID drop down");
						String val_atrib = eachPage.getAttribute("value");
						dd_select.selectByValue(val_atrib);						
					}
					if(find)
						break;	
					
					
				}
				
				if(!find)
				{
					System.out.println("Test_MerchantConfiguration() -> cannot find the value in payment id drop down : " + Req_PaymentID);
				}
				
				
				
				select("fade");
	
				Thread.sleep(2000);
				
				// Select Auth currency
				Select authCurr = new Select(driver.findElement(By.id("addAuthCurr")));
				
				authCurr.selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,2));
	
				select("fade");
	
				Thread.sleep(2000);
	
				// select settle currency
				Select setCurr = new Select(driver.findElement(By.id("addSettCurr")));
				
				setCurr.selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,3));
	
				select("fade");
	
				Thread.sleep(2000);
	
				// select bin ID
				//Select binID = new Select(driver.findElement(By.id("addbinId")));
				
				//binID.selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,4));
				
				Select selectBox = new Select(driver.findElement(By.id("addbinId")));
				//selectBox.selectByIndex(3); 
				
				//start ###########  chnge by hari, index getting change in different release.
				//when chetu removed
				//selectBox.selectByVisibleText("534491-Chetu Bank");
				selectBox.selectByIndex(1);
	            //end   ###########
				select("fade");
	
				Thread.sleep(2000);
	
				// Enter processor merchant ID
				input("addPMID", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,5));
	
				// Enter config ID
				input("addConfigId", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,6));
	
				// Enter velocity amount
				input("addVelocity", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,7));
	
				// Enter credit velocity amount
				input("addCreditVal", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig", 1,8));
	
				// check 3D secure attempted
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 9).equalsIgnoreCase("yes")) 
				{
					select("addcommMode0");
				}
	
				// check 3D secure authenticated
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 10).equalsIgnoreCase("yes")) 
				{
					select("addcommMode1");
				}
	
				// CHeck ECOM
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 11).equalsIgnoreCase("yes")) 
				{
					select("addcommMode2");
				}
	
				// check MOTO
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 12).equalsIgnoreCase("yes")) 
				{
					select("addcommMode3");
				}
	
				// Check recurring
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", 1, 13).equalsIgnoreCase("yes")) 
				{
					select("addcommMode4");
				}
	
				// Click on SAVE
				driver.findElement(By.xpath(".//*[@id='addBlockForm']/div/input[1]")).click();
				
				Thread.sleep(5000);
	
			}
		

	@Test(description = "To verify paypage configuration functionality", dependsOnMethods = { "Test_MerchantConfiguration" })
		public static void Test_PaypageConfig() throws FileNotFoundException,IOException, InvalidFormatException, InterruptedException 
		{
			//login();
			
			// goto manage merchants page
			String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Merchants"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
		
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Merchant")));
			// select the created merchant
	
			String MIDprefix = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 27);
	
			// wait for page to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).isDisplayed();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix + "'"+ ")]/td[1]"))).click();
	
			// click on paypage config link
			select("PayPageJQ");
	
			// select all card type
			int rowCount = Excel.get_rowCount("Resourses\\Data\\PG_PortalData.xlsx", "MerchantConfig");
	
			for (int i = 1; i <= rowCount - 1; i++) 
			{
				new Select(driver.findElement(By.id("cardType"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","MerchantConfig", i, 1));
	
			}
			
			// enter response url
			input("responseURL", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "PaypageConfig", 1, 1));
	
			// select background clor
			select_Dropdown("bgColor", "White");
	
			// select font type
			select_Dropdown("fontStyle", "Arial");
	
			// select font size
			select_Dropdown("fontSize", "10");
	
			// select font color
			select_Dropdown("primryTextClr", "Black");
	
			// select button color
			select_Dropdown("bottonColor", "Blue");
	
			// select header color
			select_Dropdown("headerClr", "Blue");
	
			// check/uncheck hide header
			select("hideHeader");
	
			// select iFrame or redirect page
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 9).equalsIgnoreCase("iFrame")) 
			{
				select("iFramePage");
	
				input("height", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "PaypageConfig", 1,10));
				
				input("width", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 11));
			}
			else
			{
				select("pageType");
	
				driver.findElement(By.id("logoFile")).sendKeys(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 12));
	
				
				 
			}
	
			select("upload");
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 15).equalsIgnoreCase("yes")) 
			{
				select("displayItemDtls");
	
			}
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 16).equalsIgnoreCase("yes")) 
			{
				select("displayCustDtls");
			}
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 17).equalsIgnoreCase("yes")) 
			{
				select("displayBillDtls");
	
			}
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 18).equalsIgnoreCase("yes")) 
			{
				select("displayMerchantDtls");
			}
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 19).equalsIgnoreCase("yes")) 
			{
				select("displyAddressInput");
			}
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 20).equalsIgnoreCase("yes")) 
			{
				select("emailApprove");
			}
	
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","PaypageConfig", 1, 21).equalsIgnoreCase("yes")) 
			{
				select("emailDeclines");
	
			}
	
			// enter merchant email address
			input("merchantEmailAddrs", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "PaypageConfig", 1, 22));
	
			select("save");
	
		}

@Test(description = "To verify FSS Configuration functionality", dependsOnMethods = { "Test_PaypageConfig" })
		public static void Test_FSSConfig() throws InvalidFormatException,IOException, InterruptedException 
		{
			
			if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant",1, 12).equalsIgnoreCase("yes")) 
			{
				 
				
				// goto manage merchants pages
				String[] arr_elem = {"Configuration", "Merchant Configuration","Manage Merchants"};						
				Navigate_Menu_From_To_New(driver,arr_elem);	
				 
				
				// select the created merchant
				String MIDprefix = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 27);
				
				//wait for some time to page to load
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix+ "'" + ")]/td[1]"))).isDisplayed();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[starts-with(@id," + "'" + MIDprefix+ "'" + ")]/td[1]"))).click();
	
				// click on FSS configuration
				select("FSSJQ");
	
				// Check FSS enabled
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","FSSConfig", 1, 1).equalsIgnoreCase("yes")) 
				{
					select("fssEnabled1");
				} else
				{
					select("fssEnabled2");
				}
	
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","FSSConfig", 1, 1).equalsIgnoreCase("yes")) 
				{
					//select("negativeDb");
					if ( !driver.findElement(By.id("negativeDb")).isSelected())
					{
					     driver.findElement(By.id("negativeDb")).click();
					}				
				}
	
				new Select(driver.findElement(By.id("white"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "FSSConfig",1, 2));
	
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","FSSConfig", 1, 2).equalsIgnoreCase("Stop after X times")) 
				{
					input("white1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "FSSConfig", 1,3));
				}
	
				new Select(driver.findElement(By.id("gray"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "FSSConfig",1, 4));
	
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","FSSConfig", 1, 4).equalsIgnoreCase("Stop after X times")) 
				{
					input("gray1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "FSSConfig", 1,5));
				}
	
				new Select(driver.findElement(By.id("black"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "FSSConfig",1, 6));
	
				if (Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","FSSConfig", 1, 6).equalsIgnoreCase("Stop after X times")) 
				{
					input("black1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "FSSConfig", 1,7));
				}
	
				// Click on Save
				select("save");
			}
			else
			{
				Reporter.log("FSS Configuration not applicable");
			}
	
		}

	@Test(description = "To verify Add Role functionality", dependsOnMethods = { "Test_FSSConfig" })
		public static void Test_AddRole() throws FileNotFoundException,IOException, InvalidFormatException, InterruptedException 
		{
			//login();
			
			// goto manage Roles page
			String[] arr_elem = {"Configuration", "User Configuration","Manage Roles"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
		
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Role")));
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
	
			// click on add
			select("AddJQ");
	
			select("pemissionDisplay");
			
			Thread.sleep(4000);
			if(!driver.findElement(By.id("hierarchyList")).isEnabled())
			{
				System.out.println("dropdown for  hierarchyList is not enable yet");
			}
	
			// select hierarchy
			select_Dropdown("hierarchyList", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "Role", 1, 0));
	
			select("pemissionDisplay");
			
			Thread.sleep(2000);
			
			// enter role name
			input("roleName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","Role", 1, 1));
	
			// enter description
			input("roleDesc", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","Role", 1, 2));
	
			// check select all
			select("checkAll");
	
			// click save
			driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[3]/input")).click();
										
			Thread.sleep(3000);
			
			// again click on save button, becuase sometimes error occuring while click on save button first time.
			//driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[3]/input")).click();
										
			// verify that role has been added
			Thread.sleep(15000);
	
			String excel_RoleName = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "Role", 1,1);
			
			if(driver.findElements( By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).size() != 0)
			{
				System.out.println("Add Role Module: found the element that was creating issue");				
			}
			else
			{
				System.out.println("Add Role Module: cannot  found the element that was creating issue");
			}
			
			
			String Create_RoleName =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td"))).getText(); 
			
			System.out.println("The excel Role is : " + excel_RoleName  + " and the Created Role is : " + Create_RoleName);
			
			//Assert.assertEquals(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "Role", 1,1),wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='1']/td[1]"))).getText());
			Assert.assertEquals(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "Role", 1,1),wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td"))).getText());			
			//	/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td
			
			
		}

	@Test(description = "To verify Add users functionality", dependsOnMethods = { "Test_AddRole" })
	    public static void Test_AddUsers() throws FileNotFoundException,IOException, InvalidFormatException, InterruptedException 
	{
	 
	  // goto Manage users page
		String[] arr_elem = {"Configuration", "User Configuration","Manage Users"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
		
		//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Users")));
		boolean isGot_Country = false;
		
		while(isGot_Country == false)
		{
			// click on add
			select("AddJQ");
	
			// enter first name
			input("firstName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 0));
	
			// enter last name
			input("lastName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 1));
	
			// enter username
			input("userName", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 2));
			
			//To use in relogin in Test_mailNotification_and_login method
			huser_disposal = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 2);
	
			// select hierarchy
			new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hierarchyList")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 3));
	
			select("fade");
	
			Thread.sleep(3000);
	
			// select role
			new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("roleadd")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "Role", 1, 1));
	
			select("fade");
	
			Thread.sleep(3000);
	
			// select entity id
			new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("entityId")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 0));
	
			select("fade");
	
			Thread.sleep(3000);
	
			// enter address1
			input("address1", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 6));
	
			// enter address2
			input("address2", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 7));

			// select country
			// check for the values fill in Country Drop Down box or if not then refresh the page and then try again
			
		
			List<WebElement> pg = new Select(driver.findElement(By.id("country"))).getOptions();   //Get all options in dropdown
			int i = 0;
			
			for(WebElement eachPage:pg)
			{
				i =i +1;
				String option_text = eachPage.getText();	//Save text of each option
			}
			
			System.out.println("The number of options get from country drop down is : " + i);
			
			if(i >= 2)
			{
				isGot_Country = true;
			
			}
			else
			{			
				driver.navigate().refresh();
				Thread.sleep(4000);
			
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("AddJQ")));
			}
			
		}
		
		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 8));
		
		select("fade");

		Thread.sleep(3000);

		// select state
		if (driver.findElement(By.id("state")).isEnabled()) 
		{
			String State_val = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers",1, 9);
			
			new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("state")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers",1, 9));
		}

		// enter city
		input("city", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 10));

		// enter zipcode
		input("zipcode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 11));

		// email
		input("email", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 12));
		
		
		

		// select country area code
		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("countryAreaCode")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 13));

		// enter area code
		input("areaCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 14));

		// enter phone number
		input("phoneNumber", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 15));

		// select time zone
		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("timezone")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 16));

		// select language
		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("language")))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 17));

		// click save
		driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div/input")).click();
		
		Thread.sleep(4000);
		
		wait.until(ExpectedConditions.titleContains("Manage Users"));
		boolean   tx_user = driver.getTitle().contains("Manage Users");
		System.out.println("containsManage Users   " +tx_user);
	 
		Assert.assertTrue(tx_user);
		
		//To find email ID, use in relogin in Test_mailNotification_and_login method
		String mail_full  =Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 12);
				
		//Reset password
		
		
		
		
		//log out for using this browser for fetching mail,creating new instance of webDriver not worked
		//logout();
		
		
		System.out.println("before cut and direct taken from excel is::"+ mail_full);
		hpass_disposal =  find_new_reset_pass_from_mail(mail_full);
		
		  System.out.println("huser_disposal from last of AddUser  : "+huser_disposal + "  and hpass_disposal :  "+hpass_disposal);
		
		
	}


	// ================ modification first start

	//This method for confirming notification and relogin 
	@Test(description = "To test that the user is able to login with the user id and password that he receives in email notification foe AddUser and able to login" ,dependsOnMethods={"Test_AddUsers"})
	 
		public static void Test_mailNotifyAddUser_ResetPass() throws InterruptedException, FileNotFoundException, IOException
	    {
	    
		BaseSetup();
		
		System.out.println("huser_disposal from start of Test_mailNotifyAddUser_ResetPass  :"+huser_disposal + "  and hpass_disposal :  "+hpass_disposal);
		
		if(huser_disposal==null || huser_disposal==null )
		{
			System.out.println("huser_disposal OR huser_disposal is null");
			Assert.assertTrue(false);
		}
		newLogin (huser_disposal,hpass_disposal);
		
		//New  and confirm password
		driver.findElement(By.xpath("//*[@id='newPass']")).sendKeys(newPass);
		driver.findElement(By.xpath("//*[@id='confirmPass']")).sendKeys(newPass);
		
		//Submit click
		
		driver.findElement(By.id("submit")).click();
		boolean change_succ =driver.getPageSource().contains("Password Changed Successfully");
		//Confirm ok
		Thread.sleep(3000);
		driver.findElement(By.id("okBtn")).click();
		
		//To get page below string for success
		Thread.sleep(3000);
		
		
		Assert.assertTrue(change_succ);
		
	    }


	@Test(description ="Reset password from user management and reset password",dependsOnMethods ={"Test_mailNotifyAddUser_ResetPass"} )

	    public static void ResetPassWithNewOne() throws InterruptedException, FileNotFoundException, IOException
	{
		
		System.out.println("ResetPassAnd_Relogin function  start execution");
		 
		login();
		//open manageUsers for reset  password
		
		//It worked properly but for making URL generalization
	   // driver.get("https://dev1portal.gpcloud.com/APC/app/manageUsers/manageUsersPage");
	  
		// select the Manage User from the sub menu: ,Configuration-> User Configuration-> Manage Users.
		String[] arr_elem = {"Configuration", "User Configuration","Manage Users"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
		
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[1]")));
	    //find username
	    huser_disposal = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[1]")).getText();
	   
	   //find mail
	    
	   String tempMail= driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText();
	   
	    //click for select first row
	    Thread.sleep(3000);     
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).click();
	    
	   //click for Reset
	    Thread.sleep(1000);     
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[9]/input")).click();
	                                                         
	    
	   //click for ok
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id='ChangePassConfirm']/div[2]/input[3]")).click();
	    Thread.sleep(2000);
	    //ok
	    driver.findElement(By.xpath("//*[@id='ChangePassSuccessMsg']/div[2]/input")).click();
	    
	    Thread.sleep(2000);
	    logout();
	   
	  //find password from displosal mail
	    
	    hpass_disposal= find_new_reset_pass_from_mail(tempMail);
	    
	     
	     System.out.println("huser_disposal from start of ResetPassWithNewOne  :"+huser_disposal + "  and hpass_disposal :  "+hpass_disposal);
	     
	     
	     BaseSetup();
	     
	     
	     System.out.println("huser_disposal from start of Test_mailNotifyAddUser_ResetPass  :"+huser_disposal + "  and hpass_disposal :  "+hpass_disposal);
	 	
	     if(huser_disposal==null || huser_disposal==null )
			{
				System.out.println("huser_disposal OR huser_disposal is null");
				Assert.assertTrue(false);
			}
	     newLogin (huser_disposal,hpass_disposal);
	 	
	 	//New  and confirm password
	 	driver.findElement(By.xpath("//*[@id='newPass']")).sendKeys(reset_pass);
	 	Thread.sleep(3000);
	 	driver.findElement(By.xpath("//*[@id='confirmPass']")).sendKeys(reset_pass);
	 	
	 	//Submit click
	 	
	 	driver.findElement(By.id("submit")).click();
	 	boolean change_succ =driver.getPageSource().contains("Password Changed Successfully");
	 	//Confirm ok
	 	Thread.sleep(3000);
	 	driver.findElement(By.id("okBtn")).click();
	 	
	 	//To get page below string for success
	 	Thread.sleep(3000);
	 	
	 	
	 	Assert.assertTrue(change_succ);
	 	
	    
	}


	@Test(description="Relogin with  new password ",dependsOnMethods={"ResetPassWithNewOne"})
	public static void TestRelogin()
	{
		
	   System.out.println("Test Relogin execution started ");
		
	   //This function called for relogin with new user/pass taken from ResetPassWithNewOne method
	   
	   System.out.println("before calling Relogin"+"  huser_disposal  :"+huser_disposal+"   reset_pass  "+reset_pass);
	   
	   if(huser_disposal==null || huser_disposal==null )
		{
			System.out.println("huser_disposal OR huser_disposal is null");
			Assert.assertTrue(false);
		}
	   ReLogin(huser_disposal, reset_pass);
		
		
		wait.until(ExpectedConditions.titleIs("System Dashboard"));
		assert driver.getCurrentUrl().contains("dashboard");
	}
	 	
	// ===========modification first end
	
	///Test_AddUsers
	@Test(description = "To verify that the Newly created user is displaying in the User Report", dependsOnMethods = {"TestRelogin"},alwaysRun =true)
		public static void Test_CheckAddedUserInUserReport() throws InterruptedException, InvalidFormatException, IOException
		{
			 
		     login();
			
			// navigate to  Reporting -> Admin Reports -> User Report
			String[] arr_elem = {"Reporting", "Admin Reports","User Report"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
		
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("User_Report")));
			
			// Fetch the Newly Created User Name from Excel sheet		
			String CreateUserName = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddUsers", 1, 2);
			
			System.out.print("The create Username is : " + CreateUserName);
			System.out.print("\n");
			
			boolean isUnamePresent = driver.getPageSource().contains(CreateUserName);
			
			///Assert.assertTrue(isUnamePresent);
				
		}
	
	
	@Test(description = "TO verify Add API user functionality", dependsOnMethods = { "Test_CheckAddedUserInUserReport" },alwaysRun =true)
		public static void Test_AddAPIUser() throws InvalidFormatException,IOException, InterruptedException 
		{
			//login();
			// go to Configuration -> User Configuration -> Manage API users
			String[] arr_elem = {"Configuration", "User Configuration","Manage API Users"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_API_Users")));
	
			// click on Add
			select("AddJQ");
	
			System.out.print("The MId for Add API User is : " + Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 0));
			// select merchant
			
			//somtimes dropdown element not visible then refresh browser
			System.out.println("no of element in dropdown shown:  "+driver.findElements(By.id("midAdd")).size());
			
			Thread.sleep(4000);
			new Select(driver.findElement(By.id("midAdd"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 0));
	        //by hari
						
			//new Select(driver.findElement(By.id("midAdd"))).selectByIndex(2);
			
			 
			//System.out.println("The User Id from excel of APIUser is : " +  Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","APIUser", 1, 1));
			
			// enter user ID
			String API_USerID = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","APIUser", 1, 1);
			input("userIdAdd", API_USerID);
			
			// need to save the API UserID in Paypage Excel sheet so that it will be use by Paypage test cases
			Excel.setData("Resourses\\Data\\PG_Paypage_data.xlsx", "Sheet1", 1, 1, API_USerID);
			
			
			// enter email id
			input("emailIdAdd", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 2));
	        String apim =       Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 2);
			// click save
			driver.findElement(By.xpath(".//*[@id='addUserForm']/div/input[1]")).click();
			Thread.sleep(8000);
	
			// Verify API user has been added
			String userID = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","APIUser", 1, 1);
	    
			//this was given earlier but i have changed simply  for pagecontains.
			//assert wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='" + userID + "']/td[1]"))).isDisplayed();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")));
			boolean apim_found = driver.getPageSource().contains(apim);
			 
			
			System.out.println("apim_found :"+apim_found );
			 Assert.assertTrue(apim_found);
			
	
		}

	
	@Test(description =" To verify the functionality of add Alert in the system",dependsOnMethods = { "Test_AddAPIUser" }, enabled=true)  // created by ankur
		public static void Test_AddAlert() throws FileNotFoundException, IOException, InterruptedException
		{			
			// select the Alert Config from System Configuration
			String[] arr_elem = {"Configuration", "System Configuration","Alert Config"};						
			Navigate_Menu_From_To_New(driver,arr_elem);
			
			//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Alert_Config")));
			
			// check if the "LICENSE_ALREADY_EXPIRED" Identifier is already exist or not, if exist then delete first		
			if(driver.getPageSource().contains("LICENSE_ALREADY_EXPIRED"))
			{
				// mean already exists and we need to delete it
				
				System.out.println("The Alert already exists");
				
				// click on the Table row that contains the text "LICENSE_ALREADY_EXPIRED"			
				List<WebElement> links = driver.findElements(By.tagName("td"));
	            Iterator<WebElement> itr = links.iterator();
	            boolean isFound =false;
	            while(itr.hasNext())
	            {
	                String test = itr.next().getText();              	
	                if(test.equals("LICENSE_ALREADY_EXPIRED"))
	                {           
	                	isFound = true;
	                	System.out.println("Found LICENSE_ALREADY_EXPIRED in TD element");
	                	itr.next().click();	           
	                }
	                
	                if(isFound)
	                {
	                	break;
	                }
	            }
					
				Thread.sleep(3000);
				
				// click on the delete button
				select("EditJQ");
				
				// now new small window will open and we will click on "Yes" button
				driver.findElement(By.xpath("/html/body/div[10]/div[2]/input")).click();
				
				Thread.sleep(3000);
				
			
			}
			else
			{
				System.out.println("not exists");
			}
			
			// click on Add Alert button
			select("AddJQ");
			
			// select "LICENSE_ALREADY_EXPIRED" from Alert Identifier Drop down box
			//select_Dropdown("identifier","LICENSE_ALREADY_EXPIRED");
			Thread.sleep(3000);
			Select dropdown = new Select(driver.findElement(By.id("identifier")));
			//dropdown.selectByVisibleText("LICENSE_ALREADY_EXPIRED");
			dropdown.selectByIndex(12);
			
			// select Merchant from Role drop down list
			select_Dropdown("role","Merchant");
			
			// enter some text into subject text box
			input("friendlyText", "Test Alert");
			
			// click on save button
			driver.findElement(By.xpath("/html/body/div[8]/div[2]/form/div/input")).click();
			
			
			
			
			// Checking the Result that new Alert has been added or not			
			//*************************
			
			// fetching the value of newly added Alert from xpath
			//String New_Alert_Name = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).getText();
			boolean text_present = driver.getPageSource().contains("LICENSE_ALREADY_EXPIRED");
			// checking the actual alert name with the expected Alert Name
			Assert.assertEquals(text_present,true);
			
			wait= new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("AddJQ")));
			//Thread.sleep(4000);
		
		}
		
	@Test(description= "To verify that user is able to delete an existing alert",dependsOnMethods = { "Test_AddAlert" },alwaysRun =true)  // need to test again right now from hari pc runnung ok,  but from my pc 404 not found issue
	    public  static  void  Test_deleteAlert() throws InterruptedException
	    {
				// select the Alert Config from System Configuration
		
				String[] arr_elem = {"Configuration", "System Configuration","Alert Config"};						
				Navigate_Menu_From_To_New(driver,arr_elem);
		
				//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Alert_Config")));
	           	           
	           // find that element present or not	           
	           if(driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).size() == 0)
	           {
	        	  // First condition :No record is present
	              System.out.println("First condition for zero record executed");
	                  
	              int len = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).size();
	              System.out.println(" lenght when zero record :" + len);
	              Assert.assertTrue(true);
	           }
	           else
	           {
	              // delete the first record
	              WebElement ele_FirstIden= driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td"));                                         
	              WebElement ele_FirstRole =driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]"));
	              
	              String ele_FirstRec =  ele_FirstIden.getText() + ele_FirstRole.getText(); 
	              
	              System.out.println("The  First Record is: " + ele_FirstRec);
	              
	              ele_FirstIden.click();
	                  
	              //To click delete button.
	              driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[4]/div/ul/li[2]/a")).click();
	              
	              //To confirm ok
	              driver.findElement(By.xpath("/html/body/div[10]/div[2]/input")).click();
	       
	              // Refresh the page
	              Thread.sleep(2000);
	              driver.navigate().refresh();
	              
	              // Now look for remaining records	              
	              if(driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).size() ==0)
	              {
	                // Second condition :  This means the record has been deleted successfully and no other record remains
	                System.out.println("Second condition  when only 1 record found, executed");
	                
	                int len = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).size();
	                System.out.println(" lenght when only 1 record :" + len);
	                Assert.assertTrue(true);
	                    
	              }else
	              {
	                // Third Condition:  Other records also there	                
	                System.out.println("Third condition  when more than 2 records found,executed");
	                
	                int len = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).size();
	                System.out.println(" lenght when more than 2 records record :" + len);
	                WebElement ele_UpdatedFirstIden= driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td"));
	                
	                //for role text
	                WebElement ele_UpdatedFirstRole= driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]"));
	                
	                String ele_SecondRec= ele_UpdatedFirstIden.getText() + ele_UpdatedFirstRole.getText();
	                
	                System.out.println("The second Record is : " + ele_SecondRec);
	                
	                if(ele_FirstRec.equalsIgnoreCase(ele_SecondRec))
	                {
	                	Assert.assertTrue(false);
	                }
	                else
	                {
	                    Assert.assertTrue(true);
	                }                        
	              }	                  	                 
	           }	           	    
	    }
	
	
	@Test(description = "To verify that the admin is  able to Reset the Password for Users",dependsOnMethods = { "Test_deleteAlert" },enabled=true)
	public static void Test_ResetPassword_Users() throws InterruptedException, FileNotFoundException, IOException
	{
	
		// select the Manage User from the sub menu: ,Configuration-> User Configuration-> Manage Users.
		String[] arr_elem = {"Configuration", "User Configuration","Manage Users"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
		
		//driver.get(GetNewURL(Conf.getProperty("PGUrl"),Conf.getProperty("Manage_Users")));
		
		// wait for some time o load the page			
		Thread.sleep(5000);
		
		// check if any of the Record is available in the page for clicking on the Reset Password button.
		// Note: the xpath of first record in page is : /html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[9]/input 
		Boolean isPresent = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[9]/input")).size() > 0;
		
		if(isPresent)
		{
			// mean atleast 1 record is present to reset the password
			
			// click on first record Reset Password button
			driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[9]/input")).click();
								         
			
			// Dialog box will open and we will click on "Ok" button
			driver.findElement(By.xpath("/html/body/div[14]/div[2]/input[3]")).click();
			
			
			// now if the following text is displayed in the screen then it means Password has been reset successfully
			//"Password has been reset. New password will be send through email to the respective email id within 2 minutes"
			
			Thread.sleep(3000);
			
			Assert.assertTrue(driver.getPageSource().contains("Password has been reset"));
			
		}
		else
		{
			System.out.println("Test_ResetPassword_Users() : There is no Record is present for resetting of password, please add atleast one record");
			// In this condition, we will pass the test, later we need to check whether we have to add user then change password or not
			Assert.assertTrue(true); 
		}
		
	}
	
	@Test(description ="To test that for a transaction record (monthly)  for Processor fee appears in the Processor Fee Report",dependsOnMethods={"Test_ResetPassword_Users"},alwaysRun =true )

	public static void  Test_Processor_fee_appears() throws InterruptedException

	{
		String[] arr_elem = {"Reporting", "Admin Reports", "Processor Fee Report"};
		Navigate_Menu_From_To_New(driver,arr_elem);
		Thread.sleep(4000);
		
		assert (driver.getCurrentUrl().contains("ProcessorFeeReportPage") );
		
	}

	
	

	
	//Condition for cancel: txn should be only success and only single row should occur
	
	@Test(description ="To verify the Cancel of transaction in transaction Search Screen", dependsOnMethods = {"Test_Processor_fee_appears"},alwaysRun =true )
	
	
	   public static void Trans_Search_Cancel() throws InterruptedException, FileNotFoundException, IOException
	    {
	          String Cancel_Comment = "";
	           
	          boolean IsTestOver = false;
	           	          
	          String[] arr_elem = {"Transactions", "Transaction Search"};	
	         
	   		  Navigate_Menu_From_To_New(driver,arr_elem);
	           
	           Thread.sleep(3000);
	           
              // wait till  first row of record can display
	           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td"))).isDisplayed();
	           
	          //sort out transaction search for txn success and txn type auth through dropdown
	           select_Dropdown("status", "TXN_SUCCESS");
	           select_Dropdown("transType", "AUTH");
	           
	           //click for search
	           select("search");
	           Thread.sleep(3000);
	           
	           //click for minimize search block
	           select_new("//*[@id='collapse']/span");
	           
	           int rowCount=driver.findElements(By.xpath("//table[@id='grid']/tbody/tr")).size();
	           
	           System.out.println("rowCount of major table  :"+rowCount);

	           int j =2;
	           
	           for(int i=2;i<=rowCount;i++)
	           {     

	          assert driver.getTitle().contains("Transaction Search");
	   
	          Thread.sleep(3000);
	   
	          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td"))).isDisplayed();
	          driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td")).click();
	          JavascriptExecutor jse = (JavascriptExecutor)driver;
	          jse.executeScript("window.scrollBy(0,250)", "");
	                              
	          Thread.sleep(10000);
	                 
	          WebElement table =driver.findElement(By.id("subGrid"));

	          // Now get all the TR elements from the table 
	   
	          java.util.List<WebElement> ls = table.findElements(By.tagName("tr")); 
	    
	          int  row_count = ls.size();
	          System.out.println("row count  for sub table before cancel  "+ row_count); 
	   
	          // And iterate over them, getting the cells 
	          
	         boolean success_found  =(driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[7]")).getAttribute("title").equals("TXN_SUCCESS"));
	         System.out.println("success_found  " +success_found);
	          for (WebElement row : ls) 
		              { 	                   
	             if(row_count != 2 )
	             {
	            	 System.out.println("this record can't be cancel");
	            	 break;                                              
	             } 
		                
	             else
	             {
	            	 
	            	 
	            	 //if first row attribute having other than TXN_SUCCESS, it will break
	            	 if(!success_found)
	            		 break;
                 System.out.println("this record  will cancel  :");
                 //pick one record record
                  driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).click(); 
                 
                 //click for cancel
                 driver.findElement(By.id("CANCELJQ")).click();
                 
                 // enter a random value
                 Cancel_Comment = ProcessPGExcelData.getRandonName();
                 Thread.sleep(5000);
                 System.out.println("random :   "+Cancel_Comment);
                 driver.findElement(By.xpath("/html/body/div[18]/div[2]/form/table/tbody/tr[6]/td[2]/textarea")).sendKeys(Cancel_Comment);
            
                 //click for save
                  
                 driver.findElement(By.xpath("/html/body/div[18]/div[2]/form/div[2]/input")).click();
                 
                // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td")));
                 Thread.sleep(4000);
                 
                 boolean ank = driver.getPageSource().contains(Cancel_Comment);
                 boolean canel_succ =driver.getPageSource().contains("CANCEL_SUCCESS");
                 Thread.sleep(5000);
                 System.out.println("Cancel_Comment   :"+ ank + " canel_succ  " +canel_succ);
                 
                 //if in pagesource  cancel_succ not found try once more
                 
                 if(!canel_succ)
                 {
                 	System.out.println("text after cancel :"+driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td[7]")).getText());
                 	if(driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td[7]")).getText().equals("CANCEL_SUCCESS"))
                 	canel_succ =true;
                 }
                 
                 WebElement table1 =driver.findElement(By.id("subGrid"));
                 
                 // Now get all the TR elements from the sub table  after cancel
          
                 java.util.List<WebElement> ls1 = table1.findElements(By.tagName("tr")); 
           

                 int  row_count_af_cancel = ls1.size();
                 System.out.println("row_count_af_cancel :"+row_count_af_cancel);
                 
                 Assert.assertTrue(canel_succ);
                 IsTestOver =true;
                 System.out.println("Statement after Assert :" + IsTestOver);
                 //System.exit(0);                        
               }      
                
	              if(IsTestOver)
	              {
	            	  break;
	              }
	           }
	           	           
	            if(i==21 && IsTestOver == false)
	            {
	                   System.out.println(" new screen record will open\n");
	                   driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[5]/div/table/tbody/tr/td[2]/table/tbody/tr/td[6]/span")).click();
	                   i=2;
	            }
	            
	            if(IsTestOver)
	            {
	                   break;
	            }      
		                        
	           }    //end of first for loop            
	        }
		    //condition for settle txn =success  or already settle +curent settle< eligible amt
		    
		  @Test(description ="To Test the Transaction Settle in Transaction Search Screen",dependsOnMethods ={"Trans_Search_Cancel"},alwaysRun =true)
		     public static void TransSettle_TransSearch() throws InterruptedException
		     {
		     	//driver.get(GetNewURL(Conf.getProperty("PGUrl"), Conf.getProperty("TransactionSearch")));
			 	String[] arr_elem = {"Transactions", "Transaction Search"};						
		   		Navigate_Menu_From_To_New(driver,arr_elem);
		            
		     	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td"))).isDisplayed();
		            
		            
		     	 //sort out transaction search for txn success and txn type auth through dropdown
		           select_Dropdown("status", "TXN_SUCCESS");
		           select_Dropdown("transType", "AUTH");
		           
		           //click for search
		           select("search");
		           Thread.sleep(6000);
		           
		           //click for minimize search block
		           select_new("//*[@id='collapse']/span");
		           
		        int rowCount=driver.findElements(By.xpath("//table[@id='grid']/tbody/tr")).size();
		        
		        System.out.println("rowCount of upper table generic table path  :"+rowCount);
		        // String td_Title =null;
		        WebElement table =driver.findElement(By.id("grid"));
		        java.util.List<WebElement> ls = table.findElements(By.tagName("tr")); 
		         
		         int  row_count_id = ls.size();
		        System.out.println("row count  of upper table is :   " + row_count_id);   
		        
		        boolean flag =false;
		      
		        int count=1;
		      
		        for(int i=2;i<=row_count_id;i++)
		        { 	                   
		        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td"))).isDisplayed();
		        	driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td")).click();
		        	Thread.sleep(2000);
		           
		        	System.out.println("Record number Searched : " +count++ + "  i :"+ i);
		                          
		        	// for sub table 
		        	WebElement table9 =driver.findElement(By.id("subGrid"));
		           
		           // Now get all the TR elements from the table 
		            java.util.List<WebElement> ls9 = table9.findElements(By.tagName("tr")); 
		     
		            int  row_count9 = ls9.size();
		            System.out.println("row count  for sub table before  "+ row_count9);      
		      
		            boolean title_found =false;
		            
		             //only  it has  to be txn success for settle      
		            if(  row_count9 ==2 )
		            {
		            	title_found  =(driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[7]")).getAttribute("title").equals("TXN_SUCCESS"));                
		            }
		                   
		            Thread.sleep(2000);
		            System.out.println("title_found  :  "+title_found);
		                   
		            if(title_found)
		            {
		                 // here we need to click on lower table:
		                 // iterate the row and find the row that contains TXN_SUCCESS and click in that row
		                 
		                 // Click on first row
		                 driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")).click();
		                 Thread.sleep(1000);
		                 //click settle button
		                 driver.findElement(By.id("SETTLEJQ")).click();
		                 Thread.sleep(1000);
		                 //enter settlement amount
		                 driver.findElement(By.id("settleAmt")).sendKeys("0.5");
		                 // enter a random value
		                 String Settle_Comment = ProcessPGExcelData.getRandonName();
		                 Thread.sleep(1000);
		                 System.out.println("random :   "+Settle_Comment);
		                 driver.findElement(By.id("descripitor")).sendKeys(Settle_Comment+"descripitor");
		                 driver.findElement(By.id("comments")).sendKeys(Settle_Comment);
		                    
		                 Thread.sleep(4000);
		                 
		                 //To save 
		                 driver.findElement(By.xpath("/html/body/div[16]/div[2]/form/div[2]/input")).click();
		                 
		                 //wait untill one more recard after cancel diplayed
		                 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td")));
	                     Thread.sleep(5000);
		                 
		                 //So that  comment flush out properly
		                // Navigate_Menu_From_To_New(driver,arr_elem);
		              //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td"))).isDisplayed();
		                 
		                 WebElement table_af =driver.findElement(By.id("subGrid"));
		                        
		                 // Now get all the TR elements from the table 
		           
		                 java.util.List<WebElement> ls_af = table_af.findElements(By.tagName("tr")); 
		                    
		                 int  row_count_af = ls_af.size();
		                 System.out.println("row count  for sub table  after settle  "+ row_count_af);       
		                   
		                 // boolean err_txt =driver.getPageSource().contains("Settlement Amount + Already Settled Amount cannot be greater than Eligible Amount");
		                 boolean sett_display  = driver.findElement(By.id("SETTLEJQ")).isDisplayed();
		                 boolean text_occur = driver.getPageSource().contains(Settle_Comment); 
		                  Thread.sleep(5000);
		                 System.out.println("text_occur  :  "+text_occur  + " Settle_enable  "+sett_display);
		                 
		                 
		                 //To check  whether row count of sub table before settle < row count of sub table after settle
		                 if((row_count_af>row_count9) || text_occur)
		                 {
		                	 flag =true; 
		                 }                            
		                 else	                                
		                 {	                                
		                	 System.out.println("else part for next search\n");
		                	 // To click cancel
		                	 driver.findElement(By.xpath("/html/body/div[16]/div[2]/form/div[2]/input[2]")).click();
		                	 Thread.sleep(2000);                        
		                 }	                               	                         
		               }
		                   
		               //To check  last record in upper table at first screen
		               if(i==21)
		               {
		            	   System.out.println(" new screen record will open\n");
		                   driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[5]/div/table/tbody/tr/td[2]/table/tbody/tr/td[6]/span")).click();
		                   i=1;
		               }
		               	                   
		               //To check  last record in upper table at last screen
		               if(flag==true ||count==499) 
		               {
		                     break;
		               }
		        }
		         
		        // To Check the Result
		        boolean settle_success = driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td[7]")).getAttribute("title").equals("SETTLEMENT_SUCCESS");  
		        		
		        		 
		        Thread.sleep(4000);
		        System.out.println("atlast pagesource contain   SETTLEMENT_SUCCESS" +settle_success);
		        System.out.println("flag  :"+flag);
		        boolean settle_succ =driver.getPageSource().contains("SETTLEMENT_SUCCESS");
		        System.out.println("getPageSource().containsb  settle_succ  "+ settle_succ);
		        Assert.assertTrue( settle_success || settle_succ);
		        
		        
		     }   
	   
		    
		    
		     //always run=true
		    //condition for refund : txn =success,SETTLEMENT_SUCCESS	
		  @Test(description ="To test the Refund Transaction functionality for a Transaction in Transaction Search screen",dependsOnMethods ={"TransSettle_TransSearch"},alwaysRun =true)
		     public static void TransRefund() throws InterruptedException
		     {
		           // driver.get(GetNewURL(Conf.getProperty("PGUrl"), Conf.getProperty("TransactionSearch")));
				 	String[] arr_elem = {"Transactions", "Transaction Search"};						
			   		Navigate_Menu_From_To_New(driver,arr_elem);
		            

		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td"))).isDisplayed();
		            
		            
		            //sort out transaction search for txn success and txn type auth through dropdown
			           select_Dropdown("status", "TXN_SUCCESS");
			           select_Dropdown("transType", "AUTH");
			           
			           //click for search
			           select("search");
			           Thread.sleep(6000);
			           
			           //click for minimize search block
			           select_new("//*[@id='collapse']/span");
		            
		            
		            int rowCount=driver.findElements(By.xpath("//table[@id='grid']/tbody/tr")).size();
		            
		            System.out.println("rowCount of upper table generic table path  :"+rowCount);
		            // String td_Title =null;
		            WebElement table =driver.findElement(By.id("grid"));
		            java.util.List<WebElement> ls = table.findElements(By.tagName("tr")); 
		             
		             int  row_count_id = ls.size();
		            System.out.println("row count  of upper table is :   " + row_count_id);   
		            
		            boolean flag =false;
		          
		             int count=1;
		          
		            for(int i=20;i<=row_count_id;i++)
		            { 
		                   
		            
		                   boolean Bn_pg_before_click = driver.getPageSource().contains("SETTLEMENT_SUCCESS"); 
		                   
		                   boolean Bn_pg_sett_rais_fail = (driver.getPageSource().contains("SETTLEMENT_RAISED")||driver.getPageSource().contains("SETTLEMENT_FAILED")); 
		                   
		                   int count_sett_before_click = driver.getPageSource().split("SETTLEMENT_SUCCESS").length-1;
		                   System.out.println("Is Settle_Success exis on page before click on page : " +Bn_pg_before_click  + " and count is :" +  count_sett_before_click);  // 4
		                   
		                   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td"))).isDisplayed();
		                   driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td")).click();
		                   Thread.sleep(2000);
		                   // JavascriptExecutor jse = (JavascriptExecutor)driver;
		                   //jse.executeScript("window.scrollBy(0,250)", "");
		                   
		                   System.out.println("Record number Searched :  " +count++ +"  i   :"+ i);
		                   
		                   
		                   // for sub table 
		                   WebElement table9 =driver.findElement(By.id("subGrid"));
		                   
		                   // Now get all the TR elements from the table 
		            
		                    java.util.List<WebElement> ls9 = table9.findElements(By.tagName("tr")); 
		             
		                    int  row_count9 = ls9.size();
		                   System.out.println("row count  for sub table  "+ row_count9);       
		              
		                    boolean title_found =false;
		                   
		                    //more than one 
		                    if( row_count9 !=2)
		                   {
		                   title_found =driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td[7]")).getAttribute("title").equals("SETTLEMENT_SUCCESS");
		                   }
		                     
		                   
		                   Thread.sleep(2000);
		                   System.out.println("title_found  :  "+title_found);
		                   
		                   if(title_found)
		                   {
		                         // here we need to click on lower table:
		                         // iterate the row and find the row that contains SETTLE_SUCCESS and click in that row
		                         // Click on Refund button
		                         System.out.println("We got the text and Assert true");
		                         Thread.sleep(2000);
		                          driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]")).click();
		                         
		                         Thread.sleep(1000);
		                         driver.findElement(By.id("REFUNDJQ")).click();
		                         
		                         Thread.sleep(1000);
		                   
		                         driver.findElement(By.id("refundAmt")).sendKeys("1");
			                         // enter a random value
			                     String Refund_Comment = ProcessPGExcelData.getRandonName();
			                   
			                     System.out.println("random :   "+Refund_Comment);
			                   //driver.findElement(By.id("comments")).sendKeys(Refund_Comment);
			                      driver.findElement(By.xpath("/html/body/div[17]/div[2]/form/table/tbody/tr[7]/td[2]/textarea")).sendKeys(Refund_Comment);
			                    
			                     Thread.sleep(4000);
		                         //To save 
		                          driver.findElement(By.xpath("html/body/div[17]/div[2]/form/div[2]/input[1]")).click();
		                          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td")));
		                           Thread.sleep(2000);
		                          //after successful refund one row should increase either by refund success or fail 
		          	              WebElement table10 =driver.findElement(By.id("subGrid"));
		                          
		                          java.util.List<WebElement> ls10 = table10.findElements(By.tagName("tr")); 
		                          
		                          int  row_count10 = ls10.size();
		                         
		                          boolean reund_success  =driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr["+row_count10+"]/td[7]")).getText().equalsIgnoreCase("REFUND_SUCCESS");
		                          //getAttribute("title").equals("REFUND_SUCCESS");
		                          Thread.sleep(3000);
		                        
		                          String lastrowText =driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr["+row_count10 +"]/td[9]")).getText();
		                          System.out.println("row count  for sub table   after refund  "+ row_count10 +"  reund_success  "+reund_success  +"  lastrowText  "+lastrowText);       
		         	                   
		                          
		                          
		                          //Thread.sleep(10000);
		                         boolean cancelDisplay;
		                         cancelDisplay =driver.findElement(By.id("CANCELJQ")).isDisplayed();
		                         
		                          Thread.sleep(3000);
		                         System.out.println("cancelDisplay:  "+cancelDisplay);
		                         Thread.sleep(1000);
		                         
		                          boolean text_occur =driver.getPageSource().contains(Refund_Comment);
		                         // text_occur = !(driver.getPageSource().contains("REFUND_FAILED"));
		                          System.out.println("REFUND_FAILED  is :"+ !text_occur);
		                          
		                         Thread.sleep(2000);
		                         System.out.println("text_occur  :"+text_occur);
		                         //if(text_occur && (!Bn_pg_sett_rais_fail) )
		                         if(text_occur)
		                         {
		                        	 
		                        	 if(reund_success)
		                        	 {
		                        		 flag =true;  
		                        	 }
		                        	 
		                        	 else
		                        	 {
		                        		 break;
		                        	 }
		                                
		                          }
		                                
		                          else
		                                
		                         {
		                                
		                                
		                                 System.out.println("else part for next search\n");
		                              
		                                 Thread.sleep(2000);
		                                
		                                //cancel	
		                                driver.findElement(By.xpath("/html/body/div[17]/div[2]/form/div[2]/input[2]")).click();
		                                Thread.sleep(2000);
		                                i++;
		                                //click for next record in upper table
		                                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td"))).isDisplayed();
		                                
		                                 driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[3]/div[3]/div/table/tbody/tr["+i+"]/td")).click();
		                                
		                                 
		                          }
		                                
		                         
		                   }
		                   
		                   if(i==21 && flag==false)
		                   {
		                         System.out.println(" new screen record will open\n");
		                          driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div/div[5]/div/table/tbody/tr/td[2]/table/tbody/tr/td[6]/span")).click();
		                         i=2;
		                   }
		                   
		                   
		                   
		                   if(flag==true ||count==499)
		                         break;
		            }
		         
		           
	               
		           // boolean reund_success = driver.getPageSource().contains("REFUND_SUCCESS");
		            Thread.sleep(2000);
		         //   System.out.println("atlast pagesource contain   title_found =driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[3]/td[7]")).getAttribute("title").equals("SETTLEMENT_SUCCESS");" +reund_success);
		            System.out.println("flag  :"+flag);
		            
		            Assert.assertTrue(flag  ); 
		            

		     } 
		
		  
		//Condition for cancel: txn should be only success and only single row should occur
			
			 @Test(description = "To test the accessibility of the system for User as per the role assigned to it",dependsOnMethods = {"TransRefund","Test_AddUsers"}) 
			   public void Test_Accessibilty_System_Asper_Role() throws InvalidFormatException, IOException, InterruptedException   
				{
				
			 
				System.out.println(" \n"+"  huser_disposal  : "+huser_disposal+ "   reset_pass  "+reset_pass);
				
				if(huser_disposal==null )
				{
					System.out.println("huser_disposal  is null");
					Assert.assertTrue(false);
				}
				ReLogin(huser_disposal, reset_pass);
			 
			
			// wait for page load complete 
			   Thread.sleep(5000);
			
			   if(driver.getPageSource().contains("Virtual Terminal"))
			   	{
			   		Assert.assertTrue(true);
			   	}else
			   	{
			   		Assert.assertTrue(false);
			   	}
			  	
			   	//driver.quit();
			
			  	}
			
			   
		 
		      @Test(description = "To test the Virtual Transaction of the merchant user who has the right of Virtual transacrtion menu",dependsOnMethods ={"Test_Accessibilty_System_Asper_Role","Test_AddUsers"} ) 
			 	public void Test_Virtual_Transaction() throws InvalidFormatException, IOException, InterruptedException  
			 	{
			 	

				    // find the Username from excel sheet.
				 	// find the password from mail
				 	// Login to Portal
				 	// Check for Virtual Terminal menu item
				 	// if it is there then test pass otherwise fail.
				 	
				    System.out.println(" from Test_Virtual_Transaction Relogin  with  huser_disposal  "+huser_disposal+"  reset_pass   " +reset_pass);
				 	
				    if(huser_disposal==null )
					{
						System.out.println("huser_disposal");
						Assert.assertTrue(false);
					}
				    
				 	ReLogin(huser_disposal, reset_pass );
				 	
				  
				 	 
				 	// wait for page load complete 
				 	Thread.sleep(5000);
				
				 	if(driver.getPageSource().contains("Virtual Terminal") == false)  
				 	{
				 		// means that the user does not have Right of the Virtual Transaction menu and test case fails
				 		Assert.assertTrue(false);
				 	}
				 	
				 			 
				 	
					// Goto Transactions -> Virtual Terminal
				 	String[] arr_elem = {"Transactions", "Virtual Terminal"};						
			   		Navigate_Menu_From_To_New(driver,arr_elem);
			        
			   		
			   		// wait for some time to load the Virtual Transaction Page
			   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentMethod"))).isDisplayed();
			   		
			   		
			   		// enter the Amount in the amount field
			   		driver.findElement(By.id("txnAmount")).clear();
			   		driver.findElement(By.id("txnAmount")).sendKeys("200");
			   		
			   		// select the currency type from currency drio down
			   		WebElement Curr_DD = driver.findElement(By.id("currencyType"));
			   		Select cur_sel = new Select(Curr_DD);
			   		
			   		cur_sel.selectByIndex(1);
			   		
			   		
			   		// enter the order ref in order red textbox
			   		driver.findElement(By.id("orderRef")).clear();
			   		driver.findElement(By.id("orderRef")).sendKeys("order07051402");
			   		
			   		// select the Credit card type from drop down
			   		WebElement Curtype_DD = driver.findElement(By.id("cCType"));
			   		Select curtype_sel = new Select(Curtype_DD);
			   		
			   		System.out.println(" selected string : "+curtype_sel.getFirstSelectedOption().getText());
			   		
			   		curtype_sel.selectByIndex(1);
			   		
			   		// enter the credit card number in credit card textbox
			   		driver.findElement(By.id("cardNo")).clear();
			   		driver.findElement(By.id("cardNo")).sendKeys("5453010000083683");
			   		
			   		
			   		// select the card expiry month
			  		WebElement Expiry_month_DD = driver.findElement(By.id("expDateMonth"));
			   		Select Exp_month_sel = new Select(Expiry_month_DD);
			   		
			   		Exp_month_sel.selectByIndex(1);
			   		
			   		
			   		// select the card expiry year
			  		WebElement Expiry_year_DD = driver.findElement(By.id("expDateYear"));
			   		Select Exp_year_sel = new Select(Expiry_year_DD);
			   		
			   		Exp_year_sel.selectByIndex(2);
			   		
			   		
			   		// enter the name on card
			   		
			   		driver.findElement(By.id("cardHolderName")).clear();
			   		driver.findElement(By.id("cardHolderName")).sendKeys("ankur");
			   		
			   		// enter the security code
			   		driver.findElement(By.id("secCode")).clear();
			   		driver.findElement(By.id("secCode")).sendKeys("176");
			   		
			   		
			   		// click on the save button
			   		driver.findElement(By.id("save")).click();
			   		
			   		// wait for Response page to load
			   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("makeAnotherTransaction"))).isDisplayed();
			   		
			   		if(driver.getPageSource().contains("1000"))
			   		{
			   			// pass
			   			Assert.assertTrue(true);
			   			
			   		}
			   		else
			   		{
			   			System.out.println("Test_Virtual_Transaction() -> Response is not success");
			   			// fail
			   			Assert.assertTrue(false);
			   		}
			   		
				 	
				 	// enter the details in the required fields and click on process button
				 	
				 	// check the status

			 	}
	
		
		      @Test(description ="To Test the Add Block functionality for card Number" ,dependsOnMethods ={"Test_Virtual_Transaction"} ,alwaysRun =true)  // Portal one done, but to check transaction need to check
		      public static void Test_AddBlock_cardNumber() throws InterruptedException, InvalidFormatException, IOException
		      {
		         String cur_date ="";
		        
		         
		         login();
		         
		      	// Navigate to Configuration ->  System Configuration -> Manage Blocks
		      	String[] arr_elem = {"Configuration", "System Configuration", "Manage Blocks"};						
		      		Navigate_Menu_From_To_New(driver,arr_elem);
		      		
		      		
		      		//At first make INACTIVE (this is why ,if bottom of this function code unable to make INACTIVE)
		      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]"))).isDisplayed();
		      		  if(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).isDisplayed())
		      				
		      				  if(
		      						  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).getText().contains("BLACK")&&
		      						  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText().contains("3699")
		      				     )                                  
		      				  {
		      					 System.out.println("block type "+driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).getText()); 
		      				     System.out.println("card last 4 digit "+ driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText());
		      				      
		      					 driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).click();
		      			 
		      					Thread.sleep(2000);
		      					
		      					//click for Edit
		      					driver.findElement(By.id("EditJQ")).click();
		      					Thread.sleep(2000);
		      					
		      					//Make inactive from dropdown
		      					select_Dropdown("editstatus", "INACTIVE");
		      					
		      					//click for save
		      					driver.findElement(By.xpath("//*[@id='editBlockForm']/div/input[1]")).click();
		      					
		      					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]"))).isDisplayed();
		      					if( !driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText().contains("3699"))
		      					{
		      						System.out.println("inactivated successfully from begining");
		      						//driver.quit();
		      					} 
		      				  
		      				  }
		      		   
		      		   //Scroll window down
		      			JavascriptExecutor jse = (JavascriptExecutor)driver;
		      			jse.executeScript("window.scrollBy(0,250)", "");
		      			Thread.sleep(1000);
		      		
		      		 
		      		
		         
		      		// wait until the "Add" button is displayed in the screen
		      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		      		
		      		// click on "Add" button
		      		//select("AddJQ");   somtimes not worked
		      		driver.findElement(By.id("AddJQ")).click();
		      		
		      		// wait for 2 seconds
		      		Thread.sleep(2000);
		      		
		      		// select "Black" option  from Block Category drop down
		      		input("addBlockCategory", "BLACK");
		      		
		      		// select "Card Number" option  from Block Type drop down
		      		input("addBlockType", "Card Number");

		      		// enter 100 value in value text box
		      		String Random_CardNum = ProcessPGExcelData.getRandonValues("cardnumber");	
		      		//driver.findElement(By.id("addvalue")).sendKeys(Random_CardNum);
		      		driver.findElement(By.id("addvalue")).sendKeys("5210280656593699");
		      		
		      		
		      			   			   		
		      		
		      		// select from date
		      		select("addStartDate");
		      		Thread.sleep(2000);
		      		
		      		// from date should be today's date
		      		cur_date = Get_Current_Date();
		      		System.out.println("The cuurrent data calculated from java code is : " + cur_date);
		      		
		      		select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		      			   
		      		
		      		// select To date	   		
		      		select("addEndDate");
		      		Thread.sleep(2000);
		      		
		      		// select next year from year drop down	   		
		      		WebElement WE_Todate = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker div.ui-datepicker-header div.ui-datepicker-title select.ui-datepicker-year"));	   			 
		      		Select dd_todate = new Select(WE_Todate);
		      		dd_todate.selectByIndex(1); // always select the value from to date that is in index 1, means 1 year ahead
		      		
		      		// find the table that contains the dates
		      		select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		      		
		      		
		      	
		      		// Get Random comments
		      		String comments = ProcessPGExcelData.getRandonName();
		      		
		      		// enter  Random comment in comment text  field
		      		driver.findElement(By.id("addcomments")).sendKeys(comments);
		      		
		      		Thread.sleep(2000);
		      		
		      		// click on Save button	   		
		      		driver.findElement(By.cssSelector("html body div#addBlock.popup_block div.message form#addBlockForm div.sep input.button")).click();
		      		
		      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]"))).isDisplayed();
		      		Thread.sleep(2000);
		      		//getting if page source contain submitted comment
		      		boolean is_commentExist = driver.getPageSource().contains(comments);
		      		
		    		Thread.sleep(4000);

		      		//getting if page source contain submitted cc card number
		      		boolean last_f4_digit_again =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText().contains("3699");
		      		
		      		
		      		// Checking the Result, we will check whether the comments added in block is displaying or not.
		      		System.out.println("is_commentExist :"+is_commentExist+ " last_f4_digit_again: "+last_f4_digit_again);
		      		if(!is_commentExist && !last_f4_digit_again)
		     	 	{
		     	 		System.out.println("Test_AddBlock_cardNumber() -> Error in getting the comments in page");
		     	 		Assert.assertTrue(false);
		     	 	}
		     	 		

		      	
		      	  	//************** open the PG Paypage Transaction Page**********************
		      	WebDriver driver_paypage = new FirefoxDriver();
		      	driver_paypage.manage().window().maximize();
		      	
		      	driver_paypage.get(Conf.getProperty("PG_Paypage_URL"));
		      	
		      	Thread.sleep(3000);
		      	// wait for page to load properly
		      	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();
		      	
		      	// Input value in Order ID field, value should be random
		      	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
		      	driver_paypage.findElement(By.id("OrderID")).clear();
		      	driver_paypage.findElement(By.id("OrderID")).sendKeys(Random_Order_ID);
		      	
		      	//input("OrderID", Random_Order_ID);
		      	
		      	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		      	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 0);
		      	driver_paypage.findElement(By.id("MerchantID")).clear();
		      	driver_paypage.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
		      	
		      	//input("MerchantID", Merchant_ID);
		      	
		      	// input the IP Address to check for block
		      	//driver_paypage.findElement(By.id("SOURCE_IP")).clear();
		      	//driver_paypage.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
		      	//input("SOURCE_IP", Random_IP);
		      	
		      	
		      	// select No to Is 3D Secure drop down box			
		      	Select dropdown = new Select(driver_paypage.findElement(By.id("Is3DSecure")));	
		      	dropdown.selectByVisibleText("NO");
		      	
		      	//select_Dropdown("Is3DSecure","NO");
		      	
		      	// input API user ID in text box
		      	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 1);
		      	driver_paypage.findElement(By.id("UserId")).clear();
		      	driver_paypage.findElement(By.id("UserId")).sendKeys(API_USerID);
		      	//input("UserId", API_USerID);
		      	
		      	
		      	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
		      	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
		      	driver_paypage.findElement(By.id("DateAndTime")).clear();
		      	driver_paypage.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
		      	//input("DateAndTime", CurDateTime);
		      	
		      	
		      	// Click on CheckOut button			
		      	driver_paypage.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
		      	
		      	
		      	// now credit card page loads****************************************
		      	Thread.sleep(3000);
		      	
		      	boolean have_XPath =false;
		      		
		      	if(driver_paypage.findElements(By.xpath("/html/body/form/div/div/div/div/font/div/table/tbody/tr[2]/td/input[2]")).size() > 0)
		      	{
		      		System.out.println("Got xpath");
		      		driver_paypage.findElement(By.xpath("/html/body/form/div/div/div/div/font/div/table/tbody/tr[2]/td/input[2]")).click();
		      		have_XPath = true;
		      	}
		      	
		      	if(!have_XPath)
		      	{
		      		System.out.println("Got csspath");
		      		driver_paypage.findElement(By.cssSelector("html body form#creditcard div#parentDiv.container_12 div.grid_12 div#hd2.box div.boxin font div#payPage table tbody tr td input#cardType")).click();
		      	}
		      	
		      	
		      	// input card number
		      	driver_paypage.findElement(By.id("cardNumber")).clear();
		      	//driver_paypage.findElement(By.id("cardNumber")).sendKeys(Random_CardNum); // note: Random card does not work here as we need correct MasterCard Number
		      	driver_paypage.findElement(By.id("cardNumber")).sendKeys("5210280656593699");
		      	
		      	// input cardholder name     	
		      	driver_paypage.findElement(By.id("cardHolderName")).clear();
		      	driver_paypage.findElement(By.id("cardHolderName")).sendKeys("haris");
		      	
		      	// select Card Expirt Month from drop down
		      	WebElement WE = driver_paypage.findElement(By.id("expiryDateMonth"));	   			 
		      	Select dd_tod = new Select(WE);
		      	dd_tod.selectByIndex(1); // 
		      	
		      	// select Card Expirt Year from drop down
		      	WebElement WE1 = driver_paypage.findElement(By.id("expiryDateYear"));	   			 
		      	Select dd_tod1 = new Select(WE1);
		      	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
		      	
		      	// input CVV Number
		        	driver_paypage.findElement(By.id("cvvNumber")).clear();
		      	driver_paypage.findElement(By.id("cvvNumber")).sendKeys("222");
		      	
		      	Thread.sleep(2000);
		      	
		      	// click on Submit button
		      	driver_paypage.findElement(By.id("save")).click();
		      	
		      	// wait to Response page to load		
		      	Thread.sleep(4000);
		      	
		      	// check the Response
		      	boolean res_code_block = driver_paypage.getPageSource().contains("1058");
		      	boolean res_Text_IP = driver_paypage.getPageSource().contains("negative");
		      	//boolean res_Text_IP = true;
		      	
		      	System.out.println(" res_code_block : " + res_code_block + " and res_Text : " + res_Text_IP);
		      	
		      	//close page here
		      	driver_paypage.quit();
		      	if(res_code_block == true && res_Text_IP == true)
		      	{
		      		System.out.println("The Response contains both the block code and text");
		      		Assert.assertTrue(true);			
		      	}
		      	else
		      	{
		      		System.out.println("Either the Response or the text is missing in Response Text");
		      		Assert.assertTrue(false);
		      	}
		      	
		      	//At last make INACTIVE
		      	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]"))).isDisplayed();
		     	  if(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).isDisplayed())
		     			
		     			  if(
		     					  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).getText().contains("BLACK")&&
		     					  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText().contains("3699")
		     			     )                                  
		     			  {
		     				 System.out.println("block type "+driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).getText()); 
		     			     System.out.println("card last 4 digit "+ driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText());
		     			      
		     				 driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]")).click();
		     		 
		     				Thread.sleep(2000);
		     				
		     				//click for Edit
		     				driver.findElement(By.id("EditJQ")).click();
		     				Thread.sleep(2000);
		     				
		     				//Make inactive from dropdown
		     				select_Dropdown("editstatus", "INACTIVE");
		     				
		     				//click for save
		     				driver.findElement(By.xpath("//*[@id='editBlockForm']/div/input[1]")).click();
		     				
		     				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]"))).isDisplayed();
		     				if( !driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/fieldset/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]")).getText().contains("3699"))
		     				{
		     					System.out.println("inactivated successfully from begining");
		     					//driver.quit();
		     				} 
		     			  
		     			  }
		     	   
		      		
		      }
		      
		      
		     
		      
		      @Test(description ="To Test the Add Block functionality for IP Address",dependsOnMethods ={"Test_AddBlock_cardNumber"},alwaysRun =true )  // Portal one done, but to check transaction need to check
		      public static void Test_AddBlock_IP_Address() throws InterruptedException, InvalidFormatException, IOException
		      {
		         String cur_date ="";
		         
		      	// Navigate to Configuration ->  System Configuration -> Manage Blocks
		      	String[] arr_elem = {"Configuration", "System Configuration", "Manage Blocks"};						
		      		Navigate_Menu_From_To_New(driver,arr_elem);
		         
		      		// wait until the "Add" button is displayed in the screen
		      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		      		
		      		// click on "Add" button
		      		select("AddJQ");
		      		
		      		// wait for 2 seconds
		      		Thread.sleep(2000);
		      		
		      		// select "Black" option  from Block Category drop down
		      		input("addBlockCategory", "BLACK");
		      		
		      		// select "Card Number" option  from Block Type drop down
		      		input("addBlockType", "IP Address");

		      		// enter Random IP Address value in value text box
		      		String Random_IP = ProcessPGExcelData.getRandonValues("IPAddress");
		      		driver.findElement(By.id("addvalue")).sendKeys(Random_IP);
		      		
		      			   			   		
		      		
		      		// select from date
		      		select("addStartDate");
		      		Thread.sleep(2000);
		      		
		      		// from date should be today's date
		      		cur_date = Get_Current_Date();
		      		System.out.println("The cuurrent data calculated from java code is : " + cur_date);
		      		
		      		select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		      			   
		      		
		      		// select To date	   		
		      		select("addEndDate");
		      		Thread.sleep(2000);
		      		
		      		// select next year from year drop down	   		
		      		WebElement WE_Todate = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker div.ui-datepicker-header div.ui-datepicker-title select.ui-datepicker-year"));	   			 
		      		Select dd_todate = new Select(WE_Todate);
		      		dd_todate.selectByIndex(1); // always select the value from to date that is in index 1, means 1 year ahead
		      		
		      		// find the table that contains the dates
		      		select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		      		
		      		
		      	
		      		// Get Random comments
		      		String comments = ProcessPGExcelData.getRandonName();
		      		System.out.println("Test_AddBlock_IP_Address() -> The dynamic comments is :" + comments);
		      		// enter  Random comment in comment text  field
		      		driver.findElement(By.id("addcomments")).sendKeys(comments);
		      		
		      		Thread.sleep(2000);
		      		
		      		// click on Save button	   		
		      		driver.findElement(By.cssSelector("html body div#addBlock.popup_block div.message form#addBlockForm div.sep input.button")).click();
		      			   		
		      		
		      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		      		//comment should flush out					
		      		Navigate_Menu_From_To_New(driver,arr_elem);
		         
		      		// wait until the "Add" button is displayed in the screen
		      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		      		
		      		Thread.sleep(10000);
		      		
		      		//To search for enter comment/Ip exust
		      		boolean is_commentExist = driver.getPageSource().contains(comments);
		      		boolean is_randomIpExist = driver.getPageSource().contains(Random_IP);
		      		
		      		System.out.println("is_commentExist   :"+is_commentExist +"   is_randomIpExist:  "+is_randomIpExist);
		      		
		      		if(!is_commentExist || !is_randomIpExist)
		      		{
		      			System.out.println("Test_AddBlock_IP_Address() -> Error in getting the comments in page");
		      			Assert.assertTrue(false);
		      		}
		      			
		      		
		      		// Checking the Result, we will check whether the comments added in block is displaying or not.
		      		//Assert.assertTrue(is_commentExist);
		      		
		      		
		      		// Now after adding Block in Portal, we need to check this block in Paypage Transaction, the same IP address should be blocked

		      	   
		      	// open the PG Paypage Transaction Page
		      	WebDriver driver_paypage = new FirefoxDriver();
		      	driver_paypage.manage().window().maximize();
		      	
		      	driver_paypage.get(Conf.getProperty("PG_Paypage_URL"));
		      	
		      	Thread.sleep(3000);
		      	// wait for page to load properly
		      	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();
		      	
		      	// Input value in Order ID field, value should be random
		      	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
		      	driver_paypage.findElement(By.id("OrderID")).clear();
		      	driver_paypage.findElement(By.id("OrderID")).sendKeys(Random_Order_ID);
		      	
		      	//input("OrderID", Random_Order_ID);
		      	
		      	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		      	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 0);
		      	driver_paypage.findElement(By.id("MerchantID")).clear();
		      	driver_paypage.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
		      	
		      	//input("MerchantID", Merchant_ID);
		      	
		      	// input the IP Address to check for block
		      	driver_paypage.findElement(By.id("SOURCE_IP")).clear();
		      	driver_paypage.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
		      	//input("SOURCE_IP", Random_IP);
		      	
		      	
		      	// select No to Is 3D Secure drop down box			
		      	Select dropdown = new Select(driver_paypage.findElement(By.id("Is3DSecure")));	
		      	dropdown.selectByVisibleText("NO");
		      	
		      	//select_Dropdown("Is3DSecure","NO");
		      	
		      	// input API user ID in text box
		      	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 1);
		      	driver_paypage.findElement(By.id("UserId")).clear();
		      	driver_paypage.findElement(By.id("UserId")).sendKeys(API_USerID);
		      	//input("UserId", API_USerID);
		      	
		      	
		      	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
		      	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
		      	driver_paypage.findElement(By.id("DateAndTime")).clear();
		      	driver_paypage.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
		      	//input("DateAndTime", CurDateTime);
		      	
		      	
		      	// Click on CheckOut button			
		      	driver_paypage.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
		      		
		      	
		      	// wait to Response page to load		
		      	Thread.sleep(4000);
		      	
		      	// check the Response
		      	boolean res_code_block = driver_paypage.getPageSource().contains("1058");
		      	boolean res_Text_IP = driver_paypage.getPageSource().contains("negative");
		      	
		      	System.out.println("Test_AddBlock_IP_Address() -> res_code_block : " + res_code_block + " and res_Text_IP : " + res_Text_IP);
		      	driver_paypage.quit();
		      	if(res_code_block == true && res_Text_IP == true)
		      	{
		      		System.out.println(" The Response contains both the block code and text");
		      		Assert.assertTrue(true);			
		      	}
		      	else
		      	{
		      		System.out.println(" Either the Response or the IP text is missing in Response Text");
		      		Assert.assertTrue(false);
		      	}
		      		     		     		
		      }
		      
		      

		     @Test(description ="To Test the Add Block functionality for EMAIL ID",dependsOnMethods ={"Test_AddBlock_IP_Address"},alwaysRun =true)  // Portal one done, but to check transaction need to check
		     public static void Test_AddBlock_MailId() throws InterruptedException, InvalidFormatException, IOException
		          {
		     	 		String cur_date ="";
		                
		     	 		// Navigate to Configuration ->  System Configuration -> Manage Blocks
		                  String[] arr_elem = {"Configuration", "System Configuration", "Manage Blocks"};                                     
		                   Navigate_Menu_From_To_New(driver,arr_elem);
		             
		                   // wait until the "Add" button is displayed in the screen
		                   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		                   
		                   // click on "Add" button
		                   select("AddJQ");
		                   
		                   // wait for 2 seconds
		                   Thread.sleep(2000);
		                   
		                   // select "Black" option  from Block Category drop down
		                   input("addBlockCategory", "BLACK");
		                   
		                   // select "Card Number" option  from Block Type drop down
		                  // input("addBlockType", "Card Number");
		                   
		                   //select USERID option from Block Type drop down
		                   input("addBlockType", "EMAIL ID");

		                   // enter 100 value in value text box
		                   
		                  
		                   //String Random_UserID = "black@" + ProcessPGExcelData.getRandonValues("cardnumber")+"ymail.com";
		                   String Random_MailID = "black" +  ProcessPGExcelData.getRandonName()+"@ymail.com";
		                   
		                   driver.findElement(By.id("addvalue")).sendKeys(Random_MailID);
		                   
		                    System.out.println("Random_MailID  :" +Random_MailID); 
		                    
		                    //for customerID
		                    String Random_UserID = "User" +  ProcessPGExcelData.getRandonName();
		                   
		                   // select from date
		                   select("addStartDate");
		                   Thread.sleep(2000);
		                   
		                   // from date should be today's date
		                   cur_date = Get_Current_Date();
		                   System.out.println("The cuurrent data calculated from java code is : " + cur_date);
		                   
		                   select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		                             
		                   
		                   // select To date                  
		                   select("addEndDate");
		                   Thread.sleep(2000);
		                   
		                   // select next year from year drop down                
		                   WebElement WE_Todate = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker div.ui-datepicker-header div.ui-datepicker-title select.ui-datepicker-year"));                             
		                   Select dd_todate = new Select(WE_Todate);
		                   dd_todate.selectByIndex(1); // always select the value from to date that is in index 1, means 1 year ahead
		                   
		                   // find the table that contains the dates
		                   select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		                   
		                   
		            
		                   // Get Random comments
		                   String comments = ProcessPGExcelData.getRandonName();
		                   
		                   // enter  Random comment in comment text  field
		                   driver.findElement(By.id("addcomments")).sendKeys(comments);
		                   
		                   Thread.sleep(2000);
		                   
		                   // click on Save button                  
		                   driver.findElement(By.cssSelector("html body div#addBlock.popup_block div.message form#addBlockForm div.sep input.button")).click();
		                                        
		                   Thread.sleep(10000);
		                   
		                   boolean is_commentExist = driver.getPageSource().contains(comments);
		                   boolean is_randomUserExist = driver.getPageSource().contains(Random_UserID);
		                   
		                   
		                   // Checking the Result, we will check whether the comments added in block is displaying or not.
		                  // Assert.assertTrue(is_commentExist);
		                   
		               
		          		
		          		if(!is_commentExist || !is_randomUserExist)
		          		{
		          			System.out.println("Test_AddBlock_MAILID() -> Error in getting the comments in page");
		          			
		          			try{
		          				Assert.assertTrue(false);
		          			}
		          			catch(Throwable th)
		          			{
		          				System.out.println("here AddBlock_MAILID()  exception handle");
		          			}
		          		}
		          			
		          		
		          		
		          		
		          		// Now after adding Block in Portal, we need to check this block in Paypage Transaction, the same IP address should be blocked

		          	   
		     	     	// open the PG Paypage Transaction Page
		     	     	WebDriver driver_paypage = new FirefoxDriver();
		     	     	driver_paypage.manage().window().maximize();
		     	     	
		     	     	driver_paypage.get(Conf.getProperty("PG_Paypage_URL"));
		     	     	
		     	     	Thread.sleep(6000);
		     	     	// wait for page to load properly
		     	     	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();
		     	     	
		     	     	// Input value in Order ID field, value should be random
		     	     	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
		     	     	driver_paypage.findElement(By.id("OrderID")).clear();
		     	     	driver_paypage.findElement(By.id("OrderID")).sendKeys(Random_Order_ID);
		     	     	
		     	     	//input("OrderID", Random_Order_ID);
		     	     	
		     	     	
		     	     	// input the CustID  to check for block
		     	     	driver_paypage.findElement(By.id("CustomerID")).clear();
		     	     	driver_paypage.findElement(By.id("CustomerID")).sendKeys(Random_UserID);
		     	     	//input("SOURCE_IP", Random_IP);
		     	     	
		     	     	
		     	     	
		     	     	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		     	     	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 0);
		     	     	driver_paypage.findElement(By.id("MerchantID")).clear();
		     	     	driver_paypage.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
		     	     	
		     	     	//input("MerchantID", Merchant_ID);
		          	
		     	      	//Edited by hari on 13AUG-14,here code for taking input email is not given
		      	     	driver_paypage.findElement(By.id("EmailID")).clear();
		      	     	driver_paypage.findElement(By.id("EmailID")).sendKeys(Random_MailID);
		          	
		          	
		     	     	// select No to Is 3D Secure drop down box			
		     	     	Select dropdown = new Select(driver_paypage.findElement(By.id("Is3DSecure")));	
		     	     	dropdown.selectByVisibleText("NO");
		     	     	
		     	     	//select_Dropdown("Is3DSecure","NO");
		     	     	
		     	     	// input API user ID in text box
		     	     	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 1);
		     	     	driver_paypage.findElement(By.id("UserId")).clear();
		     	     	driver_paypage.findElement(By.id("UserId")).sendKeys(API_USerID);
		     	     	//input("UserId", API_USerID);
		     	     	
		     	     	
		     	     	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
		     	     	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
		     	     	driver_paypage.findElement(By.id("DateAndTime")).clear();
		     	     	driver_paypage.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
		     	     	//input("DateAndTime", CurDateTime);
		     	     	
		     	     	
		     	     	// Click on CheckOut button			
		     	     	Thread.sleep(1000);
		     	     	driver_paypage.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
		     	     		
		     	     	
		     	     	Thread.sleep(3000);
		     	     	
		     	     	// check the Response
		     	     	boolean res_code_block = driver_paypage.getPageSource().contains("1058");
		     	     	//boolean res_Text_IP = driver_paypage.getPageSource().contains("IP");
		     	     	 
		     	     	
		     	     	System.out.println("  res_code_block : " + res_code_block );
		     	     	driver_paypage.quit();
		     	     	//if(res_code_block == true && res_Text_IP == true)
		     	     	if(res_code_block)
		     	     	{
		     	     		System.out.println("  The Response contains both the block code   for userId i.e. 1058  ");
		     	     		Assert.assertTrue(true);			
		     	     	}
		     	     	else
		     	     	{
		     	     		System.out.println("Test_AddBlock_MailId() -> Either the Response or the IP text is missing in Response Text");
		     	     		Assert.assertTrue(false);
		     	     	}
		                   	               
		                      
		      }
		      
		   
		     @Test(description ="To Test the Add Block functionality for MOBILE NUMBE",dependsOnMethods ={"Test_AddBlock_MailId"},alwaysRun =true)  // Portal one done, but to check transaction need to check
		     public static void Test_AddBlock_MobileNumber() throws InterruptedException, InvalidFormatException, IOException
		     		{
		     	 
		     	  	String cur_date ="";
		     	
		     	  	// Navigate to Configuration ->  System Configuration -> Manage Blocks
		     	  	String[] arr_elem = {"Configuration", "System Configuration", "Manage Blocks"};                                     
		     	  	Navigate_Menu_From_To_New(driver,arr_elem);
		     	  	
		     	  	
		     	    // wait until the "Add" button is displayed in the screen
		     	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		     	    
		     	    // click on "Add" button
		     	    select("AddJQ");
		     	    
		     	    // wait for 2 seconds
		     	    Thread.sleep(2000);
		     	    
		     	    // select "Black" option  from Block Category drop down
		     	    input("addBlockCategory", "BLACK");

		     	    // select "Card Number" option  from Block Type drop down
		     	   // input("addBlockType", "Card Number");
		     	    
		     	    //select MOBILE NUMBER option from Block Type drop down
		     	    input("addBlockType", "MOBILE NUMBER");
		     	
		     	    // enter Random mobile number in value text box
		     	    String Random_Mob = ProcessPGExcelData.getRandonValues("Mobile");
		     	    
		     	    driver.findElement(By.id("addvalue")).sendKeys(Random_Mob);
		     	    
		     	                                              
		     	    
		     	    // select from date
		     	    select("addStartDate");
		     	    Thread.sleep(2000);
		     	    
		     	    // from date should be today's date
		     	    cur_date = Get_Current_Date();
		     	    System.out.println("The cuurrent data calculated from java code is : " + cur_date);
		     	    
		     	    select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		     	              
		     	    
		     	    // select To date                  
		     	    select("addEndDate");
		     	    Thread.sleep(2000);
		     	    
		     	    // select next year from year drop down                
		     	    WebElement WE_Todate = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker div.ui-datepicker-header div.ui-datepicker-title select.ui-datepicker-year"));                             
		     	    Select dd_todate = new Select(WE_Todate);
		     	    dd_todate.selectByIndex(1); // always select the value from to date that is in index 1, means 1 year ahead
		     	    
		     	    // find the table that contains the dates
		     	    select_date_From_Datepicker("html body div#ui-datepicker-div.ui-datepicker table.ui-datepicker-calendar",cur_date);
		     	    


		     	    // Get Random comments
		     	    String comments = ProcessPGExcelData.getRandonName();
		     	    
		     	    // enter  Random comment in comment text  field
		     	    driver.findElement(By.id("addcomments")).sendKeys(comments);
		     	    
		     	    Thread.sleep(2000);
		     	    
		     	    // click on Save button                  
		     	    driver.findElement(By.cssSelector("html body div#addBlock.popup_block div.message form#addBlockForm div.sep input.button")).click();
		     	                         
		     	  
		          	Navigate_Menu_From_To_New(driver,arr_elem);
		     	  	
		     	  	
		     	    // wait until the "Add" button is displayed in the screen
		     	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		     	    
		     	    Thread.sleep(3000);
		     	    
		     	    boolean is_commentExist = driver.getPageSource().contains(comments);
		     	    boolean is_randomMobExist = driver.getPageSource().contains(Random_Mob);
		     	    
		     	    if(!is_commentExist  || !is_randomMobExist)
		     	    {
		     	 	   System.out.println("Test_AddBlock_MobileNumber() -> Error in adding the Mobile number block");
		     	 	   Assert.assertTrue(false);   
		     	    }
		     	    // Checking the Result, we will check whether the comments added in block is displaying or not.
		     	    
		     	    
		     	    
		     	    
		     	    

		     	 	// open the PG Paypage Transaction Page
		     	 	WebDriver driver_paypage = new FirefoxDriver();
		     	 	driver_paypage.manage().window().maximize();
		     	 	
		     	 	driver_paypage.get(Conf.getProperty("PG_Paypage_URL"));
		     	 	
		     	 	Thread.sleep(3000);
		     	 	
		     	 	// Input value in Order ID field, value should be random
		     	 	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
		     	 	driver_paypage.findElement(By.id("OrderID")).clear();
		     	 	driver_paypage.findElement(By.id("OrderID")).sendKeys(Random_Order_ID);
		     	
		     	
		     	 	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		     	 	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 0);
		     	 	driver_paypage.findElement(By.id("MerchantID")).clear();
		     	 	driver_paypage.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
		     	
		     	 	
		     	 	// input the Mobile Number to check for block
		     	 	driver_paypage.findElement(By.id("MobileNumber")).clear();
		     	 	driver_paypage.findElement(By.id("MobileNumber")).sendKeys(Random_Mob);
		     	 	
		     	 	
		     	 	
		     	 	// select No to Is 3D Secure drop down box			
		     	 	Select dropdown = new Select(driver_paypage.findElement(By.id("Is3DSecure")));	
		     	 	dropdown.selectByVisibleText("NO");
		     	 	
		     	 	//select_Dropdown("Is3DSecure","NO");
		     	 	
		     	 	// input API user ID in text box
		     	 	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 1);
		     	 	driver_paypage.findElement(By.id("UserId")).clear();
		     	 	driver_paypage.findElement(By.id("UserId")).sendKeys(API_USerID);
		     	 	//input("UserId", API_USerID);
		     	 	
		     	 	
		     	 	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
		     	 	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
		     	 	driver_paypage.findElement(By.id("DateAndTime")).clear();
		     	 	driver_paypage.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
		     	 	//input("DateAndTime", CurDateTime);
		     	 	
		     	 	
		     	 	// Click on CheckOut button			
		     	 	driver_paypage.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
		     	 		
		     	
		     	 	// wait to Response page to load		
		     	 	Thread.sleep(2000);
		     	 	
		     	 	// check the Response
		     	 	boolean res_code_block = driver_paypage.getPageSource().contains("1058");
		     	 	boolean res_Text_Mobile = driver_paypage.getPageSource().contains("MOBILE NUMBER");
		     	 	
		     	 	System.out.println("Test_AddBlock_MobileNumber() -> res_code_block : " + res_code_block + " and res_Text_Mobile : " + res_Text_Mobile);
		     	 	driver_paypage.quit();
		     	 	if(res_code_block == true && res_Text_Mobile == true)
		     	 	{
		     	 		System.out.println("Test_AddBlock_MobileNumber() -> The Response contains both the block code and Mobile text");
		     	 		Assert.assertTrue(true);			
		     	 	}
		     	 	else
		     	 	{
		     	 		System.out.println("Test_AddBlock_MobileNumber() -> Either the Response or the Mobile text is missing in Response Text");
		     	 		Assert.assertTrue(false);
		     	 	}
		     	 		     		     		
		     		}

		    		      
		      @Test(description ="To Test the Add Processor functionality", dependsOnMethods = {"Test_Virtual_Transaction"},alwaysRun =true)
		          public static void Test_AddProcessor() throws InterruptedException, InvalidFormatException, IOException
		          {
		     	    String processorName ="";
		     	    
		          	// Navigate to Configuration ->  System Configuration -> Manage Processors
		     	 	String[] arr_elem = {"Configuration", "System Configuration", "Manage Processors"};						
		        		Navigate_Menu_From_To_New(driver,arr_elem);
		             
		        		// wait until the "Add" button is displayed in the screen
		        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		        		
		        		// click on "Add" button
		        		select("AddJQ");
		        		
		        		// wait for 2 seconds
		        		Thread.sleep(2000);
		        		
		        		//input value for processor name from excel sheet
		        		processorName = Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 0);
		        		input("addprocessorName", processorName);
		        		

		        		//input value for processor code from excel sheet	   				
		        		input("addprocessorCode", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 1));
		        		
		        		//input value for Contact Person  from excel sheet	   				
		        		input("addcontactPerson", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 2));

		        		//input value for Email Address from excel sheet	   				
		        		input("addemail", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 3));
		        		

		        		//check "Ecom" value from Comm Mode checkbox 
		         		//open by hari 22-JULY-14
		         		select("commModeEcom1");
		        		
		        		// Input Primary Request URL from excel sheet
		        		input("addPrimaryReqURL", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 5));
		        		
		        		// Input Primary Response URL from excel sheet
		        		input("addPrimaryResURL", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 6));
		        		
		        		
		        		//select country from excel sheet
		        		select_Dropdown("addcountry", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 9));
		        		
		        		// select payment method from excel sheet
		        		select_Dropdown("paymentMethodList", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","addprocessor", 1, 10));
		        		
		        		
		        		Thread.sleep(2000);
		        		
		        		// click on Save button
		        		driver.findElement(By.cssSelector("html body div#addProcessors.popup_block div.message form#addProcessor.fields div.sep input.button")).click();
		        		
		        		
		        		Thread.sleep(3000);
		        		
		             //To flush out properly 
		        		Navigate_Menu_From_To_New(driver,arr_elem);
		             
		        		// wait until the "Add" button is displayed in the screen
		        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		        		
		        		
		        		boolean is_ProcessorExist = driver.getPageSource().contains(processorName);
		        		Thread.sleep(2000);
		        		
		        		// Checking the Result, we will check whether the Processor name added is displaying or not.
		        		Assert.assertTrue(is_ProcessorExist);
		        		
		        		
		          }
		      
		      @Test(description ="To Test the Edit Processor functionality",dependsOnMethods  ={"Test_AddProcessor"},alwaysRun =true)
		          public static void Test_Edit_Processor() throws InterruptedException, InvalidFormatException, IOException
		          {
		     	    // Navigate to Configuration ->  System Configuration -> Manage Processors
		     	 	String[] arr_elem = {"Configuration", "System Configuration", "Manage Processors"};						
		        		Navigate_Menu_From_To_New(driver,arr_elem);
		             
		        		// wait until the "Add" button is displayed in the screen
		        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddJQ"))).isDisplayed();
		        		
		        		
		        		// select the first record from the table	   			   		
		        		WebElement baseTable = driver.findElement(By.id("grid"));
		        		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));	   		
		        		Iterator<WebElement> itr = tableRows.iterator();
		             
		        		while(itr.hasNext())
		        		{
		        			String test = itr.next().getText();              	
		                 System.out.println("options text : " + test);
		                 WebElement we = itr.next();
		                 
		                 we.click();
		                 break;               
		        		}
		        		
		        		
		        		// click on "Edit" button
		        		select("EditJQ");
		        		
		        		// wait for 2 seconds
		        		Thread.sleep(2000);
		        		
		        			   		
		        		// Edit the payment method
		        		// first find the selected value and then choose different value to update
		        		WebElement dropDownListBox = driver.findElement(By.id("editpaymentMethodList"));
		        		Select dd_payment = new Select(dropDownListBox);
		        		dd_payment.selectByValue("3457777495");	
		        			   		Thread.sleep(2000);
		        		
		        		// click on Save button
		        		driver.findElement(By.cssSelector("html body div#editProcessors.popup_block div.message form#editProcessor.fields div.sep input.button")).click();
		        										   	
		        		// wait for 3 seconds
		        		Thread.sleep(3000);
		        		
		        		
		        		//boolean is_ProcessorExist = driver.getPageSource().contains(processorName);
		        		
		        		
		        		// Checking the Result, we can check whether the option we have edited is shoiwng as selected
		        		Assert.assertTrue(true);
		        			
		          }


		   	  
		     	     
	 @AfterMethod
	 
	 public void TearDown(ITestResult result) throws IOException 
	 { 
		  System.out.println("Test is shutting down");
		  
		  if(!result.isSuccess())
		  {
			   System.out.println("The TestCase Result is failed and going to capture screenshot");
			   System.out.print("Test Case Fails,Going to take the Screenshot");
			   File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
			   System.out.print("Screenshot is captured and stored in the application path and link of screenShot is attached in TestNG Report");
			   
			   //String strdestFile = new File("").getAbsolutePath() + "\\" + "Failure_ScreenShots" + "\\" + "Test_Merchant_THIRDPARTYPURCHASE_GEN.jpg";
			   
			   String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
			   
			   System.out.println("The Timestamp is : " + timeStamp );
			   
			   String strdestFile = new File("").getAbsolutePath() + "\\selenium-reports\\html\\" + timeStamp + "" + "\\" + result.getName() + ".jpg";
			   
			   
			   System.out.println("The Final ScrenShot path : " + strdestFile);
			   
			   File destFile = new File(strdestFile);
			   
			   FileUtils.copyFile(screenshot, destFile);
			   
			   String LinkPath = "file:///" + strdestFile;
			   Reporter.setCurrentTestResult(result); 
			   Reporter.log("<a href=" + LinkPath + " target='_blank' >" + "View ScreenShot" + "</a>");
			   Reporter.setCurrentTestResult(null); 			  
		  }
	
	 }
 
	 
	 	/* public void TearDown(ITestResult result) throws IOException 
		 { 
			  System.out.println("Test is shutting down");
			  
			  if(!result.isSuccess())
			  {
				  
				//  System.setProperty("org.uncommons.reportng.escape-output", "true");
				   System.out.println("The TestCase Result is failed and going to capture screenshot");
				   System.out.print("Test Case Fails,Going to take the Screenshot");
				   File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
				   System.out.print("Screenshot is captured and stored in the application path and link of screenShot is attached in TestNG Report");
				   
				   //String strdestFile = new File("").getAbsolutePath() + "\\" + "Failure_ScreenShots" + "\\" + "Test_Merchant_THIRDPARTYPURCHASE_GEN.jpg";
				   
				   String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
				   
				   System.out.println("The Timestamp is : " + timeStamp );
				   
				   String strdestFile = new File("").getAbsolutePath() + "\\selenium-reports\\html\\" + timeStamp + "" + "\\" + result.getName() + ".jpg";
				   
				   
				   
				   System.out.println("The Final ScrenShot path : " + strdestFile);
				   
				   File destFile = new File(strdestFile);
				   
				   FileUtils.copyFile(screenshot, destFile);
				   
				   String LinkPath = "file:///" + strdestFile;
				  
				   //hari, manually make it for scape,but not any benifit either true or false
				   //Reporter.setEscapeHtml(false); 
				   
				   
				  
				   Reporter.setCurrentTestResult(result); 
				   
				  
				   Reporter.log("<a href=" + LinkPath + " target='_blank' >" + "View ScreenShot" + "</a>");
				  // Reporter.log("<br/><a href="+LinkPath +" target='_blank' >" + "View ScreenShot" + "</a>");
				   
				  
				 
				 
				 
				 
				   Reporter.setCurrentTestResult(null); 	
				   
				   
				   
				   
				   
				   File file = TestSuite_PGportal.getFileName();
					
					
					 try(BufferedReader br = new BufferedReader(new FileReader(file))) {
					        StringBuilder sb = new StringBuilder();
					        String line = br.readLine();

					        while (line != null) {
					            sb.append(line);
					            sb.append(System.lineSeparator());
					            line = br.readLine();
					        }
					        String everything = sb.toString();
					        System.out.println(everything);
					       everything=everything.replaceAll("\\&lt;", "<");
					       everything=everything.replaceAll("\\&gt;", ">");
					       
					       everything=everything.replaceAll("\\ ScreenShot&lt;/a&gt;;", " ScreenShot</a>");
					       everything=everything.replaceAll("\\&lt;a;", "<a");
					       everything=everything.replaceAll("\\&lt;", "<");
					       everything=everything.replaceAll("\\&gt;View;", ">View");
					       
					       FileWriter fileWritter = new FileWriter(file);
			   	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			   	        bufferWritter.write(everything);
			   	        bufferWritter.close();
					       
					       System.out.println(everything);
					        
			  }
					 
			  }
		
		 }
	 



	 public static File getFileName()
		{
			File file=null;
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh", Locale.US);
			Calendar cal = Calendar.getInstance();
			String tillHour = dateFormat.format(cal.getTime());
			int minute = cal.get(Calendar.MINUTE);
			System.out.println(minute);
			String a =  new SimpleDateFormat("a").format(cal.getTime());
			
			
			
			 file = new File("D:\\Hfolder\\project_setup_9_may\\XSLT_Reports\\"+tillHour+"_"+minute+"_"+a+"\\reporterOutput.html");
			
			System.out.println(file.getAbsolutePath());
			
		   if(!(file.exists()))
		   {
			   
			   file = new File("D:\\Hfolder\\project_setup_9_may\\XSLT_Reports\\"+tillHour+"_"+(minute-1)+"_"+a+"\\reporterOutput.html"); 
			   
		   }
			
			
			
			return file;
		}


*/
}
