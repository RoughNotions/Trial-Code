package com.apc.FMS;

import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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


public class Htest extends BaseTest {

//@BeforeSuite
	@BeforeTest

    public void TestSetup() throws InvalidFormatException, IOException, InterruptedException{
	
	// updating API data file
	
		UpdateDataFile.update("Resourses\\Data\\API_Data_Global.xlsx");
	
		System.out.println("System is initiating the TestSuite_GlobalChecks suite");
		
		login();
		System.out.println("api_url" +api_url);
		
		DisableAllGlobalChecks();
		DisableAllMerchantChecks();
}



/*
@Test

    public void Test_global_accountholder_name_pattern_vel() throws IOException, InterruptedException

    {
    
	
	    // navigate to manage fraud detail page

		//navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		String[] arr_elem = {"Manage Setting", "Configuration","Manage Fraud Details"};						
		Navigate_Menu_From_To_New(driver,arr_elem);
		
		// select global from dropdown		
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		select_cls("boxin"); 
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");

		// select account holder name pattern velocity check
		select("jqg_grid_1148");

		//click save button		
		select("saveManageFruadsGlobal");

		//select("saveManageFruadsGlobal");
		
		// go to score configuration page
		//navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		String[] arr_elem1 = {"Manage Setting", "Configuration","Score Configuration"};						
		Navigate_Menu_From_To_New(driver,arr_elem1);
		
		// enter global in check type		
		select_Dropdown("check", "Global");
		
		//select("tabel-heading");
		select_cls("boxin"); 
		
		//System.out.println("dropdown value: "+driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div[1]/div/div/div[2]/div[3]/table/tbody/tr/td[2]/select/option[2]")).getText());
		//select account holder in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div[2]/div[1]/div/div/div[2]/div[3]/table/tbody/tr/td[2]/select/option[2]", "ACCOUNTHOLDERNAMEPATTERN_VEL");
        Thread.sleep(2000);
		//click show details button		
		select("btnSearchDetails");
		
		
		WebElement no_of_hour= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rg2min")));
		no_of_hour.sendKeys(Keys.CONTROL+"a");
		no_of_hour.sendKeys(Keys.DELETE);
		no_of_hour.sendKeys("1");
		
		
		WebElement no_of_time= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rg1min")));
		no_of_time.sendKeys(Keys.CONTROL+"a");
		no_of_time.sendKeys(Keys.DELETE);
		no_of_time.sendKeys("1");		
		
		select("saveGlobal");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 2);		
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
	
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 3);	
		String Check = ResponseParser.check;		   
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(Check,"Blocked");
    }

@Test(description = "To verify negative check for account holder name")
	
	
	public void Test_global_account_holder_name_nb() throws IOException, InterruptedException, InvalidFormatException

	{
	   
		
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		select_cls("boxin"); 
				
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		
		// select account holder name nb check
		select("jqg_grid_1168");
		
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		
		// go to -ve db configuration page
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));

		
		// enter global in check type		
		select_Dropdown("userType", "Global");
		
		// select account holder name DB from negative check drop down
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[2]", "ACCOUNTHOLDER_NAME_NB");
		
		
		// click search
		select("chkTypeSearch");
		
		// click add new button
		select("addDetails");

		
		// enter in -ve value		
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx", "Sheet1",3, 18));
		
		// enter reason		
		input("reason", "fraud");
		
		// enter message
		input("message", "fraud");
		
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 4);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
        Assert.assertEquals(resCode, "1000");
        Assert.assertEquals(check, "Blocked");
	}
*/
@Test(description = "To verify global AIFP velocity check ") 


    public void Test_global_AFIP_VEL() throws IOException, InterruptedException

    {
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown	
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
		select_cls("boxin"); 
		
		//click on page anywhere to make merchant name text box appear.		
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select bin_cc velocity check
		select("jqg_grid_1109");
		select("jqg_grid_1135");
		
		//click save button				
		select("saveManageFruadsGlobal");
		
		//select("saveManageFruadsGlobal");
		select("saveManageFruadsGlobal");
		
		// go to negative db config
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
		
		// enter global in check type
		select_Dropdown("userType", "Global");
		
		// select("tabel-heading");
		select_cls("boxin"); 
		
		// select valid moblie from negative check drop down		
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[10]", "VALID_MOBILE");
		
		// click search		
		select("chkTypeSearch");
		
		// click add new button		
		select("addDetails");
		
		// enter in -ve value	
		input("checkValue", Input.getProperty("Mobile_Number"));
		
		// enter reason
		input("reason", "fraud");
		
		// enter message
		input("message", "fraud");
		
		// click submit button
		select("btnSubmit");
		
		select("site_body");
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Fraud_Configuration"));
		
		// enter global in check type
		select_xpath("//*[@id='1']/td[3]/div/div[1]/span");
		input("1_config_value", "1");
		select_xpath("//*[@id='1']/td[3]/div/div[2]/span");
		select_xpath("//*[@id='2']/td[3]/div/div[1]/span");		
		input("2_config_value", "1");		
		select_xpath("//*[@id='2']/td[3]/div/div[2]/span");
		
		
		String[] myArgs = {"check1"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 5);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 6);
		String check = ResponseParser.check1;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		//Common.Common.driver.close();
    }
