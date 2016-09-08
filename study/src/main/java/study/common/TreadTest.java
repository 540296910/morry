package study.common;

public class TreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r1 = new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(this.getClass().getName());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Runnable r2 = new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(this.getClass().getName());
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
	
	

}
