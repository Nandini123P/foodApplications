package com.FoodApp.DBUtils;
import java.sql.Connection;
import java.sql.DriverManager;


public class MyConnect {

		private static Connection con;
		
		public static Connection connect() {
		try 
		{
	        String url = "jdbc:mysql://localhost:3306/oct_jdbc";
	        String dbun = "root";
	        String dbpwd = "root";

	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(url, dbun, dbpwd);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return con;
	}

	
}
