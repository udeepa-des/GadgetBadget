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
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

}
