package kr.ac.yeongnam.manage.ui;


public class TotalManagementMain {
	
	public static void main(String[] args) {
		
		TotalManagementUI tmu = new TotalManagementUI();
		try {
			tmu.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
