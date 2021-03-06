package kr.ac.yeongnam.manage.ui;


public class TotalManagementUI extends BaseUI {

	public void showFirstMenu() {
		System.out.println("==============================");
		System.out.println("\t1. 로그인");
		System.out.println("\t2. 회원가입");
		System.out.println("==============================");
	}
	
	public void showSecondMenu() {
		System.out.println("==============================");
		System.out.println("1. 계좌 전체 조회"); // AccountInfoAllUI
		System.out.println("2. 계좌번호로 조회"); // AccountInfoNoUI
		System.out.println("3. 은행별 계좌 조회"); // AccountInfoBankUI
		System.out.println("4. 계좌 등록"); // AccountAddUI
		System.out.println("5. 계좌 수정"); // AccountUpdateUI
		System.out.println("6. 계좌 삭제"); // AccountDeleteUI
		System.out.println("7. 입금"); // DepositUI
		System.out.println("8. 출금"); // WithrawUI
		System.out.println("9. 이체"); // TransferUI
		System.out.println("10. 계좌 신규 생성"); // CreateAccountUI
		System.out.println("0. 종료"); // ExitUI
		System.out.println("==============================");
		
		System.out.print("원하시는 서비스 번호를 입력하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		IAccount ia = null;
		
		showFirstMenu();		
		int logJoin = scanInt("원하시는 서비스 번호를 입력하세요 : ");
		
		if(logJoin == 1) {
			ia = new LoginUI();
			ia.execute();
		} else if(logJoin == 2) {
			ia = new JoinUI();
			ia.execute();
		}
		
		int serviceNum = -1;
		while (serviceNum != 0) {

			showSecondMenu();
			serviceNum = Integer.parseInt(sc.nextLine());

			switch (serviceNum) {
			case 1:
				ia = new AccountInfoAllUI();
				ia.execute();
				break;
			
			case 2:
				ia = new AccountInfoNoUI();
				ia.execute();
				break;
			case 3:
				ia = new AccountInfoBankUI();
				ia.execute();
				break;
			case 4:
				ia = new AccountAddUI();
				ia.execute();
				break;
			case 5:
				ia = new AccountUpdateUI();
				ia.execute();
				break;
			case 6:
				ia = new AccountDeleteUI();
				ia.execute();
				break;
			case 7:
				ia = new DepositUI();
				ia.execute();
				break;
			case 8:
				ia = new WithrawUI();
				ia.execute();
				break;
			case 9:
				ia = new TransferUI();
				ia.execute();
				break;	
			case 10:
				ia = new CreateAccountUI();
				ia.execute();
				break;
			case 0:
				ia = new ExitUI();
				ia.execute();
				break;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println();
			}
		}
	}
}
