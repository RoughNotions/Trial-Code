package com.apc.FMS;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apc.autotest.handler.AutoTestFacade;
import com.apc.autotest.service.ResponseParser;
import com.apc.common.BaseTest;
import com.apc.common.Excel;
import com.apc.common.UpdateDataFile;


public class TestSuite_MerchantChecks extends BaseTest
{

	
	//@BeforeSuite
	@BeforeTest

    public void TestSetup() throws InvalidFormatException, IOException, InterruptedException
    {
	
	
		 
		
		// updating API data file
	
	  // UpdateDataFile.update("Resourses\\Data\\API_Data_Merchant.xlsx");
	
		System.out.println("System is initiating the TestSuite_MerchantChecks suite");
	
		login();
		System.out.println("api_url" +api_url);
		
		//DisableAllGlobalChecks();
		//DisableAllMerchantChecks();
    }
	 	
	@Test(description = "To verify negative check for account holder name Pattern Velocity Check") 
	   
    public void Test_Merchant_accountholder_name_pattern_vel() throws IOException  , InterruptedException // pass

    {
	    // navigate to manage fraud detail page
		
        navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
        
        // select Merchant from dropdown         
        select_Dropdown("check", "Merchant");
        
        ///select_cls("boxin");
        select_cls("boxin");        
        // Input the value in Merchant Name textbox
        input("merchantName", Conf.getProperty("Merchant_Name"));
        
        // Click on Free Area to make Textbox appear
       /// select_cls("boxin");
        select_cls("boxin"); 
        // Click on Search Button
        select("search-merchant");
        
        // uncheck all selected checks    
        select("cb_grid");
        // Check all selected checks  
        
        Thread.sleep(3000);
        select("cb_grid");
        
        Thread.sleep(3000);
        
        // select ACCOUNTHOLDERNAMEPATTERN_VEL check    
        select("jqg_grid_1148");
        		//  jqg_grid_1148  Pattern
        		// jqg_grid_1168   DB
        		
        ///select_cls("boxin");
        select_cls("boxin");
        //click save button        
        select("saveManageFruadsGlobal");
        
        ///select_cls("boxin");
       /// select_cls("boxin");
        Thread.sleep(1000);
        driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Manage Setting")));
		Thread.sleep(2000);
        // go to score configuration page
        navigate_menu(Conf.getProperty("Menu_Manage_setting"));
		navigate_menu(Conf.getProperty("Menu_confg"));
        navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
        
        // enter Merchant in check type            
        select_Dropdown("check",  "Merchant");
        
        // Click on Free Area to make Textbox appear 
        ///select_cls("boxin");
        select_cls("boxin");
        // Input the value in Merchant Name textbox
        input("merchantName", Conf.getProperty("Merchant_Name"));
        
        // Click on Free Area to make Textbox appear 
        ///select_cls("boxin");
        select_cls("boxin");
        // Wait for other actions to be performed
        Thread.sleep(2000);
   
        // Selecting the Desired drop down value using Javacscript inject 
        ((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[1].selected='true'");
       
       
        //click show details button       
        select("btnSearchDetails");
        
        // Select Daily option value form combo box
        select_Dropdown("baseTime",  "DAILY"); 
        
        // Clear the value of Maximum Text field value
      //  Common.input("r1max", "");
        
        // Enter the value of Maximum Text field to 1
        input("r1max", "1");
        
        // Clear the value of Score Text field value
        input("r1scores", "");
        
        // Enter the value of First Score Text field to 30
        input("r1scores", "300");
        
        // Enter the value of Second Score Text field to 70
        input("r2scores", "700");

        // Enter the value of Third Score Text field to 100
        input("r3scores", "1000");


        // Enter the Save Button
        select("save");
        
    	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
    	setExecuteArray(myArgs);

        
        // Web Servie Call
        new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 2);              
        String resCode = ResponseParser.responseCode;
        Assert.assertEquals(resCode,"1000");
        String Check = ResponseParser.check;         
        Assert.assertEquals(Check,"Blocked");


	}
	 

	@Test(description = "To verify negative check for account holder name Negative DB")  


	public void Test_Merchant_account_holder_name_nb() throws IOException, InterruptedException, InvalidFormatException  // Pass
	
	{
	   
		
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		
		 Thread.sleep(3000);
		 
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		
		select("cb_grid");
	
		 Thread.sleep(3000);
		 
		// select account holder name nb check
		select("jqg_grid_1168");
		
		select_cls("boxin");
	
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
	
		// Wait for sometime to load other part
		Thread.sleep(3000);
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		//Common.select_Dropdown_wait("userType", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/select/option[3]", "Merchant");
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[6]", "FRAUD_ACCOUNT");
	
	
		// click search
		//Common.select("chkTypeSearch");
	
		// click add new button
		select("addDetails");
		
		//String AccHolderName = Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant", 2, 34);
		//System.out.println("Value of AccountHolder Name : " + AccHolderName);
		
		String AccName = Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant", 2, 36);
		System.out.println("Value of AccountHolder Name : " + AccName);
	   
		 // change by hari 04Sept14 earlier taking alphanumeric ,but need of numeric
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant", 2, 36));  // for taking value from Excel		
	
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 3);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	}


@Test(description = "To verigy negative list check for Address_Country")

	public void Test_Merchant_Address_Country_NegativeList_Check() throws IOException,InterruptedException // pass
	{
	    // navigate to manage fraud detail page
		
	    navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	    
	    // select Merchant from dropdown         
	    select_Dropdown("check", "Merchant");
	    
	    select_cls("boxin");
	    
	    // Input the value in Merchant Name textbox
	    input("merchantName", Conf.getProperty("Merchant_Name"));
	    
	    
	    select_cls("boxin");
	    
	    // Click on Search Button
	    select("search-merchant");
	    
	    // uncheck all selected checks    
	    select("cb_grid");
	    
	    Thread.sleep(3000);
	    
	    select("cb_grid");
	    
	    Thread.sleep(3000);
	    
		// select ADDRESS_COUNTRY check	
		select("jqg_grid_1221");
	           
		select_cls("boxin");
	    //click save button        
	    select("saveManageFruadsGlobal");
	    
	    select_cls("boxin");
	    
	    Thread.sleep(3000);
	    
	    navigate_menu(Conf.getProperty("Menu_Negative_List"));
	
	    // Input the value in Merchant Name textbox
	    input("merchantName", Conf.getProperty("Merchant_Name")); 
	    
	    // select Block from dropdown         
	    select_Dropdown("scopeFor1", "Blocked");
	    
	    // select ADDRESS_COUNTRY from dropdown        
	    select_Dropdown("scopeFor2", "ADDRESS_COUNTRY");
	    
	    //click Search button        
	    select("searchDetails");
	    
	    Thread.sleep(3000);
	    
	    // select INDIA Country from Drop Down
	    select("jqg_neglisttable_IN");
	    
	    //click Save button        
	    select("negListSave");
	    
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
	    
	    // Web Servie Call
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 4);              
	    String resCode = ResponseParser.responseCode;
	    Assert.assertEquals(resCode,"1000");
	    String Check = ResponseParser.check;         
	    Assert.assertEquals(Check,"Blocked");
	
	    
	}

