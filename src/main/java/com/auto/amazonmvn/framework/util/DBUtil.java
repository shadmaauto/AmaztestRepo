package com.auto.amazonmvn.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		dbConnect();
		
		
//		while(rsObj.next()==true){
//			int columnCount=rsObj.getMetaData().getColumnCount();
//			for(int i=1;i<=columnCount;i++){
//				String strVal=rsObj.getString(i);
//				System.out.println(strVal);	
//				System.out.println("\n");
//			}
//			
//		}
	}

	private static Connection dbConnect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connObj=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root"); 
		Boolean status=connObj.isClosed();
		System.out.println(status);
		if(status==false){
			return connObj;
		}else return null;
		
	}
	
	public ResultSet  dbGetData(String query) throws SQLException, ClassNotFoundException {
		Connection connObj=dbConnect();
		Statement st=connObj.createStatement();
		ResultSet rsObj=st.executeQuery(query);
		return rsObj;
	}
	
	
}
