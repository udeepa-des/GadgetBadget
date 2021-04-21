package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Fund {
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}