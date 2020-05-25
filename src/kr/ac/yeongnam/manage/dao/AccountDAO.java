package kr.ac.yeongnam.manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.yeongnam.manage.service.AccountSequence;
import kr.ac.yeongnam.manage.util.ConnectionFactory;
import kr.ac.yeongnam.manage.util.JDBCClose;
import kr.ac.yeongnam.manage.vo.AccountVO;



public class AccountDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AccountVO myVo;
	private StringBuilder sql;
	private List<AccountVO> list;

	public AccountVO login(String id, String pw) {
		
		try {
			//String Password = null;
			String password = pw;
			conn = new ConnectionFactory().getConnection();
			sql = new StringBuilder();
			
			sql.append("SELECT ID, PW FROM USER_TABLE WHERE ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				System.out.println("해당 ID 정보가 없습니다.");
			} else {
				while(rs.next()) {
					password = rs.getString(2);
				}
				
				if (!(password.equals(pw))) {
					System.out.println("비밀번호가 틀렸습니다.");
				} else {
					System.out.println("============================================================================");
					System.out.println("[" + id + "]님, 로그인 하셨습니다.");
					System.out.println("============================================================================");
					Thread.sleep(1000);
					myVo = new AccountVO(id, pw);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}
		return myVo;
	}

	public AccountVO join(String id, String pw) {
		
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
				System.out.println("이미 존재하는 ID입니다.");
				
			} else {
				sql = new StringBuilder();
				sql.append("INSERT INTO USER_TABLE(ID, PW) VALUES(?,?)");
				
				System.out.println(sql.toString());
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.executeUpdate();
				conn.commit();

				System.out.println(id + "님, 회원가입이 성공적으로 완료됐습니다.");
				// Thread.sleep(10);
				myVo = new AccountVO(id, pw);
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
		return myVo;
	}
	
	public List<AccountVO> accountInfoAll() {
		conn = new ConnectionFactory().getConnection();
		list = new ArrayList<>();
		try {
			sql = new StringBuilder();
			sql.append("SELECT * FROM ACCOUNT ORDER BY BANK");
			pstmt = conn.prepareStatement(sql.toString());
			// pstmt.setString(1, myVo.getId());
			rs = pstmt.executeQuery();
			// AccountSequence aseq = new AccountSequence();
			
			while (rs.next()) {
				String bank = rs.getString("BANK");
				String accountNo = rs.getString("ACCOUNT_NO");
				String accountHolder = rs.getString("ACCOUNT_HOLDER");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
				// String enrollDate = rs.getString("ENROLLDATE");
				AccountVO accountVO = new AccountVO(bank, accountNo, accountHolder, balance, alias);
				list.add(accountVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}	
		return list;
	}
	
	public List<AccountVO> accountInfoNoUI(String accountNO) {
		conn = new ConnectionFactory().getConnection();
		list = new ArrayList<>();
		
		try {
			sql = new StringBuilder();
			sql.append("SELECT * FROM ACCOUNT WHERE ACCOUNT_NO = ?");
			pstmt = conn.prepareStatement(sql.toString());
			// pstmt.setString(1, myVo.getId());
			pstmt.setString(1, accountNO);
			rs = pstmt.executeQuery();
			// AccountSequence aseq = new AccountSequence();
			
			while (rs.next()) {
				String bank = rs.getString("BANK");
				String accountNo = rs.getString("ACCOUNT_NO");
				String accountHolder = rs.getString("ACCOUNT_HOLDER");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
				// String enrollDate = rs.getString("ENROLLDATE");
				AccountVO accountVO = new AccountVO(bank, accountNo, accountHolder, balance, alias);
				list.add(accountVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}	
		return list;
	}
	
	public List<AccountVO> accountInfoBankUI(String bankName) {
		conn = new ConnectionFactory().getConnection();
		list = new ArrayList<>();
		
		try {
			sql = new StringBuilder();
			sql.append("SELECT * FROM ACCOUNT WHERE BANK = ?");
			pstmt = conn.prepareStatement(sql.toString());
			// pstmt.setString(1, myVo.getId());
			pstmt.setString(1, bankName);
			rs = pstmt.executeQuery();
			// AccountSequence aseq = new AccountSequence();
			
			while (rs.next()) {
				String bank = rs.getString("BANK");
				String accountNo = rs.getString("ACCOUNT_NO");
				String accountHolder = rs.getString("ACCOUNT_HOLDER");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
				// String enrollDate = rs.getString("ENROLLDATE");
				AccountVO accountVO = new AccountVO(bank, accountNo, accountHolder, balance, alias);
				list.add(accountVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}	
		return list;
	}
	
	
	
	/*
	public void RegisterAccount(AccountVO account) {

		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("INSERT INTO ACCOUNT(no, id, bankName, accountNo, identity, balance, alias) "
					+ " VALUES(SEQ_ACCOUNT_NO.NEXTVAL, ?, ?, ?, ?, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, myVo.getId());
			pstmt.setString(2, account.getBankName());
			pstmt.setString(3, account.getAccountNo());
			pstmt.setString(4, account.getIdentity());
			pstmt.setInt(5, account.getBalance());
			pstmt.setString(6, account.getAlias());
			
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}

	public boolean Transfer(String accountNo, String destAccount, int amount) {
		
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT"
					+ " SET BALANCE = BALANCE - ?"
					+ " WHERE ACCOUNTNO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, amount);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, myVo.getId());
			pstmt.executeUpdate();
			
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT"
					+ " SET BALANCE = BALANCE + ?"
					+ " WHERE ACCOUNTNO = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, amount);
			pstmt.setString(2, destAccount);
			pstmt.executeUpdate();
			conn.commit();

		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return true;
	}

	public List<AccountVO> SelectAllAccount() {
		
		conn = new ConnectionFactory().getConnection();
		list = new ArrayList<>();
		try {
			sql = new StringBuilder();
			sql.append("SELECT * FROM ACCOUNT WHERE ID = ? ORDER BY ENROLLDATE");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, myVo.getId());
			rs = pstmt.executeQuery();
			AccountSequence aseq = new AccountSequence();
			
			while (rs.next()) {
				String bankName = rs.getString("BANKNAME");
				String accountNo = rs.getString("ACCOUNTNO");
				String identity = rs.getString("IDENTITY");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
				String enrollDate = rs.getString("ENROLLDATE");
				
				AccountVO accountVO = new AccountVO(aseq.getBoardSequence(), myVo.getId(), bankName, accountNo, identity, balance, alias, enrollDate);
				list.add(accountVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}	
		return list;
	}

	public List<AccountVO> SelectByBankName(String bankName) {
		
		conn = new ConnectionFactory().getConnection();
		list = new ArrayList<>();
		try {
			sql = new StringBuilder();
			sql.append("SELECT * FROM ACCOUNT WHERE ID = ? AND BANKNAME = ? ORDER BY ENROLLDATE");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, myVo.getId());
			pstmt.setString(2, bankName);
			rs = pstmt.executeQuery();
			AccountSequence aseq = new AccountSequence();
			
			while (rs.next()) {
				String accountNo = rs.getString("ACCOUNTNO");
				String identity = rs.getString("IDENTITY");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
				String enrollDate = rs.getString("ENROLLDATE");
			
				AccountVO accountVO = new AccountVO(aseq.getBoardSequence(), myVo.getId(), bankName, accountNo, identity, balance, alias, enrollDate);
				list.add(accountVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}
		return list;
	}

	public boolean WithDraw(String accountNo, int money) {
		
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT"
					+ " SET BALANCE = BALANCE - ?"
					+ " WHERE ACCOUNTNO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, myVo.getId());
			pstmt.executeUpdate();
	
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return true;
	}

	public boolean Deposit(String accountNo, int money) {
		
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT"
					+ " SET BALANCE = BALANCE + ?"
					+ " WHERE ACCOUNTNO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return true;
	}

	public boolean DeleteAccount(String accountNo) {
		
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("DELETE ACCOUNT"
					+ " WHERE ACCOUNTNO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNo);
			pstmt.setString(2, myVo.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return true;
	}

	public boolean ChangeAccountAlias(String alias, String accountNo) {
		
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT"
					+ " SET ALIAS = ?"
					+ " WHERE ACCOUNTNO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, alias);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return true;
	}

	public int isExist(String accountNo) {
		
		int count = 0;
		conn = new ConnectionFactory().getConnection();
		
		try {
			sql = new StringBuilder();
			sql.append("SELECT COUNT(ACCOUNTNO)"
				    + " FROM ACCOUNT"
					+ " WHERE ACCOUNTNO = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}
		return count;
	}
	
	public boolean isCorrect(String accountNo, int withDrawMoney) {
		
		conn = new ConnectionFactory().getConnection();
		int myBalance = 0;
		try {
			conn.setAutoCommit(false);
			
			sql = new StringBuilder();
			sql.append("SELECT BALANCE"
					+ " FROM ACCOUNT"
					+ " WHERE ACCOUNTNO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNo);
			pstmt.setString(2, myVo.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				myBalance = rs.getInt("BALANCE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}
		return myBalance > withDrawMoney;
	}

	public boolean Join(String id, String pw) {
		
		conn = new ConnectionFactory().getConnection();
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("INSERT INTO CLIENT"
					+ " VALUES(SEQ_CLIENT_NO.NEXTVAL, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			int cnt = pstmt.executeUpdate();
			if (cnt == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
			JDBCClose.close(rs);
		}
		return true;
	}
	*/
}
	
	
	
	