@Test(description = "To verigy negative list check for BIN_Address_Country")

	public void Test_Merchant_Bin_Address_Country_NegativeList_Check() throws IOException,InterruptedException // Pass
	{
	    // navigate to manage fraud detail page
		
	    navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	    
	    // select Merchant from dropdown         
	    select_Dropdown("check", "Merchant");
	    
	    select_cls("boxin");
	    
	    // Input the value in Merchant Name textbox
	    input("merchantName", Conf.getProperty("Merchant_Name"));
	    
	    
	    select_cls("boxin");
	    
	    // Click on Search Button
	    select("search-merchant");
	    
	    Thread.sleep(3000);
	    
	    // uncheck all selected checks    
	    select("cb_grid");
	    
	    Thread.sleep(3000);
	    select("cb_grid");
	    
	    Thread.sleep(3000);
	    
		// select BIN_ADDRESS_COUNTRY check	
		select("jqg_grid_1207");
		
		 select_cls("boxin");
	           
	    //click save button        
	    select("saveManageFruadsGlobal");
	    
	    select_cls("boxin");
	    
	    Thread.sleep(3000);
	    
	   
		String[] myArgs = { "BINCountryAddr"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
	    
	    // Web Servie Call
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 5);              
	    String resCode = ResponseParser.responseCode;
	    Assert.assertEquals(resCode,"1000");
	    String Check = ResponseParser.BINCountryAddr;         
	    Assert.assertEquals(Check,"Yes");
		    	    
	}
 
@Test(description = "To verigy negative list check for BIN_Country")

public void Test_Merchant_BIN_Country_NegativeList_Check() throws IOException,InterruptedException // pass
{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));


	// select Merchant from dropdown..........................................
	select_Dropdown("check", "Merchant");


	//click on page anywhere to make merchant name text box appear.
	select_cls("boxin");

	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));

	select("search-merchant");
	
	 Thread.sleep(3000);
	 
	// uncheck all selected checks
	select("cb_grid");
	
	Thread.sleep(3000);
	select("cb_grid");
    
	Thread.sleep(3000);
	
	// select BIN_COUNTRY check	
	select("jqg_grid_1220");
    
	select_cls("boxin");
	
    //click save button        
    select("saveManageFruadsGlobal");
    
    select_cls("boxin");
    
    Thread.sleep(3000);
    
    navigate_menu(Conf.getProperty("Menu_Negative_List"));

    // Input the value in Merchant Name textbox
    input("merchantName", Conf.getProperty("Merchant_Name")); 
    
    // select Block from dropdown         
    select_Dropdown("scopeFor1", "Blocked");
    
    // select BIN_COUNTRY from dropdown        
    select_Dropdown("scopeFor2", "BIN_COUNTRY");
    
    //click Search button        
    select("searchDetails");
    
    Thread.sleep(3000);
    
    // select INDIA Country from Drop Down
    select("jqg_neglisttable_IN");
    
    //click Save button        
    select("negListSave");
    
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);

    
    // Web Servie Call
    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 4);              
    String resCode = ResponseParser.responseCode;
    Assert.assertEquals(resCode,"1000");
    String Check = ResponseParser.check;         
    Assert.assertEquals(Check,"Blocked");

    
}

@Test(description = "To verify negative check for Card holder name Pattern Velocity Check") 

public void Test_Merchant_Cardholder_name_pattern_vel() throws IOException , InterruptedException   // Pass  

{
    // navigate to manage fraud detail page

    navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
    
    // select Merchant from dropdown         
    select_Dropdown("check", "Merchant");
    
    select_cls("boxin");
    
    // Input the value in Merchant Name textbox
    input("merchantName", Conf.getProperty("Merchant_Name"));
    
    
    select_cls("boxin");
    
    // Click on Search Button
    select("search-merchant");
    
    Thread.sleep(3000);
    
    // uncheck all selected checks    
    select("cb_grid");
    
    Thread.sleep(3000);
    
    select("cb_grid");
    
    Thread.sleep(3000);
    
	// select CARDHOLDERNAMEPATTERN_VEL check	
	select("jqg_grid_1213");
           
	select_cls("boxin");
	  
    //click save button        
    select("saveManageFruadsGlobal");
    
    select_cls("boxin");
    
    Thread.sleep(3000);
    
    // go to score configuration page
    navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
    
    // enter global in check type            
    select_Dropdown("check",  "Merchant");
    
    select_cls("boxin");
    
    // Input the value in Merchant Name textbox
    input("merchantName", Conf.getProperty("Merchant_Name"));
    
    select_cls("boxin");
    
    Thread.sleep(10000);

    ((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[3].selected='true'");
   
   
    //click show details button       
    select("btnSearchDetails");
    
    // Select Daily option value form combo box
    select_Dropdown("baseTime",  "DAILY"); 
            
    // Enter the value of Maximum Text field to 1
    input("r1max", "1");
        
    // Enter the value of First Score Text field to 30
    input("r1scores", "300");
    
    // Enter the value of Second Score Text field to 70
    input("r2scores", "700");

    // Enter the value of Third Score Text field to 100
    input("r3scores", "1000");


    // Enter the Save Button
    select("save");
    
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);

    
    // Web Servie Call
    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 7);              
    String resCode = ResponseParser.responseCode;
    Assert.assertEquals(resCode,"1000");
    String Check = ResponseParser.check;         
    Assert.assertEquals(Check,"Blocked");

}



