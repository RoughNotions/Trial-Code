package com.apc.PGportal;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apc.common.Excel;
import com.apc.common.ProcessPGExcelData;
import com.apc.common.UpdatePGExcel;


public class TestSuite_PG_Paypage  extends BaseTestPG
{

	
	@BeforeTest
		public static void PG_Paypage_setup() throws FileNotFoundException, IOException, InvalidFormatException
		{		
			//UpdatePGExcel.update_PG_API_Data("Resourses\\Data\\API_Data_PG.xlsx");
		
			System.out.println("PG Paypage test suite initializing.......");
			BaseSetup();
			Init_ConfigFile();
		}
	

	@Test(description = "To test the Paypage Non 3d transaction")
		public void Test_PG_Paypage_NonThreeD_Trans() throws InvalidFormatException, IOException, InterruptedException
		{
			// ******************* First Page Load here : The Paypage Details Page*******************
		
			// open the PG Paypage Transaction Page
			driver.get(Conf.getProperty("PG_Paypage_URL"));
			
			// wait for page to load properly
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();
			
			// Input value in Order ID field, value should be random
			String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
			input("OrderID", Random_Order_ID);
			
			// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
			String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 0);
			input("MerchantID", Merchant_ID);
			
			// select No to Is 3D Secure drop down box			
			select_Dropdown("Is3DSecure","NO");
			
