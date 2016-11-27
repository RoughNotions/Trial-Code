package com.apc.PGRequestHandle.PGservice;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

//import com.apc.autotest.utils.FieldsMapInitilizer;
import com.apc.PGRequestHandle.PGutils.FieldsMapInitilizer;


public class ValuesMapInitilizer {

	// public static ArrayList<String> valuesList;

	public void doInitilize(int rownum, String paymentID) {
		
		int cur_col = 0;
		
		ArrayList<String> valuesList = new ArrayList<String>();
		XSSFSheet sheet = FieldsMapInitilizer.workbook.getSheetAt(0);

		Row row = sheet.getRow(rownum);
		// For each row, iterate through all the columns
		Iterator<Cell> cellIterator = row.cellIterator();

		while (cellIterator.hasNext()) {
			// System.out.println("num++");
			cur_col = cur_col + 1;
			System.out.println("doInitilize() -> Row / Col : " + rownum  + " / " + cur_col);
			Cell cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			// Check the cell type and format accordingly
			switch (cell.getCellType()) {

			/*
			 * case Cell.CELL_TYPE_NUMERIC: //
			 * System.out.print(cell.getNumericCellValue() + "\t");
			 * valuesList.add(cell.getStringCellValue()); break;
			 */
			case Cell.CELL_TYPE_BLANK:
				// System.out.println("blank");
				System.out.print("" + "\t");
				valuesList.add("*");
				break;
			case Cell.CELL_TYPE_STRING:				
				if(cur_col == 13 && paymentID.length() > 0) // ankur, change for sending the Payment ID value to request
				{
					System.out.print("Ankur test of Request data row wise : " + cell.getStringCellValue() + "\t");
					valuesList.add(paymentID);
				}else
				{
					valuesList.add(cell.getStringCellValue());
				}
				break;
			// System.out.print(cell.getStringCellValue() + "\t");
			// valuesList.add(cell.getStringCellValue());

			}

		}
		System.out.println("\n666");

		for (int i = 0; i < valuesList.size(); i++)
			System.out.print((valuesList.get(i)).toString());

		// RequestMapBuilder.doInitilize(valuesList,rownum);
		ServiceFactory.getRequestMapBuilder().doInitilize(valuesList, rownum);

	}
}
