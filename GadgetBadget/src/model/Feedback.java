package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Feedback {
	
	// set connection
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exfeedback", "root", "");

			// For testing
			
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
     }
	
	
	
	
	
}