			// input API user ID in text box
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 1);
			input("UserId", API_USerID);
			
			
			// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
			String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
			input("DateAndTime", CurDateTime);
			
			
			// Click on CheckOut button			
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[33]/td[3]/input")).click();
			
			
			
			// ******************* Next Page Load here : The Card Details Page*******************
			
			// wait for card details page to load properly
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("save"))).isDisplayed();
			
			// select MasterCard from check box
		
	   		boolean have_XPath =false;
	   		
	   		if(driver.findElements(By.xpath("/html/body/form/div/div/div/div/font/div/table/tbody/tr[2]/td/input[2]")).size() > 0)
	   		{
	   			System.out.println("Got xpath");
	   			driver.findElement(By.xpath("/html/body/form/div/div/div/div/font/div/table/tbody/tr[2]/td/input[2]")).click();
	   			have_XPath = true;
			}
	   		if(!have_XPath)
	   		{
	   			System.out.println("Got csspath");
	   			driver.findElement(By.cssSelector("html body form#creditcard div#parentDiv.container_12 div.grid_12 div#hd2.box div.boxin font div#payPage table tbody tr td input#cardType")).click();
	   		}
			
			
			// Input Card Number into Card Number Text Box
			input("cardNumber", "5555555555554444");
			
			// Input Card HolderName into Card Holder Name Text Box
			input("cardHolderName", "ankur");
			
			// select Expiry date - month
			select_Dropdown("expiryDateMonth", "01");
			
			// select Expiry date - Year, always choose 2nd option as it will be future year
			//Select sel = new Select(driver.findElement(By.id("expiryDateYear")));
			//sel.deselectAll();
			//sel.selectByVisibleText("Value");
			
			select_Dropdown("expiryDateYear", "2015");
			
			// input CVV into CVV textBox
			
			input("cvvNumber", "123");
			
			Thread.sleep(2000);
			// click on Save Button
			select("save");
			
		
			// ******************* Next Page Load here : The Response Details Page*******************
			
			// wait for some times to load the Response page load properly
			Thread.sleep(3000);
			
			
			//Check the Result here
			
			// if Page contains the following text then means pass else fail
			
			boolean Req_Succ_Text = driver.getPageSource().contains("Request successful");
			boolean Res_Dec_Text = driver.getPageSource().contains("1000");
			
			if(Req_Succ_Text == true && Res_Dec_Text == true)
			{
				Assert.assertTrue(true);
				
			}
			else
			{
				System.out.println("Test_PG_Paypage_NonThreeD_Trans() -> The Response page does not contain Request Successfull or response code 1000");
				Assert.assertTrue(false);
			}
			
		}	
			
	@Test(description = "To test the Paypage 3d transaction")
		public void Test_PG_Paypage_ThreeD_Trans() throws InvalidFormatException, IOException, InterruptedException
		{
			// ******************* First Page Load here : The Paypage Details Page*******************
		
			// open the PG Paypage Transaction Page
			//driver.get(Conf.getProperty("PG_Paypage_URL"));
			driver.get("https://paypage.apcld.net/MerchantSite/");
			
			// wait for page to load properly
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Checksum"))).isDisplayed();
			
			// Input value in Order ID field, value should be random
			String Random_Order_ID = ProcessPGExcelData.getRandonValues("PaypageOrderID");
			input("OrderID", Random_Order_ID);
			
			// Input Merchant ID, it should be from the execel file that shuld be updated by portal test case
			// Note: This Merchant ID has 3D secure to YES at time of creation of Merchant, so it will Run
			String Merchant_ID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 0);
			input("MerchantID", "RGroup75231248138338");
			
			// select No to Is 3D Secure drop down box			
			select_Dropdown("Is3DSecure","YES");
			
			// input API user ID in text box
			String API_USerID = Excel.getData("Resourses\\Data\\PG_Paypage_data.xlsx","Sheet1", 1, 1);
			input("UserId", "RIGroup1234");
			
			
			// Input Current Date Time in Date And Time Text box, format = ddmmyyyyhhmmss
			String CurDateTime = get_Cur_DateTime("ddMMyyyyHHmmss");
			input("DateAndTime", CurDateTime);
			
			
			// Click on CheckOut button			
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[33]/td[3]/input")).click();
			
			
			
			// ******************* Next Page Load here : The Card Details Page*******************
			
			// wait for card details page to load properly
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("save"))).isDisplayed();
			
			// select MasterCard from check box
			driver.findElement(By.xpath("/html/body/form/div/div[2]/div/div/font/div/table/tbody/tr[2]/td/input[2]")).click();
			
			// Input Card Number into Card Number Text Box
			//input("cardNumber", "5555555555554444");
			input("cardNumber", "5453010000083683");
			
			// Input Card HolderName into Card Holder Name Text Box
			input("cardHolderName", "ankur");
			
			// select Expiry date - month
			select_Dropdown("expiryDateMonth", "01");
			
			// select Expiry date - Year, always choose 2nd option as it will be future year
			//Select sel = new Select(driver.findElement(By.id("expiryDateYear")));
			//sel.deselectAll();
			//sel.selectByVisibleText("Value");
			
			select_Dropdown("expiryDateYear", "2015");
			
			// input CVV into CVV textBox
			
			input("cvvNumber", "123");
			
			Thread.sleep(2000);
			// click on Save Button
			select("save");
			
			
			// ******************* Next Page Load here : The 3D Secure Page Details Page*******************
			
			// wait for 3D secure page to load properly
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardHolderSelect"))).isDisplayed();
			
			// input secure pin in text box
			if(driver.findElements(By.xpath("/html/body/form/table/tbody/tr[4]/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input")).size() > 0)
			{
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[4]/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input")).sendKeys("secjane1");
			}
			else if(driver.findElements(By.cssSelector("html body form table tbody tr td table tbody tr td table tbody tr td table tbody tr td input.monospace")).size() > 0)
			{
				driver.findElement(By.cssSelector("html body form table tbody tr td table tbody tr td table tbody tr td table tbody tr td input.monospace")).sendKeys("secjane1");
			}
			else
			{
				System.out.println("The Locator of Secure Pin text box does not found");
				
				Assert.assertTrue(false);
				
			}
			
			
			boolean have_XPath =false;
	   		
	   		if(driver.findElements(By.xpath("/html/body/form/table/tbody/tr[4]/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[9]/td[2]/table/tbody/tr/td[2]/input")).size() > 0)
	   		{
	   			System.out.println("Got xpath");
	   			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[4]/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[9]/td[2]/table/tbody/tr/td[2]/input")).click();
	   			have_XPath = true;
			}
	   		if(!have_XPath)
	   		{
	   			System.out.println("Got csspath");
	   			driver.findElement(By.cssSelector("html body form table tbody tr td table tbody tr td table tbody tr td table tbody tr td table tbody tr td input")).click();
	   		}
			
			
			
			// ******************* Next Page Load here : The Response Details Page*******************
			
			// wait for some times to load the Response page load properly
			Thread.sleep(3000);
			
			
			//Check the Result here
			
			// if Page contains the following text then means pass else fail
			
		
			boolean Req_Succ_Text = driver.getPageSource().contains("Request successful");
			boolean Res_Dec_Text = driver.getPageSource().contains("1000");
			
			if(Req_Succ_Text == true && Res_Dec_Text == true)
			{
				Assert.assertTrue(true);
				
			}
			else
			{
				System.out.println("Test_PG_Paypage_ThreeD_Trans() -> The Response page does not contain Request Successfull or response code 1000");
				Assert.assertTrue(false);
			}
			
		
		}
				
				
	
	
	
	@AfterTest
		public static void PG_Paypage_TearDown() throws FileNotFoundException, IOException, InvalidFormatException
		{					
			System.out.println("PG Paypage test suite tear down.......");
			
			if(driver != null)
			{
				driver = null;
			}
			
			
		}
	


}
