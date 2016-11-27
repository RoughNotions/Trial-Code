package com.apc.autotest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.apc.autotest.utils.FieldsMapInitilizer;

public class ResponseManager {

	public void manage(String response, int rownum, String resCode,
			String resMessage) {
		try {
			System.out.println("rownumber " + rownum);
			Cell cell = null;
			FileInputStream file = null;
			file = new FileInputStream(new File(FieldsMapInitilizer.EXCEL_PATH));

			// TODO Auto-generated catch block
			XSSFWorkbook workbook = null;
			workbook = new XSSFWorkbook(file);

			// workbook style
			CellStyle globalStyle = workbook.createCellStyle();
			globalStyle.setWrapText(true);
			globalStyle.setAlignment(XSSFCellStyle.VERTICAL_TOP);

			CellStyle failTestStyle = workbook.createCellStyle();
			failTestStyle.setWrapText(true);
			failTestStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			failTestStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

			CellStyle passTestStyle = workbook.createCellStyle();
			passTestStyle.setWrapText(true);
			passTestStyle.setFillForegroundColor(IndexedColors.BLUE_GREY
					.getIndex());
			passTestStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			// TODO Auto-generated catch block
			XSSFSheet sheet = workbook.getSheet("Response");
			sheet.setDefaultRowHeight((short) 1000);
			sheet.setDefaultColumnWidth((short) 25);
			// sheet.setDefaultColumnWidth((short) 10000);
			XSSFRow testCaseRow = sheet.createRow(rownum);

			// creating cell for XML response
			Cell xmlResponseCell = testCaseRow.createCell(2);
			xmlResponseCell.setCellStyle(globalStyle);
			xmlResponseCell.setCellValue(response);

			// creating Cell from response code
			Cell responseCode = testCaseRow.createCell(0);

			responseCode.setCellStyle(globalStyle);
			responseCode.setCellValue(resCode);

			// creating Cell from response message
			Cell responseMsg = testCaseRow.createCell(1);

			responseMsg.setCellStyle(globalStyle);
			responseMsg.setCellValue(resMessage);

			// creating Cell from response message
			Cell testResult = testCaseRow.createCell(3);

			// testResult.setCellStyle(style);
			if (resCode.equals("1000")) {
				testResult.setCellValue("PASS");
				testResult.setCellStyle(passTestStyle);
			} else {
				testResult.setCellValue("FAIL");
				testResult.setCellStyle(failTestStyle);
			}

			// file.close();

			FileOutputStream outFile = new FileOutputStream(new File( 
					FieldsMapInitilizer.EXCEL_PATH));
			workbook.write(outFile);

			outFile.close();

			// TODO Auto-generated catch block
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
