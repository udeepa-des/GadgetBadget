package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
	
	      // create method to Insert Feedback Details
	
	public String insertFb(String name, String contactnum, String email, String comment, String OvrlExp) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			
			String query = " insert into feedb  (ID,Name,ContactNumber,Email,Comment,overallExperience)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, contactnum);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, comment);
			preparedStmt.setString(6, OvrlExp);
			
			// execute the statement
			
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
}