@Test(description = "To verify negative check for Card holder name Negative DB") 


	public void Test_Merchant_Card_holder_name_nb() throws IOException, InterruptedException, InvalidFormatException   // Pass
	
	{
	   
		
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		Thread.sleep(3000);
		 
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		select("cb_grid");
	
		 Thread.sleep(3000);
		 
		// select Card holder name nb check
		select("jqg_grid_1212");
	
		 select_cls("boxin");
		 
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		// Wait for sometime to load other part
		Thread.sleep(3000);
	
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		//Common.select_Dropdown_wait("userType", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/select/option[3]", "Merchant");
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select Card holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[3]", "CARDHOLDER_NAME_NB");
	
	
		// click search
		//Common.select("chkTypeSearch");
	
		// click add new button
		select("addDetails");
	
	
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 7, 27));  // for taking value from Excel , temp commented, need to use		
		//Common.input("checkValue", "ankur");  // for taking value from Excel
		
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);

		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 8);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	}
 
@Test(description = "To verify negative check for Card Type") 


public void Test_Merchant_Card_Type() throws IOException, InterruptedException, InvalidFormatException   // Need to implement this check later, right now result is coming as "Not Found" bit it should come as "Block"

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));


	// select Merchant from dropdown..........................................
	select_Dropdown("check", "Merchant");


	//click on page anywhere to make merchant name text box appear.
	select_cls("boxin");

	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));

	select("search-merchant");
	Thread.sleep(3000);
	 
	// uncheck all selected checks
	select("cb_grid");
	
	Thread.sleep(3000);
	 
	select("cb_grid");

	Thread.sleep(3000);

	// select Card Type General check
	select("jqg_grid_1229");

	select_cls("boxin");
	
	//click save button		
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	// Wait for sometime to load other part
	Thread.sleep(3000);
	
	// navigate to Card Type Configuration page
	navigate_menu(Conf.getProperty("Menu_CardTypeConfig"));
			
	//click Add button		
	select("btnNewRole");
	
	Thread.sleep(2000);
	
	// select Credit from dropdown
	select_Dropdown("cardP", "DEBIT");
	
	Thread.sleep(2000);
	
	// select GOLD from dropdown
	select_Dropdown("categoryP", "GOLD");
			
	Thread.sleep(2000);
	
	// Input the value in Score textbox
	input("scoreP", "500");
	
	Thread.sleep(2000);
	
	// select ATM from dropdown
	select_Dropdown("brandP", "ATM");

	Thread.sleep(2000);
	
	// Input the value in Merchant Name textbox
	input("mName", Conf.getProperty("Merchant_Name"));
	
	Thread.sleep(2000);
	
	//click Save button		
	select("saveapiUser");
	
	Thread.sleep(3000);
	
	
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);

	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 9);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Not Found");   // this is temporay, need to change later to Block
	
	
}


@Test(description = "To verify negative check for Card Fraud") 


	public void Test_Merchant_CC_FRAUD() throws IOException, InterruptedException, InvalidFormatException   //  Pass
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		Thread.sleep(3000);
		 
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		 
		select("cb_grid");
	
		Thread.sleep(3000);
		 
		// select CC_Fraud nb check
		select("jqg_grid_1100");
	
		select_cls("boxin");
	
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		// Wait for some time
		Thread.sleep(3000);
	
	
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));


		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		//Common.select_Dropdown_wait("userType", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/select/option[3]", "Merchant");
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select CC_FRAUD from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[4]", "CC_FRAUD");
	
	
		// click search
		//Common.select("chkTypeSearch");
	
		// click add new button
		select("addDetails");
	
		System.out.println("CC No: " +  Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 9, 26));
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 9, 26));  // for taking value from Excel , temp commented, need to use		
		//Common.input("checkValue", "ankur");  // for taking value from Excel
		
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);

		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 10);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");

}

@Test(description = "To verify negative check for CC Fraud Series Negative DB") 


    public void Test_Merchant_CC_FARUD_SERIES() throws IOException, InterruptedException, InvalidFormatException    // Pass

{
   	
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	// select Merchant from dropdown..........................................
	select_Dropdown("check", "Merchant");


	//click on page anywhere to make merchant name text box appear.
	select_cls("boxin");

	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// click on Search Merchant button
	select("search-merchant");
	
	Thread.sleep(3000);
	// uncheck all selected checks
	select("cb_grid");
	
	Thread.sleep(3000);
	// Check all selected checks
	select("cb_grid");
	
	Thread.sleep(3000);
	
	// select CC_FARUD_SERIES  nb check
	select("jqg_grid_1101");
	
	select_cls("boxin");
	//click save button		
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	Thread.sleep(3000);

	// go to -ve db configuration page
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));

	// enter Merchant in check type		
	select_Dropdown("userType", "Merchant");
	//Common.select_Dropdown_wait("userType", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/select/option[3]", "Merchant");

	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));

	// select CC FRAUD SERIES from negative check drop down
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[5]", "CC_FRAUD_SERIES");

	// click add new button
	select("addDetails");

	// enter in -ve value		
	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 10, 31));  // for taking value from Excel , temp commented, need to use		
	//Common.input("checkValue", "ankur");  // for taking value from Excel
	
	// enter reason		
	input("reason", "fraud");

	// enter message
	input("message", "fraud");

	// click submit button
	select("btnSubmit");
	
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);

	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 11);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");
}

@Test(description = "To verify negative check for CC_VEL")  

