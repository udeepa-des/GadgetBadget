package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
    public String insertUser(String Name, String Address, String Telephone, String Email, String UserName,String Password) {
		
		String output = "" ;
		
		try {
			Connection con = Connect();
			if(con == null) {
				return "Error while connecting to the database for inserting. "; 
			}
			
			//create a prepard statement 
			String query = "insert into userservice (Name,Address,Telephone,Email,UserName,Password) values(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			// binding values
			pst.setString(1,Name);
			pst.setString(2,Address);
			pst.setString(3,Telephone);
			pst.setString(4,Email);
			pst.setString(5,UserName);
			pst.setString(6,Password);
			
			// execute the statement
			pst.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			output = "Inserted successfully";
		}catch(Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
    
    public String UpdateUser(String ID,String Name, String Address, String Telephone, String Email, String UserName,String Password) {
    	String output = "";
		Connection con = Connect();
		try{
		
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "update userservice set Name=?, Address=?, Telephone=?, Email=?, UserName=?, Password=?"
					+ " where id=?";
			
			PreparedStatement pddstmt = con.prepareStatement(query);
			pddstmt.setString(1,Name);
			pddstmt.setString(2,Address);
			pddstmt.setString(3,Telephone);
			pddstmt.setString(4,Email);
			pddstmt.setString(5,UserName);
			pddstmt.setString(6,Password);
			
			// execute the statement
			pddstmt.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output = "Updated successfully.";
    	
    }catch (Exception e) {
    	
    	output = "Error while inserting the item.";
		System.err.print(e.getMessage());
    }
    	    
	return output;
	
 }
    
	

}
