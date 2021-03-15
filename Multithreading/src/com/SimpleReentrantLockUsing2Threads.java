package com;

import java.util.concurrent.locks.ReentrantLock;

/**
 * In this example we will be seeing simple uses of
 * Reentrant Lock class using lock() and unlock().
 * @author Mayar
 */
public class SimpleReentrantLockUsing2Threads {
	
	public static void main(String[] args) {
		/*
		 * We are creating object of Reentrant Lock by parametrized constructor 
		 * which takes boolean argument. The boolean argument which is passed here 
		 * is fairness. It means it will assign the processor to the thread which
		 * is waiting in the Queue for longest time. This will prevent Thread starvation.
		 */
		ReentrantLock rel = new ReentrantLock(true);
		
		/*
		 * By this way we can create object of Reentrant lock without fairness.
		 * This will be equivalent to ReentrantLock rel = new ReentrantLock(false);
		 * In this case the threads which are in waiting queue may not get chance 
		 * to execute due to unfair behaviour and may lead to thread starvation.  
		 */
		//To see unfair behaviour of Reentrant uncomment below code and test.
		//ReentrantLock rel = new ReentrantLock();
		
		int start = 1;
		int N = 10;
		
		Printer printer = new Printer(start, N, rel);
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				printer.print();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				printer.print();
			}
		});
		
		t1.start();
		t2.start();
	}
}

class Printer{
	
	int counter;
	int N;
	ReentrantLock rel;
	
	public Printer(int counter, int N, ReentrantLock rel) {
		this.counter = counter;
		this.N = N;
		this.rel = rel;
	}
	
	public void print() {
		while(counter < N) {
			try {
				rel.lock();
				System.out.println(Thread.currentThread().getName() +" "+ counter++);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				rel.unlock();
			}
		}
	}
}