public void Test_Merchant_CC_Vel() throws IOException  , InterruptedException  // Pass

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");

	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);
	// uncheck all selected checks    
	select("cb_grid");
	// Check all selected checks   
	
	Thread.sleep(3000);
	
	select("cb_grid");

	// select CC_VEL check    
	select("jqg_grid_1106");
	       
	select_cls("boxin");
	
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(4000);

	// go to score configuration page
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
	// enter Merchant in check type            
	select_Dropdown("check",  "Merchant");
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));

	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(10000);
	
	// Selecting the Desired drop down value using Javacscript inject 
	((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[6].selected='true'");
	
	Thread.sleep(3000);
	
	//click show details button       
	select("btnSearchDetails");

	// Select Daily option value form combo box
	select_Dropdown("baseTime",  "DAILY"); 
	
	// Enter the value of Maximum Text field to 1
	input("r1max", "2");
	    
	// Enter the value of First Score Text field to 30
	input("r1scores", "500");
	
	// Enter the value of Maximum Text field to 2
	input("r2max", "5");
	    
	// Enter the value of Second Score Text field to 70
	input("r2scores", "700");
	
	// Enter the value of Third Score Text field to 100
	input("r3scores", "1000");

	// Enter the Save Button
	select("save");
	
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);


    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 12);              
    String resCode = ResponseParser.responseCode;
    Assert.assertEquals(resCode,"1000");
	    
    String Check = ResponseParser.check;               
    Assert.assertEquals(Check,"Blocked");

}

@Test(description = "To verify negative check for CC_ZIP_VEL")  

	public void Test_Merchant_CC_ZIP_Vel() throws IOException , InterruptedException  // Pass 
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		Thread.sleep(3000);
		 
		// uncheck all selected checks    
		select("cb_grid");
		
		 Thread.sleep(3000);
		 
		// Check all selected checks    
		select("cb_grid");
		
		// select CC_ZIP_VEL check    
		select("jqg_grid_1231");
		 
		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(4000);
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter Merchant in check type            
		select_Dropdown("check",  "Merchant");

		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Wait for other actions to be performed
		Thread.sleep(10000);
		
		// Selecting the Desired drop down value using Javacscript inject 
		((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[6].selected='true'");
		
		
		//click show details button       
		select("btnSearchDetails");
		
		// Select Daily option value form combo box
		select_Dropdown("baseTime",  "DAILY"); 
		
		// Enter the value of Maximum Text field to 2
		input("r1max", "2");
		
		// Enter the value of First Score Text field to 30
		input("r1scores", "500");
		
		// Enter the value of Second Score Text field to 70
		input("r2scores", "700");
		
		// Enter the value of Third Score Text field to 100
		input("r3scores", "1000");
		
		// Enter the Save Button
		select("save");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);

     
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 13);              
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 14);       
		String Check = ResponseParser.check;               
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(Check,"Blocked");

	}

@Test(description = "To verify negative check for Card Type")  


	public void Test_Merchant_Currency_Country_Mismatch() throws IOException, InterruptedException, InvalidFormatException  // Pass
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		 
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select CURRENCY_COUNTRY_MISMATCH check    
		select("jqg_grid_1222");
		
		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "CountryCurrencyMatch"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
				
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 15);              
	    String resCode = ResponseParser.responseCode;
	    Assert.assertEquals(resCode,"1000");
	    
	    String Check = ResponseParser.CountryCurrencyMatch;               
	    Assert.assertEquals(Check,"Yes");
	
	}


@Test(description = "To verify negative check for DISTINCT_BIN_COUNTRY")  

public void Test_Merchant_Distinct_Bin_Country() throws IOException // Pass,
, InterruptedException

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");
	
	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);	
	
	// uncheck all selected checks    
	select("cb_grid");
	
	Thread.sleep(3000);
	 
	// Check all selected checks    
	select("cb_grid");
	
	// select Distinct_Bin_Country check    
	select("jqg_grid_1227");
	
	 select_cls("boxin");
	 
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	
	Thread.sleep(3000);
	
	// go to score configuration page
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
	// enter Merchant in check type            
	select_Dropdown("check",  "Merchant");
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(10000);
	
	// Selecting the Desired drop down value using Javacscript inject 
	((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[7].selected='true'");
	
	
	//click show details button       
	select("btnSearchDetails");
	
	// Select Daily option value form combo box
	select_Dropdown("baseTime",  "DAILY"); 
	
	
	// Clear the value of Maximum Text field value
	input("r1max", "");
	
	// Enter the value of Maximum Text field to 2
	input("r1max", "2");
	
	// Clear the value of Score Text field value
	input("r1scores", "");
	
	// Enter the value of First Score Text field to 30
	input("r1scores", "600");
	
	// Clear the value of Maximum Text field value
	input("r2max", "");
	
	// Enter the value of Maximum Text field to 2
	input("r2max", "4");
	
	// Enter the value of Second Score Text field to 70
	input("r2scores", "700");
	
	// Enter the value of Third Score Text field to 100
	input("r3scores", "1000");
	
	// Enter the Save Button
	select("save");
	    
	
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
			
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 16);              
	String resCode = ResponseParser.responseCode;
	Assert.assertEquals(resCode,"1000");
	
	String Check = ResponseParser.check;               
	Assert.assertEquals(Check,"Blocked");
	
}

 
@Test(description = "To verify negative check for DISTINCT_IP_COUNTRY") 

	public void Test_Merchant_Distinct_IP_Country() throws IOException   // Pass
	, InterruptedException
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		 
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		// Check all selected checks    
		select("cb_grid");
		
		// select Distinct_IP_Country check    
		select("jqg_grid_1226");
		
		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter Merchant in check type            
		select_Dropdown("check",  "Merchant");
	
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Wait for other actions to be performed
		Thread.sleep(10000);
		
		// Selecting the Desired drop down value using Javacscript inject 
		((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[8].selected='true'");
		
		
		//click show details button       
		select("btnSearchDetails");
		
		// Select Daily option value form combo box
		select_Dropdown("baseTime",  "DAILY"); 
		
		// Clear the value of Maximum Text field value
		input("r1max", "");
		
		// Enter the value of Maximum Text field to 2
		input("r1max", "2");
		
		// Clear the value of Score Text field value
		input("r1scores", "");
		
		// Enter the value of First Score Text field to 30
		input("r1scores", "500");
		
		// Enter the value of Maximum Text field 3 
		input("r2max", "3");
		
		// Enter the value of Second Score Text field to 70
		input("r2scores", "700");
		
		// Enter the value of Third Score Text field to 100
		input("r3scores", "1000");
		
		// Enter the Save Button
		select("save");
		    
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
				
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 18);              
	    String resCode = ResponseParser.responseCode;
	    Assert.assertEquals(resCode,"1000");
	    
	    String Check = ResponseParser.check;               
	    Assert.assertEquals(Check,"Blocked");
	
	}
	

