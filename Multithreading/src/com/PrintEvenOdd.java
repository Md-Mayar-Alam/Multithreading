package com;

class EvenOddPrinter{
	
	int count;
	int printUpto;
	
	public EvenOddPrinter(int count, int printUpto) {
		this.count = count;
		this.printUpto = printUpto;
	}
	
	public void printEvenNumber() {
		synchronized(this) {
			while(count <= printUpto) {
				if(count%2 == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println(Thread.currentThread().getName() +" "+ count++);
					notify();
				}
			}
		}
	}
	
	public void printOddNumber() {
		synchronized(this) {
			while(count <= printUpto) {
				if(count%2 != 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println(Thread.currentThread().getName() +" "+ count++);
					notify();
				}
			}
		}
	}
}

public class PrintEvenOdd {

	public static void main(String[] args) {
		
		EvenOddPrinter eop = new EvenOddPrinter(1, 10);
		
		Thread t1= new Thread(new Runnable() {
			@Override
			public void run() {
				eop.printEvenNumber();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				eop.printOddNumber();
			}
		});
		
		t1.start();
		t2.start();
	}
}
