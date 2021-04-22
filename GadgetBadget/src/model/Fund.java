package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	private Connection connect() {
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
	
	public String insertFunds(String companyName,String researchid, String researchName, String investAmount, String description) {
		String output = "" ;
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database for inserting. "; 
			}
			String query = "insert into funds (companyName,researchid,researchName,investAmount,description) values(?,?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,companyName);
			pst.setInt(2,Integer.parseInt(researchid));
			pst.setString(3, researchName);
			pst.setDouble(4, Double.parseDouble(investAmount));
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
	
	public String updateFunds(String ID,String companyName,String researchid, String researchName, String investAmount, String description) {
		String output = "";
		Connection con = connect();
		
		try {
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "update funds set companyName=?, researchid=?, researchName=?, investAmount=?, description=?"
					+ " where id=?";
			
			PreparedStatement pdstmt = con.prepareStatement(query);
			
			pdstmt.setString(1,companyName);
			pdstmt.setInt(2,Integer.parseInt(researchid));
			pdstmt.setString(3, researchName);
			pdstmt.setDouble(4, Double.parseDouble(investAmount));
			
			
		}catch(Exception e) {
			output = "Error while inserting the item.";
			System.err.print(e.getMessage());
		}
		
		return output;
	}
	
}