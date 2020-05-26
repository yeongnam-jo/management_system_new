package kr.ac.yeongnam.manage.ui;

import java.util.Scanner;

import kr.ac.yeongnam.manage.service.AccountService;
import kr.ac.yeongnam.manage.service.AccountServiceFactory;



public class BaseUI implements IAccount {
	
	public Scanner sc;

	protected AccountService AccountService;

	public BaseUI() {
		sc = new Scanner(System.in);
		AccountService = AccountServiceFactory.getBoardService();
	}

	protected int scanInt(String msg) {
		System.out.println(msg);
		return Integer.parseInt(sc.nextLine());
	}
	
	protected long scanLong(String msg) {
		System.out.println(msg);
		long tmp = sc.nextLong();
		sc.nextLine();
		return tmp;
	}
	
	protected String scanStr(String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}

	@Override
	public void execute() throws Exception {
	}

}
