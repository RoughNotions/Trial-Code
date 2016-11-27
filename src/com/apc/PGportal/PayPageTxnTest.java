/* This code written by Harishankar, UAT will not work cause CARD TYPE PAGE has different look than Dev1 and S1*/
/*  It would work for Template1, plz check from mechantConfig-Paypage */
package com.apc.PGportal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apc.common.Excel;
import com.apc.common.ProcessPGExcelData;
import com.apc.common.UpdatePGExcel;

public class PayPageTxnTest extends BaseTestPG
{

	@BeforeTest
	public static void setup() throws FileNotFoundException, IOException, InvalidFormatException
	{	
		//UpdatePGExcel.updatePGdata();
		System.out.println("PayPage test suite initializing.......");
		BaseSetup();
		Init_ConfigFile();
	}
 
	@Test(description ="To verify Non-3D Txn with Currency EUR and Card Type VISA ")
	public static void visaNon3DEurAuthTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		System.out.println("MID "+Merchant_ID);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		System.out.println("MID "+Merchant_ID);
		driver.findElement(By.id("MerchantID")).clear();
		
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("NO");
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "VISA");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
	driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		res_code  = driver.getPageSource().contains("1000");
	}
	
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	

	@Test(description ="To verify Non-3D Txn with Currency USD and Card Type VISA ")
	public static void visaNon3DUSDAuthTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the Currency Code to Text Box
	driver.findElement(By.id("Currency")).clear();
	driver.findElement(By.id("Currency")).sendKeys("USD");
	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	 
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("NO");
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "VISA");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
	driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		res_code  = driver.getPageSource().contains("1000");
	}
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	

    @Test(description ="To verify Non-3D Txn with Currency EUR and Card Type MASTER ")
	public static void masterNon3DEurAuthTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("NO");
	
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "MASTER");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
	driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		res_code  = driver.getPageSource().contains("1000");
	}
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	

	@Test(description ="To verify Non-3D Txn with Currency USD and Card Type MASTER ")
	public static void masterNon3DUSDAuthTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the Currency Code to Text Box
	driver.findElement(By.id("Currency")).clear();
	driver.findElement(By.id("Currency")).sendKeys("USD");
	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	 
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("NO");
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "MASTER");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum); 
	driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	 
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		System.out.println("selected in second time from : masterNon3DUSDAuthTest");
		res_code  = driver.getPageSource().contains("1000");
	}
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	
	
	@Test(description ="To verify Non-3D,OneStep Txn with Currency EUR and Card Type VISA ")
	public static void visaNon3DEurOneStepTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("YES");
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "VISA");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
	driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		res_code  = driver.getPageSource().contains("1000");
	}
	
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	

	@Test(description ="To verify Non-3D,OneStep Txn with Currency USD and Card Type VISA ")
	public static void visaNon3DUSDOneStepTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the Currency Code to Text Box
	driver.findElement(By.id("Currency")).clear();
	driver.findElement(By.id("Currency")).sendKeys("USD");
	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	 
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("YES");
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "VISA");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
	driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		res_code  = driver.getPageSource().contains("1000");
	}
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	

	@Test(description ="To verify Non-3D,OneStep Txn with Currency EUR and Card Type MASTER ")
	public static void masterNon3DEurOneStepTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("YES");
	
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "MASTER");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
	driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		res_code  = driver.getPageSource().contains("1000");
	}
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	

	@Test(description ="To verify Non-3D,OneStep Txn with Currency USD and Card Type MASTER ")
	public static void masterNon3DUSDOneStepTest() throws InvalidFormatException, IOException, InterruptedException
	{
	 
    //called function to page setup and open required url	
	 payPageSetup();
	// wait for page to load properly
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

     String CurDateM = get_Cur_DateTime("ddMM");
	// Input value in Order ID field, value should be random,combination of current date+environment
	String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
	driver.findElement(By.id("OrderID")).clear();
	driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);
	
	
	// Choose to fill MID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}
	// Choose to fill MID for Dev1 and UAT
	else
	{
		// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
		String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
		driver.findElement(By.id("MerchantID")).clear();
		driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
	}

	
	// input the Currency Code to Text Box
	driver.findElement(By.id("Currency")).clear();
	driver.findElement(By.id("Currency")).sendKeys("USD");
	
	// input the IP Address to check for block
	//driver.findElement(By.id("SOURCE_IP")).clear();
	//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
	//input("SOURCE_IP", Random_IP);
	
	
	// select No to Is 3D Secure drop down box			
	Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
	dropdown.selectByVisibleText("NO");
	
	 
	// Choose to fill UID for S1
	if(envStr.equalsIgnoreCase("s1"))
	{
		// input API user ID in text box for s1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	// Choose to fill UID for UAT and Dev1
	else
	{
		
		if(envStr.equalsIgnoreCase("uat")) 
		{
			// input API user ID in text box for uat
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
		else
		{
			// input API user ID in text box for dev1
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(API_USerID);
		}
		
	}
	
	// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
	String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
	driver.findElement(By.id("DateAndTime")).clear();
	driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
	 
	//Select AuthCapture from Drop down
	dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
	dropdown.selectByVisibleText("YES");
	
	// Click on CheckOut button			
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();
	
	
	// now credit card page loads****************************************
	Thread.sleep(5000);
	
	Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());
	
	//select card type.
	select_Dropdown("cardType", "MASTER");
	
	
	// input card number
	driver.findElement(By.id("cardNumber")).clear();
	//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum); 
	driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");
	
	// input cardholder name     	
	driver.findElement(By.id("cardHolderName")).clear();
	driver.findElement(By.id("cardHolderName")).sendKeys("haris");
	
	// select Card Expirt Month from drop down
	WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
	Select dd_tod = new Select(WE);
	dd_tod.selectByIndex(1); // 
	
	// select Card Expirt Year from drop down
	WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
	Select dd_tod1 = new Select(WE1);
	dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead
	
	// input CVV Number
	driver.findElement(By.id("cvvNumber")).clear();
	driver.findElement(By.id("cvvNumber")).sendKeys("222");
	
	Thread.sleep(2000);
	
	// click on Submit button
	driver.findElement(By.id("save")).click();
	
	// wait to Response page to load		
	
	Thread.sleep(4000);
	// check the Response
	boolean res_code  = driver.getPageSource().contains("1000");
	 
	Thread.sleep(2000);
	boolean res_Text = driver.getPageSource().contains("Request successful");
	Thread.sleep(1000);
	//if response code occur after request response then it work, otherwise above code for res_code work.
	if(!res_code)
	{
		System.out.println("selected in second time from : masterNon3DUSDAuthTest");
		res_code  = driver.getPageSource().contains("1000");
	}
	
	System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	


	
//#############################################3DTransaction##################################//	

@Test(description ="To verify 3D Txn with Currency EUR and Card Type VISA ")
public static void visa3DEurAuthTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);

