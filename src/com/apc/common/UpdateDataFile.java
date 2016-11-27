package com.apc.common;

import java.io.File;
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
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@SuppressWarnings("unused")
public class UpdateDataFile {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public static void update(String ExcelPath) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub

	InputStream inp = new FileInputStream(ExcelPath);
	
	Workbook wb = WorkbookFactory.create(inp);
	
	Sheet sheet = wb.getSheetAt(0);
	
	int rowCount = sheet.getPhysicalNumberOfRows();
	
	//Set date format
	
	/*CreationHelper createHelper = wb.getCreationHelper();
	CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setDataFormat(
        createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));*/
	
	//System.out.println(rowCount);
	
	for(int i = 1; i <= rowCount-1; i++){
	
		Row row = sheet.getRow(i);
		Cell cell = row.getCell(8);
	    if (cell == null)
	        cell = row.createCell(8);
	    cell.setCellType(Cell.CELL_TYPE_STRING);
	    
	    // RNG
	    Random rand = new Random();
	     String TxnID1 = "Work"+ rand.nextInt(1000000);
			//System.out.println(TxnID1);
			Random rand2 = new Random();
		String TxnID2 = "Log" + rand2.nextInt(1000000);
		
		String TxnID = TxnID1 + TxnID2;
		
	    cell.setCellValue(TxnID);
	    
	  
	    //print current date time 
	    
	    Cell cell1 = row.getCell(10);
	    
	    if (cell1 == null)
	    	
	    	cell1 = row.createCell(10);
	   
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
	    System.out.println("API data file updated");
	    
	}

	
		
	}
	

