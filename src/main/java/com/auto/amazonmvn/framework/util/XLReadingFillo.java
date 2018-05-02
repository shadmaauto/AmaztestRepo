package com.auto.amazonmvn.framework.util;


import Exception.FilloException;
import Fillo.Connection;
import Fillo.Fillo;
import Fillo.Recordset;


//class to read locator values from xls using fillo
public class XLReadingFillo  {

	
	public static void main(String args[]) throws FilloException{
		getLocatorValue("SIgnInUserNameED");
	}
	public static void getLocatorValue(String locatorName) throws FilloException{
		
	
	Fillo fillo=new Fillo();
	Connection connection=fillo.getConnection("or\\or.xlsx");
	String strQuery="Select * from orsheet where LocatorName='"+locatorName+"'";
	//String strQuery="Select * from orsheet";
	Recordset recordset=connection.executeQuery(strQuery);
	while(recordset.next()==true){
		
		System.out.println(recordset.getField("LocatorValue"));	
		
	}

}
}
