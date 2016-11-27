package com.apc.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;




public class ProcessPGExcelData 
{
	
	// For AddBank sheet
	public static int Col_BankName_Index = 0;
	public static int Col_BankID_Index = 1;
	public static int Col_Email_Index = 11;
	public static int Col_ContactNum_Index = 14;
	
	
	// for BankBinConfig
	public static int Col_BinNumber_Index = 0;
	
	// for Add Corporates	
	public static int Col_CorporateName_Index = 0;
	public static int Col_CorporateID_Index = 1;
	public static int Col_CorpEmail_Index = 9;
	public static int Col_CorpPhone_Index = 12;
	
	
	// for Add Merchant
	public static int Col_AddMer_BussName_Index = 0;
	public static int Col_AddMer_DisplayName_Index = 1;
	public static int Col_AddMer_MCC_Index = 5;
	public static int Col_AddMer_ValidFrom_Index = 22;
	public static int Col_AddMer_ValidTo_Index = 23;
	public static int Col_AddMer_CustomName_Index = 27;
	public static int Col_AddMer_UserName_Index = 34;
	public static int Col_AddMer_Phone1_Index = 36;
	public static int Col_AddMer_Phone2_Index = 39;
	public static int Col_AddMer_PrimEmail_Index = 40;
	public static int Col_AddMer_SeconEmail_Index = 41;
	public static int Col_AddMer_Level1Email_Index = 49;
	public static int Col_AddMer_Email_Index = 54;
	public static int Col_AddMer_Phone_Index = 57;
	
	// for APIUser
	public static int Col_APIUSer_UserId_Index = 1;
	public static int Col_APIUSer_Email_Index = 2;
	
	// for Role
	public static int Col_Role_RoleName_Index = 1;
	
	// for Add Users
	public static int Col_AddUsers_UserName_Index = 2;
	public static int Col_AddUsers_Email_Index = 12;
	public static int Col_AddUsers_Phone_Index = 15;
	
	// for PayPageConfig
	public static int Col_PayPageConfig_MerEmail_Index = 22;
	
	// for Processor
	public static int Col_Processor_ProcessName_Index = 0;
	public static int Col_Processor_ProcessCode_Index = 1;
	public static int Col_Processor_ProcessEmail_Index = 3;
	
	
	public  void update(String ExcelPath,String SheetName) throws InvalidFormatException, IOException {
	
	InputStream inp = new FileInputStream(ExcelPath);
	
	Workbook wb = WorkbookFactory.create(inp);
	
	Sheet sheet = wb.getSheet(SheetName);   // ExcelIndex is dynamic , pass in argument
	
	if(sheet == null)
	{
		System.out.println("Sheet is null");
	}
	
	int rowCount = sheet.getPhysicalNumberOfRows();
	
	setCellValue_AddBank(sheet,SheetName,rowCount);
	
	 
	FileOutputStream fileOut = new FileOutputStream(ExcelPath);
    wb.write(fileOut);
    fileOut.close();
    System.out.println("API data file updated");
	    
	}



