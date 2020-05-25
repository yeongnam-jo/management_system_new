package kr.ac.yeongnam.manage.service;

public class AccountServiceFactory {
	
	private static AccountService service = null;
	
	public static AccountService getBoardService() {

		if(service == null) {
			service = new AccountService();
		} 
			
		//계속 하나를 공유하도록 만드는 클래스이다.
		return service;
	}
}
