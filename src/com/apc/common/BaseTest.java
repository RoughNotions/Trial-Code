package com.apc.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	
     public static   WebDriver driver;
     public static WebDriverWait wait;
     public static Properties Conf;
     public static Properties Input;
     
     public static String api_url;
     
   //String api_url = Common.prop.getProperty("API_URL");
     
     public static Boolean IsNeedToSkipChecks = false;
     public static String ChecksToExecute[] ;
     
     
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////////////////////////////////LOGIN/////////////////////////////////////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
     public static void login()
	{
		Conf = new Properties();

		try {
		    //load a properties file
			Conf.load(new FileInputStream("Resourses\\Config.properties"));
			
		} catch (IOException ex) {
				ex.printStackTrace();
		 }

			
		Input = new Properties();
		
		try {
			Input.load(new FileInputStream("Resourses\\Portal_Data.properties"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// login.............................................................
			
		
		   api_url =  Conf.getProperty("API_URL");
		
		  
		   driver = new FirefoxDriver();
		
		/*System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
        driver = new ChromeDriver();*/

		    driver.manage().window().maximize();
			String baseUrl = Conf.getProperty("URL");
			driver.get(baseUrl);		
			
			 wait=new WebDriverWait(driver,60);
			
			 
			
			WebElement username=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
			username.sendKeys(Conf.getProperty("username"));
			
			System.out.println("Called Login method");
			
			WebElement passw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));		
			
			passw.sendKeys(Conf.getProperty("password"));
			
			//WebElement submit=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnSubmit")));
			//WebElement submit=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginButton")));
			
		    //submit.click();
			driver.findElement(By.className("loginButton")).click();
	}
	
	// MENU NAVIGATION
	/*public static void navigate_menu(String xpath1) throws InterruptedException
	{
	
	
		
	Actions action = new Actions(driver);
	System.out.println("xpath :" + xpath1);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));
	//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	
	 
	action.moveToElement(driver.findElement(By.xpath(xpath1))).click().build().perform();
	Thread.sleep(200);
			
	}
	*/
     //MENU NAVIGATION
     // above function commented and change as per FMS UI changed on Sept-14
	public static void navigate_menu(String xpath1) throws InterruptedException
	{
	
		//driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Manage Setting")));
		//Thread.sleep(2000);
		
	Actions action = new Actions(driver);
	System.out.println("xpath :" + xpath1);
	
	///wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));
	//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	action.moveToElement(driver.findElement(By.linkText("Manage Setting"))).build().perform();
	action.moveToElement(driver.findElement(By.linkText("Configuration"))).build().perform();
	 
	action.moveToElement(driver.findElement(By.xpath(xpath1))).click().build().perform();
	 
	Thread.sleep(200);
			
	}
	
	/// Select Dropdown
	
	public static void select_Dropdown(String id,String value) throws InterruptedException{
		
		WebElement element = BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));	

		element.sendKeys(value);
		//driver.findElement(By.id("gridIDValue")).click();
		Thread.sleep(100);
		 
	}
	
	
    
	/// Select DropDown after wait
	
	public static void select_Dropdown_wait(String Dropdown_ID, String XPATH_of_list, String List_value) throws InterruptedException
	{
		
		WebElement Dropdown= BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Dropdown_ID)));
		Dropdown.click();
		
		//@SuppressWarnings("unused")
		//WebElement Dropdown_List =BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_of_list)));
		Thread.sleep(2000);
		Dropdown.sendKeys(List_value);
	}
	
	
	/// Select
	
	public static void select( String id) throws InterruptedException{
		
		WebElement element= BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		element.click();
		Thread.sleep(2000);
	}
	
public static void select_new( String XPATH_of_list){
		
		WebElement element= BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_of_list)));
		element.click();
	}
	
public static void select_cls( String cls)
{
	
	WebElement element= BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(cls)));
	element.click();
}


