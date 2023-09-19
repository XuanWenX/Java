package ThreadDemo;

import java.util.Random;

public class ThreadSample1 {
	class Runner extends Thread{

		@Override
		public void run() {
			Integer speed = new Random().nextInt(10);
			for(int i=0; i<10;i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i +" "+ this.getName() +" "+ speed*i+" "+speed);
			}
		}

		
	}
	
	public void start() {
		Runner A = new Runner();
		A.setName("nishan");
		
		Runner B = new Runner();
		B.setName("chenmin");
		
		A.start();
		B.start();
	}
	
	public static void main(String[]args) {
		new ThreadSample1().start();
	}

}
