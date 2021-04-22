package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	public String readUser() {
		
		String output = "";
		try {
			Connection con = Connect();
			if(con == null) {
				return "Error while connecting to the database for reading";
			}
			output = "<table border='1'><tr><th>ID</th><th>Name</th><th>Address</th>"
					+ "<th>Telephone</th><th>Email</th>"
					+ "<th>UserName</th><th>Password</th>";
			
			String query= "select* from userservice";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
			
			// Iterate through the rows in the result set
			String ID = Integer.toString(rs.getInt("ID"));
			String Name = rs.getString("Name");
			String Address = rs.getString("Address");
			String Telephone = rs.getString("Telephone");
			String Email = rs.getString("Email");
			String UserName = rs.getString("UserName");
			String Password = rs.getString("Password");
			
			output += "<tr><td>"+ID+"</td>";
			output += "<td>"+Name+"</td>";
			output += "<td>"+Address+"</td>";
			output += "<td>"+Telephone+"</td>";
			output += "<td>"+Email+"</td>";
			output += "<td>"+UserName+"</td>";
			output += "<td>"+Password+"</td>";
			
			}
			con.close();
			
			// Complete the html table
						output += "</table>";
							
		}catch(Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	
	
	
	

}
