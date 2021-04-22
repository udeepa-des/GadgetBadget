package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database for reading";
			}
			output = "<table border='1'><tr><th>ID</th><th>Company Name</th><th>Research ID</th>"
					+ "<th>Research Name</th><th>Invest Amount</th>"
					+ "<th>Description</th></tr>";
			String query= "select* from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String ID = Integer.toString(rs.getInt("id"));
				String companyName = rs.getString("companyName");
				String researchID = Integer.toString(rs.getInt("researchid"));
				String researchName = rs.getString("researchName");
				String invetmentAmount = Float.toString(rs.getFloat("investAmount"));
				String description = rs.getString("description"); 
				
				output += "<tr><td>"+ID+"</td>";
				output += "<td>"+companyName+"</td>";
				output += "<td>"+researchID+"</td>";
				output += "<td>"+researchName+"</td>";
				output += "<td>"+invetmentAmount+"</td>";
				output += "<td>"+description+"</td>";
				
			}
			con.close();
			
			output += "</table>";
		}catch(Exception e) {
			output = "Error while reading the info";
			System.err.println(e.getMessage());
			System.out.println(e);
		}
		return output;
	}
	
	public String insertFunds(String companyName,int researchid, String researchName, float investAmount, String description) {
		String output = "" ;
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database for inserting. "; 
			}
			String query = "insert into funds (companyName,researchid,researchName,investAmount,description) values(?,?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,companyName);
			pst.setInt(2,researchid);
			pst.setString(3, researchName);
			pst.setFloat(4, investAmount);
			pst.setString(5, description);
			pst.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output = "Inserted Successfully.";
		}catch(Exception e) {
			output ="Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}