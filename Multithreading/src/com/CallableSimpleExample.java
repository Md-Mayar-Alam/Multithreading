package com;

import java.util.concurrent.Callable;

public class CallableSimpleExample {
	
	public static void main(String[] args) {
		
		CallablePrinter cp = new CallablePrinter(1, 10);
		
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return cp.print();
			}
		};
		
		try {
			Integer count = callable.call();
			System.out.println("Current count = "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class CallablePrinter{
	
	int count;
	int N;
	
	public CallablePrinter(int count, int N) {
		this.count = count;
		this.N = N;
	}
	
	public int print() {
		while(count < N) {
			System.out.println(Thread.currentThread().getName() +" "+ count++);
		}
		return count;
	}
}
