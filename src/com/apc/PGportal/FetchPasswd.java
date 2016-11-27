package com.apc.PGportal;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.apc.common.Excel;

public class FetchPasswd {

	public static String getPass() throws InterruptedException, InvalidFormatException, IOException{
		
		
		// script to login to disposable mail only using username and fetch the Password through the body of latest mail 
		WebDriver  d  = new FirefoxDriver();
		
		// open the URL in Browser http://www.mailinator.com/
		d.get("http://www.mailinator.com/");
		
		// Clear the Username text field
		d.findElement(By.id("inboxfield")).clear();
		
		//Enter the Username in  text field
		d.findElement(By.id("inboxfield")).sendKeys(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 12));
		
		// wait for 2 seconds
		Thread.sleep(2000);
		
		// find the login button element and click on it
		d.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[2]/div/div/btn")).click();
		
		// wait for 2 seconds
		Thread.sleep(2000);
		
		// find the latest email form inbox and click on it
		d.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div[3]/ul/li/a/div[2]")).click();
		
		/// wait for 2 seconds
		Thread.sleep(2000);
		
		// get the text in Password element from Message body part
		String FullPassword = d.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div/div/div/div/div[3]/div/div/div/font/font/font/font/font/font/font/font/font/font/font/font/span/font")).getText();
		
		// fetch the only password text
		String OnlyPass = FullPassword.substring(9); 
		
		System.out.println("The password fetched from Email is : " + OnlyPass);
		
		Excel.setData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 18, OnlyPass);
		
		return OnlyPass;
		
	}
		
public static String getPass_yopmail(String Username) throws InterruptedException, InvalidFormatException, IOException{
		
		
		// script to login to disposable mail only using username and fetch the Password through the body of latest mail 
		WebDriver  d  = new FirefoxDriver();
		
		d.manage().window().maximize();
		
		// open the URL in Browser http://www.mailinator.com/
		d.get("http://www.yopmail.com/");
		
		// Clear the Username text field
		d.findElement(By.id("login")).clear();
		
		//Enter the Username in  text field
		d.findElement(By.id("login")).sendKeys(Username);
		
		// wait for 2 seconds
		Thread.sleep(2000);
		
		// find the login button element and click on it
		d.findElement(By.cssSelector("html body.bodyindex center div div.al_l div.al_l table.wc tbody tr td table.wc tbody tr td div form#f table tbody tr td input.sbut")).click();
								
		// wait for 2 seconds
		Thread.sleep(2000);
		
		// find the latest email form inbox and click on it
	/*	
		boolean found = d.findElements(By.cssSelector("html body.bodyinbox div div#m1.m div.um a.lm span.lmfd")).size() !=0;
		if(!found)
		{
			System.out.println("Indox mail not found by css selector");
			
			found = d.findElements(By.xpath("/html/body/div/div[8]/div/a/span")).size() !=0;
			if(!found)
			{
				System.out.println("Indox mail not found by xpath selector");
			}
		}
	*/		
		//d.findElement(By.cssSelector("html body.bodyinbox div div#m1.m div.um a.lm span.lmfd span.lmf")).click();
		
		/// wait for 2 seconds
		Thread.sleep(2000);
		
		// get the text in Password element from Message body part
		String FullPassword = d.findElement(By.xpath("/html/body/div/div[3]/div[2]")).getText();
										
		System.out.println("The password fetched from Email is : " + FullPassword);
		
		// fetch the only password text
		String OnlyPass = FullPassword.substring(9); 
		
		System.out.println("The password fetched from Email is : " + OnlyPass);
		
		Excel.setData("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers", 1, 18, OnlyPass);
		
		return OnlyPass;
		
	}
		
	
	}
	

