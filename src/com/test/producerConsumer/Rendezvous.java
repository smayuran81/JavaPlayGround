package com.test.producerConsumer;

public class Rendezvous {
	
	
	public static void main(String[] args) {
		
		
		Printer print = new Printer();
		Thread t1 = new Thread(new TaskEvenOdd(print, 10, false));
		Thread t2 = new Thread(new TaskEvenOdd(print, 10, true));
		
		t1.start();
		t2.start();
	}
}
	
	class TaskEvenOdd implements Runnable {
		
		private int max;
	    private Printer print;
	    private boolean isEvenNumber;

		
		public TaskEvenOdd(Printer print , int max, boolean isEvenNumber) {
			
			 	this.print = print;
		        this.max = max;
		        this.isEvenNumber = isEvenNumber;
		    }
			
				

		@Override
		public void run() {
			
			int number = isEvenNumber ==true ?2:1;
			while(number<=max) {
				if(isEvenNumber) {
					print.printEven(number);
				}
				else {
					print.printOdd(number);
				}
				number=number+2;
			}
			
			
		}
		
	}
	
	 class Printer {
		 
		 boolean isOdd = true;
		 
		 synchronized void printEven(int number) {
			 
			 
			 while(isOdd == true) {
				 
				 try {
					 wait();
				 }catch(InterruptedException ie){
					 ie.printStackTrace();
				 }
			 }
				 
				 System.out.println("Even: " + number);
				 isOdd=true;
				 notifyAll();
				 

		 }
		 
		 
		 synchronized void printOdd(int number) {
			 
			 while(isOdd == false) { 
				 
				 try {
					 wait();
				 }catch(InterruptedException ie) {
					 ie.printStackTrace();
				 }
			 }
				 
				 System.out.println("Odd: " + number);
				 isOdd=false;
				 notifyAll();
				 
			 
		 }
	 }
		



