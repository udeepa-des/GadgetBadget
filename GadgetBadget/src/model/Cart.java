package model;

import java.sql.*;

public class Cart {
	//connecting to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	public String insert(String researchID, String name, String price, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// insert query
			String query = " insert into cart"
					+ "			(`ID`,`researchID`,`researchName`,`Amount`,`Description`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, researchID);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
			// executing the statement
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
	public String read()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Create the html table
			output = "<table border='1'><tr><th>research ID</th><th>research Name</th>" +
					"<th>Amount</th>" +
					"<th>Description</th></tr>";
			String query = "select * from cart";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterating through the rows
			while (rs.next())
			{
				String ID = Integer.toString(rs.getInt("ID"));
				String researchID = rs.getString("researchID");
				String researchName = rs.getString("researchName");
				String Amount = Double.toString(rs.getDouble("Amount"));
				String Description = rs.getString("Description");
				// Add values to the html table
				output += "<tr><td>" + researchID + "</td>";
				output += "<td>" + researchName + "</td>";
				output += "<td>" + Amount + "</td>";
				output += "<td>" + Description + "</td>";
				
			}
			con.close();
			// Complete the table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String update(String ID, String rId, String name, String price, String desc)
	
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// update query
			String query = "UPDATE cart SET researchID=?,researchName=?,Amount=?,Description=?"
					+ "WHERE ID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, rId);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String delete(String cartID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// delete query
			String query = "delete from cart where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cartID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}



