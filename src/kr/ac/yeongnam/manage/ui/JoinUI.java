package kr.ac.yeongnam.manage.ui;

public class JoinUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		System.out.println("회원가입 서비스입니다.");
		String id = scanStr("사용하실 ID를 입력해주세요 : ");
		String password = scanStr("사용하실 비밀번호를 입력해주세요 : ");
		
		AccountService.join(id, password);
		
	}

}
