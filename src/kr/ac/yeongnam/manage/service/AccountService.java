package kr.ac.yeongnam.manage.service;

import java.util.List;

import kr.ac.yeongnam.manage.dao.AccountDAO;
import kr.ac.yeongnam.manage.vo.AccountVO;



public class AccountService {
	
	private AccountDAO accountDAO;
	
	public AccountService() {
		accountDAO = new AccountDAO();
	}
	
	public void login(String id, String pw) {
		accountDAO.login(id,pw);
	}
	
	public void join(String id, String pw) {
		accountDAO.join(id, pw);
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
	
	public void accountUpdateUI(String accountNo, String alias) {
		accountDAO.accountUpdateUI(accountNo, alias);
	}

	public void accountDeleteUI(String accountNo) {
		accountDAO.accountDeleteUI(accountNo);
	}

	public void depositUI(String accountNo, long money) {
		accountDAO.depositUI(accountNo, money);
	}
	
	public void transferUI(String accountNoFrom, long money, String accountNoTo) {
		accountDAO.transferUI(accountNoFrom, money, accountNoTo);
	}
	
	public void withrawUI(String accountNo, long money) {
		accountDAO.withrawUI(accountNo, money);
	}
	
	public void createAccountUI(String accountNo, String bank, String accountHolder, long firstMoney, String alias) {
		accountDAO.createAccountUI(accountNo, bank, accountHolder, firstMoney, alias);
	}
	
}