@Test(description = "To verify negative check for EMAIL_PATTERN_VEL") 

	public void Test_Merchant_Email_Pattern_Vel() throws IOException , InterruptedException   // Pass
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		 
		// uncheck all selected checks    
		select("cb_grid");
		
		 Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select Email_Pattern_Vel check    
		select("jqg_grid_1162");
		
		select_cls("boxin");
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter Merchant in check type            
		select_Dropdown("check",  "Merchant");
	
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Wait for other actions to be performed
		Thread.sleep(10000);
		
		// Selecting the Desired drop down value using Javacscript inject 
		((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[9].selected='true'");
		
		
		//click show details button       
		select("btnSearchDetails");
		
		// Select Daily option value form combo box
		select_Dropdown("baseTime",  "DAILY"); 
		
		
		// Enter the value of Maximum Text field to 2
		input("r1max", "2");
	
		// Enter the value of First Score Text field to 30
		input("r1scores", "500");
		
		
		// Enter the value of Maximum Text field to 2
		input("r2max", "5");
		
		// Enter the value of Second Score Text field to 70
		input("r2scores", "700");
		
		// Enter the value of Third Score Text field to 100
		input("r3scores", "1000");
		
		// Enter the Save Button
		select("save");
	    
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);

				
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 20);              
	    String resCode = ResponseParser.responseCode;
	    Assert.assertEquals(resCode,"1000");
	    
	    String Check = ResponseParser.check;               
	    Assert.assertEquals(Check,"Blocked");
	
	}

@Test(description = "To verify negative check for EMAIL_VEL")  

public void Test_Merchant_Email_Vel() throws IOException , InterruptedException  // Pass

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");

	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);
	
	// uncheck all selected checks    
	select("cb_grid");
	// Check all selected checks   
	
	Thread.sleep(3000);
	
	select("cb_grid");
	
	// select Email_Vel check    
	select("jqg_grid_1110");
	
	 select_cls("boxin");
	 
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	Thread.sleep(3000);
	
	// go to score configuration page
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
	// enter Merchant in check type            
	select_Dropdown("check",  "Merchant");

	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(10000);
	
	// Selecting the Desired drop down value using Javacscript inject 
	((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[10].selected='true'");
	
	Thread.sleep(3000);
	
	//click show details button       
	select("btnSearchDetails");
	
	// Select Daily option value form combo box
	select_Dropdown("baseTime",  "DAILY"); 
	
	
	// Enter the value of Maximum Text field to 2
	input("r1max", "2");

	// Enter the value of First Score Text field to 30
	input("r1scores", "500");
	
	// Enter the value of Maximum Text field to 5
	input("r2max", "5");
	
	// Enter the value of Second Score Text field to 70
	input("r2scores", "700");
	
	// Enter the value of Third Score Text field to 100
	input("r3scores", "1000");
	
	// Enter the Save Button
	select("save");
	
	Thread.sleep(3000);
    
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);

	
    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 21);              
    String resCode = ResponseParser.responseCode;
    Assert.assertEquals(resCode,"1000");
    
    String Check = ResponseParser.check;               
    Assert.assertEquals(Check,"Blocked");
}

@Test(description = "To verify negative check for Fraud Account Negative DB")  


	public void Test_Merchant_Fraud_Account_nb() throws IOException, InterruptedException, InvalidFormatException // Pass
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
	
		Thread.sleep(3000);
		
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		
		select("cb_grid");
			

		// select Fraud Accopunt nb check
		select("jqg_grid_1143");
		
		select_cls("boxin");
	
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
	
		// Wait for sometime to load other part
		Thread.sleep(3000);
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		//Common.select_Dropdown_wait("userType", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/select/option[3]", "Merchant");
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[6]", "FRAUD_ACCOUNT");
	
	
		// click search
		//Common.select("chkTypeSearch");
	
		// click add new button
		select("addDetails");
		
		System.out.println("Value of Fraud Account : " + Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 21, 36));
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant",21, 36));  // for taking value from Excel		
	
		// enter reason		
		input("reason", "fraud");

		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 22);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	    
	    
	}

@Test(description = "To verify General check for FREE EMAIL") 

	public void Test_Merchant_Free_Email() throws IOException , InterruptedException  // Pass
		
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
			
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		 
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select FREE_EMAILDOMIAN check    
		select("jqg_grid_1205");
		       
		select_cls("boxin");
		 
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "FreeEmailDomain"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	        
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 23);              
	    String resCode = ResponseParser.responseCode;	    
	    Assert.assertEquals(resCode,"1000");	    
	    
	    String Femail =ResponseParser.FreeEmailDomain;   
	    System.out.println("Free Mail Response : " + Femail);
	    
	    Assert.assertEquals(Femail,"Yes");
	 
	}
	
@Test(description = "To verify General check for FundingSource Pattern Velocity")  // Pass

public void Test_Merchant_FundingSourcePattern_Vel() throws IOException , InterruptedException  // Funding Source means Bank Code

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");
	
	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);
	
	// uncheck all selected checks    
	select("cb_grid");
	// Check all selected checks    
	
	Thread.sleep(3000);
	
	select("cb_grid");
	
	// select Funding Source Pattern Velocity check    
	select("jqg_grid_1149");
	       
	select("search-merchant");
	
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	Thread.sleep(3000);
	
	
	// go to score configuration page
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
	// enter Merchant in check type            
	select_Dropdown("check",  "Merchant");

	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(10000);
	
	// Selecting the Desired drop down value using Javacscript inject 
	//((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[14].selected='true'");  change by hari
	((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[11].selected='true'");
	
	Thread.sleep(2000);
	
	//click show details button       
	select("btnSearchDetails");
	
	// Select Daily option value form combo box
	select_Dropdown("baseTime",  "DAILY"); 
	
	
	// Enter the value of Maximum Text field to 2
	input("r1max", "2");

	// Enter the value of First Score Text field to 30
	input("r1scores", "500");
	
	// Enter the value of Maximum Text field to 2
	input("r2max", "5");
	
	// Enter the value of Second Score Text field to 70
	input("r2scores", "700");
	
	// Enter the value of Third Score Text field to 100
	input("r3scores", "1000");
	
	// Enter the Save Button
	select("save");
    
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
        
    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 24);              
    String resCode = ResponseParser.responseCode;	    
    Assert.assertEquals(resCode,"1000");	    
    
    String Check =ResponseParser.check;   
    System.out.println("check Response : " + Check);
    
    Assert.assertEquals(Check,"Blocked");
    	 
}