//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("NO");

// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "VISA");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();
// wait to Response page to load
Thread.sleep(4000);

//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);
// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	res_code  = driver.getPageSource().contains("1000");
}


System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
	 
	Assert.assertTrue(res_code && res_Text);
	 
	
 }	
@Test(description ="To verify 3D Txn with Currency USD and Card Type VISA ")
public static void visa3DUSDAuthTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the Currency Code to Text Box
driver.findElement(By.id("Currency")).clear();
driver.findElement(By.id("Currency")).sendKeys("USD");

// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
 
//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("NO");

// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "VISA");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);

//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);
	 
// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	res_code  = driver.getPageSource().contains("1000");
}

System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	

@Test(description ="To verify 3D Txn with Currency EUR and Card Type MASTER ")
public static void master3DEurAuthTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);

//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("NO");


// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "MASTER");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);

//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);

// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	res_code  = driver.getPageSource().contains("1000");
}

System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	

@Test(description ="To verify 3D Txn with Currency USD and Card Type MASTER ")
public static void master3DUSDAuthTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the Currency Code to Text Box
driver.findElement(By.id("Currency")).clear();
driver.findElement(By.id("Currency")).sendKeys("USD");

// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
 
//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("NO");

// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "MASTER");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum); 
driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);

//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);

// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
 
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	System.out.println("selected in second time from : masterNon3DUSDAuthTest");
	res_code  = driver.getPageSource().contains("1000");
}

System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	

@Test(description ="To verify 3D,OneStep Txn with Currency EUR and Card Type VISA ")
public static void visa3DEurOneStepTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);

//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("YES");

// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "VISA");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);

//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);

// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	res_code  = driver.getPageSource().contains("1000");
}


System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	

@Test(description ="To verify 3D,OneStep Txn with Currency USD and Card Type VISA ")
public static void visa3DUSDOneStepTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the Currency Code to Text Box
driver.findElement(By.id("Currency")).clear();
driver.findElement(By.id("Currency")).sendKeys("USD");

// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
 
//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("YES");

// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "VISA");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
driver.findElement(By.id("cardNumber")).sendKeys("4012001037141112");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);
//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);

// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	res_code  = driver.getPageSource().contains("1000");
}

System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	

@Test(description ="To verify 3D,OneStep Txn with Currency EUR and Card Type MASTER ")
public static void master3DEurOneStepTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);

//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("YES");


// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "MASTER");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum);  
driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);
//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);

// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	res_code  = driver.getPageSource().contains("1000");
}

