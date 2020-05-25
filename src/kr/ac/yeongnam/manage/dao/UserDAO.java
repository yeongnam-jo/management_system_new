package kr.ac.yeongnam.manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.yeongnam.manage.util.ConnectionFactory;
import kr.ac.yeongnam.manage.util.JDBCClose;
import kr.ac.yeongnam.manage.vo.AccountVO;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sql;
	
public boolean login(String id, String pw) {
	
	boolean result = true;
	try {
		// String Password = null;
		String password = pw;
		conn = new ConnectionFactory().getConnection();
		sql = new StringBuilder();

		sql.append("SELECT ID, PW FROM USER_TABLE WHERE ID = ?");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();

		if (!rs.next()) {
			result = true;
			// 해당 ID가 없는 경우. 즉, while 반복 돌아야 하는 경우
		} else {
			result = false;
			// 해당 ID가 있는 경우. 즉, while 반복 빠져나가야 하는 경우
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCClose.close(conn, pstmt);
		JDBCClose.close(rs);
	}
	return result;
}

	public boolean join(String id, String pw) {
		boolean result = true;
		
		try {
			// String Password = null;
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			
			sql.append("SELECT ID FROM USER_TABLE WHERE ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
				// System.out.println("이미 존재하는 ID입니다.");
				// while 돌아야 하는 경우, result = true
				
			} else {
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}
		return result;
	}
	

}
