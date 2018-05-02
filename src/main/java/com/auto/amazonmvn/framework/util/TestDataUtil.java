package com.auto.amazonmvn.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.auto.amazonmvn.framework.util.*;

import Exception.FilloException;
import Fillo.Connection;
import Fillo.Fillo;
import Fillo.Recordset;

public class TestDataUtil {

	public static Map<String,String> DataMap;
	
	public static void main(String[] args) throws FilloException, IOException {
		// TODO Auto-generated method stub
 //getTestCaseData("TC_001");
		//getTestCaseDatapoi("TC_001");
		List<Integer> liData=getAllMatchedRowNumberList("TC_002");
		System.out.println("no. of matching rows are:"+liData.size());
		for(int i=0;i<liData.size();i++){
			System.out.println("loop run no.:"+i);
			TestDataUtil.getTestCaseDatapoi(liData.get(i));
			}
	}

	public static void getTestCaseData(String argTestCaseId) throws FilloException, IOException{
			
		Fillo fillo=new Fillo();
		//Connection connection=fillo.getConnection(PropertiesUtil.getConfiurationValue("TestDataPath"));
		Connection connection=fillo.getConnection("TestData/TestData.xlsx");
		String strQuery="Select * from testDataSheet1 where TestCaseId='"+argTestCaseId+"'";
		//String strQuery="Select * from orsheet";
		Recordset recordset=connection.executeQuery(strQuery);
		DataMap =new HashMap<String ,String>();
		while(recordset.next()==true){
			List<String> liColumnNames=recordset.getFieldNames();
			for(int i=3;i<liColumnNames.size()-1;i=i+2){
				String keycolumnName=liColumnNames.get(i);
				//System.out.println(keycolumnName);
				String keycolumnVAlue=liColumnNames.get(i+1);
				//System.out.println(keycolumnVAlue);
				String keyFieldName=recordset.getField(keycolumnName);
				//System.out.println(keyFieldName);
				String keyFieldValue=recordset.getField(keycolumnVAlue);
				//System.out.println(keyFieldValue);
				//instead of above 4 lines below 2 lines can also be used but in that case i needs to start from 4
				//String keyFieldName=recordset.getField(i).value();
				//String keyFieldName=recordset.getField(i+1).value()
				if(keyFieldName.trim().equals("")==false){
				DataMap.put(keyFieldName, keyFieldValue);
			}
			}
				
			//System.out.println(recordset.getField("LocatorValue"));	
			
		}
		
				
}
	
	public static int getColumnNumberByCoulmnName(Sheet sheetObj,String argColumnName){
		Row headerRow=sheetObj.getRow(0);
		int columnCount=headerRow.getLastCellNum();
		int desiredColumnNumber=0;
		for(int i=0;i<=columnCount-1;i++){
			Cell cellObj=headerRow.getCell(i);
			String colName=cellObj.getStringCellValue();
			if(colName.equalsIgnoreCase(argColumnName)==true){
				desiredColumnNumber=i;
				break;
			}
		}return desiredColumnNumber;
	}
	
	public static List<Integer> getAllMatchedRowNumberList(String argTestCaseId) throws IOException{
		List<Integer> liData=new ArrayList<>();
		try{
			
				String tdPath=PropertiesUtil.getConfiurationValue("TestDataPath");
				Workbook WbkObj=getWorkBook(tdPath);
				Sheet sheetObj= WbkObj.getSheet("testDataSheet1");
				//System.out.println("1");
				int tcIdColumnNumber=getColumnNumberByCoulmnName(sheetObj, "TestCaseId");
				//System.out.println("column no. is : "+tcIdColumnNumber);
				int rowCount=sheetObj.getLastRowNum();
				//System.out.println("row count is : "+rowCount);
				
				for(int i=1;i<=rowCount;i++){
					Row rowObj=sheetObj.getRow(i);
					Cell cellObj = rowObj.getCell(tcIdColumnNumber);
					String cellValue=cellObj.getStringCellValue();
					if(cellValue.equalsIgnoreCase(argTestCaseId)){
						liData.add(i);
					}
				}
				}
		catch(FileNotFoundException fe){
			fe.getMessage();
		}
		catch(Exception e){
			return null;
		}
		
		return liData;
	}
	
	
	public static void getTestCaseDatapoi(int desiredRowNumber) throws IOException{
		String tdPath=PropertiesUtil.getConfiurationValue("TestDataPath");
		Workbook WbkObj=getWorkBook(tdPath);
		Sheet sheetObj= WbkObj.getSheet("testDataSheet1");
		MissingCellPolicy mcp=Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;
		DataMap = new HashMap<String,String>();
		Row rowObj=sheetObj.getRow(desiredRowNumber);
		int colCount=rowObj.getLastCellNum();
		int startDataNumber=getColumnNumberByCoulmnName(sheetObj, "FeldName1");
		String dataKeyValue="";
		for(int i=startDataNumber;i<colCount-1;i=i+2){
			String dataKeyName=rowObj.getCell(i,mcp).getStringCellValue();
			
			if(dataKeyName.equals("")==false){
				Cell cellObj=rowObj.getCell(i+1,mcp);
				if(cellObj.getCellType()==Cell.CELL_TYPE_STRING){
					dataKeyValue=rowObj.getCell(i+1,mcp).getStringCellValue();
				}else{
					Double dblValue=rowObj.getCell(i+1,mcp).getNumericCellValue();
					Integer intValue=dblValue.intValue();
					dataKeyValue=intValue.toString();
					}
				DataMap.put(dataKeyName, dataKeyValue);
			}
					
		}
	
		
	}
	
	
	
	private static Workbook getWorkBook(String filePath) throws IOException{
		FileInputStream fis=new FileInputStream(filePath);
		String[] strArr=filePath.split("\\.");
		String strExt=strArr[1];
		//System.out.println(strExt);
		Workbook wkObj=null;
		if(strExt.equalsIgnoreCase("xlsx")){
			wkObj = new XSSFWorkbook(fis);
		}else{
			wkObj=new HSSFWorkbook(fis);
		}return wkObj;
	}

}
