package model;

import java.sql.*;

public class Research {
	
	//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
		
		//-------insert------
		public String insertResearch(String rName, String rPhone, String rEmail, String rAddress, String projectName, String rCost)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into research"
						+ "			(`RID`,`rName`,`rPhone`,`rEmail`,`rAddress`,`projectName`,`rCost`)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, rName);
				preparedStmt.setString(3, rPhone);
				preparedStmt.setString(4, rEmail);
				preparedStmt.setString(5, rAddress);
				preparedStmt.setString(6, projectName);
				preparedStmt.setString(7, rCost);

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the research.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//---------read-------
		public String readResearch()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Research Name</th><th>Phone</th>" +
		 "<th>Email</th>" +
		 "<th>Address</th>" +
		 "<th>Project Name</th>" +
		 "<th>Cost</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from research";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String RID = Integer.toString(rs.getInt("RID"));
		 String rName = rs.getString("rName");
		 String rPhone = rs.getString("rPhone");
		 String rEmail = rs.getString("rEmail");
		 String rAddress = rs.getString("rAddress");
		 String projectName = rs.getString("projectName");
		 String rCost = rs.getString("rCost");

		 // Add into the html table
		 output += "<tr><td>" + rName + "</td>";
		 output += "<td>" + rPhone + "</td>";
		 output += "<td>" + rEmail + "</td>";
		 output += "<td>" + rAddress + "</td>";
		 output += "<td>" + projectName + "</td>";
		 output += "<td>" + rCost + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='research.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='RID' type='hidden' value='" + RID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the research.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		//------update-----
		public String updateResearch(String RID, String rName, String rPhone, String rEmail, String rAddress,String projectName,String rCost)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE research SET rName=?,rPhone=?,rEmail=?,rAddress=?,projectName=?,rCost=? WHERE RID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, rName);
			 preparedStmt.setString(2, rPhone);
			 preparedStmt.setString(3, rEmail);
			 preparedStmt.setString(4, rAddress);
			 preparedStmt.setString(5, projectName);
			 preparedStmt.setString(6, rCost);
			 preparedStmt.setInt(7, Integer.parseInt(RID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the research.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
		
		
		//------delete------
			public String deleteResearch(String RID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from items where RID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(RID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the research.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 
}
