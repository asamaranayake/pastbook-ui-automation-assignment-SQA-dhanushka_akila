package com.pastbook.automation.util;

/**
 * @author Akila
 *
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {

	 
	public Workbook getWorkBook(String Path) throws Exception {

		Workbook workbook = null;

		if (Path.contains(".xlsx")) {

			try {
				FileInputStream str = new FileInputStream(Path);
				workbook = new XSSFWorkbook(str);

			} catch (FileNotFoundException e) {
				throw new Exception("File not found in path :" + Path);
			}

		} else if (Path.contains(".xls")) {
			try {
				FileInputStream str = new FileInputStream(Path);
				workbook = new HSSFWorkbook(str);
			} catch (FileNotFoundException e) {
				throw new Exception("File not found in path :" + Path);
			}
		} else {

			throw new Exception("Invalid File Path / Not an excel path : "
					+ Path);
		}
		return workbook;
	}
	
	
	public Sheet getSheetData(String path ,int sheetno) throws Exception{
		
		Workbook  wb = getWorkBook(path);
		
		if(wb instanceof HSSFWorkbook) {
			HSSFSheet sheet1  = (HSSFSheet) wb.getSheetAt(sheetno);
			return sheet1;
		}else if (wb instanceof XSSFWorkbook){
			XSSFSheet sheet1  = (XSSFSheet) wb.getSheetAt(sheetno);
			return sheet1;
		}else {
			throw new Exception("Invalid workbook object recieved");
		}
		
		
	}
	
	public ArrayList<Sheet> getAllSheetData(String path) throws Exception{
		
          Workbook  wb = getWorkBook(path);
		
		if(wb instanceof HSSFWorkbook) {
			
			ArrayList<Sheet>  sheetlist = new ArrayList<Sheet>(wb.getNumberOfSheets());
			for (int i = 0; i < wb.getNumberOfSheets(); i++)
			sheetlist.add((HSSFSheet)wb.getSheetAt(i));
			return sheetlist;
			
		}else if (wb instanceof XSSFWorkbook){
	        
			ArrayList<Sheet>  sheetlist = new ArrayList<Sheet>(wb.getNumberOfSheets());
			for (int i = 0; i < wb.getNumberOfSheets(); i++)
			sheetlist.add((XSSFSheet)wb.getSheetAt(i));
			return sheetlist;
			
		}else {
			throw new Exception("Invalid workbook object recieved");
		}
	}
	// Use this method to get a perticular sheet :-)
	public Sheet getSheetData(String path ,String sheetname) throws Exception{
		
		Workbook  wb = getWorkBook(path);
		
		if(wb instanceof HSSFWorkbook) {
			HSSFSheet sheet1  = (HSSFSheet) wb.getSheet(sheetname.trim());
			return sheet1;
		}else if (wb instanceof XSSFWorkbook){
			XSSFSheet sheet1  = (XSSFSheet) wb.getSheet(sheetname.trim());
			return sheet1;
		}else {
			throw new Exception("Invalid workbook object recieved");
		}
		
		
	}
	// Use this method to get all the sheets in a hashmap
    public HashMap<String,Sheet> getAllSheetDataWithTitel(String Path) throws Exception{

		
        Workbook  wb = getWorkBook(Path);
		
		if(wb instanceof HSSFWorkbook) {
			
			HashMap<String,Sheet>  sheetlist = new HashMap<String, Sheet>(wb.getNumberOfSheets()) ;
			for (int i = 0; i < wb.getNumberOfSheets(); i++){
			    HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(i); 
				sheetlist.put(sheet.getSheetName(),sheet);
			}
			return sheetlist;
			
		}else if (wb instanceof XSSFWorkbook){
	        
			HashMap<String,Sheet>  sheetlist = new HashMap<String, Sheet>(wb.getNumberOfSheets()) ;
			for (int i = 0; i < wb.getNumberOfSheets(); i++){
				XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(i); 
				sheetlist.put(sheet.getSheetName(),sheet);
			}
			return sheetlist;
			
		}else {
			throw new Exception("Invalid workbook object recieved");
		}
	
		
	}
    // Use this method to get all the sheet data in a 2D array
    public String[][] getExcelData(String fileName, String sheetName) throws Exception {
 		String[][] arrayExcelData = null;
 		try {
 	
 			Sheet sh = getSheetData(fileName, sheetName);
 			
 			int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
 			int totalNoOfRows = sh.getPhysicalNumberOfRows();
 			
 			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
 			
 			for (int i= 1 ; i < totalNoOfRows; i++) {

 				Row row = sh.getRow(i);
 				for (int j=0; j < totalNoOfCols; j++) {
 					arrayExcelData[i-1][j] = row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
 					
 				}

 			}
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		} catch (Exception e){
 			System.out.println(e);
 			e.printStackTrace();
 		}
 		return arrayExcelData;
 	}
	

}
