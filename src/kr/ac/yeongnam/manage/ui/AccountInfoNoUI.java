package kr.ac.yeongnam.manage.ui;

import java.util.List;

import kr.ac.yeongnam.manage.vo.AccountVO;

public class AccountInfoNoUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		System.out.println("계좌번호로 계좌를 조회하는 서비스입니다.");
		String accountNO = scanStr("계좌번호를 입력해주세요 : ");
		
		List<AccountVO> list = AccountService.accountInfoNoUI(accountNO);

		if(list.isEmpty()) {
			System.out.println("등록된 계좌가 없습니다.");
			// Thread.sleep(1000);
		} else {
			// String bank, String accountNo, String accountHolder, int balance, String alias
			System.out.println("은행명\t계좌번호\t계좌주명\t잔고\t별칭");
			for (AccountVO account : list) {
				System.out.println(account.getBank() + "\t" + account.getAccountNo() 
				+ "\t" + account.getAccountHolder() + "\t" + account.getBalance() 
				+  "\t" + account.getAlias());
			}
		}
	}
	
	

}