	public static void setCellValue_AddBank(Sheet sheet,String SheetName,int RowCount)
	{
		String ExistingVal ="";
		String NewVal = "";
		
		for(int i = 1; i <= RowCount-1; i++){
			
			Row row = sheet.getRow(i);
			
			if(SheetName == "AddBank") // For AddBank Sheet
			{
			
				// Bank Name
				Cell cell_BankName = row.getCell(Col_BankName_Index);
			    if (cell_BankName == null)
			    	cell_BankName = row.createCell(Col_BankName_Index);
			    
			    cell_BankName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_BankName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_BankName_Index,SheetName);
			    
			    cell_BankName.setCellValue(NewVal);
			    
			    
			    
			    // Bank Id
			    Cell cell_BankID = row.getCell(Col_BankID_Index);
			    if (cell_BankID == null)		    	
			    	cell_BankID = row.createCell(Col_BankID_Index);
			   
			    cell_BankID.setCellType(Cell.CELL_TYPE_STRING);
			    ExistingVal = cell_BankID.toString();	
			    NewVal = getNextVal(ExistingVal, Col_BankID_Index,SheetName);
			    cell_BankID.setCellValue(NewVal);
			    
			    
			    // Email ID
			    Cell cell_EmailID = row.getCell(Col_Email_Index);
			    if (cell_EmailID == null)		    	
			    	cell_EmailID = row.createCell(Col_Email_Index);
			   
			    cell_EmailID.setCellType(Cell.CELL_TYPE_STRING);
			    ExistingVal = cell_EmailID.toString();	
			    NewVal = getNextVal(ExistingVal, Col_Email_Index,SheetName);
			    cell_EmailID.setCellValue(NewVal);
			    
			    
			    
			    // Contact Number
			    Cell cell_ContactNum = row.getCell(Col_ContactNum_Index);
			    if (cell_ContactNum == null)		    	
			    	cell_ContactNum = row.createCell(Col_ContactNum_Index);
			   
			    cell_ContactNum.setCellType(Cell.CELL_TYPE_STRING);
			    ExistingVal = cell_ContactNum.toString();
			    NewVal = getNextVal(ExistingVal, Col_ContactNum_Index,SheetName);
			    cell_ContactNum.setCellValue(NewVal);
			}
			else if(SheetName == "BankBinConfig") // // For BankBinConfig Sheet
			{
				Cell cell_BinNum = row.getCell(Col_BinNumber_Index);
			    if (cell_BinNum == null)
			    	cell_BinNum = row.createCell(Col_BinNumber_Index);
			    
			    cell_BinNum.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_BinNum.toString();		
			    NewVal = getNextVal(ExistingVal, Col_BinNumber_Index,SheetName);
			    
			    cell_BinNum.setCellValue(NewVal);
				
			}
			else if(SheetName == "AddCorporate") // // For AddCorporate Sheet
			{
							 
				 // Corporate Name
				Cell cell_CorpName = row.getCell(Col_CorporateName_Index);
			    if (cell_CorpName == null)
			    	cell_CorpName = row.createCell(Col_CorporateName_Index);
			    
			    cell_CorpName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_CorpName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_CorporateName_Index,SheetName);
			    
			    cell_CorpName.setCellValue(NewVal);	
				
			 // Corporate Id
				Cell cell_CorpId = row.getCell(Col_CorporateID_Index);
			    if (cell_CorpId == null)
			    	cell_CorpId = row.createCell(Col_CorporateID_Index);
			    
			    cell_CorpId.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_CorpId.toString();		
			    NewVal = getNextVal(ExistingVal, Col_CorporateID_Index,SheetName);
			    
			    cell_CorpId.setCellValue(NewVal);
			    
			    
			    
			 // Corporate Email
				Cell cell_CorpEmail = row.getCell(Col_CorpEmail_Index);
			    if (cell_CorpEmail == null)
			    	cell_CorpEmail = row.createCell(Col_CorpEmail_Index);
			    
			    cell_CorpEmail.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_CorpEmail.toString();		
			    NewVal = getNextVal(ExistingVal, Col_CorpEmail_Index,SheetName);
			    
			    cell_CorpEmail.setCellValue(NewVal);
			    
			    
			 // Corporate Phone
				Cell cell_CorpPhone = row.getCell(Col_CorpPhone_Index);
			    if (cell_CorpPhone == null)
			    	cell_CorpPhone = row.createCell(Col_CorpPhone_Index);
			    
			    cell_CorpPhone.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_CorpPhone.toString();		
			    NewVal = getNextVal(ExistingVal, Col_CorpPhone_Index,SheetName);
			    
			    cell_CorpPhone.setCellValue(NewVal);
				
			}
			else if(SheetName == "APIUser") // // For APIUser Sheet
			{
				//User Id
				Cell cell_UID = row.getCell(Col_APIUSer_UserId_Index);
			    if (cell_UID == null)
			    	cell_UID = row.createCell(Col_APIUSer_UserId_Index);
			    
			    cell_UID.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_UID.toString();		
			    NewVal = getNextVal(ExistingVal, Col_APIUSer_UserId_Index,SheetName);
			    
			    cell_UID.setCellValue(NewVal);
			    
			    
			    // Email Id
				Cell cell_Email = row.getCell(Col_APIUSer_Email_Index);
			    if (cell_Email == null)
			    	cell_Email = row.createCell(Col_APIUSer_Email_Index);
			    
			    cell_Email.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Email.toString();		
			    NewVal = getNextVal(ExistingVal, Col_APIUSer_Email_Index,SheetName);
			    
			    cell_Email.setCellValue(NewVal);
				
			}
			else if(SheetName == "Role") // // For Role Sheet
			{
				//Role Name
				Cell cell_RoleName = row.getCell(Col_Role_RoleName_Index);
			    if (cell_RoleName == null)
			    	cell_RoleName = row.createCell(Col_Role_RoleName_Index);
			    
			    cell_RoleName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_RoleName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_Role_RoleName_Index,SheetName);
			    
