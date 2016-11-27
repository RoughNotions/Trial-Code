package com.apc.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UpdatePGExcel 
{


	public static void updatePGdata() throws InvalidFormatException, IOException
	{
		
		ProcessPGExcelData p = new ProcessPGExcelData();
		
		System.out.println("App is going to update the Excel data");
			
		
		System.out.println("Going to update Add bank sheet data");
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "AddBank");
		
		System.out.println("Going to update BankBinConfig sheet data");
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "BankBinConfig");
		
		System.out.println("Going to update AddCorporate sheet data");
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "AddCorporate");
		
		System.out.println("Going to update APIUser sheet data");
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "APIUser");

		System.out.println("Going to update Role sheet data");
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "Role");

		
		System.out.println("Going to update AddMerchant sheet data");
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "AddMerchant");
		
				
		System.out.println("Going to update AddUsers sheet data");  
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "AddUsers");
		
		System.out.println("Going to update PaypageConfig sheet data"); 
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "PaypageConfig");
		
			
		System.out.println("Going to update Processor sheet data"); 
		p.update("Resourses\\Data\\PG_PortalData.xlsx", "addprocessor");
		
		
		System.out.println("PG Portal data updated");
	}
	
	
	public static void update_PG_API_Data(String ExcelPath) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub

	InputStream inp = new FileInputStream(ExcelPath);
	
	Workbook wb = WorkbookFactory.create(inp);
	
	Sheet sheet = wb.getSheetAt(0);
	
	int rowCount = sheet.getPhysicalNumberOfRows();
	
	
	// these is logic for PG API request, we need to use same order id for cancel, settle and Refund transaction
	
	String orignal_orderID = "";
	boolean is_needoCreateOrderId;
	
	// MerchantOrder id is at colum  = 11 (start from 0)
	
	for(int i = 1; i <= rowCount-1; i++)
	{
	
		Row row = sheet.getRow(i);
		Cell cell = row.getCell(11);
	    if (cell == null)
	        cell = row.createCell(11);
	    cell.setCellType(Cell.CELL_TYPE_STRING);
	    
	    is_needoCreateOrderId = IsNeedToCreateNewOrderId(ExcelPath,2,3,i);
		System.out.println("update_PG_API_Data() -> For Row index : " + i + ", is_needoCreateOrderId : " + is_needoCreateOrderId);
	    
	    if(is_needoCreateOrderId)
	    {
		    // RNG
		    Random rand = new Random();
		     String TxnID1 = "PGTxnID"+ rand.nextInt(1000000);
				//System.out.println(TxnID1);
				Random rand2 = new Random();
			String TxnID2 = "Log" + rand2.nextInt(1000000);
			
			String TxnID = TxnID1 + TxnID2;
			
			orignal_orderID = TxnID;
			cell.setCellValue(TxnID);
	    }
	    else  // set the previoud one (required for cancel, settle and refund trans)
	    {	    	
			cell.setCellValue(orignal_orderID);
	    }
		
		
		
	  
	    //print current date time is at 18 colum
	    
	    Cell cell1 = row.getCell(18);
	    
	    if (cell1 == null)
	    	
	    	cell1 = row.createCell(18);
	   
	    Format formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
	    String s = formatter.format(new Date());
	    	cell1.setCellValue(s);
	    	//cell1.setCellStyle(cellStyle);
	    	 //cell1.setCellType(Cell.CELL_TYPE_STRING);
	    ///System.out.println(x)
		
		
		 // Write the output to a file
	   	
	}
	 
		FileOutputStream fileOut = new FileOutputStream(ExcelPath);
	    wb.write(fileOut);
	    fileOut.close();
	    System.out.println("API data for PG file updated");
	    
	}

	
	public static boolean IsNeedToCreateNewOrderId(String ExcelPath,int sheetIndex,int col_Index,int RowIndex) throws InvalidFormatException, IOException
	{
		
		InputStream inp = new FileInputStream(ExcelPath);
		
		Workbook wb = WorkbookFactory.create(inp);
		
		Sheet sheet = wb.getSheetAt(sheetIndex);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
				
		for(int i = 1; i <= rowCount-1; i++)
		{
			
			//System.out.println("IsNeedToCreateNewOrderId() -> required Row is : " + RowIndex + " Row loop count -> cur row index :" + i);
			
			if(RowIndex != i) continue;
			
			System.out.println("IsNeedToCreateNewOrderId() -> RowIndex match" + i);
			
			Row row = sheet.getRow(i);
			if(row == null)
			{
				System.out.println("IsNeedToCreateNewOrderId() -> Error getting the Row at index : " + i);
				return false;
			}
			
			Cell cell = row.getCell(col_Index);
		    if (cell == null)
		    {		
		    	System.out.println("IsNeedToCreateNewOrderId() -> Error getting the Cell at index : " + col_Index);
		    	return false;
		    }
		    
		    String cell_val = cell.toString(); 
		    System.out.println("The Cell value : " + cell_val);
		    
		    if(cell_val.compareToIgnoreCase("create") == 0)
		    {		   
		    	System.out.println("IsNeedToCreateNewOrderId() -> got the match");
		    	return true;
		    }
		    else
		    {
		    	return false;
		    }
		    
		}	
				   
		return false;
	}
	
	
	public static void set_PaymentID_PG_API_Excel(String ExcelPath, int sheetIndex,int RowIndex, int ColIndex, String PaymentID) throws InvalidFormatException, IOException
	{
		int Exact_RowNum =-1;
		
		Exact_RowNum = RowIndex -1;
		
		InputStream inp = new FileInputStream(ExcelPath);
		
		Workbook wb = WorkbookFactory.create(inp);
		
		Sheet sheet = wb.getSheetAt(sheetIndex);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
				
		
		for(int i = 1; i <= rowCount-1; i++)
		{
			if(Exact_RowNum != i) continue;
			
			System.out.println("set_PaymentID_PG_API_Excel() -> Row  num to match : " + i);
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(ColIndex);
		    if (cell == null)
		        cell = row.createCell(ColIndex);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    
		    cell.setCellValue(PaymentID);
		
		}
		
		// write to Excel file
		FileOutputStream fileOut = new FileOutputStream(ExcelPath);
	    wb.write(fileOut);
	    fileOut.close();
	    System.out.println("API data for PG file updated");
		
		
		
	}

	
}
