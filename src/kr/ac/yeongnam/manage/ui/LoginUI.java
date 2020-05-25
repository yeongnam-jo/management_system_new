package kr.ac.yeongnam.manage.ui;

public class LoginUI extends BaseUI {
	
	
	@Override
	public void execute() throws Exception {
		
		String id = scanStr("ID 입력 : ");
		String password = scanStr("비밀번호 입력 : ");
		
		AccountService.login(id, password);
	}
	
	/*
	@Override
	public void execute(String msg) throws Exception {
		
		String id = scanStr("ID 입력 : ");
		String password = scanStr("비밀번호 입력 : ");
		
		AccountService.login(id, password, msg);
	}
	*/
}
