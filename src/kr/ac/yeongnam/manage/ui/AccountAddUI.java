package kr.ac.yeongnam.manage.ui;

import kr.ac.yeongnam.manage.vo.AccountVO;

public class AccountAddUI extends BaseUI{
	
	
	
	@Override
	public void execute() throws Exception {
		
		System.out.println("계좌 등록 서비스입니다.");
		System.out.println("[하나], [신한], [우리], [국민] 은행만 등록 가능합니다.");
		String bank = scanStr("은행을 입력해주세요 : ");
		
		while(!bank.equals("하나") && !bank.equals("신한") && !bank.equals("우리") && !bank.equals("국민")) {
			System.out.println("[하나], [신한], [우리], [국민] 은행만 등록 가능합니다.");
			System.out.println("다시 입력해주세요.");
			bank = scanStr("은행을 입력해주세요 : ");
			
		}
		
		String accountNo = scanStr("계좌번호를 입력하세요(예: 110-356) : ");
		String accountHolder = scanStr("계좌주명을 입력하세요 : ");
		int balance = scanInt("잔액을 입력해주세요 : ");
		String alias = scanStr("계좌의 별칭을 입력하세요 : ");
		
		AccountVO account = new AccountVO();
		account.setBank(bank);
		account.setAccountNo(accountNo);
		account.setAccountHolder(accountHolder);
		account.setBalance(balance);
		account.setAlias(alias);
		
		
		AccountService.accountAddUI(account);

	}

}
