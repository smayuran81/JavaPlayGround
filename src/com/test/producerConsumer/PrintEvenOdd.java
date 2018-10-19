package com.test.producerConsumer;

public class PrintEvenOdd {

	public static void main(String[] args) {

		Printer print = new Printer(2);
		Thread t1 = new Thread(new TaskEvenOdd(print, 10, 2));
		Thread t2 = new Thread(new TaskEvenOdd(print, 10, 3));

		t1.start();
		t2.start();
	}
}

class TaskEvenOdd implements Runnable {

	private int max;
	private Printer print;
	private int startNumber;

	public TaskEvenOdd(Printer print, int max, int startNumber) {

		this.print = print;
		this.max = max;
		this.startNumber = startNumber;
	}

	@Override
	public void run() {

		// int number = isEvenNumber ==true ?2:1;
		boolean isEvenNumber = startNumber % 2 == 0 ? true : false;

		while (startNumber <= max) {
			if (isEvenNumber) {
				print.printEven(startNumber);
			} else {
				print.printOdd(startNumber);
			}
			startNumber = startNumber + 2;
		}

	}

}

class Printer {

	boolean isOdd;
	int startNumber;

	Printer(int startNumber) {
		this.startNumber = startNumber;

		isOdd = startNumber % 2 == 0 ? false : true;

	}

	synchronized void printEven(int number) {

		while (isOdd == true) {

			try {
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

		System.out.println("Even: " + number);
		isOdd = true;
		notifyAll();

	}

	synchronized void printOdd(int number) {

		while (isOdd == false) {

			try {
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

		System.out.println("Odd: " + number);
		isOdd = false;
		notifyAll();

	}
}
