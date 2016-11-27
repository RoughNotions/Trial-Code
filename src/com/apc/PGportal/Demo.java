package com.apc.PGportal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apc.common.Excel;
import com.apc.common.ProcessPGExcelData;
import com.apc.common.UpdatePGExcel;


public class Demo  extends BaseTestPG
{

	
	@BeforeTest
		public static void PG_Paypage_setup() throws FileNotFoundException, IOException, InvalidFormatException
		{		
			//UpdatePGExcel.update_PG_API_Data("Resourses\\Data\\API_Data_PG.xlsx");
			//System.out.println("PG Paypage test suite initializing.......");
			//UpdatePGExcel.updatePGdata();
			BaseSetup();
			Init_ConfigFile();
		}
	
	@Test 
		public static void Test_Login() throws FileNotFoundException, IOException, InterruptedException 
		{	
			// login to the portal
			login();
	
			// verify that url contains dashboard
			assert driver.getCurrentUrl().contains("dashboard");
		}
	
	
	//Condition for cancel: txn should be only success and only single row should occur
	
	
	

@Test(description = "TO verify Add Merchant functionality", dependsOnMethods = { "Test_Login" })
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
		
		
		// ////////////////////////////////////  hari changed start

		//input("validFrom", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 22));

		//input("validTo", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx","AddMerchant", 1, 23));

		driver.findElement(By.id("validFrom")).sendKeys("09-14-2015");   //.click();09-14-2015
		Thread.sleep(2000);
		driver.findElement(By.id("validTo")).sendKeys("09-14-2016");
		
		Thread.sleep(2000);
		//select_date_From_Datepickerh("validFrom","09-14-2015");
		
		///select_date_From_Datepickerh("validFrom","09-14-2016");
		
		
		
		
		////////////////////////hari end///////////////////
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

	


	
	
	
	
public static void select_date_From_Datepickerh(String CSSlocator,String date_to_pick)
{
	
	WebElement dateWidget = driver.findElement(By.id(CSSlocator));
		
		//List<WebElement> rows=dateWidget.findElements(By.tagName("tr"));  
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));  
	    
		for (WebElement cell: columns)
		{  	   		
			if (cell.getText().equals(String.valueOf(date_to_pick)))
			{  
				cell.findElement(By.linkText(String.valueOf(date_to_pick))).click();  
				break;
			}
	   }  
	
	
}
	
	
	






