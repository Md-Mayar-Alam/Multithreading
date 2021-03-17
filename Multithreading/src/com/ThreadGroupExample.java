package com;

public class ThreadGroupExample {

	public static void main(String[] args) {
		
		/*
		 * Every Thread in java belongs to some group. main Thread belongs to Main group
		 * Every Thread group in java is the child group of System group either directly or indirectly.
		 * Hence System group act as for all Thread groups in java.
		 * System group contains system level Threads. For ex: Garbage Collectors are system
		 * level Threads. System group contains several system level Threads like
		 * 1. Finalizer i.e Garbage Collector
		 * 2. Reference Handler
		 * 3. Signal Dispatcher
		 * 4. Attach Listener ...etc
		 */
		
		System.out.println("Current Thread= "+ Thread.currentThread());
		System.out.println("Current Thread Thread group= "+ Thread.currentThread().getThreadGroup());
		System.out.println("Current Thread Thread group name = "+ Thread.currentThread().getThreadGroup().getName());
		System.out.println("Current Thread Thread group parent = "+Thread.currentThread().getThreadGroup().getParent());
		System.out.println("Current Thread Thread group parent name = "+Thread.currentThread().getThreadGroup().getParent().getName() );
		
		/*
		 * In java we have ThreadGroup class which is present in java.lang package 
		 * and it is a direct child of Object class.
		 * Constructors
		 * =============
		 * 1. ThreadGroup tg = new ThreadGroup(String groupName);
		 * this line will be executed by Main Thread so it will be child of main group.
		 * As Main Thread belongs to main group.
		 * 
		 * 2. ThreadGroup tg = new ThreadGroup(ThreadGroup parentThreadGroup, String groupName);
		 */
		
		ThreadGroup tg = new ThreadGroup("First Group");
		System.out.println("Tg Thread Group parent's name= "+ tg.getParent().getName());	// o/p: main
		
		ThreadGroup childGroup = new ThreadGroup(tg, "Second Name");
		System.out.println("Child Thread Group parent's name= "+ childGroup.getParent().getName());	// o/p: First Group
	}
}
