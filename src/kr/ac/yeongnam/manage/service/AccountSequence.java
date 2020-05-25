package kr.ac.yeongnam.manage.service;

public class AccountSequence {

	private int accountSequence = 1;
	
	public int getBoardSequence() {
		
		return accountSequence++;
	}
}
