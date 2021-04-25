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
	
	public String readFunds() {
		String output = "";
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database for reading";
			}
			output = "<table border='1'><tr><th>ID</th><th>Cart ID</th><th>Research Name</th>"
					+ "<th>Invest Amount</th>"
					+ "<th>Description</th></tr>";
			String query= "select* from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String ID = Integer.toString(rs.getInt("id"));
				String researchid = Integer.toString(rs.getInt("cartid"));
				String researchName = rs.getString("researchName");
				String invetmentAmount = Double.toString(rs.getDouble("investAmount"));
				String description = rs.getString("description"); 
				
				output += "<tr><td>"+ID+"</td>";
				output += "<td>"+researchid+"</td>";
				output += "<td>"+researchName+"</td>";
				output += "<td>"+invetmentAmount+"</td>";
				output += "<td>"+description+"</td>";
				
			}
			con.close();
			
			output += "</table>";
		}catch(Exception e) {
			output = "Error while reading the details";
			System.err.println(e.getMessage());
			System.out.println(e);
		}
		return output;
	}
	
	public String insertFunds(String cartid,String researchName, String invetmentAmount, String description) {
		String output = "" ;
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database for inserting. "; 
			}
			String query = "insert into funds (cartid,researchName,investAmount,description) values(?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1,Integer.parseInt(cartid));
			pst.setString(2,researchName);
			pst.setDouble(3, Double.parseDouble(invetmentAmount));
			pst.setString(4, description);
			pst.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output = "Inserted Successfully.";
		}catch(Exception e) {
			output ="Error while inserting the fund.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateFunds(String ID, String cartid, String researchName, String invetmentAmount, String description) {
		String output = "";
		Connection con = connect();
		
		try {
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "update funds set cartid=?, researchName=?, investAmount=?, description=?"
					+ " where id=?";
			
			PreparedStatement pdstmt = con.prepareStatement(query);
			
			pdstmt.setInt(1,Integer.parseInt(cartid));
			pdstmt.setString(2,researchName);
			pdstmt.setDouble(3, Double.parseDouble(invetmentAmount));
			pdstmt.setString(4, description);
			pdstmt.setInt(5,Integer.parseInt(ID));
			pdstmt.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output = "Updated successfully.";
			
			
			
		}catch(Exception e) {
			output = "Error while updating the Funds.";
			System.err.print(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteFund(String id) {
		String output = "";
		try {
			Connection con = connect();
			if(con==null) {
				return "Error while connecting to database for deleting.";
			}
			
			String query = "delete from funds where id=?";
			
			PreparedStatement pdstmt = con.prepareStatement(query);
			
			pdstmt.setInt(1, Integer.parseInt(id));
			
			pdstmt.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output = "Deleted Successfully";
			
		}catch(Exception e) {
			output = "Error while deleting the Funds.";
			System.err.print(e.getMessage());
		}
		
		return output;
	}
	
}