public static void select_xpath( String xpath){
		
		WebElement element= BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.click();
	}
	/// Input
	public static void input(String id, String value){
		
		WebElement element= BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		element.sendKeys(Keys.CONTROL+"a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(value);
	}
	
	public static void setExecuteArray(String arrChecks[]) {
		
		ChecksToExecute = arrChecks;
	
	}
	
	public static String[] getExecuteArray() {
		
		return ChecksToExecute;
		
		
	}
	
	public static void  DisableAllGlobalChecks() throws InterruptedException 
	{
		
		// navigate to manage fraud detail page
		navigate_menu(Conf.getProperty("Menu_Manage_setting"));
		navigate_menu(Conf.getProperty("Menu_confg"));
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
		
		///String[] arr_elem = {"Manage Setting", "Configuration","Manage Fraud Details"};						
		///Navigate_Menu_From_To_New(driver,arr_elem);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr/td")));
		// select global from dropdown
		select_Dropdown("check", "Global");
		 
		//Thread.sleep(2000);
		
		//click on page anywhere to make merchant name text box appea
		//select("tabel-heading");
		select_cls("boxin"); 
		// uncheck all selected checks
		select("cb_grid");
		
		Thread.sleep(2000);
		
		select("cb_grid");
		
		Thread.sleep(2000);
		
		//click save button		
		select("saveManageFruadsGlobal");
		
		Thread.sleep(2000);
		
	}
	
	
	public static void  DisableAllMerchantChecks() throws InterruptedException 
	{
		
	    // navigate to manage fraud detail page
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Manage Setting")));
		navigate_menu(Conf.getProperty("Menu_Manage_setting"));
		navigate_menu(Conf.getProperty("Menu_confg"));
		navigate_menu(Conf.getProperty("Menu_Manage_Fraud_Details"));
        
        // select Merchant from dropdown         
        select_Dropdown("check", "Merchant");
        
       // Thread.sleep(2000);
        select_cls("boxin"); 
        ///select("tabel-heading");
        
        // Input the value in Merchant Name textbox
        input("merchantName", Conf.getProperty("Merchant_Name"));
        
        // Click on Free Area to make Textbox appear
       // ////////////select("tabel-heading");
        
        // Click on Search Button
        select("search-merchant");
        
        Thread.sleep(3000);
        
        // uncheck all selected checks    
        select("cb_grid");
        // Check all selected checks  
        
        Thread.sleep(3000);
        
        select("cb_grid");
     
        //select("tabel-heading");
        
        Thread.sleep(2000);
        
        //click save button        
        select("saveManageFruadsGlobal");
        
        Thread.sleep(3000);
		
	}
	
	public static String GetNewURL(String BaseURL,String URLTOAppend)
	 { String ret = "";
	 
	  int endIndex = BaseURL.lastIndexOf("/");
	  if(endIndex != -1)
	  {
	          String rootURL = BaseURL.substring(0, endIndex); // not forgot to put check if( endIndex != -1)
	          System.out.println("Index is : " + endIndex +"The string after removing the / is : " + rootURL);
	          
	          ret = rootURL + "/" + URLTOAppend;
	          
	         
	  }
	  else 
	  {
	   System.out.println("Cannot find symbol / in string : " + BaseURL);
	  }
	  
	   return ret;
	  
	 }
	
	public static void Navigate_Menu_From_To_New(WebDriver d, String Arr_elemes[]) throws InterruptedException
	{
		//Thread.sleep(1000);	
		d.navigate().refresh();
		Thread.sleep(2000);
		
		for(int i =0;i<Arr_elemes.length;i++)
		{
			 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Arr_elemes[i])));
			
					
			d.findElement(By.linkText(Arr_elemes[i])).click();
			Thread.sleep(2000);	
		}
		
		
		     	
	}
	public static void navigate_menuh(String xpath)
	{
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath(xpath))).click().build().perform();
	System.out.println("menuh executed successfully.");
	}  
}


