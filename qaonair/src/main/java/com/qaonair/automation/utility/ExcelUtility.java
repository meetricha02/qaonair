package com.qaonair.automation.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelUtility {
String[][] xData, xTC;        
	public int nRows;
	public int rEID, rTC,rdata;
	
	
	
	
	@Test
	//Read Test cases sheet from Excel//
	public String[][] readTestcases() throws IOException{
		System.out.println("+++++++++++++++Reading TestCase Sheet+++++++++");
		//Get xlpath from Properties File//
		String fpath=PropertyFileReader.getProperty("xlpath");
		FileInputStream fIn= new FileInputStream(fpath);
		HSSFWorkbook wb = new HSSFWorkbook(fIn); 
		HSSFCell cell;
		HSSFSheet sheet=wb.getSheet(PropertyFileReader.getProperty("fTC"));
				
		//Calculating Rows and Columns//
		HSSFRow row= sheet.getRow(0);
		rTC=sheet.getLastRowNum()+1;
		int cTC=sheet.getRow(0).getLastCellNum();
		System.out.println("No. of rows in TestCases sheet are "+ rTC);
		System.out.println("No. of Columns in TestCases sheet are "+ cTC);
		
		xTC = new String[rTC][cTC];
		for (int i=1; i<rTC;i++){
			HSSFRow rowTemp= sheet.getRow(i);	
			for(int j=0;j<cTC;j++){
				cell= rowTemp.getCell(j);
				String testcaseValue = "-";
				if (cell!=null){
					testcaseValue = cellToString(cell);
					System.out.println("The TestCase Sheet values are:"+ testcaseValue);
					xTC[i][j]= testcaseValue;
				}
			}
		}
		return xTC;	
	}

	
	
	
	@Test
	public String[][] readTestSteps() throws IOException{

		String fpath=PropertyFileReader.getProperty("xlpath");
		FileInputStream fin= new FileInputStream(fpath);
		HSSFWorkbook wb = new HSSFWorkbook(fin); 
		HSSFCell cell;
		HSSFSheet sheet=wb.getSheet(PropertyFileReader.getProperty("fTS"));
		HSSFRow row= sheet.getRow(0);
		 nRows=sheet.getLastRowNum()+1;
		int nCols=sheet.getRow(0).getLastCellNum();

		System.out.println("No. of rows in the"+ PropertyFileReader.getProperty("fTS")+"Sheet are"+ nRows);
		System.out.println("No. of Columns in"+ PropertyFileReader.getProperty("fTS")+" Sheet are"+ nCols);

		//making a 2D array in memory equal to the size of array read
		xData=new String  [nRows][nCols];
		for(int i=1;i<nRows;i++){
			HSSFRow row1= sheet.getRow(i);
			for(int j=0;j<nCols;j++){
				cell= row1.getCell(j);
				String value = "-";
				if (cell!=null){
					value = cellToString(cell);
					System.out.println("The xData[][] values are:"+ value);
					xData[i][j]= value;

				}
			}
		}
		return xData;
	} 

	
	@Test()
	public String[][] readElementID() throws IOException{
		String fpath=PropertyFileReader.getProperty("xlpath");
		FileInputStream fin= new FileInputStream(fpath);
		HSSFWorkbook wb = new HSSFWorkbook(fin); 
		HSSFCell cell;
		HSSFSheet sheet=wb.getSheet(PropertyFileReader.getProperty("fEID"));
		HSSFRow row= sheet.getRow(0);
		 rEID=sheet.getLastRowNum()+1;
	int	 cEID=sheet.getRow(0).getLastCellNum();	

			System.out.println("No. of rows in the EID Sheet are"+ rEID);
			System.out.println("No. of Columns in EID Sheet are"+ cEID);

			String[][] xElement=new String  [rEID][cEID];
			for(int i=1;i<rEID;i++){
				row= sheet.getRow(i);
				
				for(int j=0;j<cEID;j++){
					cell= row.getCell(j);
					String value = "-";
					if (cell!=null){
						value = cellToString(cell);
						System.out.println("The xElement[][] values are:"+ value);
						xElement[i][j]= value;
					}
				}
			}
			return xElement;		
	}
	
	
	
	@Test(enabled=false)
	public String[][] readSheetData() throws IOException{
		String fpath=PropertyFileReader.getProperty("xlpath");
		FileInputStream fin= new FileInputStream(fpath);
		HSSFWorkbook wb = new HSSFWorkbook(fin); 
		HSSFCell cell;
		HSSFSheet sheet=wb.getSheet(PropertyFileReader.getProperty("fTestData"));
		HSSFRow row= sheet.getRow(0);
		 rdata=sheet.getLastRowNum()+1;
	int	 cdata=sheet.getRow(0).getLastCellNum();	
	System.out.println("No. of rows in the TestData Sheet are"+ rdata);
	System.out.println("No. of Columns in TestData Sheet are"+ cdata);
	
	String[][] xSheetData=new String  [rdata][cdata];
	for(int i=1;i<rdata;i++){
		row= sheet.getRow(i);
		
		for(int j=0;j<cdata;j++){
			cell= row.getCell(j);
			String value = "-";
			if (cell!=null){
				value = cellToString(cell);
				System.out.println("The xElement[][] values are:"+ value);
				xSheetData[i][j]= value;
			}
		}
	}
	return xSheetData;	
		
	}
	
	
	@Test
	public static String cellToString(HSSFCell cell) { 
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();                        
		Object result;                        
		switch (type) {                            
		case HSSFCell.CELL_TYPE_NUMERIC: //0                                
			result = cell.getNumericCellValue();                                
			break;                            
		case HSSFCell.CELL_TYPE_STRING: //1                                
			result = cell.getStringCellValue();                                
			break;                            
		case HSSFCell.CELL_TYPE_FORMULA: //2                                
			throw new RuntimeException("We can't evaluate formulas in Java");  
		case HSSFCell.CELL_TYPE_BLANK: //3                                
			result = "%";                                
			break;                            
		case HSSFCell.CELL_TYPE_BOOLEAN: //4     
			result = cell.getBooleanCellValue();       
			break;                            
		case HSSFCell.CELL_TYPE_ERROR: //5       
			throw new RuntimeException ("This cell has an error");    
		default:                  
			throw new RuntimeException("We don't support this cell type: " + type); 
		}                        
		return result.toString();      
	}

		
	}
	
	
	
	
	

