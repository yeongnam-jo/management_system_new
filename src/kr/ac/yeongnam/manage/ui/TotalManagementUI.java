package kr.ac.yeongnam.manage.ui;

//import ui.IAccount;
//import ui.LoginUI;

public class TotalManagementUI extends BaseUI {

	public void showFirstMenu() {
		System.out.println("====================");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		// System.out.print("원하시는 서비스 번호를 입력하세요 : ");
	}
	
	public void showSecondMenu() {
		System.out.println("====================");
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
		
		System.out.print("원하시는 서비스 번호를 입력하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		IAccount ia = null;
		//IAccount ia2 = null;
		
		
		showFirstMenu();		
		int logJoin = scanInt("원하시는 서비스 번호를 입력하세요 : ");
		
		/*
		boolean check = true;
		while(check) {
		}
		*/
		
		if(logJoin == 1) {
			ia = new LoginUI();
			ia.execute();
		} else if(logJoin == 2) {
			ia = new JoinUI();
			ia.execute();
		}
		

		boolean service = true;
		while (service) {

			showSecondMenu();
			int serviceNum = Integer.parseInt(sc.nextLine());

			/*
			switch(serviceNum) {
			case 1:
				System.out.println("계좌조회 기능입니다.");
			}
			*/
			switch (serviceNum) {
			case 1:
				ia = new AccountInfoAllUI();
				ia.execute();
				service = false;
				break;
			
			case 2:
				ia = new AccountInfoNoUI();
				ia.execute();
				service = false;
				break;
			case 3:
				ia = new AccountInfoBankUI();
				ia.execute();
				service = false;
				break;
			case 4:
				ia = new AccountAddUI();
				ia.execute();
				service = false;
				break;
			case 5:
				ia = new AccountUpdateUI();
				ia.execute();
				service = false;
				break;
			case 6:
				ia = new AccountDeleteUI();
				ia.execute();
				service = false;
				break;
			case 7:
				ia = new DepositUI();
				ia.execute();
				service = false;
				break;
			case 8:
				ia = new WithrawUI();
				ia.execute();
				service = false;
				break;
			case 9:
				ia = new TransferUI();
				ia.execute();
				service = false;
				break;	
			case 10:
				ia = new CreateAccountUI();
				ia.execute();
				service = false;
				break;
			case 0:
				ia = new ExitUI();
				ia.execute();
				service = false;
				break;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println();
			}
		}
	}
}
