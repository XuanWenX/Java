package ThreadDemo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadSampleC {
	class Runner implements Callable<Integer>{
		public String name;

		@Override
		public Integer call() throws Exception {
			Integer speed = new Random().nextInt(10);
			Integer result = 0;
			for(int i =0; i<10;i++) {
				Thread.sleep(i*100+500);
				result = i*speed;
				System.out.println(i +" "+ this.name +" "+ speed*i+" "+speed);
			}
			
			return result;
		}
		
		
	}
	public void start() throws InterruptedException, ExecutionException{
		ExecutorService A = Executors.newFixedThreadPool(3);
		Runner RunA = new Runner();
		RunA.name = "nishan";
		Runner RunB = new Runner();
		RunB.name = "chenmin";
		Future<Integer> p1 = A.submit(RunA);
		Future<Integer> p2 = A.submit(RunB);
		A.shutdown();
		System.out.println(RunA.name +p1.get());
		System.out.println(RunB.name + p2.get());
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new ThreadSampleC().start();
	}

}

    
