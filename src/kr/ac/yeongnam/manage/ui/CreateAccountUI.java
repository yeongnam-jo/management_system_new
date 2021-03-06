package kr.ac.yeongnam.manage.ui;

import java.util.Random;

public class CreateAccountUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		Random r = new Random();
		
		System.out.println("계좌 생성 서비스입니다.");
		System.out.println("[하나], [신한], [우리], [국민] 은행만 생성 가능합니다.");
		String bank = scanStr("은행을 입력해주세요 : ");
		String accountHolder = scanStr("계좌주명을 입력하세요 : ");
		String alias = scanStr("계좌의 별칭을 입력하세요 : ");
		long firstMoney = scanLong("처음 입금하실 금액을 입력해주세요(1000원 이상) : ");
		
		while(firstMoney < 1000) {			
			firstMoney = scanLong("처음 입금하실 금액을 입력해주세요(1000원 이상) : ");
		}

		StringBuilder sql = new StringBuilder();
		for(int i = 1; i <= 7; i++) {
			if(i % 4 == 0) {
				sql.append("-");
			} else {
				int num = r.nextInt(8) + 1;
				sql.append(num);
			}
		}
		String accountNo = sql.toString();
		AccountService.createAccountUI(accountNo, bank, accountHolder, firstMoney, alias);
		
	}

}
