package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	   //create method to read feedback Details
	
	public String readIFb() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			

			output = "<table border=‘1’ class=\"table table-dark\" ><tr><th>Customer Name</th>"
					+ "<th>Contact Number</th><th>Email</th>" + "<th>Comments</th><th>Overall Experience</th>"    
					+ "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from feedb";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt("ID"));
				String name = rs.getString("Name");
				String contactnum = rs.getString("ContactNumber");
				String email = rs.getString("Email");
				String Comment = rs.getString("Comment");
				String OvrlExp = rs.getNString("overallExperience");
				
				// Add into the HTML table
				
				output += "<tr><td>" + name + "</td>";
				output += "<td>" + contactnum + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + Comment + "</td>";
				output += "<td>" + OvrlExp + "</td>";
				
				// buttons
				
				output += "<td><input name='btnUpdate' "
						+ " type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='itemps.jsp'>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove'class='btn btn-danger'>" + "<input name='itemID' type='hidden' "
						+ " value='" + ID + "'>" + "</form></td></tr>";
			}
			con.close();
			con.close();
			
			// Complete the HTML table
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Feedback Details.";
			System.err.println(e.getMessage());
		}
		return output;

	}
	
	      //create method to update feedback details
	
		public String updateFb(String ID ,String name, String contactnum, String email, String comment, String OvrlExp)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 
			 String query = "UPDATE feedb SET Name=?,ContactNumber=?,Email=?,Comment=?,overallExperience=? WHERE ID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, contactnum);
			 preparedStmt.setString(3, email);
			 preparedStmt.setString(4, comment);
			 preparedStmt.setString(5, OvrlExp);
			 preparedStmt.setInt(6, Integer.parseInt(ID));
			 
			 // execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the Feedback Details.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 
		
	
	
	
	
}
