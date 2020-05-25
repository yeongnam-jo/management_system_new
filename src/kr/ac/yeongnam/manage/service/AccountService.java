package kr.ac.yeongnam.manage.service;

import java.util.List;

import kr.ac.yeongnam.manage.dao.AccountDAO;
import kr.ac.yeongnam.manage.vo.AccountVO;



public class AccountService {
	
	private AccountDAO accountDAO;
	// private UserDAO userDAO;
	
	public AccountService() {
		accountDAO = new AccountDAO();
	}
	
	public void login(String id, String pw) {
		accountDAO.login(id,pw);
	}
	
	
//	public void login(String id, String pw, String msg) {
//		userDAO.login(id, pw);
//	}
	
	public void join(String id, String pw) {
		accountDAO.join(id, pw);
		//userDAO.login(id, pw);
	}
	
	public List<AccountVO> accountInfoAllUI() {
		
		return accountDAO.accountInfoAll();
	}
	
	public List<AccountVO> accountInfoNoUI(String accountNO){
		return accountDAO.accountInfoNoUI(accountNO);
	}
	
	public List<AccountVO> accountInfoBankUI(String bank){
		return accountDAO.accountInfoBankUI(bank);
	}
	
	public void accountAddUI(AccountVO account) {
		accountDAO.accountAddUI(account);
	}
	
	
	
	
	/*
	public void registerAccount(AccountVO account) {
	
		accountDAO.RegisterAccount(account);
	}
	
	public boolean deleteAccount(String accountNo) {
		
		return accountDAO.DeleteAccount(accountNo);
	}
	
	public boolean changeAccountAlias(String alias, String accountNo) {
		
		return accountDAO.ChangeAccountAlias(alias, accountNo);
	}

	
	public List<AccountVO> selectAllAccount() {
		
		return accountDAO.SelectAllAccount();
	}
	
	public List<AccountVO> selectByBankName(String bankName) {
		
		return accountDAO.SelectByBankName(bankName);
	}


	public boolean withDraw(String accountNo, int money) {
		return accountDAO.WithDraw(accountNo, money);
	}
	
	public boolean transfer(String accountNo, String destAccount, int Amount) {
		
		return accountDAO.Transfer(accountNo, destAccount, Amount);
	}
	
	public boolean deposit(String accountNo, int money) {
		
		return accountDAO.Deposit(accountNo, money);
	}
	
	public int isExist(String accountNo) {
	
		return accountDAO.isExist(accountNo);
	}
	
	public boolean isCorrect(String accountNo, int withDrawMoney) {
		
		return accountDAO.isCorrect(accountNo, withDrawMoney);
	}

	public boolean join(String id, String pw) {
		return accountDAO.Join(id, pw);
	}
	*/

}