			    cell_RoleName.setCellValue(NewVal);
			}			
			else if(SheetName == "AddUsers") // // For Add User Sheet
			{
				//User Name
				Cell cell_UserName = row.getCell(Col_AddUsers_UserName_Index);
			    if (cell_UserName == null)
			    	cell_UserName = row.createCell(Col_AddUsers_UserName_Index);
			    
			    cell_UserName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_UserName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddUsers_UserName_Index,SheetName);
			    
			    cell_UserName.setCellValue(NewVal);
			    
				//Email
				Cell cell_Email = row.getCell(Col_AddUsers_Email_Index);
			    if (cell_Email == null)
			    	cell_Email = row.createCell(Col_AddUsers_Email_Index);
			    
			    cell_Email.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Email.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddUsers_Email_Index,SheetName);
			    
			    cell_Email.setCellValue(NewVal);
			    
			    
			    
				//Phone Number
				Cell cell_Phone = row.getCell(Col_AddUsers_Phone_Index);
			    if (cell_Phone == null)
			    	cell_Phone = row.createCell(Col_AddUsers_Phone_Index);
			    
			    cell_Phone.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Phone.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddUsers_Phone_Index,SheetName);
			    
			    cell_Phone.setCellValue(NewVal);
			}
			else if(SheetName == "AddMerchant") // // For Add Merchant Sheet
			{
				System.out.println("Enter the Update function for AddMerchant Sheet");
				//Business Name
				Cell cell_BussName = row.getCell(Col_AddMer_BussName_Index);
			    if (cell_BussName == null)
			    	cell_BussName = row.createCell(Col_AddMer_BussName_Index);
			    
			    cell_BussName.setCellType(Cell.CELL_TYPE_STRING);
			    			  	   
			    ExistingVal = cell_BussName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_BussName_Index,SheetName);
			    
			    cell_BussName.setCellValue(NewVal);
			    
				//Display Name
				Cell cell_dispName = row.getCell(Col_AddMer_DisplayName_Index);
			    if (cell_dispName == null)
			    	cell_dispName = row.createCell(Col_AddMer_DisplayName_Index);
			    
			    cell_dispName.setCellType(Cell.CELL_TYPE_STRING);
			    			  	    
			    ExistingVal = cell_dispName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_DisplayName_Index,SheetName);
			    
			    cell_dispName.setCellValue(NewVal);
			    
			    
			    
				//MCC
				Cell cell_MCC = row.getCell(Col_AddMer_MCC_Index);
			    if (cell_MCC == null)
			    	cell_MCC = row.createCell(Col_AddMer_MCC_Index);
			    
			    cell_MCC.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_MCC.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_MCC_Index,SheetName);
			    
			    cell_MCC.setCellValue(NewVal);
			    
			    
				//Valid From
				Cell cell_VFrom = row.getCell(Col_AddMer_ValidFrom_Index);
			    if (cell_VFrom == null)
			    	cell_VFrom = row.createCell(Col_AddMer_ValidFrom_Index);
			    
			    cell_VFrom.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_VFrom.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_ValidFrom_Index,SheetName);
			    
			    cell_VFrom.setCellValue(NewVal);
			    
			    
				//Valid To
				Cell cell_VTo = row.getCell(Col_AddMer_ValidTo_Index);
			    if (cell_VTo == null)
			    	cell_VTo = row.createCell(Col_AddMer_ValidTo_Index);
			    
			    cell_VTo.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_VTo.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_ValidTo_Index,SheetName);
			    
			    cell_VTo.setCellValue(NewVal);
			    
			    
				//Custom Name
				Cell cell_CName = row.getCell(Col_AddMer_CustomName_Index);
			    if (cell_CName == null)
			    	cell_CName = row.createCell(Col_AddMer_CustomName_Index);
			    
			    cell_CName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_CName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_CustomName_Index,SheetName);
			    
			    cell_CName.setCellValue(NewVal);
			    
			    
				//User Name
				Cell cell_UName= row.getCell(Col_AddMer_UserName_Index);
			    if (cell_UName == null)
			    	cell_UName = row.createCell(Col_AddMer_UserName_Index);
			    
			    cell_UName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_UName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_UserName_Index,SheetName);
			    
			    cell_UName.setCellValue(NewVal);
			    
			    
			    
				//Phone1
				Cell cell_Phone1 = row.getCell(Col_AddMer_Phone1_Index);
			    if (cell_Phone1 == null)
			    	cell_Phone1 = row.createCell(Col_AddMer_Phone1_Index);
			    
			    cell_Phone1.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Phone1.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_Phone1_Index,SheetName);
			    
			    cell_Phone1.setCellValue(NewVal);
			    
			    
			    
				//Phone2
				Cell cell_Phone2 = row.getCell(Col_AddMer_Phone2_Index);
			    if (cell_Phone2 == null)
			    	cell_Phone2 = row.createCell(Col_AddMer_Phone2_Index);
			    
			    cell_Phone2.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Phone2.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_Phone2_Index,SheetName);
			    
			    cell_Phone2.setCellValue(NewVal);
			    
			    
			    
				//Primary Email
				Cell cell_PEmail = row.getCell(Col_AddMer_PrimEmail_Index);
			    if (cell_PEmail == null)
			    	cell_PEmail = row.createCell(Col_AddMer_PrimEmail_Index);
			    
			    cell_PEmail.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_PEmail.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_PrimEmail_Index,SheetName);
			    
			    cell_PEmail.setCellValue(NewVal);
			    
			    
			    
				//Secondary Email
				Cell cell_SEmail = row.getCell(Col_AddMer_SeconEmail_Index);
			    if (cell_SEmail == null)
			    	cell_SEmail = row.createCell(Col_AddMer_SeconEmail_Index);
			    
			    cell_SEmail.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_SEmail.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_SeconEmail_Index,SheetName);
			    
			    cell_SEmail.setCellValue(NewVal);
			    
			    
			    
				//Level1 Email
				Cell cell_L1Email = row.getCell(Col_AddMer_Level1Email_Index);
			    if (cell_L1Email == null)
			    	cell_L1Email = row.createCell(Col_AddMer_Level1Email_Index);
			    
			    cell_L1Email.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_L1Email.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_Level1Email_Index,SheetName);
			    
			    cell_L1Email.setCellValue(NewVal);
			    
			    
				//Email
				Cell cell_Email = row.getCell(Col_AddMer_Email_Index);
			    if (cell_Email == null)
			    	cell_Email = row.createCell(Col_AddMer_Email_Index);
			    
			    cell_Email.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Email.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_Email_Index,SheetName);
			    
			    cell_Email.setCellValue(NewVal);
			    
			    
				//Phone Number
				Cell cell_PhoneNum = row.getCell(Col_AddMer_Phone_Index);
			    if (cell_PhoneNum == null)
			    	cell_PhoneNum = row.createCell(Col_AddMer_Phone_Index);
			    
			    cell_PhoneNum.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_PhoneNum.toString();		
			    NewVal = getNextVal(ExistingVal, Col_AddMer_Phone_Index,SheetName);
			    
			    cell_PhoneNum.setCellValue(NewVal);
			}
			else if(SheetName == "PaypageConfig") // // For PaypageConfig Sheet
			{
				//Merchant Email ID
				
				System.out.println("Col_PayPageConfig_MerEmail_Index :"+Col_PayPageConfig_MerEmail_Index);
				System.out.println("value from excl sheet  "+row.getCell(Col_PayPageConfig_MerEmail_Index));
				
				Cell cell_RoleName = row.getCell(Col_PayPageConfig_MerEmail_Index);
			    if (cell_RoleName == null)
			    	cell_RoleName = row.createCell(Col_PayPageConfig_MerEmail_Index);
			    
			    cell_RoleName.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_RoleName.toString();		
			    NewVal = getNextVal(ExistingVal, Col_PayPageConfig_MerEmail_Index,SheetName);
			    
			    cell_RoleName.setCellValue(NewVal);
			}
			else if(SheetName == "addprocessor") // // For  Processor Sheet
			{
				// Processor Name, validation: only alpha and unique
				Cell cell_Proc_Name = row.getCell(Col_Processor_ProcessName_Index);
			    if (cell_Proc_Name == null)
			    	cell_Proc_Name = row.createCell(Col_Processor_ProcessName_Index);
			    
			    cell_Proc_Name.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Proc_Name.toString();		
			    NewVal = getNextVal(ExistingVal, Col_Processor_ProcessName_Index,SheetName);
			    
			    cell_Proc_Name.setCellValue(NewVal);
			    
			    
				// Processor Code, validation: exact 11 digits
				Cell cell_Proc_Code = row.getCell(Col_Processor_ProcessCode_Index);
			    if (cell_Proc_Code == null)
			    	cell_Proc_Code = row.createCell(Col_Processor_ProcessCode_Index);
			    
			    cell_Proc_Code.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Proc_Code.toString();		
			    NewVal = getNextVal(ExistingVal, Col_Processor_ProcessCode_Index,SheetName);
			    
			    cell_Proc_Code.setCellValue(NewVal);
			    
			    
				// Processor Email, validation: unique
				Cell cell_Proc_Email = row.getCell(Col_Processor_ProcessEmail_Index);
			    if (cell_Proc_Email == null)
			    	cell_Proc_Email = row.createCell(Col_Processor_ProcessEmail_Index);
			    
			    cell_Proc_Email.setCellType(Cell.CELL_TYPE_STRING);
			    
			  	    
			    ExistingVal = cell_Proc_Email.toString();		
			    NewVal = getNextVal(ExistingVal, Col_Processor_ProcessEmail_Index,SheetName);
			    
			    cell_Proc_Email.setCellValue(NewVal);
			}			
		}	
		
	}
		
	
	
	
	
	
	public static String getNextVal(String PrevVal, int ColIndex, String SheetName)
	{
		String retval = "";
		
		if(SheetName == "AddBank")
		{
			// Bank Name
			if(ColIndex == Col_BankName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "BName1";
				}
				else 
				{
					System.out.println("The value after substring for Bank Name is : " + PrevVal.substring(5));
					retval = "BName" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
			// Bank Id
			else if(ColIndex == Col_BankID_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "BankAAA1000";
				}
				else 
				{
					System.out.println("The value after substring for Bank Id is : " + PrevVal.substring(7));
					retval = "BankAAA" + String.valueOf(Integer.parseInt(PrevVal.substring(7)) + 1);				
				}
			}
			// Email ID
			else if(ColIndex == Col_Email_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "troyt1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for Bank Email ID is : " + intval);
					retval = "troyt" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}
			// Contact Number
			else if(ColIndex == Col_ContactNum_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "9999110000";
				}
				else 
				{
					System.out.println("The value after substring is Bank Contact No : " + PrevVal.substring(5));
					retval = "99991" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
		}
		else if(SheetName == "BankBinConfig")
		{
			// Bin Number
			if(ColIndex == Col_BinNumber_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "111000";
				}
				else 
				{
					System.out.println("The value after substring for Bin Number is : " + PrevVal.substring(2));
					retval = "11" + String.valueOf(Integer.parseInt(PrevVal.substring(2)) + 1);				
				}
			}
		}
		else if(SheetName == "AddCorporate")
		{
			// Corporate Name
			if(ColIndex == Col_CorporateName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = getRandonName();
				}
				else 
				{
					System.out.println("The value after substring for Corporate name is : " + PrevVal);
					retval = getRandonName();
				}
			}
			// Corporate Id
			if(ColIndex == Col_CorporateID_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "corport1000";
				}
				else 
				{
					System.out.println("The value after substring for Corporate Id is : " + PrevVal.substring(7));
					retval = "corport" + String.valueOf(Integer.parseInt(PrevVal.substring(7)) + 1);				
				}
			}
			// Corporate Email
			else if(ColIndex == Col_CorpEmail_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "pavit1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for corp Email is : " + intval);
					retval = "pavit" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}
			// Corporate Contact Number
			else if(ColIndex == Col_CorpPhone_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "9999210000";
				}
				else 
				{
					System.out.println("The value after substring for Corp Phone is : " + PrevVal.substring(5));
					retval = "99992" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
		}
		else if(SheetName == "APIUser")
		{
			// User id   // validation: length should be exact 11 chars [allow alpha-numeric]
			if(ColIndex == Col_APIUSer_UserId_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "APIUsid1000";
				}
				else 
				{
					System.out.println("The value after substring for APIUser UserID is : " + PrevVal.substring(7));
					retval = "APIUsid" + String.valueOf(Integer.parseInt(PrevVal.substring(7)) + 1);				
				}
			}
			//Email
			else if(ColIndex == Col_APIUSer_Email_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "kroat1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for APIUser Email is : " + intval);
					retval = "kroat" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";	
				}
			}
		}
		else if(SheetName == "Role")
		{
			// Role Name
			if(ColIndex == Col_Role_RoleName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "AutoRole1000";
				}
				else 
				{
					
					
					System.out.println("The value after substring for Role Role Name is : " + PrevVal.substring(8));
					retval = "AutoRole" + String.valueOf(Integer.parseInt(PrevVal.substring(8)) + 1);	
					//retval = "AutoRole1252";
					
				}
			}				
		}
		else if(SheetName == "AddUsers")
		{
			// User Name
			if(ColIndex == Col_AddUsers_UserName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "DailyAdmin1000";
				}
				else 
				{
					System.out.println("The value after substring for AddUsers UserName is : " + PrevVal.substring(10));
					retval = "DailyAdmin" + String.valueOf(Integer.parseInt(PrevVal.substring(10)) + 1);				
				}
			}							
			// Email id
			if(ColIndex == Col_AddUsers_Email_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "hariu1000@mailcatch.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for Add User Email is : " + intval);
					retval = "hariu" + String.valueOf(Integer.parseInt(intval) + 1) + "@mailcatch.com";	
				}
			}						
			// Phone
			if(ColIndex == Col_AddUsers_Phone_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "9999310000";
				}
				else 
				{
					System.out.println("The value after substring for Add User Phone is : " + PrevVal.substring(5));
					retval = "99993" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
		}
		else if(SheetName == "AddMerchant")
		{
			// Buss Name
			if(ColIndex == Col_AddMer_BussName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = getRandonName();
				}
				else 
				{
					System.out.println("The value after substring for Buss Name  is : " +getRandonName());
					retval = getRandonName();				
				}
			}							
			// Display Name
			if(ColIndex == Col_AddMer_DisplayName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "MerchantName1000";
				}
				else 
				{
					String strval = PrevVal.substring(12);
					int intval = Integer.parseInt(strval);
					
					System.out.println("The value after substring for Display Name is : " + intval);
					retval = "MerchantName" + String.valueOf(intval + 1);	
				}
			}						
			// MCC
			if(ColIndex == Col_AddMer_MCC_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "1000";
				}
				else 
				{
					System.out.println("The value after substring for MCC is : " + PrevVal);
					retval = String.valueOf(Integer.parseInt(PrevVal) + 1);				
				}
			}
			// Valid From
			if(ColIndex == Col_AddMer_ValidFrom_Index)
			{				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");				
				Date date = new Date();				
				retval = dateFormat.format(date);
			}
			// Valid To
			if(ColIndex == Col_AddMer_ValidTo_Index)
			{				
				Calendar javaCalendar = null;
				javaCalendar = Calendar.getInstance();
				String FutureDate = javaCalendar.get(Calendar.DATE) + "-" + (javaCalendar.get(Calendar.MONTH) + 2) + "-" + javaCalendar.get(Calendar.YEAR);
				
				//Format Date
				String d = String.valueOf(javaCalendar.get(Calendar.DATE));
				if(d.length() == 1)
				{
					d= "0" + d;
				}

				//Format Month
				String m = String.valueOf(javaCalendar.get(Calendar.MONTH) + 2);
				if(m.length() == 1)
				{
					m= "0" + m;
				}

				
				//Format Year
				String y = String.valueOf(javaCalendar.get(Calendar.DATE));
				
				FutureDate = d + m + y;
				
				System.out.println("The Future Date in Add Merchant is : " + FutureDate);
				
				retval = FutureDate;
			}		
			// Custom Name
			if(ColIndex == Col_AddMer_CustomName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = getRandonName();
				}
				else 
				{
					System.out.println("The value after substring for Custom Name is : " + getRandonName());
					retval = getRandonName();			
				}
			}
			// User Name
			if(ColIndex == Col_AddMer_UserName_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "daily1000";
				}
				else 
				{
					System.out.println("The value after substring for User Name is : " + PrevVal.substring(5));
					retval = "daily" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
			// Phone1
			if(ColIndex == Col_AddMer_Phone1_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "9999410000";
				}
				else 
				{
					System.out.println("The value after substring for Phone1 is : " + PrevVal.substring(5));
					retval = "99994" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
			// Phone2
			if(ColIndex == Col_AddMer_Phone2_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "9999510000";
				}
				else 
				{
					System.out.println("The value after substring for Phone2 is : " + PrevVal.substring(5));
					retval = "99995" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
			// Primary Email ID
			else if(ColIndex == Col_AddMer_PrimEmail_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "harim1000@mailcatch.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for Add Merchant Primary Email ID is : " + intval);
					retval = "harim" + String.valueOf(Integer.parseInt(intval) + 1) + "@mailcatch.com";				
				}
			}
			// Secondary Email ID
			else if(ColIndex == Col_AddMer_SeconEmail_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "pangu1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for Add Merchant Secondary Email ID is : " + intval);
					retval = "pangu" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}
			// Level1 Email ID
			else if(ColIndex == Col_AddMer_Level1Email_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "hulky1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for Add Merchant Level1 Email ID is : " + intval);
					retval = "hulky" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}
			// Email ID
			else if(ColIndex == Col_AddMer_Email_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "white1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for Add Merchant Email ID is : " + intval);
					retval = "white" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}
			// Phone Number
			if(ColIndex == Col_AddMer_Phone_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "9999610000";
				}
				else 
				{
					System.out.println("The value after substring for Add Merchant Phone is : " + PrevVal.substring(5));
					retval = "99996" + String.valueOf(Integer.parseInt(PrevVal.substring(5)) + 1);				
				}
			}
		}	
		else if(SheetName == "PaypageConfig")
		{
			// Merchant Email Address
			if(ColIndex == Col_PayPageConfig_MerEmail_Index)
			{
				if(PrevVal.length() == 0)
				{
					retval = "gurja1000@gmail.com";
				}
				else 
				{
					String intval = PrevVal.substring(5,9);
					System.out.println("The value after substring for PaypageConfig Email ID is : " + intval);
					retval = "gurja" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}	
		}		
		else if(SheetName == "addprocessor")
		{
			// Processor Name
			if(ColIndex == Col_Processor_ProcessName_Index)
			{
				retval = getRandonName();				
			}
			// Processor Code
			else if(ColIndex == Col_Processor_ProcessCode_Index)
			{
				retval = getRandonValues("ProcessorCode");
			}
			// Processor Email
			else if(ColIndex == Col_Processor_ProcessEmail_Index)
			{
				if(PrevVal.length() == 0)
				{
					//retval = "gurja1000@gmail.com"; (5,9)
					retval = "dharam1000@gmail.com";  //(6,10)

				}
				else 
				{
					String intval = PrevVal.substring(6,10);
					System.out.println("The value after substring for Processor Email ID is : " + intval);
					retval = "dharam" + String.valueOf(Integer.parseInt(intval) + 1) + "@gmail.com";				
				}
			}
		}
		else 			
		{
			System.out.println("Bad Sheet Name : " + SheetName);
		}
	
		return retval;
		
	}
	
	public static String getRandonName()
	{
		
		String[] array = {"s", "t", "u", "v","w","y"};
		
		Random ran = new Random();
		
		String gen = array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)];
				
		System.out.println("from getRandonName() def :   "+gen.toUpperCase());
		
		return gen;
	}
	
	
	public static String getRandonValues(String Val_type)
	{
		String ret ="";
		Random ran = new Random();
		
		if(Val_type.equalsIgnoreCase("cardnumber"))
		{
			String[] array = {"1", "2", "3", "4","5","6","7", "8", "9", "0","1","2","3","4","5","6"};
			
			
			ret = array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)];
		}
		else if(Val_type.equalsIgnoreCase("IPAddress"))
		{
			

			ret = ran.nextInt(256) + "." + ran.nextInt(256) + "." + ran.nextInt(256) + "." + ran.nextInt(256);
			
			
		}
		else if(Val_type.equalsIgnoreCase("MSISDN") || (Val_type.equalsIgnoreCase("USERID")) )
		{			
			String[] array = {"1", "2", "3", "4","5","6","7"};			
			
			ret = array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)];
						
		}
		else if(Val_type.equalsIgnoreCase("ProcessorCode"))
		{			
			String[] array = {"1", "2", "3", "4","5","6","7","8","9","1","2"};			
			
			ret = array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)];
						
		}
		else if(Val_type.equalsIgnoreCase("PaypageOrderID"))
		{			
			String[] array = {"1", "2", "3", "4","5","6","7","8","9","1","2"};			
			
			ret = array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)];
						
		}
		else if(Val_type.equalsIgnoreCase("Mobile"))
		{			
			String[] array = {"1", "2", "3", "4","5","6","7","8","9","1"};			
			
			ret = array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)] + array[ran.nextInt(array.length)];
						
		}
		
	
				
		System.out.println("getRandonValues() : return for : " + Val_type  + " = " + ret);
		
		
		
		return ret;
	}
		
}	
	



