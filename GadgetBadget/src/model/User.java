package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class User {
	//A common method to connect to the Databse
	private Connection Connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//provide the correct details: DBServer/DBName, UserName, Password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	
	
	

}
