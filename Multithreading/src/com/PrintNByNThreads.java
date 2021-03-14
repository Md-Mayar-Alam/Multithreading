package com;

/**
 * This class is an example to print N Natural Numbers
 * Using N no of given Threads
 * @author Mayar
 */
public class PrintNByNThreads {

	public static void main(String[] args) {
		
		int startCounter = 1;
		int printUpto = 15;
		int noOfThreads = 4;

		PrintExecutor pe = new PrintExecutor(startCounter, printUpto, noOfThreads);
		
		NaturalNumberPrinter nnp1 = new NaturalNumberPrinter(pe, 1);
		NaturalNumberPrinter nnp2 = new NaturalNumberPrinter(pe, 2);
		NaturalNumberPrinter nnp3 = new NaturalNumberPrinter(pe, 3);
		NaturalNumberPrinter nnp4 = new NaturalNumberPrinter(pe, 0);
		
		Thread t1 = new Thread(nnp1, "Thread-1");
		Thread t2 = new Thread(nnp2, "Thread-2");
		Thread t3 = new Thread(nnp3, "Thread-3");
		Thread t4 = new Thread(nnp4, "Thread-4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		/*for(int i=1; i<= noOfThreads; i++) {
			Thread thread = new Thread(nnp, "Thread-"+i);
			thread.start();
		}*/
	}
}

class PrintExecutor{
	
	int startCounter;
	int printUpto;
	int noOfThreads;
	
	public PrintExecutor(int startCounter, int printUpto, int noOfThreads) {
		this.startCounter = startCounter;
		this.printUpto = printUpto;
		this.noOfThreads = noOfThreads;
	}
	
	public void print(int result) {
		synchronized (this) {
			while(startCounter <= printUpto) {
				if(startCounter%noOfThreads == result) {
					System.out.println(Thread.currentThread().getName() +" "+ startCounter++);
					notifyAll();
				}else {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

class NaturalNumberPrinter implements Runnable{
	
	PrintExecutor printExecutor;
	int result;
	
	public NaturalNumberPrinter(PrintExecutor pe, int result) {
		this.printExecutor = pe;
		this.result = result;
	}
	
	@Override
	public void run() {
		printExecutor.print(result);
	}
}