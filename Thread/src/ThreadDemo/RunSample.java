package ThreadDemo;

import java.util.Random;

public class RunSample {
	class Runner implements Runnable{
		Object lock = new Object();
		@Override
		public void run() {
			synchronized (lock) {
				
		    try {
			Integer speed = new Random().nextInt(10);
			for(int i=0; i<10;i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i +" "+ Thread.currentThread().getName() +" "+ speed*i+" "+speed);
			}}
			catch (Exception e) {
				e.printStackTrace();
			}
			}
			}
		
		
	}


	public void start() {
		Runner A =new Runner();
		Thread threadA = new Thread(A);
		threadA.setName("nishan");
		
		Runner B =new Runner();
		Thread threadB = new Thread(B);
		threadB.setName("chenmin");
		
		threadA.start();
		threadB.start();
		
	}
	
	public static void main(String[] args) {
		new RunSample().start();
	}
}
