package kr.ac.yeongnam.manage.vo;
//VO (Value Object) 클래스.

public class AccountVO {
	private String id; //아이디
	private String pw; //비밀번호
	private String bank; //은행명
	private String accountNo; //계좌번호
	private String accountHolder; //계좌주명
	private int    balance; //잔액
	private String Alias; //별칭
	
	public AccountVO() {
		
	}
	
	public AccountVO(String id, String pw) {
		this.id = id;
		this.pw = pw;

	}
	
	public AccountVO(String bank, String accountNo, String accountHolder, int balance, String alias) {
		super();
		this.bank = bank;
		this.accountNo = accountNo;
		this.accountHolder = accountHolder;
		this.balance = balance;
		Alias = alias;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	
}