/*	
@Test(description = "To verify BIN_CC velocity check ")


    public void Test_global_bin_cc_vel() throws IOException, InterruptedException

    {
		// navigate to manage fraud detail page	
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select bin_cc velocity check
		select("jqg_grid_1151");
	
	
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type
		select_Dropdown("check", "Global");
		// select("tabel-heading");
		
		//select bin_cc in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[4]", "BIN_CC_VEL");
		
		// select("tabel-heading");
		
		//click search button
        select("btnSearchDetails");
		
		WebElement no_of_hour= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rg2min")));
		no_of_hour.sendKeys(Keys.CONTROL+"a");
		no_of_hour.sendKeys(Keys.DELETE);
		no_of_hour.sendKeys("1");

		WebElement no_of_time= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rg1min")));
		no_of_time.sendKeys(Keys.CONTROL+"a");
		no_of_time.sendKeys(Keys.DELETE);
		no_of_time.sendKeys("1");		
		
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 7);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode, "1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",8);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode, "1000");		
		Assert.assertEquals(check, "Blocked");
	    //Common.Common.driver.close();
    }

@Test
    public void Test_global_bin_email_vel() throws IOException, InterruptedException

    {

		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appea
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select bin_email velocity check
		select("jqg_grid_1176");
			
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		// go to score configuration page.................................................
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// Select GLobal from Dropdown
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		
		//select bin_email in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[5]", "BIN_EMAIL_VEL");
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
		
        // Enter value
		input("rg2min", "1");
				
		input("rg1min","1");
		
		// Click on save button
		select("saveGlobal");
		
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",9);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode, "1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",10);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode, "1000");
		Assert.assertEquals(check, "Blocked");
	    //Common.Common.driver.close();					
				
    }	

@Test(description = "To verify global bin IP velocity check")


    public void Test_global_bin_ip_vel() throws IOException, InterruptedException

    {

		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
		
		// select global from dropdown	
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select bin_ip velocity check
		select("jqg_grid_1177");
				
		//click save button				
		select("saveManageFruadsGlobal");
						
		select("saveManageFruadsGlobal");
						
		// go to score configuration page				
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
						
		// enter global in check type								
		select_Dropdown("check", "Global");
						
		// select("tabel-heading");
		//select bin_ip in score for drop down
						
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[6]", "BIN_IP_VEL");
						
		// select("tabel-heading");
										
		//click search button
		select("btnSearchDetails");
						
	    input("rg2min", "1");
	    input("rg1min", "1");
								
						
		select("saveGlobal");						
						
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",11);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode, "1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",12);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode, "1000");		
		Assert.assertEquals(check, "Blocked");
	    //Common.Common.driver.close();
						
    }

@Test(description = "To verify global bin mobile velocity check")


    public void Test_global_bin_mobile_vel() throws IOException, InterruptedException

    {
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
			
		// uncheck all selected checks	
		select("cb_grid");
		select("cb_grid");
		
		// select bin_mobile velocity check
		select("jqg_grid_1178");
			
		//click save button			
		select("saveManageFruadsGlobal");
	
		select("saveManageFruadsGlobal");
		
		// go to score configuration page.................................................
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type
		select_Dropdown("check", "Global");
		// select("tabel-heading");
		
		//select bin_mobile in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[7]", "BIN_MOBILE_VEL");
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
		
		input("rg2min", "1");
		
		input("rg1min","1");	
		
		// Click on save button
		select("saveGlobal");
		
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",13);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",14);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
	    //Common.Common.driver.close();

    }

@Test(description = "To verify card holder name pattern velocity check")


    public void Test_global_card_holder_name_pattern_vel() throws IOException, InterruptedException

    {

		// navigate to manage fraud detail page

		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
			
		// uncheck all selected checks	
		select("cb_grid");
		select("cb_grid");

		//select card holder name pattern velocity from score for dropdown
		select("jqg_grid_1213");
	
		//click save button		
		select("saveManageFruadsGlobal");
	
		select("saveManageFruadsGlobal");
		// go to score configuration page.................................................	
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
			
		// enter global in check type		
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");

		//select card holder name pattern vel  in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[8]", "CARDHOLDERNAMEPATTERN_VEL");
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
		
		input("rg2min", "1");
		
		input("rg1min","1");	
		
		// Click on save button
		select("saveGlobal");
	
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",15);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",16);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
	    //Common.Common.driver.close();
		
			
	}

@Test(description = "To verify negative check for GLobal card holder name")


    public void Test_global_card_holder_name_nb() throws IOException, InvalidFormatException, InterruptedException

    {

		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
		
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");


		//select card holder name pattern velocity from score for dropdown		
		select("jqg_grid_1212");
		
		//click save button				
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
	
		// go to -ve db configuration
		// page.................................................
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
		
		// enter global in check type		
		select_Dropdown("userType", "Global");
		
		//wait for elements to appear
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[3]", "CARDHOLDER_NAME_NB");
		// select("tabel-heading");

		// click add new button			
		select("addDetails");

		
		// enter in -ve value
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 16, 27));
		
		// enter reason
		input("reason", "fraud");
		
		// enter message
		input("message", "fraud");
		
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",17);
		String resCode = ResponseParser.responseCode;
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		//Common.driver.close();
			
	}

@Test(description = "To verify CC Bin velocity check" ,enabled = false)


    public void Test_global_cc_bin_vel() throws IOException, InterruptedException

    {
	
		// navigate to manage fraud detail page

		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
			
		// uncheck all selected checks	
		select("cb_grid");
		select("cb_grid");
		
		// select cc_bin velocity check
		select("jqg_grid_1170");
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
			
		// go to score configuration page.................................................
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
		// enter global in check type
		select_Dropdown("check", "Global");
			
		// select("tabel-heading");
	
		//select cc_bin in score for drop down
		select_Dropdown_wait("scopeFor","/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[9]","CC_BIN_VEL");      
	
		// select("tabel-heading");
	
		//click search button
		select("btnSearchDetails");
	
		input("rg2min", "1");
		input("rg1min", "2");
			
	
		select("saveGlobal");
			
			
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",18);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",19);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		//Common.driver.close();

    }
	
@Test(description = "To verify number of different email combination check within in a specified time")


	public void Test_global_cc_email_vel() throws IOException, InterruptedException

	{

		//Common.login();
		//navigate to manage fraud detail page

		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		//select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		//uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		//select account holder name pattern velocity check
		select("jqg_grid_1150");

		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		//go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		//enter global in check type
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		
		//select account holder in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[10]", "CC_EMAIL_VEL");

		//click show details button		
		select("btnSearchDetails");
		
		input("rg2min", "1");
			
		input("rg1min", "1");
		select("saveGlobal");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",20);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",21);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		//Common.driver.close();

	}

@Test(description = "To verify Global CC fraud check")


    public void Test_global_cc_fraud_neg() throws IOException, InvalidFormatException, InterruptedException

    {

		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
			
		// uncheck all selected checks	
		select("cb_grid");
		select("cb_grid");
			
		// select cc_fraud  check	
		select("jqg_grid_1100");
			
		//click save button		
		select("saveManageFruadsGlobal");
			
		select("saveManageFruadsGlobal");
					
		// go to -ve db configuration
		// page.................................................		
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
		
		// enter global in check
		// type.............................................
		select_Dropdown("userType", "Global");
		
		//wait for elements to appear								
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[4]", "CC_FRAUD");
		// select("tabel-heading");

		// click add new			
		select("addDetails");

		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 21, 26));
								
		// enter reason					
		input("reason", "fraud");
                    
		// enter message
		input("message", "fraud");
		
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",22);
		String resCode = ResponseParser.responseCode;
		
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
	
    }	

@Test(description = "To verify Global CC Fraud series check")


	public void Test_global_cc_fraud_series() throws IOException, InvalidFormatException, InterruptedException

	{

		//navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		//select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		//uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		//select cc_fraud_series  check
		select("jqg_grid_1101");
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		//go to -ve db configuration
		//page.................................................
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
		

		//enter global in check
		select_Dropdown("userType", "Global");
		
		//wait for elements to appear
		select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[5]", "CC_FRAUD_SERIES");
		// select("tabel-heading");
		
		//click add new
		//button.........................................................		
		select("addDetails");

		input("checkValue",  Excel.getData("Resourses\\Data\\API_Data_Global.xlsx", "Sheet1",22, 31));
			
		//enter reason	
		input("reason", "fraud");
		
		//enter message
		input("message", "fraud");
		
		//click submit button
		select("btnSubmit");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",23);
		String resCode = ResponseParser.responseCode;
		
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		

	}

@Test(description = "To verify global credit card & IP velocity check")


	public void Test_global_cc_ip_vel() throws IOException, InterruptedException

	{

		//navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
		// select global from dropdown
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appea
		// select("tabel-heading");
	
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");

		// select cc_ip velocity check	
		select("jqg_grid_1167");		
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		// go to score configuration page.................................................
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// Select GLobal from Dropdown
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		
		//select cc_ip_vel in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[11]", "CC_IP_VEL");
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
		
        // Enter value
		input("rg2min", "1");
				
		input("rg1min","1");
		
		// Click on save button
		select("saveGlobal");
		
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",24);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");

		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",25);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");

	
	}

@Test(description = "To verify global credit card & Mobile velocity check")


	public void Test_global_cc_mobile_vel() throws IOException, InterruptedException

	{

		//navigate to manage fraud detail page		
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appea
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");

		// select cc_ip velocity check				
		select("jqg_grid_1171");		
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		// go to score configuration page.................................................
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// Select GLobal from Dropdown
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		
		//select cc_ip_vel in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[12]", "CC_MOBILE_VEL");
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
		
        // Enter value
		input("rg2min", "1");
				
		input("rg1min","1");
		
		// Click on save button
		select("saveGlobal");
		
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",26);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");

		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",27);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
					
	}

@Test(description = "To verify decline limit check", enabled = false)  // need to check with developers
    public void Test_global_declinelimit() {

	// skipped
}

@Test(description = "To verify global email bin  velocity check ")   // done


	public void Test_global_email_bin_vel() throws IOException, InterruptedException

	{

		// navigate to manage fraud detail page		
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select email_bin velocity check
		select("jqg_grid_1172");
		
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
	
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
	
		// enter global in check type
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
	
		//select bin_cc in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[13]", "EMAIL_BIN_VEL");
	
		// select("tabel-heading");
		
		//click search butto
	    select("btnSearchDetails");
		
	    input("rg2min", "1");
		
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",30);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals("1000", resCode);
	
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",31);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		
	}

@Test(description = "To verify Global email cc velocity check")

	public void Test_global_email_cc_vel() throws IOException, InterruptedException

	{
			
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	
		// select global from dropdown
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
	
	
		//select email_cc velocity check		
		select("jqg_grid_1152");
				
				
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
		
				
		//select email_cc_vel in score for drop down		
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[14]", "EMAIL_CC_VEL");
		
		
		// select("tabel-heading");
		//click search button

	    select("btnSearchDetails");
		
	    input("rg2min", "1");
		
			
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",32);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");

		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",33);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");	
			
	}	

@Test(description = "To verify Global email ip velocity check")



	public void Test_global_email_ip_vel() throws IOException, InterruptedException
	
	{
	
		
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		
		// select global from dropdown
		select_Dropdown("check", "Global");
		
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select email_ip velocity check
		select("jqg_grid_1164");
		
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
		
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type
		select_Dropdown("check", "Global");
		
		// select("tabel-heading");
				
		
		//select email_cc_vel in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[15]", "EMAIL_IP_VEL");
		
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
		
		
		input("rg2min", "1");
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		//Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",34);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",35);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");	

	}	

@Test(description = "To verify Global email mobile velocity vheck")   // pass


	public void Test_global_email_mobile_vel() throws IOException, InterruptedException
	{

		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown		
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
			
		// select email_mobile velocity check
		select("jqg_grid_1173");
		
		//click save button		
		select("saveManageFruadsGlobal");
				
		select("saveManageFruadsGlobal");
		
		// go to score configuration page						
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type		
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");

		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[16]", "EMAIL_MOBILE_VEL");
		
		
		// select("tabel-heading");		
			
		//click search button
	    select("btnSearchDetails");
		
	    input("rg2min", "1");
			
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
	
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 36);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 37);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
	}

@Test(description = "To verify Global email pattern velocity check")   // done

	public void Test_global_email_pattern_vel() throws IOException, InterruptedException

	{
	
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
		// select global from dropdown	
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select email_pattern velocity check
		select("jqg_grid_1162");
	
		//click save button		
		select("saveManageFruadsGlobal");
	
		select("saveManageFruadsGlobal");
			
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
			
		// enter global in check type
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
			
		//select email_pattern_vel in score for drop down	
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[17]", "EMAIL_PATTERN_VEL");
		
		// select("tabel-heading");
		
		//click search button
		select("btnSearchDetails");
	
		input("rg2min", "1");
				
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 38);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",39);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");

	}


@Test(description = "To verify negative check for Global Fraud Account")  // Pass


	public void Test_global_FraudAccount_nb() throws IOException, InvalidFormatException, InterruptedException  
		
	{
	
		//navigate to manage fraud detail page
		
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		// select global from dropdown..........................................
		select_Dropdown("check", "Global");

		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
			
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		
		//select Fraud Account  negative DB check from score for dropdown
		select("jqg_grid_1143");
			
		//click save button		
		select("saveManageFruadsGlobal");
			
		select("saveManageFruadsGlobal");
			
		
		// go to -ve db configuration
		// page.................................................
		
		navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
				
		// enter global in check type							
		select_Dropdown("userType", "Global");
						
			
		//wait for elements to appear		
		select_Dropdown_wait( "checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[3]", "FRAUD_ACCOUNT");
		// select("tabel-heading");

	
		// click add new button			
		select("addDetails");
	
		
		// enter in -ve value
		input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 39, 36));
		
		// enter reason
		input("reason", "fraud");
				
		// enter message
		input("message", "fraud");
			
		// click submit button
		select("btnSubmit");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",40);
		String resCode = ResponseParser.responseCode;
		
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		
		
		Assert.assertEquals(check, "Blocked");
		//Common.driver.close();
			
		
	}
		
@Test(description = "To verify Global Funding Source  (BIN No) pattern velocity check")  // Pass

	public void Test_global_fundingSource_pattern_vel() throws IOException, InterruptedException

	{

		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
		
		// select global from dropdown	
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
		
		// select funding Source Pattern velocity check
		select("jqg_grid_1149");
		
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		select("saveManageFruadsGlobal");
			
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
			
		// enter global in check type
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
			
		//select email_pattern_vel in score for drop down	
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[17]", "FUNDINGSOURCEPATTERN_VEL");
	
	
		// select("tabel-heading");
		
		//click search button	
		select("btnSearchDetails");
	
		input("rg2min", "1");
				
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 41);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals("1000", resCode);
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",42);
		String check = ResponseParser.check;
		Assert.assertEquals("1000", resCode);
		Assert.assertEquals(check, "Blocked");


	}

@Test(description = "To verify Global Funding Source (BIN No) velocity check")   // pass

	public void Test_global_fundingSource_vel() throws IOException, InterruptedException

	{

		// navigate to manage fraud detail page	
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
	
		// select global from dropdown	
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.		
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
	
		// select funding Source velocity check
		select("jqg_grid_1145");
		
		//click save button		
		select("saveManageFruadsGlobal");
				
		select("saveManageFruadsGlobal");	
	
		// go to score configuration page
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
				
		// enter global in check type
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
			
		//select email_pattern_vel in score for drop down
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[17]", "FUNDINGSOURCE_VEL");
	
		// select("tabel-heading");
	
		//click search button
		select("btnSearchDetails");

		input("rg2min", "1");
			
		
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 43);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals("1000", resCode);
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",44);
		String check = ResponseParser.check;
		Assert.assertEquals("1000", resCode);
		Assert.assertEquals(check, "Blocked");

	}

@Test(description = "To verify Global IP_BIN velocity vheck")    // pass 


	public void Test_global_IP_BIN_vel() throws IOException, InterruptedException
	{
		
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown		
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
			
		// select IP_BIN velocity check
		select("jqg_grid_1174");
		
		//click save button		
		select("saveManageFruadsGlobal");
				
		select("saveManageFruadsGlobal");
		
		// go to score configuration page						
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type		
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
	
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[16]", "IP_BIN_VEL");
		
		
		// select("tabel-heading");		
			
		//click search button
	    select("btnSearchDetails");
		
	    input("rg2min", "1");
			
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 45);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 46);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		
	}	

@Test(description = "To verify Global IP CC velocity vheck")     // pass

	public void Test_global_IP_CC_vel() throws IOException, InterruptedException
	{
	
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown		
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
			
		// select IP_CC velocity check
		select("jqg_grid_1159");
		
		//click save button		
		select("saveManageFruadsGlobal");
				
		select("saveManageFruadsGlobal");
		
		// go to score configuration page						
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type		
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
	
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[16]", "IP_CC_VEL");
		
		
		// select("tabel-heading");		
			
		//click search button
	    select("btnSearchDetails");
		
	    input("rg2min", "1");
			
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
	
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 47);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 48);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		
		
	}	

@Test(description = "To verify Global IP Email velocity vheck")   // pass

	public void Test_global_IP_Email_vel() throws IOException, InterruptedException
	{
		
		
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown		
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
			
		// select IP Email velocity check
		select("jqg_grid_1163");
		
		//click save button		
		select("saveManageFruadsGlobal");
				
		select("saveManageFruadsGlobal");
		
		// go to score configuration page						
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type		
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
	
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[16]", "IP_EMAIL_VEL");
		
		
		// select("tabel-heading");		
			
		//click search button
	    select("btnSearchDetails");
		
	    input("rg2min", "1");
			
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
		
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
	
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 49);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 50);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
		
	}	

@Test(description = "To verify Global IP mobile velocity vheck")   // pass


	public void Test_global_IP_mobile_vel() throws IOException, InterruptedException
	{
	
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
			
		// select global from dropdown		
		select_Dropdown("check", "Global");
	
		//click on page anywhere to make merchant name text box appear.
		// select("tabel-heading");
		
		// uncheck all selected checks
		select("cb_grid");
		select("cb_grid");
			
		// select IP_mobile velocity check
		select("jqg_grid_1181");
		
		//click save button		
		select("saveManageFruadsGlobal");
				
		select("saveManageFruadsGlobal");
		
		// go to score configuration page						
		navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
		
		// enter global in check type		
		select_Dropdown("check", "Global");
	
		// select("tabel-heading");
	
		select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[16]", "IP_MOBILE_VEL");
		
		
		// select("tabel-heading");		
			
		//click search button
	    select("btnSearchDetails");
		
	    input("rg2min", "1");
			
		input("rg1min", "1");
		
		select("saveGlobal");
		select("lblMsg");
	
		String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
		setExecuteArray(myArgs);
		
		// Call the web service
		//String api_url = Common.prop.getProperty("API_URL");
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 51);
		String resCode = ResponseParser.responseCode;
		Assert.assertEquals(resCode,"1000");
		
		new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 52);
		String check = ResponseParser.check;
		Assert.assertEquals(resCode,"1000");
		Assert.assertEquals(check, "Blocked");
	}	
	
@Test(description = "To verify global mobile bin velocity check")


    public void Test_global_mobile_bin_vel() throws IOException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	
	// select global from dropdown	
	select_Dropdown("check", "Global");

	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
	
	// uncheck all selected checks
	select("cb_grid");
	select("cb_grid");
	
	// select mobile bin velocity check
	select("jqg_grid_1181");
			
	//click save button				
	select("saveManageFruadsGlobal");
					
	select("saveManageFruadsGlobal");
					
	// go to score configuration page				
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
					
	// enter global in check type								
	select_Dropdown("check", "Global");
					
	// select("tabel-heading");
	//select bin_ip in score for drop down
					
	select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[24]", "MOBILE_BIN_VEL");
					
	// select("tabel-heading");
									
	//click search button
	select("btnSearchDetails");
					
    input("rg2min", "1");
    input("rg1min", "1");
							
					
	select("saveGlobal");		
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	//String api_url = Common.prop.getProperty("API_URL");
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 53);
	String resCode = ResponseParser.responseCode;
	Assert.assertEquals(resCode,"1000");
	
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 54);
	String check = ResponseParser.check;
	Assert.assertEquals(resCode,"1000");
	Assert.assertEquals(check, "Blocked");
					
	
}

@Test(description = "To verify global mobile CC velocity check")


    public void Test_global_mobile_cc_vel() throws IOException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	
	// select global from dropdown	
	select_Dropdown("check", "Global");

	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
	
	// uncheck all selected checks
	select("cb_grid");
	select("cb_grid");
	
	// select mobile cc velocity check
	select("jqg_grid_1153");
			
	//click save button				
	select("saveManageFruadsGlobal");
					
	select("saveManageFruadsGlobal");
					
	// go to score configuration page				
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
					
	// enter global in check type								
	select_Dropdown("check", "Global");
					
	// select("tabel-heading");
	//select bin_ip in score for drop down
					
	select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[25]", "MOBILE_CC_VEL");
					
	// select("tabel-heading");
									
	//click search button
	select("btnSearchDetails");
					
    input("rg2min", "1");
    input("rg1min", "1");
							
					
	select("saveGlobal");			
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	//String api_url = Common.prop.getProperty("API_URL");
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 55);
	String resCode = ResponseParser.responseCode;
	Assert.assertEquals(resCode,"1000");
	
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 56);
	String check = ResponseParser.check;
	Assert.assertEquals(resCode,"1000");
	Assert.assertEquals(check, "Blocked");
					
	
}

@Test(description = "To verify global mobile ip velocity check")


    public void Test_global_mobile_ip_vel() throws IOException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	
	// select global from dropdown	
	select_Dropdown("check", "Global");

	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
	
	// uncheck all selected checks
	select("cb_grid");
	select("cb_grid");
	
	// select mobile ip velocity check
	select("jqg_grid_1180");
			
	//click save button				
	select("saveManageFruadsGlobal");
					
	select("saveManageFruadsGlobal");
					
	// go to score configuration page				
	navigate_menu(Conf.getProperty("Menu_Score_Configuration"));
					
	// enter global in check type								
	select_Dropdown("check", "Global");
					
	// select("tabel-heading");
	//select bin_ip in score for drop down
					
	select_Dropdown_wait("scopeFor", "/html/body/div/div[3]/div/div[2]/div/div[2]/table[2]/tbody/tr/td[2]/select/option[27]", "MOBILE_IP_VEL");
					
	// select("tabel-heading");
									
	//click search button
	select("btnSearchDetails");
					
    input("rg2min", "1");
    input("rg1min", "1");
							
					
	select("saveGlobal");						

	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	//String api_url = Common.prop.getProperty("API_URL");
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 57);
	String resCode = ResponseParser.responseCode;
	Assert.assertEquals(resCode,"1000");
	
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 58);
	String check = ResponseParser.check;
	Assert.assertEquals(resCode,"1000");
	Assert.assertEquals(check, "Blocked");
	
}

@Test(description = "To verify Global valid domain check")


    public void Test_global_valid_Domain() throws IOException, InvalidFormatException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
	// select global from dropdown..........................................
	select_Dropdown("check", "Global");
	
	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
		
	// uncheck all selected checks	
	select("cb_grid");
	select("cb_grid");
		
	// select valid domain check	
	select("jqg_grid_1211");
		
	//click save button		
	select("saveManageFruadsGlobal");
		
	select("saveManageFruadsGlobal");
				
	// go to -ve db configuration
	// page.................................................		
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	// enter global in check
	// type.............................................
	select_Dropdown("userType", "Global");
	
	//wait for elements to appear								
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[7]", "VALID_DOMAIN");
	// select("tabel-heading");

	// click add new			
	select("addDetails");
	

	
	String value = Excel.get_DomainName(Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 58, 39));

	input("checkValue", value);
							
	// enter reason					
	input("reason", "fraud");
                
	// enter message
	input("message", "fraud");
	
	// click submit button
	select("btnSubmit");
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 59);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");


}

@Test(description = "To verify Global valid email check")


    public void Test_global_valid_Email() throws IOException, InvalidFormatException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
	// select global from dropdown..........................................
	select_Dropdown("check", "Global");
	
	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
		
	// uncheck all selected checks	
	select("cb_grid");
	select("cb_grid");
		
	// select valid email check	
	select("jqg_grid_1211");
		
	//click save button		
	select("saveManageFruadsGlobal");
		
	select("saveManageFruadsGlobal");
				
	// go to -ve db configuration
	// page.................................................		
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	// enter global in check
	// type.............................................
	select_Dropdown("userType", "Global");
	
	//wait for elements to appear								
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[8]", "VALID_EMAIL");
	// select("tabel-heading");

	// click add new			
	select("addDetails");

	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 59, 39));
							
	// enter reason					
	input("reason", "fraud");
                
	// enter message
	input("message", "fraud");
	
	// click submit button
	select("btnSubmit");
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 60);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");


}

@Test(description = "To verify Global valid IP check")


    public void Test_global_valid_IP() throws IOException, InvalidFormatException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
	// select global from dropdown..........................................
	select_Dropdown("check", "Global");
	
	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
		
	// uncheck all selected checks	
	select("cb_grid");
	select("cb_grid");
		
	// select valid IP check	
	select("jqg_grid_1102");
		
	//click save button		
	select("saveManageFruadsGlobal");
		
	select("saveManageFruadsGlobal");
				
	// go to -ve db configuration
	// page.................................................		
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	// enter global in check
	// type.............................................
	select_Dropdown("userType", "Global");
	
	//wait for elements to appear								
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[9]", "VALID_IP");
	// select("tabel-heading");

	// click add new			
	select("addDetails");

	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 60, 40));
	System.out.println("sheet1  60,40  :"+ Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 60, 40));
							
	// enter reason					
	input("reason", "fraud");
                
	// enter message
	input("message", "fraud");
	
	// click submit button
	select("btnSubmit");
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 61);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");


}

@Test(description = "To verify Global valid mobile check")


    public void Test_global_valid_Mobile() throws IOException, InvalidFormatException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
	// select global from dropdown..........................................
	select_Dropdown("check", "Global");
	
	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
		
	// uncheck all selected checks	
	select("cb_grid");
	select("cb_grid");
		
	// select valid mobile check	
	select("jqg_grid_1135");
		
	//click save button		
	select("saveManageFruadsGlobal");
		
	select("saveManageFruadsGlobal");
				
	// go to -ve db configuration
	// page.................................................		
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	// enter global in check
	// type.............................................
	select_Dropdown("userType", "Global");
	
	//wait for elements to appear								
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[10]", "VALID_MOBILE");
	// select("tabel-heading");

	// click add new			
	select("addDetails");

	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 61, 41));
							
	// enter reason					
	input("reason", "fraud");
                
	// enter message
	input("message", "fraud");
	
	// click submit button
	select("btnSubmit");
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 62);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");



}

@Test(description = "To verify Global valid UCID check")


    public void Test_global_valid_UCID() throws IOException, InvalidFormatException, InterruptedException

{

	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
	// select global from dropdown..........................................
	select_Dropdown("check", "Global");
	
	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
		
	// uncheck all selected checks	
	select("cb_grid");
	select("cb_grid");
		
	// select valid UCID check	
	select("jqg_grid_1134");
		
	//click save button		
	select("saveManageFruadsGlobal");
		
	select("saveManageFruadsGlobal");
				
	// go to -ve db configuration
	// page.................................................		
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	
	// enter global in check
	// type.............................................
	select_Dropdown("userType", "Global");
	
	//wait for elements to appear								
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[11]", "VALID_UCID");
	// select("tabel-heading");

	// click add new			
	select("addDetails");

	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 62, 42));
							
	// enter reason					
	input("reason", "fraud");
                
	// enter message
	input("message", "fraud");
	
	// click submit button
	select("btnSubmit");
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 63);
	String resCode = ResponseParser.responseCode;
	String check = ResponseParser.check;
    Assert.assertEquals(resCode, "1000");
    Assert.assertEquals(check, "Blocked");


}


@Test(description = "To verify Global FIP velocity check" , enabled = false)   // need to be implemented

public void Test_global_FIP_vel() throws IOException, InvalidFormatException, InterruptedException

{
	// navigate to manage fraud detail page
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	// select global from dropdown	
	select_Dropdown("check", "Global");

	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
	
	// uncheck all selected checks
	select("cb_grid");
	select("cb_grid");
	
	// select Valid Mobile velocity check
	select("jqg_grid_1135");
	
	// select FIP velocity check
	select("jqg_grid_1108");
	
	//click save button		
	select("saveManageFruadsGlobal");

	select("saveManageFruadsGlobal");
	
	// go to score configuration page	
	navigate_menu(Conf.getProperty("Menu_Fraud_Configuration"));
		

	// click on edit of Decline limit	
	select_xpath("/html/body/div/div[3]/div[2]/div/div[3]/table/tbody/tr/td/div[2]/div/div/div/div/table/tbody/tr/td/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]/div/div/span");	
	
	//Enter Text value
	input("1_config_value",  "1");
	
	//click on save button
	select_xpath("/html/body/div/div[3]/div[2]/div/div[3]/table/tbody/tr/td/div[2]/div/div/div/div/table/tbody/tr/td/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td[3]/div/div[2]/span");
	
	// click on edit of Decline limit	
	select_xpath("/html/body/div/div[3]/div[2]/div/div[3]/table/tbody/tr/td/div[2]/div/div/div/div/table/tbody/tr/td/div/div/div[3]/div[3]/div/table/tbody/tr[3]/td[3]/div/div/span");	
	
	//Enter Text value
	input("2_config_value",  "1");
	
	//click on save button
	select_xpath("/html/body/div/div[3]/div[2]/div/div[3]/table/tbody/tr/td/div[2]/div/div/div/div/table/tbody/tr/td/div/div/div[3]/div[3]/div/table/tbody/tr[3]/td[3]/div/div[2]/span");
	
	navigate_menu(Conf.getProperty("Menu_Negative_DB_Configuration"));
	select_Dropdown("userType", "Global");
	
	//wait for elements to appear								
	select_Dropdown_wait("checks", "/html/body/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/select/option[10]", "VALID_MOBILE");
	// select("tabel-heading");

	// click add new			
	select("addDetails");

	input("checkValue", Excel.getData("Resourses\\Data\\API_Data_Global.xlsx","Sheet1", 63, 41));
	System.out.println("Value to test : " + Excel.getData("Resourses\\Data\\API_Data_Global.xlsx", "Sheet1",63, 41));						
	// enter reason					
	input("reason", "fraud");
                
	// enter message
	input("message", "fraud");
	
	// click submit button
	select("btnSubmit");
	
	String[] myArgs = {"check"};  // will be used to execute only checks that needs to be there		
	setExecuteArray(myArgs);
	
	// Call the web service
	//String api_url = Common.prop.getProperty("API_URL");
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx", 64);
	String resCode = ResponseParser.responseCode;
	Assert.assertEquals("1000", resCode);
	
	new AutoTestFacade().sendRequest(api_url,"Resourses\\Data\\API_Data_Global.xlsx",65);
	String check = ResponseParser.check;
	Assert.assertEquals(resCode,"1000");
	Assert.assertEquals(check, "Blocked");

}
*/




//@AfterTest

    public void TestTearDown() throws InterruptedException
{
	
	
	// following code is for un-checking all the  Checks at the end 
	navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));

	// select global from dropdown	
	select_Dropdown("check", "Global");

	//click on page anywhere to make merchant name text box appear.
	// select("tabel-heading");
	select_cls("boxin"); 
	// uncheck all selected checks
	//select("cb_grid");
	Thread.sleep(2000);
	
	select("cb_grid");
	//Thread.sleep(2000);
	
	//click save button		
	select("saveManageFruadsGlobal");
	
	Thread.sleep(3000);
	//
	
	System.out.println("Test has completed...Reports will be mailed");
	driver.quit();

}

}