/*
	  @Test(description = "To test the Virtual Transaction of the merchant user who has the right of Virtual transacrtion menu") 
	 	public void Test_Virtual_Transaction() throws InvalidFormatException, IOException, InterruptedException  // half of the end work is done, starting work of fecthing the usernam and password from mail needs to do
	 	{
	 	

		    	// find the Username from excel sheet.
		 	// find the password from mail
		 	// Login to Portal
		 	// Check for Virtual Terminal menu item
		 	// if it is there then test pass otherwise fail.
		 	
		 	WebDriver temp_Driver = new FirefoxDriver();
		 	temp_Driver.manage().window().maximize();
		 	
		 	WebDriverWait temp_wait = new WebDriverWait(temp_Driver, 120);
		 	
		 	
		 	String username = " cupuser01";
		 	String firstTimePassd = "n4jt@4l6";
		 	String NewPassd = "shipra007new";
		 	
		 	boolean isNeedToReset = false;
		 	
		 	if(isNeedToReset)
		 	{
		 		// Login for the first time with the user
			 	boolean ret = Login_FirstTime(temp_Driver,username, firstTimePassd,NewPassd);
			 	if(!ret)
			 	{
			 		System.out.println("Test_Virtual_Transaction() ->  error in resetting the password");
			 		Assert.assertTrue(false);
			 	}
		 	}else
		 	{
		 		//direct login to the system
				// input("j_username",Conf.getProperty("PGusername"));
		   		
		 		temp_Driver.get(Conf.getProperty("PGUrl"));
		 		
		 		temp_Driver.findElement(By.id("j_username")).sendKeys(Conf.getProperty("PGNewUserName"));
		   		
		   		
		   		temp_Driver.findElement(By.id("j_password")).sendKeys(Conf.getProperty("PGNewUSerPassd"));
				

				temp_Driver.findElement(By.className("loginButton")).click();
		 		
		 	}
		 	
		 	// wait for page load complete 
		 	Thread.sleep(5000);
		
		 	if(temp_Driver.getPageSource().contains("Virtual Terminal") == false)  
		 	{
		 		// means that the user does not have Right of the Virtual Transaction menu and test case fails
		 		Assert.assertTrue(false);
		 	}
		 	
		 			 
		 	
			// Goto Transactions -> Virtual Terminal
		 	String[] arr_elem = {"Transactions", "Virtual Terminal"};						
	   		Navigate_Menu_From_To_New(temp_Driver,arr_elem);
	        
	   		
	   		// wait for some time to load the Virtual Transaction Page
	   		temp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentMethod"))).isDisplayed();
	   		
	   		
	   		// enter the Amount in the amount field
	   		temp_Driver.findElement(By.id("txnAmount")).clear();
	   		temp_Driver.findElement(By.id("txnAmount")).sendKeys("200");
	   		
	   		// select the currency type from currency drio down
	   		WebElement Curr_DD = temp_Driver.findElement(By.id("currencyType"));
	   		Select cur_sel = new Select(Curr_DD);
	   		
	   		cur_sel.selectByIndex(1);
	   		
	   		
	   		// enter the order ref in order red textbox
	   		temp_Driver.findElement(By.id("orderRef")).clear();
	   		temp_Driver.findElement(By.id("orderRef")).sendKeys("order07051402");
	   		
	   		// select the Credit card type from drop down
	   		WebElement Curtype_DD = temp_Driver.findElement(By.id("cCType"));
	   		Select curtype_sel = new Select(Curtype_DD);
	   		
	   		curtype_sel.selectByIndex(2);
	   		
	   		// enter the credit card number in credit card textbox
	   		temp_Driver.findElement(By.id("cardNo")).clear();
	   		temp_Driver.findElement(By.id("cardNo")).sendKeys("5453010000083683");
	   		
	   		
	   		// select the card expiry month
	  		WebElement Expiry_month_DD = temp_Driver.findElement(By.id("expDateMonth"));
	   		Select Exp_month_sel = new Select(Expiry_month_DD);
	   		
	   		Exp_month_sel.selectByIndex(1);
	   		
	   		
	   		// select the card expiry year
	  		WebElement Expiry_year_DD = temp_Driver.findElement(By.id("expDateYear"));
	   		Select Exp_year_sel = new Select(Expiry_year_DD);
	   		
	   		Exp_year_sel.selectByIndex(2);
	   		
	   		
	   		// enter the name on card
	   		
	   		temp_Driver.findElement(By.id("cardHolderName")).clear();
	   		temp_Driver.findElement(By.id("cardHolderName")).sendKeys("ankur");
	   		
	   		// enter the security code
	   		temp_Driver.findElement(By.id("secCode")).clear();
	   		temp_Driver.findElement(By.id("secCode")).sendKeys("176");
	   		
	   		
	   		// click on the save button
	   		temp_Driver.findElement(By.id("save")).click();
	   		
	   		// wait for Response page to load
	   		temp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("makeAnotherTransaction"))).isDisplayed();
	   		
	   		if(temp_Driver.getPageSource().contains("1000"))
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
	     
	   
   @AfterTest
   	public void TearDown()
   	{
	   System.out.println("The PG Test Suite is going to Tear Down");
	   
	   if(driver != null)
	   {
		   System.out.println("TearDown() : The driver object is getting free");
		   driver.quit();
	   }
	   
		  
		   
	   	} 
	   	*/
}