package com.test.concurrency;

public class TestInterrupt extends Thread {

	@Override
	public void run() {

		long number = 1L;

		while (true) {
			if (isPrime(number)) {
				System.out.println("Number %d is Prime "+ number);				
			}
			if (isInterrupted()) {
				System.out.println("The Prime gen is interrupted");
				return;
			}

			number++;
		}

	}

	private boolean isPrime(long number) {

		return number % 2 == 0 ? true : false;
	}

	public static void main(String[] args) {

		Thread task = new TestInterrupt();
		task.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
	}

}
