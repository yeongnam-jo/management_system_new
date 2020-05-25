package kr.ac.yeongnam.manage.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			/*
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521:dink";
			String id = "scott";
			String pwd = "tiger";
			*/
			
			
			String url = "jdbc:oracle:thin:@172.16.88.120:1521:dink";
			String id = "DA09";
			String pwd = "DA09";
			
			conn = DriverManager.getConnection(url, id, pwd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

