package com.apc.PGportal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

public class BaseTestPG {

	public static WebDriver driver;
	 
	public static WebDriverWait wait;
	public static Properties Conf;
	
	
	public static String huser_disposal= null;//"DailyAdmin1298";
	public static String hpass_disposal= null; 
	
	public static String newPass= "Chetu@1234";
	public static String reset_pass="Chetu@123";
	
	public static String envStr ="s1";

	public static void BaseSetup() throws FileNotFoundException, IOException 
	{

		System.out.println("Initiating Test");
		driver = new FirefoxDriver();

		wait = new WebDriverWait(driver, 120);

		driver.manage().window().maximize();

//		Conf = new Properties();

//		Conf.load(new FileInputStream("Resourses\\Config.properties"));
		
	}
	
	public static void Init_ConfigFile() throws FileNotFoundException, IOException
	{
		Conf = new Properties();

		Conf.load(new FileInputStream("Resourses\\Config.properties"));
		
	}

	
	public static void login() throws FileNotFoundException, IOException, InterruptedException {
		
		/*
		
		System.out.println("PG Login function is called");
		System.out.println("url :"+ Conf.getProperty("PGUrl"));
		System.out.println("user : "+Conf.getProperty("PGusername"));
		System.out.println("pass : "+Conf.getProperty("PGpassword"));
		
		 //for Dev1 and S1
		
		driver.get(Conf.getProperty("PGUrl"));
       
		 
		input("j_username", Conf.getProperty("PGusername"));
		
		input("j_password", Conf.getProperty("PGpassword"));
     
		*/
		//for UAT
		
		 System.out.println("PG Login function is called");
		 System.out.println("url :"+ Conf.getProperty("PGUrl_Uat"));
		 System.out.println("user : "+Conf.getProperty("PGUusername"));
		 System.out.println("pass : "+Conf.getProperty("PGUpassword"));
		 
		 driver.get(Conf.getProperty("PGUrl_Uat"));
         input("j_username", Conf.getProperty("PGUusername"));
         input("j_password", Conf.getProperty("PGUpassword"));
         
         

		 driver.findElement(By.className("loginButton")).click();

	}

