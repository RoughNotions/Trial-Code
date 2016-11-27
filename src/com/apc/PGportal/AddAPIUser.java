package com.apc.PGportal;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.apc.common.Excel;

public class AddAPIUser extends BaseTestPG {
	
	@Test
	public static void Test_AddAPIUser() throws InvalidFormatException, IOException, InterruptedException{
		
		login();
		
		driver.navigate().to("https://portal.gpcloud.com/APC/app/manageAPIUsers/manageAPIUsersPage");
		
		// click on Add
		
		select("AddJQ");
		
		// select merchant
		
		new Select(driver.findElement(By.id("midAdd"))).selectByVisibleText(Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 0));
		
		// enter user ID
		
		input("userIdAdd", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 1));
		
		// enter email id
		
		input("emailIdAdd", Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 2));
		
		// click save
		
		driver.findElement(By.xpath(".//*[@id='addUserForm']/div/input[1]")).click();
		
		// Verify API user has been added
		
		String userID =  Excel.getData("Resourses\\Data\\PG_PortalData.xlsx", "APIUser", 1, 1);
		
		assert wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='" +userID + "']/td[1]"))).isDisplayed();
		
		
	}

}
