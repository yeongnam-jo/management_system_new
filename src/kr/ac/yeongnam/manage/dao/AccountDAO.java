
package kr.ac.yeongnam.manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			String password = pw;
			conn = new ConnectionFactory().getConnection();
			sql = new StringBuilder();
			
			sql.append("SELECT ID, PW FROM USER_TABLE WHERE ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				System.out.println(">>>해당 ID 정보가 없습니다.");
				System.exit(0);
			} else {
				password = rs.getString(2);
	
				if (!(password.equals(pw))) {
					System.out.println(">>>비밀번호가 틀렸습니다.");
					System.exit(0);
				} else {
					System.out.println("==============================");
					System.out.println("[" + id + "]님! 반갑습니다.");
					System.out.println("==============================");
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
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			
			sql.append("SELECT ID FROM USER_TABLE WHERE ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(">>>이미 존재하는 ID입니다.");
				System.exit(0);
			} else {
				sql = new StringBuilder();
				sql.append("INSERT INTO USER_TABLE(ID, PW) VALUES(?,?)");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.executeUpdate();
				conn.commit();

				System.out.println(id + "님, 회원가입이 성공적으로 완료됐습니다.");
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
			sql.append("SELECT * FROM ACCOUNT WHERE ID = ? ORDER BY BANK");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, myVo.getId());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bank = rs.getString("BANK");
				String accountNo = rs.getString("ACCOUNT_NO");
				String accountHolder = rs.getString("ACCOUNT_HOLDER");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
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
			sql.append("SELECT * FROM ACCOUNT WHERE ACCOUNT_NO = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNO);
			pstmt.setString(2, myVo.getId());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bank = rs.getString("BANK");
				String accountNo = rs.getString("ACCOUNT_NO");
				String accountHolder = rs.getString("ACCOUNT_HOLDER");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
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
			sql.append("SELECT * FROM ACCOUNT WHERE BANK = ? AND ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bankName);
			pstmt.setString(2, myVo.getId());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bank = rs.getString("BANK");
				String accountNo = rs.getString("ACCOUNT_NO");
				String accountHolder = rs.getString("ACCOUNT_HOLDER");
				int balance = rs.getInt("BALANCE");
				String alias = rs.getString("ALIAS");
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
	
	
	public void accountAddUI(AccountVO account) {
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("INSERT INTO ACCOUNT(ACCOUNT_NO, BANK, ACCOUNT_HOLDER, BALANCE, ALIAS, ID) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, account.getAccountNo());
			pstmt.setString(2, account.getBank());
			pstmt.setString(3, account.getAccountHolder());
			pstmt.setInt(4, account.getBalance());
			pstmt.setString(5, account.getAlias());
			pstmt.setString(6, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("계좌등록이 완료됐습니다.");

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
	
	public void accountUpdateUI(String accountNo, String alias) {
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT SET ALIAS = ? WHERE ACCOUNT_NO = ? AND ID = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, alias);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("해당 계좌의 별칭을 수정했습니다.");

			
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
	
	public void accountDeleteUI(String accountNO) {
		conn = new ConnectionFactory().getConnection();

		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("DELETE FROM ACCOUNT WHERE ACCOUNT_NO = ? AND ID = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNO);
			pstmt.setString(2, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("해당 계좌를 삭제했습니다.");

			
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
	

	public void depositUI(String accountNo, long money) {
		conn = new ConnectionFactory().getConnection();
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("UPDATE ACCOUNT SET BALANCE = BALANCE + ? WHERE ACCOUNT_NO = ? AND ID = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, money);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println(money + "원을 입금했습니다.");

			
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
	
	
	public void transferUI(String accountNoFrom, long money, String accountNoTo) {
		conn = new ConnectionFactory().getConnection();
		try {
			sql = new StringBuilder();
			sql.append("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNoFrom);
			rs = pstmt.executeQuery();
			
			long balance = 0;
			while (rs.next()) {
				balance = rs.getLong("BALANCE");
			}
			
			if(money > balance) {
				System.out.println(">>>잔액이 부족합니다. ");
			} else {
				sql = new StringBuilder();
				sql.append("SELECT * FROM ACCOUNT WHERE ACCOUNT_NO = ? AND ID = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, accountNoTo);
				pstmt.setString(2, myVo.getId());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					conn.setAutoCommit(false);
					sql = new StringBuilder();
					sql.append("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE ACCOUNT_NO = ? AND ID = ?");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setLong(1, money);
					pstmt.setString(2, accountNoFrom);
					pstmt.setString(3, myVo.getId());
					pstmt.executeUpdate();
					
					sql = new StringBuilder();
					sql.append("UPDATE ACCOUNT SET BALANCE = BALANCE + ? WHERE ACCOUNT_NO = ? AND ID = ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setLong(1, money);
					pstmt.setString(2, accountNoTo);
					pstmt.setString(3, myVo.getId());
					pstmt.executeUpdate();
					conn.commit();
					System.out.println("\'"+ accountNoTo +"\' 계좌로 " + money + "원 이체 완료했습니다.");

				} else {
					System.out.println(">>>이체받을 계좌번호가 정확하지 않습니다.");
				}
				
			}
			
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
	
	public void withrawUI(String accountNo, long money) {
		conn = new ConnectionFactory().getConnection();
		try {
			sql = new StringBuilder();
			sql.append("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ? AND ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNo);
			pstmt.setString(2, myVo.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 해당 계좌가 있으면
				long balance = rs.getLong("BALANCE");
				
				if(money > balance) {
					System.out.println(">>>잔액이 부족합니다. ");
				} else {
					conn.setAutoCommit(false);
					sql = new StringBuilder();
					sql.append("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE ACCOUNT_NO = ? AND ID = ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setLong(1, money);
					pstmt.setString(2, accountNo);
					pstmt.setString(3, myVo.getId());
					pstmt.executeUpdate();
					conn.commit();
					System.out.println(money + "원을 인출했습니다.");

				}
			} else { // 해당 계좌가 없으면
				System.out.println(">>>해당 계좌가 없습니다.");
			}
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
			
				
	public void createAccountUI(String accountNo, String bank, String accountHolder, long firstMoney, String alias) {
		
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("INSERT INTO ACCOUNT(ACCOUNT_NO, BANK, ACCOUNT_HOLDER, BALANCE, ALIAS, ID) VALUES(?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accountNo);
			pstmt.setString(2, bank);
			pstmt.setString(3, accountHolder);
			pstmt.setLong(4, firstMoney);
			pstmt.setString(5, alias);
			pstmt.setString(6, myVo.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("새로운 계좌를 생성했습니다.");

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
		
}
	
	
	
	
