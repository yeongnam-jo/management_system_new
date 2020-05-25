package kr.ac.yeongnam.manage.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClose {

	public static void close(Statement stmt) {
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void close(Connection conn) {
		
		if(conn != null) {
			try { 
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		close(conn);
		close(stmt);
	}

	public static void close(ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}
}