@Test(description = "To test the Merchant level check HIGH RISK COUNTRY")

public void Test_Merchant_HighRiskCountry() throws IOException,InterruptedException  // Pass
{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");
	
	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);
	
	// uncheck all selected checks    
	select("cb_grid");
	
	Thread.sleep(3000);
	 
	// Check all selected checks    
	select("cb_grid");
	
	// select High Risk Country Velocity check    
	select("jqg_grid_1230");
	
	select_cls("boxin");
	 
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	Thread.sleep(3000);
	
	
	// navigate to High Risk Country page
	navigate_menu(Conf.getProperty("Menu_HighRiskCountry"));
	
	// Enter Merchant Name in Text Box
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// click on Search Button       
	select("searchDetails");
	
	// uncheck all selected checks    
	select("cb_neglisttable");
	// Check all selected checks    
	select("cb_neglisttable");
	
	// select US as Country
	//select("jqg_neglisttable_US");  change by hari
	
	//select American Samoa as country
	select("jqg_neglisttable_AS");
	// click on save button
	select("negListSave");
	
	String[] myArgs = { "HighRiskCountry"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
    // call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 25);              
	String resCode = ResponseParser.responseCode;	    
	Assert.assertEquals(resCode,"1000");	    
	
	String Check =ResponseParser.HighRiskCountry;   
	System.out.println("check Response : " + Check);   // if found then response shold be "Yes", else "No", if field is missing then "Not Applicable"
	
	Assert.assertEquals(Check,"Yes");
	
}

@Test(description = "To verify General check for IP ADDRESS COUNTRY") 

	public void Test_Merchant_IPADDRESSCOUTRY_GEN() throws IOException , InterruptedException  //Pass
	
	{
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		 
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select IP_ADDRESS_COUNTRY General check  
		select("jqg_grid_1208");

		select_cls("boxin");
		 
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "IPAddressCountry"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
			        
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 26);              
	    String resCode = ResponseParser.responseCode;	    
	    Assert.assertEquals(resCode,"1000");	    
	    
	    String Check =ResponseParser.IPAddressCountry;   
	    System.out.println("check Response : " + Check);   // if found then response shold be "Yes", else "No", if field is missing then "Not Applicable"
	    
	    Assert.assertEquals(Check,"Yes");
	    
	   
	}

@Test(description = "To verify General check for IP BIN COUNTRY") 

	public void Test_Merchant_IPBINCOUNTRY_GEN() throws IOException , InterruptedException  //Pass
	
	{
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select IP_BIN_COUNTRY General check  
		select("jqg_grid_1206");
		
		select_cls("boxin");
		 
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "IPCountryBIN"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
			        
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 27);              
	    String resCode = ResponseParser.responseCode;	    
	    Assert.assertEquals(resCode,"1000");	    
	    
	    String Check =ResponseParser.IPCountryBIN;   
	    System.out.println("check Response : " + Check);   // if found then response shold be "Yes", else "No", if field is missing then "Not Applicable"
	    
	    Assert.assertEquals(Check,"Yes");
	    
	   
	}

@Test(description = "To verify Merchant check for MOBILE_PATTERN_VEL") 

public void Test_Merchant_MOBILE_PATTERN_VEL() throws IOException , InterruptedException   // Pass

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");
	
	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);
	
	// uncheck all selected checks    
	select("cb_grid");
	// Check all selected checks    
	
	Thread.sleep(3000);
	
	select("cb_grid");
	
	// select MOBILE_PATTERN_VEL check    
	select("jqg_grid_1228");
	
	select_cls("boxin");
	
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	Thread.sleep(3000);
	
	// go to score configuration page
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
	// enter Merchant in check type            
	select_Dropdown("check",  "Merchant");

	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(10000);
	
	// Selecting the Desired drop down value using Javacscript inject 
	((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[12].selected='true'");
	
	Thread.sleep(3000);
	
	//click show details button       
	select("btnSearchDetails");
	
	// Select Daily option value form combo box
	select_Dropdown("baseTime",  "DAILY"); 
	
	
	// Enter the value of Maximum Text field to 2
	input("r1max", "2");

	// Enter the value of First Score Text field to 30
	input("r1scores", "500");
	
	// Enter the value of Maximum Text field to 2
	input("r2max", "5");
	
	// Enter the value of Second Score Text field to 70
	input("r2scores", "700");
	
	// Enter the value of Third Score Text field to 100
	input("r3scores", "1000");
	
	// Enter the Save Button
	select("save");
	
	// Enter the Save Button
	select("save");
	
	Thread.sleep(3000);
    
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
			
    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 29);              
    String resCode = ResponseParser.responseCode;
    Assert.assertEquals(resCode,"1000");
    
    String Check = ResponseParser.check; 
    if(Check == "Blocked") {    // this is a temp code, need to use only blocked at later stage
    	Assert.assertEquals(Check,"Blocked");
    }else if(Check == "Pass") {
    	Assert.assertEquals(Check,"Pass");    	
    }else if(Check == "Suspected") {
    	Assert.assertEquals(Check,"Suspected");
    }
    
    	    

}


@Test(description = "To verify Merchant check for Mobile_VEl") 

