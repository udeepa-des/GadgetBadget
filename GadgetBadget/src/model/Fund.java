package model;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
}