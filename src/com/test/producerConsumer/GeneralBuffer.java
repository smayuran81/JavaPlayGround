package com.test.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class GeneralBuffer {
	
	public static void main(String[] args) throws InterruptedException {
		
		Buffer buffer = new Buffer(2);
		
		Thread producerThread = new Thread(()-> {
			int value=0;
			while(true) {
				try {
					buffer.add(value);
					
					System.out.println("Produced " + value);
					
					value++;
					
					Thread.sleep(1000);
					
					
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				
				
			}
			
		});
		
		
		  Thread consumerThread = new Thread(() -> {
	            try {
	                while (true) {
	                    int value = buffer.get();
	                    System.out.println("Consume " + value);
	                    Thread.sleep(1000);
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        });
		  
		  producerThread.start();
		  consumerThread.start();
		  
		  producerThread.join();
		  consumerThread.join();
	}
	
	
	static class Buffer {
		int size;
		Queue<Integer> bufferList;
		
		
		Buffer(int size) {
			this.size = size;
			bufferList =  new LinkedList<>();
		}
		
		
		void add(Integer number) throws InterruptedException {
			synchronized(this) {
				
				while(bufferList.size()>=size) {
					wait();
				}
				
				bufferList.add(number);
				notifyAll();
				
			}
			
			
		}
		
		
		Integer get() throws InterruptedException {
			
			synchronized(this) {
				while(bufferList.isEmpty()) {
					wait();
				}
			
				int number = bufferList.poll();
				notify();
				return number;
			}
									
		}
	}

}