public void Test_Merchant_Mobile_VEl() throws IOException , InterruptedException   // Pass

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	// select Merchant from dropdown         
	select_Dropdown("check", "Merchant");
	
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear
	select_cls("boxin");
	
	// Click on Search Button
	select("search-merchant");
	
	Thread.sleep(3000);
	
	// uncheck all selected checks    
	select("cb_grid");
	
	Thread.sleep(3000);
	
	// Check all selected checks    
	select("cb_grid");
	
	// select Mobile_VEl check    
	select("jqg_grid_1136");
	
	select_cls("boxin");
	 
	//click save button        
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");
	
	Thread.sleep(3000);
	
	// go to score configuration page
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
	// enter Merchant in check type            
	select_Dropdown("check",  "Merchant");

	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));
	
	// Click on Free Area to make Textbox appear 
	select_cls("boxin");
	
	// Wait for other actions to be performed
	Thread.sleep(10000);
	
	// Selecting the Desired drop down value using Javacscript inject 
	((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[13].selected='true'");
	
	
	//click show details button       
	select("btnSearchDetails");
	
	Thread.sleep(3000);
	
	// Select Daily option value form combo box
	select_Dropdown("baseTime",  "DAILY"); 
	
	
	// Enter the value of Maximum Text field to 1
	input("r1max", "3");

	// Enter the value of First Score Text field to 30
	input("r1scores", "500");
	
	
	// Enter the value of Maximum Text field to 3
	input("r2max", "5");
	
	// Enter the value of Second Score Text field to 70
	input("r2scores", "700");
	
	// Enter the value of Third Score Text field to 100
	input("r3scores", "1000");
	
	// Enter the Save Button
	select("save");
	
	Thread.sleep(3000);
    
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
			
    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 30);              
    String resCode = ResponseParser.responseCode;
    Assert.assertEquals(resCode,"1000");
    
    String Check = ResponseParser.check;               
   
    if(Check == "Blocked") {    // this is a temp code, need to use only blocked at later stage
    	Assert.assertEquals(Check,"Blocked");
    }else if(Check == "Pass") {
    	Assert.assertEquals(Check,"Pass");    	
    }else if(Check == "Suspected") {
    	Assert.assertEquals(Check,"Suspected");
    }
    
    
}

@Test(description = "To verify General check for PHONENO_VALIDATION") 

	public void Test_Merchant_PHONENO_VALIDATION_GEN() throws IOException , InterruptedException  // pass
	
	{
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select PHONENO_VALIDATION General check  
		select("jqg_grid_1218");
		
		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "ValidPhoneNumber"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
			        
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 31);              
	    String resCode = ResponseParser.responseCode;	    
	    Assert.assertEquals(resCode,"1000");
	    
	    String check = ResponseParser.ValidPhoneNo;
	    Assert.assertEquals(check, "Yes");
	
	}

@Test(description = "To verify General check for PREPAID_CARD") 

	public void Test_Merchant_PREPAID_CARD_GEN() throws IOException , InterruptedException  // pass
	
	{
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		 
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select PREPAID_CARD General check  
		select("jqg_grid_1204");

		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "PrepaidCard"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
			        
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 32);              
	    String resCode = ResponseParser.responseCode;	    
	    Assert.assertEquals(resCode,"1000");
	    
	    String check = ResponseParser.PrepaidCard;
	    Assert.assertEquals(check, "Yes");
	
	}

@Test(description = "To verify General check for THIRDPARTYPURCHASE") 

	public void Test_Merchant_THIRDPARTYPURCHASE_GEN() throws IOException , InterruptedException  // pass
	
	{
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		 
		// Check all selected checks    
		select("cb_grid");
		
		// select THIRDPARTYPURCHASE General check  
		select("jqg_grid_1224");
		
		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		String[] myArgs = { "ThirdPartyPurchase"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
			        
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 33);              
	    String resCode = ResponseParser.responseCode;	    
	    Assert.assertEquals(resCode,"1000");
	    
	    String check = ResponseParser.ThirdPartyPurchase;
	    Assert.assertEquals(check, "Yes");
	
	}

@Test(description = "To verify Merchant check for UCID_VEL") 

	public void Test_Merchant_UCID_VEL() throws IOException , InterruptedException   // Pass
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select Merchant from dropdown         
		select_Dropdown("check", "Merchant");
		
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear
		select_cls("boxin");
		
		// Click on Search Button
		select("search-merchant");
		
		Thread.sleep(3000);
		
		// uncheck all selected checks    
		select("cb_grid");
		
		Thread.sleep(3000);
		
		// Check all selected checks    
		select("cb_grid");
		
		// select UCID_VEL check    
		select("jqg_grid_1138");

		select_cls("boxin");
		
		//click save button        
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
		
		Thread.sleep(3000);
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter Merchant in check type            
		select_Dropdown("check",  "Merchant");
	
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
		
		// Click on Free Area to make Textbox appear 
		select_cls("boxin");
		
		// Wait for other actions to be performed
		Thread.sleep(10000);
		
		// Selecting the Desired drop down value using Javacscript inject 
		//((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[14].selected='true'");//change by hari
		((JavascriptExecutor)driver).executeScript("document.getElementById('scopeFor')[13].selected='true'");
		
		//click show details button       
		select("btnSearchDetails");
		
		// Select Daily option value form combo box
		select_Dropdown("baseTime",  "DAILY"); 
		
		
		// Enter the value of Maximum Text field to 2
		input("r1max", "3");
	
		// Enter the value of First Score Text field to 30
		input("r1scores", "500");
		
		// Enter the value of Second Score Text field to 70
		input("r2scores", "700");
		
		// Enter the value of Third Score Text field to 100
		input("r3scores", "1000");
		
		// Enter the Save Button
		select("save");
	    
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
				
	    new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 34);              
	    String resCode = ResponseParser.responseCode;
	    Assert.assertEquals(resCode,"1000");
	    
	    String Check = ResponseParser.check;               
	    Assert.assertEquals(Check,"Blocked");
	    
	}

@Test(description = "To verify negative check for Valid_Domain Negative DB")  


	public void Test_Merchant_Valid_Domain_nb() throws IOException, InterruptedException, InvalidFormatException // Pass
	
	{
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		
		Thread.sleep(3000);
	
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		
		select("cb_grid");
			
	
		// select Valid_Domain nb check
		select("jqg_grid_1211");
				
		select_cls("boxin");
	
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
	
		// Wait for sometime to load other part
		Thread.sleep(3000);
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		//Common.select_Dropdown_wait("userType", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/select/option[3]", "Merchant");
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[7]", "VALID_DOMAIN");
	
	
		// click search
		//Common.select("chkTypeSearch");
	
		// click add new button
		select("addDetails");
		
		//Excel.get_DomainName(Excel.getData("Resourses\\Data\\API_Data_Global.xlsx", 58, 39));
		System.out.println("Value of Domain: " + Excel.get_DomainName(Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 34, 39)));
		// enter in -ve value		
		input("checkValue", Excel.get_DomainName(Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant",34, 39)));		
	
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 35);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	    
	    
	}
 	
