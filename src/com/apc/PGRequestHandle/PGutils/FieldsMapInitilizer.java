package com.apc.PGRequestHandle.PGutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.apc.autotest.handler.AutoTestFacade;
import com.apc.PGRequestHandle.PGhandler.AutoTestFacade;


public class FieldsMapInitilizer {
	public static String EXCEL_PATH;
	public static ArrayList<String> fieldsList;
	public static XSSFWorkbook workbook;
	static {
		System.out.println("STATIC");
		fieldsList = new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(new File(
					AutoTestFacade.EXCEL_PATH));

			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterator<XSSFSheet> sheetIterator=workbook.iterator();
			List<String> sheetNames = new ArrayList<String>();
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				sheetNames.add(workbook.getSheetName(i));
			}
			System.out.println("sheetsname" + sheetNames);
			if (!(sheetNames.contains("Response"))) {
				workbook.createSheet("Response");
				FileOutputStream outFile = new FileOutputStream(new File(
						AutoTestFacade.EXCEL_PATH));
				workbook.write(outFile);
				outFile.close();

			}

			// Iterate through each rows one by one
			Row row = sheet.getRow(0);

			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				// Check the cell type and format accordingly
				switch (cell.getCellType()) {

				/*
				 * case Cell.CELL_TYPE_NUMERIC:
				 * System.out.print(cell.getNumericCellValue() + "\t");
				 * fieldsMap.put(cell.getNumericCellValue(), arg1) break;
				 */
				case Cell.CELL_TYPE_STRING:
					// System.out.print(cell.getStringCellValue() + "\t");
					fieldsList.add(cell.getStringCellValue());
					// System.out.println("fields555");

					break;
				}

				// System.out.println("");
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n555");
		for (int i = 0; i < fieldsList.size(); i++)
			System.out.print((fieldsList.get(i)).toString());

	}

	public static void doIntitilize(String path, int rownum) {
		FieldsMapInitilizer.EXCEL_PATH = path;
		// System.out.println("fields" + fieldsList);

	}
}
