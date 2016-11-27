package com.apc.common;


	import java.io.File;
	import java.io.IOException;
	import java.util.Random;

	import jxl.Workbook;
	import jxl.read.biff.BiffException;
	import jxl.write.Label;
	import jxl.write.WritableSheet;
	import jxl.write.WritableWorkbook;
	import jxl.write.WriteException;
	import jxl.write.biff.RowsExceededException;

	public class RandomNumberGenerator {

		/**
		 * @param args
		 * @throws IOException 
		 * @throws BiffException 
		 * @throws WriteException 
		 * @throws RowsExceededException 
		 */
		
		//public static String TxnID = null;
		
		public static void main(String[] args) throws IOException, BiffException, RowsExceededException, WriteException {
			// TODO Auto-generated method stub

			Workbook workbook = Workbook.getWorkbook(new File("Resourses\\Data\\API_Data.xls"));
			WritableWorkbook TempWorkbook = Workbook.createWorkbook(new File("Resourses\\Data\\API_Data.xls"), workbook);
			
			WritableSheet sheet = TempWorkbook.getSheet("Sheet1");
			int rCount = sheet.getRows();
			//System.out.println(rCount);
			
			 //Random randomGenerator = new Random();
			    for (int idx = 1; idx <= rCount; ++idx){
			      //int randomInt = randomGenerator.nextInt(idx);
			      //log("Generated : " + randomInt);
			    	Random rand = new Random();
			     String TxnID1 = "Work"+ rand.nextInt(1000000);
					//System.out.println(TxnID1);
					Random rand2 = new Random();
				String TxnID2 = "Log" + rand2.nextInt(1001);
				
				Random rand3 = new Random();
				String TxnID3 = "Log" + rand3.nextInt(2001);
				
				String TxnID = TxnID1 + TxnID2 + TxnID3;
					Label label = new Label(18, idx, TxnID);
					sheet.addCell(label);
			    }
			
			
			
			TempWorkbook.write();
			TempWorkbook.close();
		
		}
	}