	// MENU NAVIGATION
	public static void navigate_menu(String xpath) {
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath(xpath))).click()
				.build().perform();

	}

	// / Select Dropdown

	public static void select_Dropdown(String id, String value) {

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(id)));

		element.sendKeys(value);
		
		//new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)))).selectByVisibleText(value);
		
	}

	// / Select DropDown after wait

	public static void select_Dropdown_wait(String Dropdown_ID,
			String XPATH_of_list, String List_value) {

		WebElement Dropdown = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(Dropdown_ID)));
		Dropdown.click();

		@SuppressWarnings("unused")
		WebElement Dropdown_List = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(XPATH_of_list)));

		Dropdown.sendKeys(List_value);
	}

	// / Select

	public static void select(String id) {

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(id)));
		element.click();
	}

	public static void select_new(String XPATH_of_list) {

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(XPATH_of_list)));
		element.click();
	}

	public static void select_xpath(String xpath) {

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	// / Input
	public static void input(String id, String value) throws InterruptedException
	{
		Thread.sleep(2000);
		
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(id)));
		
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		//element.clear();
		element.sendKeys(value);
	}

	public static void teardown() {

		driver.close();
		driver.quit();

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
		Thread.sleep(5000);
		
		for(int i =0;i<Arr_elemes.length;i++)
		{
			 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Arr_elemes[i])));
					
			d.findElement(By.linkText(Arr_elemes[i])).click();
			Thread.sleep(2000);	
		}
		
		Thread.sleep(4000);
	           	
	}
	public static String Get_Current_Date()
	{
		Calendar javaCalendar = null;   		
   		javaCalendar = Calendar.getInstance();
   		int currentDate = javaCalendar.get(Calendar.DATE);
   		
   		return String.valueOf(currentDate);
   		
	}
	
	
	public static void select_date_From_Datepicker(String CSSlocator,String date_to_pick)
	{
		
		WebElement dateWidget = driver.findElement(By.cssSelector(CSSlocator));
   		
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
	
	
	public static String get_Cur_DateTime(String Format)
	{
		// Format = ddMMyyyyHHmmss
		
		String ret ="";
		
	    Format formatter = new SimpleDateFormat(Format);
	    
	    ret = formatter.format(new Date());
		
	    return ret;
	}
	
	public static boolean Login_FirstTime(WebDriver temp_Driver,String username, String firstTimePassd, String NewPassd) throws InterruptedException
	{
		
		boolean ret = false;
		 
		
		System.out.println("The username fetced from Add User Excel data sheet is : " + username);
		
		String passoword_From_Email = firstTimePassd;
		
		System.out.println("The Password fetced from Email is : " + passoword_From_Email);
		
		temp_Driver.manage().window().maximize();
		
		// login to PG Portal
		temp_Driver.get(Conf.getProperty("PGUrl"));

		// input("j_username",Conf.getProperty("PGusername"));
		
		// input username
		temp_Driver.findElement(By.id("j_username")).sendKeys(username);
		
		// input passowrd
		temp_Driver.findElement(By.id("j_password")).sendKeys(passoword_From_Email);
		

		temp_Driver.findElement(By.className("loginButton")).click();
		
		Thread.sleep(5000);
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPass"))).isDisplayed();
				
		//wait.until(ExpectedConditions.elementToBeSelected(By.id("newPass")).);
		
		String New_Password = NewPassd;
		temp_Driver.findElement(By.id("newPass")).clear();
		temp_Driver.findElement(By.id("newPass")).sendKeys(New_Password);
		

		temp_Driver.findElement(By.id("confirmPass")).clear();
		temp_Driver.findElement(By.id("confirmPass")).sendKeys(New_Password);

		temp_Driver.findElement(By.id("submit")).click();
		
		Thread.sleep(3000);
		
		// Click on Ok button
		temp_Driver.findElement(By.id("okBtn")).click();

		//wait for Login button to become clickable
		Thread.sleep(3000);
		
		// wait for Login button to become clickable
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[3]/form/div[6]/input[2]")));
		
		//  Now again login with changed username and paasword
		
		// input username
		temp_Driver.findElement(By.id("j_username")).sendKeys(username);
		
		// input passowrd
		temp_Driver.findElement(By.id("j_password")).sendKeys(New_Password);
		

		temp_Driver.findElement(By.className("loginButton")).click();
		
		Thread.sleep(8000);
		
		// check of Result	
		if(temp_Driver.getPageSource().contains("My Profile"))
		{
			ret =  true;
		}
		else
		{
			ret=  false;
		}
		
		return ret;
		
	}
	
	/////////////////////////////HARI//////////////////////////////////////////
	
	public static String before_at_str(String str)
   	{
          	//String str ="hari12@mailcatch.com";
   	    int indx_at = str.indexOf("@");
   	    String before_at =str.substring(0, indx_at);
   	    
   	    return before_at;
   	}
   	
   	
   	//This function is able to return password  from inbox of mail
   	//input complete mail id,returning password .
   	public  static String  find_new_reset_pass_from_mail(String mail) throws InterruptedException
   	{
          	
   	   
   		    driver.get("http://mailcatch.com/en/");//inbox available till 3.5 mits
          	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Check inbox")));
          	
          	
          	String  before_at =    before_at_str(mail);
          	driver.findElement(By.xpath("//*[@id='mailboxform']/input[1]")).sendKeys(before_at);
          	driver.findElement(By.xpath("//*[@id='mailboxform']/input[2]")).click();
          	//driver.findElement(By.cssSelector("subject"))
          	
          	 
          	
          	boolean txt_found =driver.getPageSource().contains("Inbox for");
          	if(txt_found)
          	{
                 	
          	System.out.println("In box  opened successfully");
          	//Assert.assertTrue(txt_found);
          	}
          	
          	else
          	{
                 	System.out.println("In box not  opened/found");
                 	throw new SkipException("string not found");
          	}
          	
          	Thread.sleep(6000);
          	
   	   	//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='mailsList']/table/tbody/tr[2]/td[2]")));
          	 
          	 
          	//Getting text that is from add new user add or reset password
          	String first_txt= driver.findElement(By.xpath("//*[@id='mailsList']/table/tbody/tr[2]/td[2]")).getText();
          	System.out.println("Reset/NewUserAdd ? :"+ first_txt);
          	
          	//clicking for open  first mail
          	driver.findElement(By.xpath("//*[@id='mailsList']/table/tbody/tr[2]/td[1]")).click();
          	Thread.sleep(4000);
          	
          	
          	
          	//scroll window downword
          	JavascriptExecutor jse = (JavascriptExecutor)driver;
          	jse.executeScript("window.scrollBy(0,250)", "");
          	
          	//switch in frame which  having required content.
          	driver.switchTo().frame("emailframe");
          	
          	 //getting all text from  inbox
          	Thread.sleep(4000);
          	
          	 String user = driver.findElement(By.xpath("html/body")).getText();
           	//System.out.println("user  :"+user  );
          	
          	//finding exact location of password and cut actual password from all string
          	int indx =  user.indexOf("Password:");
          	 System.out.println("index of Password: "+ indx );
          	 indx += 9;
          	 String indx_aft = user.substring(indx, indx+9);
          	 
          	 //System.out.println("\n\ncontent : "+indx_aft);
          	 
          	 //closing browser
          	 driver.quit();
          	// driver.close();
          	 
          	 return indx_aft;
          	
   	}
   	
   	public static void logout()
   	{
   	    //click for logout
   	    driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/a[4]")).click();
   	    //wait till login button  shown
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("loginButton")));
   	}

 
   	
    //This function used for password reset
   public static void newLogin(String newUser,String newPass)
   	{
   		System.out.println("PG newLogin function is called");
		
		 
	    driver.get(Conf.getProperty("PGUrl"));
	 
 
		driver.findElement(By.id("j_username")).sendKeys(newUser);
		driver.findElement(By.id("j_password")).sendKeys(newPass);
		//don't click for login button  here
   	}

   //This function used for relogin with new settled password    
  public static void ReLogin(String newUser,String newPass)
 	{
 		System.out.println("PG newLogin function is called");
		
		
	    driver.get(Conf.getProperty("PGUrl"));
	 

		driver.findElement(By.id("j_username")).sendKeys(newUser);
		driver.findElement(By.id("j_password")).sendKeys(newPass);
		driver.findElement(By.className("loginButton")).click();  //for first or relogin this button will must click
 	}
  
  //This method will work for particular provided webdriver
  public static void ReLogin(String newUser,String newPass, WebDriver driver )
	{
		System.out.println("PG newLogin function is called");
		
		
	    driver.get(Conf.getProperty("PGUrl"));
	 

		driver.findElement(By.id("j_username")).sendKeys(newUser);
		driver.findElement(By.id("j_password")).sendKeys(newPass);
		driver.findElement(By.className("loginButton")).click();  //for first or relogin this button will must click
	}
  
  public static void payPageSetup() throws InterruptedException
  {
	  
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.get(Conf.getProperty("PG_Paypage_URL"));
		Thread.sleep(2000);
		
		if(Conf.getProperty("PG_Paypage_URL").contains("dev1"))
		{
			envStr ="dev1";
		}
		if(Conf.getProperty("PG_Paypage_URL").contains("paypage.apcld.net"))
		{
			envStr ="uat";
		}
		 
  }
	
}