@Test(description = "To verify negative check for Valid_Email Negative DB")  


	public void Test_Merchant_Valid_Email_nb() throws IOException, InterruptedException, InvalidFormatException // Pass
	
	{
	
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		
		Thread.sleep(3000);
	
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		
		select("cb_grid");
			
	
		// select Valid_Email nb check
		select("jqg_grid_1103");
	
		select_cls("boxin");
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
	
		// Wait for sometime to load other part
		Thread.sleep(3000);
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[8]", "VALID_EMAIL");
	

		// click add new button
		select("addDetails");
		
		System.out.println("Value of Domain: " + Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 35, 39));
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 35, 39));		
	
	
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 36);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	
	    
	}

@Test(description = "To verify negative check for Valid_IP Negative DB")  


	public void Test_Merchant_Valid_IP_NB() throws IOException, InterruptedException, InvalidFormatException // Pass
	
	{
	
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		
		Thread.sleep(3000);
		
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		
		select("cb_grid");
			
	
		// select Valid_IP nb check
		select("jqg_grid_1102");
	
		select_cls("boxin");
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
	
		// Wait for sometime to load other part
		Thread.sleep(3000);
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[9]", "VALID_IP");
	
	
		// click add new button
		select("addDetails");
		
		System.out.println("Value of IP: " + Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 36, 40));
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 36, 40));		
	
	
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 37);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	
	    
	}
@Test(description = "To verify negative check for VALID_MOBILE Negative DB")  


	public void Test_Merchant_VALID_MOBILE_NB() throws IOException, InterruptedException, InvalidFormatException // Pass
	
	{
	
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
	
		// select Merchant from dropdown..........................................
		select_Dropdown("check", "Merchant");
	
	
		//click on page anywhere to make merchant name text box appear.
		select_cls("boxin");
	
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
		select("search-merchant");
		
		Thread.sleep(3000);
	
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(3000);
		
		select("cb_grid");
			
	
		// select Valid_MOBILE nb check
		select("jqg_grid_1135");
	
		select_cls("boxin");
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select_cls("boxin");
	
		// Wait for sometime to load other part
		Thread.sleep(3000);
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	
		// enter Merchant in check type		
		select_Dropdown("userType", "Merchant");
		
	
		//Common.driver.wait(3000);
		select_cls("boxin");
		
		// Input the value in Merchant Name textbox
		input("merchantName", Conf.getProperty("Merchant_Name"));
	
	
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[10]", "VALID_MOBILE");
	
	
		// click add new button
		select("addDetails");
		
		System.out.println("Value of Mobile: " + Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 37, 41));
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 37, 41));		
	
	
		// enter reason		
		input("reason", "fraud");
	
		// enter message
		input("message", "fraud");
	
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 38);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
	    Assert.assertEquals(resCode, "1000");
	    Assert.assertEquals(check, "Blocked");
	
	    
	}
	

@Test(description = "To verify negative check for VALID_UCID Negative DB")  


public void Test_Merchant_VALID_UCID_NB() throws IOException, InterruptedException, InvalidFormatException // Pass

{


	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));


	// select Merchant from dropdown..........................................
	select_Dropdown("check", "Merchant");


	//click on page anywhere to make merchant name text box appear.
	select_cls("boxin");

	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));

	select("search-merchant");
	
	Thread.sleep(3000);

	// uncheck all selected checks
	select("cb_grid");
	
	Thread.sleep(3000);
	
	select("cb_grid");
		

	// select Valid_UCID nb check
	select("jqg_grid_1134");
	
	select_cls("boxin");

	//click save button		
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");

	// Wait for sometime to load other part
	Thread.sleep(3000);
	
	// go to -ve db configuration page
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));


	// enter Merchant in check type		
	select_Dropdown("userType", "Merchant");
	

	//Common.driver.wait(3000);
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));


	// select account holder name DB from negative check drop down
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[11]", "VALID_UCID");


	// click add new button
	select("addDetails");
	
	System.out.println("Value of UCID: " + Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant",38, 42));
	// enter in -ve value		
	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 38, 42));		


	// enter reason		
	input("reason", "fraud");

	// enter message
	input("message", "fraud");

	// click submit button
	select("btnSubmit");
	
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 39);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");

    
}

@Test(description = "To verify negative check for ZIP_CODE Negative DB")  


public void Test_Merchant_ZIP_CODE_NB() throws IOException, InterruptedException, InvalidFormatException // Pass

{


	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));


	// select Merchant from dropdown..........................................
	select_Dropdown("check", "Merchant");


	//click on page anywhere to make merchant name text box appear.
	select_cls("boxin");

	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));

	select("search-merchant");
	
	Thread.sleep(3000);

	// uncheck all selected checks
	select("cb_grid");
	
	Thread.sleep(3000);
	
	select("cb_grid");
		

	// select ZIP_CODE nb check
	select("jqg_grid_1225");
	
	select_cls("boxin");

	//click save button		
	select("saveManageFruadsGlobal");
	
	select_cls("boxin");

	// Wait for sometime to load other part
	Thread.sleep(3000);
	
	// go to -ve db configuration page
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));


	// enter Merchant in check type		
	select_Dropdown("userType", "Merchant");
	

	//Common.driver.wait(3000);
	select_cls("boxin");
	
	// Input the value in Merchant Name textbox
	input("merchantName", Conf.getProperty("Merchant_Name"));


	// select account holder name DB from negative check drop down
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div[2]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/select/option[12]", "ZIP_CODE");


	// click add new button
	select("addDetails");
	
	//  value = 54 + "-" "52"
	String Val_Country = Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx","Merchant", 39, 54);
	String Val_Zip = Excel.getData("Resourses\\Data\\API_Data_Merchant.xlsx", "Merchant",39, 52);
	
	String Final_Val = Val_Country + "-" + Val_Zip;
	System.out.println("Value of Mobile: " + Final_Val);
	
	// enter in -ve value		
	input("checkValue", Final_Val);		


	// enter reason		
	input("reason", "fraud");

	// enter message
	input("message", "fraud");

	// click submit button
	select("btnSubmit");
	
	String[] myArgs = { "check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Merchant.xlsx", 40);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");

    
}




//  End of Test Suite  

//@AfterSuite
@AfterTest

	public void TestTearDown() throws InterruptedException
	{
		
		
		
		System.out.println("Test has completed...Reports will be mailed");
		driver.quit();
	}




}
