package kr.ac.yeongnam.manage.service;

public class AccountServiceFactory {
	
	private static AccountService service = null;
	
	public static AccountService getBoardService() {

		if(service == null) {
			service = new AccountService();
		} 
			
		return service;
	}
}
