package com.apc.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	
		// TODO Auto-generated method stub

		
		//String data = method1("C:/new.xlsx",0,0);
		
		//System.out.println(data);
	
	
	
public static String getData(String FilePath,String sheetName, int rownum, int colnum) throws InvalidFormatException, IOException{
	
	
	
	
	InputStream inp = new FileInputStream(FilePath);
	
	Workbook wb = WorkbookFactory.create(inp);
	
	Sheet sheet = wb.getSheet(sheetName);
	
	
	Row row = sheet.getRow(rownum);

	Cell cell = row.getCell(colnum);
	
	
	return   cell.toString();
	
}

public static void setData(String ExcelPath,String Sheetname,int row,int col,String value) throws InvalidFormatException, IOException {
	// TODO Auto-generated method stub

	InputStream inp = new FileInputStream(ExcelPath);
	
	Workbook wb = WorkbookFactory.create(inp);
	
	Sheet sheet = wb.getSheet(Sheetname);
	
	Row row1 = sheet.getRow(row);
	
	int rowCount = sheet.getPhysicalNumberOfRows();
	
	System.out.println(rowCount);
	
	Cell cell = row1.getCell(col);
	
    if (cell == null)
        cell = row1.createCell(col);
    cell.setCellType(Cell.CELL_TYPE_STRING);
    
    cell.setCellValue(value);
    
    FileOutputStream fileOut = new FileOutputStream(ExcelPath);
    wb.write(fileOut);
    fileOut.close();
   
	
}


public static String get_DomainName(String ExcelData)
{
	String ret;
	
	   ret = ExcelData.substring(ExcelData.lastIndexOf("@") + 1);
	    
	    return ret;
	
}


public static int get_rowCount(String FilePath,String sheetName) throws InvalidFormatException, IOException{
	
InputStream inp = new FileInputStream(FilePath);
	
	Workbook wb = WorkbookFactory.create(inp);
	
	Sheet sheet = wb.getSheet(sheetName);
	
	return sheet.getPhysicalNumberOfRows();
}
	
}