System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	

@Test(description ="To verify 3D,OneStep Txn with Currency USD and Card Type MASTER ")
public static void master3DUSDOneStepTest() throws InvalidFormatException, IOException, InterruptedException
{
 
//called function to page setup and open required url	
 payPageSetup();
// wait for page to load properly
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();

 String CurDateM = get_Cur_DateTime("ddMM");
// Input value in Order ID field, value should be random,combination of current date+environment
String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
driver.findElement(By.id("OrderID")).clear();
driver.findElement(By.id("OrderID")).sendKeys(envStr+CurDateM+Random_Order_ID);


// Choose to fill MID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}
// Choose to fill MID for Dev1 and UAT
else
{
	// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
	String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 0);
	driver.findElement(By.id("MerchantID")).clear();
	driver.findElement(By.id("MerchantID")).sendKeys(Merchant_ID);
}


// input the Currency Code to Text Box
driver.findElement(By.id("Currency")).clear();
driver.findElement(By.id("Currency")).sendKeys("USD");

// input the IP Address to check for block
//driver.findElement(By.id("SOURCE_IP")).clear();
//driver.findElement(By.id("SOURCE_IP")).sendKeys(Random_IP);
//input("SOURCE_IP", Random_IP);


// select No to Is 3D Secure drop down box			
Select dropdown = new Select(driver.findElement(By.id("Is3DSecure")));	
dropdown.selectByVisibleText("YES");

 
// Choose to fill UID for S1
if(envStr.equalsIgnoreCase("s1"))
{
	// input API user ID in text box for s1
	String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 3, 1);
	driver.findElement(By.id("UserId")).clear();
	driver.findElement(By.id("UserId")).sendKeys(API_USerID);
}

// Choose to fill UID for UAT and Dev1
else
{
	
	if(envStr.equalsIgnoreCase("uat")) 
	{
		// input API user ID in text box for uat
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 5, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
	else
	{
		// input API user ID in text box for dev1
		String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 4, 1);
		driver.findElement(By.id("UserId")).clear();
		driver.findElement(By.id("UserId")).sendKeys(API_USerID);
	}
	
}

// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
driver.findElement(By.id("DateAndTime")).clear();
driver.findElement(By.id("DateAndTime")).sendKeys(CurDateTime);
 
//Select AuthCapture from Drop down
dropdown = new Select(driver.findElement(By.id("AuthCapture")));	
dropdown.selectByVisibleText("YES");

// Click on CheckOut button			
driver.findElement(By.xpath("/html/body/form/table/tbody/tr[41]/td[3]/input")).click();


// now credit card page loads****************************************
Thread.sleep(5000);

Assert.assertTrue(driver.findElement(By.id("payPage")).isDisplayed());

//select card type.
select_Dropdown("cardType", "MASTER");


// input card number
driver.findElement(By.id("cardNumber")).clear();
//driver.findElement(By.id("cardNumber")).sendKeys(Random_CardNum); 
driver.findElement(By.id("cardNumber")).sendKeys("5555555555554444");

// input cardholder name     	
driver.findElement(By.id("cardHolderName")).clear();
driver.findElement(By.id("cardHolderName")).sendKeys("haris");

// select Card Expirt Month from drop down
WebElement WE = driver.findElement(By.id("expiryDateMonth"));	   			 
Select dd_tod = new Select(WE);
dd_tod.selectByIndex(1); // 

// select Card Expirt Year from drop down
WebElement WE1 = driver.findElement(By.id("expiryDateYear"));	   			 
Select dd_tod1 = new Select(WE1);
dd_tod1.selectByIndex(3); // always select the value from to date that is in index 1, means 1 year ahead

// input CVV Number
driver.findElement(By.id("cvvNumber")).clear();
driver.findElement(By.id("cvvNumber")).sendKeys("222");

Thread.sleep(2000);

// click on Submit button
driver.findElement(By.id("save")).click();

// wait to Response page to load		

Thread.sleep(4000);

//Click for 3D seure submmit  button
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input")).click();
//wait.until(ExpectedConditions.titleContains("bankResponse"));
Thread.sleep(10000);

// check the Response
boolean res_code  = driver.getPageSource().contains("1000");
 
Thread.sleep(2000);
boolean res_Text = driver.getPageSource().contains("Request successful");
Thread.sleep(1000);
//if response code occur after request response then it work, otherwise above code for res_code work.
if(!res_code)
{
	System.out.println("selected in second time from : masterNon3DUSDAuthTest");
	res_code  = driver.getPageSource().contains("1000");
}

System.out.println("res_code : " + res_code + " and  res_Text : " + res_Text);
 
Assert.assertTrue(res_code && res_Text);
 

}	
 
	
}
